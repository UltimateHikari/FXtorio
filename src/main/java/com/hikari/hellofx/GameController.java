package com.hikari.hellofx;

import java.util.ArrayDeque;
import java.util.Queue;

import com.hikari.hellofx.Base.BaseModel;
import com.hikari.hellofx.Base.IModelInfo;
import com.hikari.hellofx.Entities.ConnectableState;
import com.hikari.hellofx.Entities.ConnectionInPoint;
import com.hikari.hellofx.Entities.ConnectionOutPoint;
import com.hikari.hellofx.Entities.ConnectionPoint;
import com.hikari.hellofx.Entities.ConstructorModel;
import com.hikari.hellofx.Entities.Conveyor;
import com.hikari.hellofx.Entities.EntityShadow;
import com.hikari.hellofx.Entities.IConnectable;
import com.hikari.hellofx.Views.ConnectableInfo;
import com.hikari.hellofx.Views.ConstructorView;
import com.hikari.hellofx.Views.ConveyorView;
import com.hikari.hellofx.Views.GameScene.GameField;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

public class GameController {
	enum State{
		Idle,
		Spawning,
		ConnectingFirst,
		ConnectingSecond
	}
	private State state = State.Idle;
	private GameAction action = GameAction.NOP;
	private final Game game = new Game();
	private final GameView view;
	private final EntityShadow shadow = new EntityShadow();
	private final Queue<BaseModel> noticed = new ArrayDeque<BaseModel>();

	
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

	private void spawn(Double x, Double y) {
		ConstructorModel model = new ConstructorModel();
		BindingController bController = new BindingController(this, model);
		ConstructorView spawned = new ConstructorView(x,y, bController);
		model.subscribe(spawned);
		view.showSpawned(spawned);
		game.addEntity(model); 
	}
	
	public void act(MouseEvent event, GameAction action_) {
		action = action_;
		assignHandler(event);
	}
	
	public void notice(BaseModel model, MouseEvent event, GameAction action_) {
		noticed.add(model);	
		action = action_;
		assignHandler(event);
	}
	
	private void assignHandler(MouseEvent event) {
		System.out.println("Doing " + action + " because of "
	+ event.getButton() + "; have " + noticed.size() + " noticed");
		switch(state) {
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
		switch(action) {
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
				//wrong action
				System.out.println("ignoring");
				noticed.remove();
		}
	}
	
	private void assignIfSpawning(MouseEvent event) {
		switch(action) {
			case SPAWN:
				spawnEntity(event);
				break;
			case CANCEL:
				returnToIdle();
				break;
			default:
				System.out.println("ignoring");
				noticed.remove();
		}
	}
	
	private void assignIfConnecting(MouseEvent event) {
		switch(action) {
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
				System.out.println("ignoring");
				noticed.remove();
		}
	}
		
	private void returnToIdle() {
		if(state == State.Spawning) {
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

	private void spawnConnection() {
		//TODO some exceptions if not instanceof
		ConnectionOutPoint out = (ConnectionOutPoint)noticed.remove();
		ConnectionInPoint in = (ConnectionInPoint)noticed.remove();
		Conveyor conveyor = new Conveyor(in);
		ConveyorView spawned = new ConveyorView(out,in);
		
		out.connect(conveyor);
		in.connect(conveyor);
		
		conveyor.subscribe(spawned);
		
		game.addConnection(conveyor);
		view.showSpawned(spawned);
		System.out.println("connected");
		conveyor.notifySubs();
	}

	private void despawnEntity(MouseEvent event) {
		BaseModel model = noticed.remove();
		((ConstructorModel)model).despawn(); //??? mb interface && handler?
	}
	
	private void spawnEntity(MouseEvent event) {
		if(state == State.Spawning) {
			spawn(shadow.getX(), shadow.getY()/*), entityName*/);
			state = State.Idle;
			disableShadow();
		}
	}
	
	private void disableShadow() {
		game.getField().turnOff();
		view.hideShadow(shadow);
	}
	

	private void suspendEntity() {
		BaseModel model = noticed.remove();
		if(((ConstructorModel)model).isOn() == false){
			((ConstructorModel)model).turnOn();
		}else {
			((ConstructorModel)model).turnOff();
		}
	}
	
	private void showInfo() {
		BaseModel model = noticed.remove();
		BindingController bController = new BindingController(this, model);
		ConnectableInfo info = new ConnectableInfo(bController);
		VBox currentInfo = view.getInfo();
		if(currentInfo instanceof IModelInfo) {
			((IModelInfo)currentInfo).disable();
		}
		model.subscribe(info);
		model.notifySubs();
		view.showInfo((IConnectable)model, info);
	}
	

	public Object moveShadow(MouseEvent event) {
		//System.out.println("movement on " + event.getX() + event.getY());
		shadow.move(event.getX(), event.getY());
		return null;
	}

	public void subscribeGameField(GameField gameField) {
		game.getField().subscribe(gameField);
		
	}
}
