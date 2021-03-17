package com.hikari.hellofx;

import java.util.ArrayDeque;
import java.util.Queue;

import com.hikari.hellofx.Base.BaseModel;
import com.hikari.hellofx.Entities.ConstructorModel;

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
	private final Queue<BaseModel> noticed = new ArrayDeque<BaseModel>();

	
	public GameController(GameScene scene_) {
		scene = scene_;
	}
	
	public void handleFieldClick(MouseEvent event) {
		if(state == State.Spawning) {
			scene.spawn(event.getX(), event.getY()/*), entityName*/);
			state = State.Idle;
		}
	}

	public void enableSpawningState() {
		state = State.Spawning;
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
			case INFO:
				showInfo();
			default:
				System.out.println("oopsie");
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
}
