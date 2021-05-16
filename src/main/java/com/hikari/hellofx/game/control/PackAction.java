package com.hikari.hellofx.game.control;

import com.hikari.hellofx.game.GameAction;

import javafx.scene.input.MouseEvent;
import lombok.Getter;

public class PackAction extends BareAction{
	@Getter
	private ClassPack pack;
	public PackAction(GameAction action, MouseEvent event, ClassPack pack) {
		super(action, event);
		this.pack = pack;
	}
}
