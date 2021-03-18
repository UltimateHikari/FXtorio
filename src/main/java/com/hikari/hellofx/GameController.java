package com.hikari.hellofx;

import java.util.ArrayDeque;
import java.util.Queue;

import com.hikari.hellofx.Base.BaseModel;
import com.hikari.hellofx.Entities.ConstructorModel;
import com.hikari.hellofx.Entities.EntityShadow;

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
	private final GameScene scene;
	private final GameFieldModel gameFieldModel;
	private final EntityShadow shadow = new EntityShadow();
	private final Queue<BaseModel> noticed = new ArrayDeque<BaseModel>();

	
	public GameController(GameScene scene_, GameFieldModel gameFieldModel_) {
		gameFieldModel = gameFieldModel_;
		scene = scene_;
	}

	public void enableSpawningState() {
		state = State.Spawning;
		gameFieldModel.turnOn();
		scene.showShadow(shadow);
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
			default:
				System.out.println("oopsie, wrong command");
		}
	}
	
	private void spawnEntity(MouseEvent event) {
		if(state == State.Spawning) {
			scene.spawn(shadow.getX(), shadow.getY()/*), entityName*/);
			state = State.Idle;
			gameFieldModel.turnOff();
			scene.hideShadow(shadow);
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
		scene.showInfo((ConstructorModel)model);
	}

	public Object moveShadow(MouseEvent event) {
		//System.out.println("movement on " + event.getX() + event.getY());
		shadow.move(event.getX(), event.getY());
		return null;
	}
}
