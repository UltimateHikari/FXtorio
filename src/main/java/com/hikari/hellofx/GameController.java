package com.hikari.hellofx;

import java.util.ArrayDeque;
import java.util.Queue;

import com.hikari.hellofx.Base.BaseModel;
import com.hikari.hellofx.Entities.Connectable;
import com.hikari.hellofx.Entities.ConstructorModel;
import com.hikari.hellofx.Entities.ConstructorView;

import javafx.event.ActionEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class GameController {
	private Integer state = 0;
	private final GameScene scene;
	private final Queue<BaseModel> noticed = new ArrayDeque<BaseModel>();

	
	public GameController(GameScene scene_) {
		scene = scene_;
	}
	
	public void handleFieldClick(MouseEvent event) {
		if(state == 1) {
			scene.spawn(event.getX(), event.getY());
			state = 0;
		}
	}
	

	public void enableSpawningState() {
		state = 1;
	}

	public void notice(BaseModel model, MouseEvent event) {
		noticed.add(model);	
		changeState(event);
	}
	
	public void notice(BaseModel model, ActionEvent event) {
		noticed.add(model);	
		System.out.println(event.getEventType());
		changeState(event);
	}
	
	private void changeState(MouseEvent event) {
		System.out.println(event.getButton());
		if(event.getButton() == MouseButton.PRIMARY) {
			if(state == 0) {
				BaseModel model = noticed.remove();
				scene.showInfo((ConstructorModel)model);
			}
		}
	}
	
	private void changeState(ActionEvent event) {
		BaseModel model = noticed.remove();
		if(((ConstructorModel)model).getState() == false){
			((ConstructorModel)model).turnOn();
		}else {
			((ConstructorModel)model).turnOff();
		}
	}

}
