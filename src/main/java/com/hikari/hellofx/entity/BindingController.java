package com.hikari.hellofx.entity;

import com.hikari.hellofx.base.BaseModel;
import com.hikari.hellofx.base.IModelSubscriber;
import com.hikari.hellofx.entity.model.cpoint.ConnectionPoint;
import com.hikari.hellofx.game.GameController;
import com.hikari.hellofx.game.control.ControlTransferObject;

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
	
	public void handleClick(ControlTransferObject cto) {
		log.debug("noticing " + model.toString());
		gController.notice(cto, model);
	}
	
	public void handleConnection(ControlTransferObject cto, ConnectionPoint point) {
		log.debug("noticing " + point.toString());
		gController.notice(cto, point);
	}
	
	public void breakBond(IModelSubscriber view) {
		model.unsubscribe(view);
	}
	
}
