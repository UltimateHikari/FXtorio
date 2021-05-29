package com.hikari.hellofx.game;

import java.util.ArrayDeque;

import com.hikari.hellofx.base.BaseModel;
import com.hikari.hellofx.base.IModelInfo;
import com.hikari.hellofx.entity.BindingController;
import com.hikari.hellofx.entity.IConnectable;
import com.hikari.hellofx.entity.IProducer;
import com.hikari.hellofx.entity.model.BasicEntityModel;
import com.hikari.hellofx.entity.model.ConnectableState;
import com.hikari.hellofx.entity.model.ConnectionInPoint;
import com.hikari.hellofx.entity.model.ConnectionOutPoint;
import com.hikari.hellofx.entity.model.EntityShadow;
import com.hikari.hellofx.entity.view.BasicConnectionView;
import com.hikari.hellofx.entity.view.info.ConnectableInfo;
import com.hikari.hellofx.game.control.ControlTransferObject;
import com.hikari.hellofx.game.control.FollowUpAction;
import com.hikari.hellofx.game.control.PackAction;
import com.hikari.hellofx.game.control.RecipeAction;
import com.hikari.hellofx.game.view.GameField;
import com.hikari.hellofx.game.view.GameView;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class GameController{
	private static final String ILLEGAL_BUTTON_ARGUMENT = "button reported wrong args:";
	
	enum State {
		IDLE, SPAWNING, CONNECTING_FIRST, CONNECTING_SECOND
	}

	enum Action {
		NOTICE, ACT, NOP
	}

	private State state = State.IDLE;
	private Action lastAction = Action.NOP;
	private final Game game = new Game();
	private final GameView view;
	private final EntityShadow shadow = new EntityShadow();
	private final ArrayDeque<BaseModel> noticed = new ArrayDeque<>();
	private ControlTransferObject cto = null;

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

	public void act(ControlTransferObject cto) {
		lastAction = Action.ACT;
		updateCTO(cto);
		assignHandler();
	}

	public void notice(ControlTransferObject cto, BaseModel model) {
		lastAction = Action.NOTICE;
		noticed.add(model);
		updateCTO(cto);
		assignHandler();
	}
	
	private void updateCTO(ControlTransferObject cto) {
		if(cto instanceof FollowUpAction) {
			cto.appendTo(this.cto);
		} else {
			this.cto = cto;
		}
	}

	private void assignHandler() {
		log.info("Doing " + cto.getAction() + " because of " + cto.getEvent().getButton() + "; have " + noticed + " noticed");
		switch (state) {
		case IDLE:
			assignIfIDLE();
			break;
		case SPAWNING:
			assignIfSPAWNING();
			break;
		case CONNECTING_FIRST, CONNECTING_SECOND:
			assignIfConnecting();
			break;
		default:
		}
	}

	private void assignIfIDLE() {
		switch (cto.getAction()) {
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
			despawnEntity();
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

	private void assignIfSPAWNING() {
		switch (cto.getAction()) {
		case SPAWN:
			spawnEntity();
			break;
		case CANCEL:
			returnToIDLE();
			break;
		default:
			cancelOperation();
		}
	}

	private void assignIfConnecting() {
		switch (cto.getAction()) {
		case CONNECT_IN:
			connectIn();
			break;
		case CONNECT_OUT:
			connectOut();
			break;
		case CANCEL:
			returnToIDLE();
			break;
		default:
			cancelOperation();
		}
	}

	private void setRecipe() {
		var model = noticed.remove();
		if(cto instanceof RecipeAction rcto && model instanceof IProducer producer) {
			producer.setCurrentRecipe(rcto.getItem());
		} else {
			log.error(ILLEGAL_BUTTON_ARGUMENT, cto, model);
			throw new IllegalArgumentException();
		}
	}
	
	private void returnToIDLE() {
		if (state == State.SPAWNING) {
			disableShadow();
		}
		game.forEachEntity(w -> w.setConnectableState(ConnectableState.NO_POINTS));
		state = State.IDLE;
		noticed.clear();
	}

	private void connectIn() {
		state = State.IDLE;
		game.forEachEntity(w -> w.setConnectableState(ConnectableState.NO_POINTS));
		if(cto instanceof PackAction pcto) {
			try {
				ConnectionOutPoint out = (ConnectionOutPoint) noticed.remove();
				ConnectionInPoint in = (ConnectionInPoint) noticed.remove();
				Spawner.spawnConnection(out, in, pcto.getPack());
			} catch(Exception e) {
				log.error("Spawn of [ " + pcto.getPack().toString() + " ] failed. " + "Already in normal state.", e);
			}
		}else {
			log.error(ILLEGAL_BUTTON_ARGUMENT, cto);
			throw new IllegalArgumentException();
		}
	}

	private void connectOut() {
		state = State.CONNECTING_SECOND;
		game.forEachEntity(w -> w.setConnectableState(ConnectableState.IN_POINTS));
	}

	private void cancelOperation() {
		if (lastAction == Action.NOTICE) {
			noticed.removeLast();
		}
		log.info("ignoring " + noticed);
	}
	
	private void despawnEntity() {
		if(noticed.remove() instanceof IConnectable model) {
			view.removeOrphan(game.removeEntity(model));
			for(BasicConnectionView i : game.removeConnectionViews(model)) {
				view.removeOrphan(i);
			}
		} else { 
			throw new IllegalArgumentException();
		}
	}

	private void spawnEntity() {
		if (state == State.SPAWNING) {
			if(cto instanceof PackAction pcto) {
				try {
					Spawner.spawnEntity(shadow.getX(), shadow.getY(), pcto.getPack(), this);
				} catch (Exception e) {
					log.error("Spawn of [ "  + pcto.getPack().toString() + " ] failed. " + "Returning to normal state.");
					e.printStackTrace();
				} finally {
					state = State.IDLE;
					disableShadow();
				}
			}else {
				log.error(ILLEGAL_BUTTON_ARGUMENT, cto);
				throw new IllegalArgumentException();
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
		var bController = new BindingController(this, model);
		var info = new ConnectableInfo(bController);
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
