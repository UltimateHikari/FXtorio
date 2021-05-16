package com.hikari.hellofx.game.control;

import com.hikari.hellofx.game.GameAction;

import javafx.scene.input.MouseEvent;

public interface ControlTransferObject {
	public GameAction getAction();
	public MouseEvent getEvent();
	public void appendTo(ControlTransferObject cto);
}
