package com.hikari.hellofx;

import com.hikari.hellofx.Base.BaseModel;
import com.hikari.hellofx.Base.IModelSubscriber;

import javafx.scene.input.MouseEvent;

/*
 * stores information about model
 * so view can poke him, and he will poke gCon
 */

public class BindingController {
	private GameController gController;
	private BaseModel model;
	public BindingController(GameController gController_, BaseModel model_) {
		gController = gController_;
		model = model_;
	}
	
	public void handleClick(MouseEvent event) {
		gController.notice(model, event);
	}
	public void breakBond(IModelSubscriber view) {
		model.unsubscribe(view);
	}
	
}
