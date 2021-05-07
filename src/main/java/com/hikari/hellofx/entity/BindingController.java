package com.hikari.hellofx.entity;

import com.hikari.hellofx.base.BaseModel;
import com.hikari.hellofx.base.IModelSubscriber;
import com.hikari.hellofx.entity.model.ConnectionPoint;
import com.hikari.hellofx.game.GameAction;
import com.hikari.hellofx.game.GameController;

import javafx.scene.input.MouseEvent;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

/*
 * stores information about model
 * so view can poke him, and he will poke gCon
 */
@Log4j2
@AllArgsConstructor
public class BindingController{
	private final GameController gController;
	private final BaseModel model;
	
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
