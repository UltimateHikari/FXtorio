package com.hikari.hellofx.game.control;

import com.hikari.hellofx.entity.Item;
import com.hikari.hellofx.game.GameAction;

import javafx.scene.input.MouseEvent;
import lombok.Getter;

public class RecipeAction extends BareAction{
	@Getter
	private Item item;
	public RecipeAction(GameAction action, MouseEvent event, Item item) {
		super(action, event);
		this.item = item;
	}
}
