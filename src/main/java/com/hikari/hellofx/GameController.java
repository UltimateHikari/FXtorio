package com.hikari.hellofx;

import java.util.Collection;

import com.hikari.hellofx.Entities.MinerView;

import javafx.scene.input.MouseEvent;

public class GameController {
	private Integer state = 0;
	private final GameScene scene;
	
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
	

}
