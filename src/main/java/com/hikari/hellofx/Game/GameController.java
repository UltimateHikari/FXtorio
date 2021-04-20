package com.hikari.hellofx.Game;

import java.util.ArrayDeque;

import com.hikari.hellofx.Base.BaseModel;
import com.hikari.hellofx.Base.ILoggable;
import com.hikari.hellofx.Base.IModelInfo;
import com.hikari.hellofx.Entities.BindingController;
import com.hikari.hellofx.Entities.ConnectableInfo;
import com.hikari.hellofx.Entities.EntityClassPair;
import com.hikari.hellofx.Entities.Connectable.ConnectableState;
import com.hikari.hellofx.Entities.Connectable.IConnectable;
import com.hikari.hellofx.Entities.Connectable.Basic.BasicEntityModel;
import com.hikari.hellofx.Entities.Connectable.Basic.BasicEntityView;
import com.hikari.hellofx.Entities.Connection.Conveyor.Conveyor;
import com.hikari.hellofx.Entities.Connection.Conveyor.ConveyorView;
import com.hikari.hellofx.Entities.ConnectionPoint.ConnectionInPoint;
import com.hikari.hellofx.Entities.ConnectionPoint.ConnectionOutPoint;
import com.hikari.hellofx.Entities.Shadow.EntityShadow;
import com.hikari.hellofx.Game.View.GameField;
import com.hikari.hellofx.Game.View.GameView;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

public class GameController implements ILoggable {
	enum State {
		Idle, Spawning, ConnectingFirst, ConnectingSecond
	}

	enum Action {
		Notice, Act, Nop
	}

	private State state = State.Idle;
	private Action lastAction = Action.Nop;
	private GameAction action = GameAction.NOP;
	private final Game game = new Game();
	private final GameView view;
	private final EntityShadow shadow = new EntityShadow();
	private final ArrayDeque<BaseModel> noticed = new ArrayDeque<BaseModel>();
	private EntityClassPair entityClassPair = null;

	public GameController(GameView view_) {
		view = view_;
	}

	private void enableSpawningState() {
		state = State.Spawning;
		game.getField().turnOn();
		view.showShadow(shadow);
	}

	private void enableConnectingState() {
		state = State.ConnectingFirst;
		game.forEachEntity(w -> w.setConnectableState(ConnectableState.OUT_POINTS));
	}

	public void act(MouseEvent event, GameAction action_, EntityClassPair entityClassPair_) {
		lastAction = Action.Act;
		action = action_;
		entityClassPair = entityClassPair_;
		assignHandler(event);
	}

	public void act(MouseEvent event, GameAction action_) {
		act(event, action_, entityClassPair);
	}

	public void notice(BaseModel model, MouseEvent event, GameAction action_) {
		lastAction = Action.Notice;
		noticed.add(model);
		action = action_;
		assignHandler(event);
	}

	private void assignHandler(MouseEvent event) {
		log("Doing " + action + " because of " + event.getButton() + "; have " + noticed.toString() + " noticed");
		switch (state) {
		case Idle:
			assignIfIdle(event);
			break;
		case Spawning:
			assignIfSpawning(event);
			break;
		case ConnectingFirst:
		case ConnectingSecond:
			assignIfConnecting(event);
			break;
		default:
		}
	}

	private void assignIfIdle(MouseEvent event) {
		switch (action) {
		case SUSPEND:
			suspendEntity();
			break;
		case INFO:
			showInfo();
			break;
		case ENTER_SPAWN:
			enableSpawningState();
			break;
		case ENTER_CONNECT:
			enableConnectingState();
			break;
		case DESPAWN:
			despawnEntity(event);
			break;
		case CANCEL:
			returnToIdle();
			break;
		default:
			cancelOperation();
		}
	}

	private void assignIfSpawning(MouseEvent event) {
		switch (action) {
		case SPAWN:
			spawnEntity(event);
			break;
		case CANCEL:
			returnToIdle();
			break;
		default:
			cancelOperation();
		}
	}

	private void assignIfConnecting(MouseEvent event) {
		switch (action) {
		case CONNECT_IN:
			connectIn(event);
			break;
		case CONNECT_OUT:
			connectOut(event);
			break;
		case CANCEL:
			returnToIdle();
			break;
		default:
			cancelOperation();
		}
	}

	private void returnToIdle() {
		if (state == State.Spawning) {
			disableShadow();
		}
		game.forEachEntity(w -> w.setConnectableState(ConnectableState.NO_POINTS));
		state = State.Idle;
		noticed.clear();
	}

	private void connectIn(MouseEvent event) {
		state = State.Idle;
		game.forEachEntity(w -> w.setConnectableState(ConnectableState.NO_POINTS));
		spawnConnection();
	}

	private void connectOut(MouseEvent event) {
		state = State.ConnectingSecond;
		game.forEachEntity(w -> w.setConnectableState(ConnectableState.IN_POINTS));
	}

	private void cancelOperation() {
		if (lastAction == Action.Notice) {
			noticed.removeLast();
		}
		log("ignoring " + noticed.toString());
	}

	private void spawnConnection() {
		// TODO some exceptions if not instanceof
		ConnectionOutPoint out = (ConnectionOutPoint) noticed.remove();
		ConnectionInPoint in = (ConnectionInPoint) noticed.remove();
		Conveyor conveyor = new Conveyor(out, in);
		ConveyorView spawned = new ConveyorView(out, in);

		conveyor.subscribe(spawned);

		game.addConnection(conveyor);
		view.showSpawned(spawned);
		conveyor.start();
		log("connected");
	}

	private void despawnEntity(MouseEvent event) {
		BaseModel model = noticed.remove();
		((BasicEntityModel) model).despawn(); // ??? mb interface && handler?
	}

	private void spawnEntity(MouseEvent event) {
		if (state == State.Spawning) {
			try {
				spawn(shadow.getX(), shadow.getY()/* ), entityName */);
			} catch (Exception e) {
				log("Spawn of [ " + entityClassPair.toString() + " ] failed. " + "Returning to normal state.");
			} finally {
				state = State.Idle;
				disableShadow();
			}
		}
	}

	private void spawn(Double x, Double y) throws Exception {
		BaseModel model = entityClassPair.getModelInstance();
		BindingController bController = new BindingController(this, model);
		BasicEntityView spawned = entityClassPair.getViewInstance(x, y, bController);
		model.subscribe(spawned);
		view.showSpawned(spawned);
		game.addEntity((IConnectable) model);
		model.start();
	}

	private void disableShadow() {
		game.getField().turnOff();
		view.hideShadow(shadow);
	}

	private void suspendEntity() {
		BaseModel model = noticed.remove();
		if (model instanceof BasicEntityModel b) {
			if (b.isOn()) {
				b.turnOff();
			} else {
				b.turnOn();
			}
		} else {
			throw new IllegalArgumentException();
		}
	}

	private void showInfo() {
		BaseModel model = noticed.remove();
		BindingController bController = new BindingController(this, model);
		ConnectableInfo info = new ConnectableInfo(bController);
		VBox currentInfo = view.getInfo();
		if (currentInfo instanceof IModelInfo) {
			((IModelInfo) currentInfo).disable();
		}
		model.subscribe(info);
		model.notifySubs();
		view.showInfo((IConnectable) model, info);
	}

	public Object moveShadow(MouseEvent event) {
		// log("movement on " + event.getX() + event.getY());
		shadow.move(event.getX(), event.getY());
		return null;
	}

	public void subscribeGameField(GameField gameField) {
		game.getField().subscribe(gameField);

	}
}
