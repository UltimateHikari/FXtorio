package com.hikari.hellofx.game.control;

import com.hikari.hellofx.game.GameAction;

import javafx.scene.input.MouseEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class BareAction implements ControlTransferObject {
	@Getter
	private GameAction action;
	@Getter
	MouseEvent event;
	
	@Override
	public void appendTo(ControlTransferObject cto) {
		//reasons in FollowUpAction
		((BareAction)cto).updateWith(this);
	}
	private void updateWith(ControlTransferObject cto) {
		action = cto.getAction();
		event = cto.getEvent();
	}
}
