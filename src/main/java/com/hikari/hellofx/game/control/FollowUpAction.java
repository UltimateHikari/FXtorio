package com.hikari.hellofx.game.control;

import com.hikari.hellofx.game.GameAction;

import javafx.scene.input.MouseEvent;

/*
 * for action that has nbot much info by itself
 * but relies on previous action
 * p.e. enter/finish spawning
 */
public class FollowUpAction extends BareAction{

	public FollowUpAction(GameAction action, MouseEvent event) {
		super(action, event);
	}

}
