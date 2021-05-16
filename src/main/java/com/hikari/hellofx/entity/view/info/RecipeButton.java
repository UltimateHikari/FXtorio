package com.hikari.hellofx.entity.view.info;

import com.hikari.hellofx.entity.BindingController;
import com.hikari.hellofx.entity.Items;
import com.hikari.hellofx.game.GameAction;
import com.hikari.hellofx.game.control.RecipeAction;

import javafx.scene.control.Button;

public class RecipeButton extends Button {
	public RecipeButton(BindingController bController, String label, Items item) {
		super(label);
		setOnMouseClicked(event -> bController.handleClick(new RecipeAction(GameAction.RECIPE, event, item)));
	}
}
