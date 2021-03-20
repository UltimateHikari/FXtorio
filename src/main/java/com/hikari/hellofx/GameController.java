package com.hikari.hellofx;

import java.util.ArrayDeque;
import java.util.Queue;

import com.hikari.hellofx.Base.BaseModel;
import com.hikari.hellofx.Entities.ConstructorModel;
import com.hikari.hellofx.Entities.EntityShadow;
import com.hikari.hellofx.Entities.IConnectable;
import com.hikari.hellofx.Views.ConnectableInfo;
import com.hikari.hellofx.Views.ConstructorView;
import com.hikari.hellofx.Views.GameScene.GameField;

import javafx.scene.input.MouseEvent;

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

	public void enableSpawningState() {
		state = State.Spawning;
		game.getField().turnOn();
		view.showShadow(shadow);
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
		System.out.println("Doing " + action + " because of " + event.getButton());
		switch(action) {
			case SUSPEND:
				suspendEntity();
				break;
			case INFO:
				showInfo();
				break;
			case SPAWN:
				spawnEntity(event);
				break;
			case DESPAWN:
				despawnEntity(event);
				break;
			default:
				System.out.println("oopsie, wrong command");
		}
	}
	
	private void despawnEntity(MouseEvent event) {
		BaseModel model = noticed.remove();
		((ConstructorModel)model).despawn(); //??? mb interface && handler?
	}
	
	private void spawnEntity(MouseEvent event) {
		if(state == State.Spawning) {
			spawn(shadow.getX(), shadow.getY()/*), entityName*/);
			state = State.Idle;
			game.getField().turnOff();
			view.hideShadow(shadow);
		}
	}
	

	private void suspendEntity() {
		BaseModel model = noticed.remove();
		if(((ConstructorModel)model).getState() == false){
			((ConstructorModel)model).turnOn();
		}else {
			((ConstructorModel)model).turnOff();
		}
	}
	
	private void showInfo() {
		BaseModel model = noticed.remove();
		BindingController bController = new BindingController(this, model);
		ConnectableInfo info = new ConnectableInfo(bController);
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
