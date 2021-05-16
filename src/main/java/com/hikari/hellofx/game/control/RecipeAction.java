package com.hikari.hellofx.game.control;

import com.hikari.hellofx.entity.Items;
import com.hikari.hellofx.game.GameAction;

import javafx.scene.input.MouseEvent;
import lombok.Getter;

public class RecipeAction extends BareAction{
	@Getter
	private Items item;
	public RecipeAction(GameAction action, MouseEvent event, Items item) {
		super(action, event);
		this.item = item;
	}
}
