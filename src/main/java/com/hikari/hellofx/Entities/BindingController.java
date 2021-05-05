package com.hikari.hellofx.Entities;

import com.hikari.hellofx.Base.BaseModel;
import com.hikari.hellofx.Base.IModelSubscriber;
import com.hikari.hellofx.Entities.ConnectionPoint.ConnectionPoint;
import com.hikari.hellofx.Game.GameAction;
import com.hikari.hellofx.Game.GameController;

import javafx.scene.input.MouseEvent;
import lombok.extern.log4j.Log4j2;

/*
 * stores information about model
 * so view can poke him, and he will poke gCon
 */
@Log4j2
public class BindingController{
	private final GameController gController;
	private final BaseModel model;
	public BindingController(GameController gController_, BaseModel model_) {
		gController = gController_;
		model = model_;
	}
	
	public void handleClick(MouseEvent event, GameAction action) {
		log.debug("noticing " + model.toString());
		gController.notice(model, event, action);
	}
	
	public void handleConnection(MouseEvent event, GameAction action, ConnectionPoint point) {
		log.debug("noticing " + point.toString());
		gController.notice(point, event, action);
	}
	
	public void breakBond(IModelSubscriber view) {
		model.unsubscribe(view);
	}
	
}
