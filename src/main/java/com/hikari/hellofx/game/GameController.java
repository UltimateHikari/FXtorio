package com.hikari.hellofx.game;

import java.util.ArrayDeque;

import com.hikari.hellofx.base.BaseModel;
import com.hikari.hellofx.base.IModelInfo;
import com.hikari.hellofx.entity.BindingController;
import com.hikari.hellofx.entity.IConnectable;
import com.hikari.hellofx.entity.model.BasicEntityModel;
import com.hikari.hellofx.entity.model.ConnectableState;
import com.hikari.hellofx.entity.model.ConnectionInPoint;
import com.hikari.hellofx.entity.model.ConnectionOutPoint;
import com.hikari.hellofx.entity.model.EntityShadow;
import com.hikari.hellofx.entity.view.info.ConnectableInfo;
import com.hikari.hellofx.game.classpack.ClassPack;
import com.hikari.hellofx.game.view.GameField;
import com.hikari.hellofx.game.view.GameView;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class GameController{
	enum State {
		IDLE, SPAWNING, CONNECTING_FIRST, CONNECTING_SECOND
	}

	enum Action {
		Notice, Act, Nop
	}

	private State state = State.IDLE;
	private Action lastAction = Action.Nop;
	private GameAction action = GameAction.NOP;
	private final Game game = new Game();
	private final GameView view;
	private final EntityShadow shadow = new EntityShadow();
	private final ArrayDeque<BaseModel> noticed = new ArrayDeque<>();
	private ClassPack classPack = null;

	public GameController(GameView view_) {
		view = view_;
		Spawner.setGame(game);
		Spawner.setView(view_);
	}

	private void enableSPAWNINGState() {
		state = State.SPAWNING;
		game.getField().turnOn();
		view.showShadow(shadow);
	}

	private void enableConnectingState() {
		state = State.CONNECTING_FIRST;
		game.forEachEntity(w -> w.setConnectableState(ConnectableState.OUT_POINTS));
	}

	public void act(MouseEvent event, GameAction action_, ClassPack pack) {
		lastAction = Action.Act;
		action = action_;
		classPack = pack;
		assignHandler(event);
	}

	public void act(MouseEvent event, GameAction action_) {
		act(event, action_, classPack);
	}

	public void notice(BaseModel model, MouseEvent event, GameAction action_) {
		lastAction = Action.Notice;
		noticed.add(model);
		action = action_;
		assignHandler(event);
	}

	private void assignHandler(MouseEvent event) {
		log.info("Doing " + action + " because of " + event.getButton() + "; have " + noticed + " noticed");
		switch (state) {
		case IDLE:
			assignIfIDLE(event);
			break;
		case SPAWNING:
			assignIfSPAWNING(event);
			break;
		case CONNECTING_FIRST, CONNECTING_SECOND:
			assignIfConnecting(event);
			break;
		default:
		}
	}

	private void assignIfIDLE(MouseEvent event) {
		switch (action) {
		case SUSPEND:
			suspendEntity();
			break;
		case INFO:
			showInfo();
			break;
		case ENTER_SPAWN:
			enableSPAWNINGState();
			break;
		case ENTER_CONNECT:
			enableConnectingState();
			break;
		case DESPAWN:
			despawnEntity(event);
			break;
		case RECIPE:
			setRecipe();
			break;
		case CANCEL:
			returnToIDLE();
			break;
		default:
			cancelOperation();
		}
	}

	private void assignIfSPAWNING(MouseEvent event) {
		switch (action) {
		case SPAWN:
			spawnEntity(event);
			break;
		case CANCEL:
			returnToIDLE();
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
			returnToIDLE();
			break;
		default:
			cancelOperation();
		}
	}

	private void setRecipe() {
		BaseModel model = noticed.remove();
		//TODO migrate to dto for controller
		log.error("set recipe");
	}
	
	private void returnToIDLE() {
		if (state == State.SPAWNING) {
			disableShadow();
		}
		game.forEachEntity(w -> w.setConnectableState(ConnectableState.NO_POINTS));
		state = State.IDLE;
		noticed.clear();
	}

	private void connectIn(MouseEvent event) {
		state = State.IDLE;
		game.forEachEntity(w -> w.setConnectableState(ConnectableState.NO_POINTS));
		try {
			ConnectionOutPoint out = (ConnectionOutPoint) noticed.remove();
			ConnectionInPoint in = (ConnectionInPoint) noticed.remove();
			Spawner.spawnConnection(out, in, classPack);
		} catch(Exception e) {
			log.error("Spawn of [ " + classPack.toString() + " ] failed. " + "Already in normal state.", e);
			//e.printStackTrace();
		}
	}

	private void connectOut(MouseEvent event) {
		state = State.CONNECTING_SECOND;
		game.forEachEntity(w -> w.setConnectableState(ConnectableState.IN_POINTS));
	}

	private void cancelOperation() {
		if (lastAction == Action.Notice) {
			noticed.removeLast();
		}
		log.info("ignoring " + noticed);
	}
	
	private void despawnEntity(MouseEvent event) {
		if(noticed.remove() instanceof IConnectable model) {
			view.removeOrphan(game.removeEntity(model));
		} else { 
			throw new IllegalArgumentException();
		}
	}

	private void spawnEntity(MouseEvent event) {
		if (state == State.SPAWNING) {
			try {
				Spawner.spawnEntity(shadow.getX(), shadow.getY(), classPack, this);
			} catch (Exception e) {
				log.error("Spawn of [ "  + classPack.toString() + " ] failed. " + "Returning to normal state.");
				e.printStackTrace();
			} finally {
				state = State.IDLE;
				disableShadow();
			}
		}
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
		log.trace("movement on " + event.getX() + event.getY());
		shadow.move(event.getX(), event.getY());
		return null;
	}

	public void subscribeGameField(GameField gameField) {
		game.getField().subscribe(gameField);

	}
}
