package com.hikari.hellofx.game.view;

import com.hikari.hellofx.game.GameAction;
import com.hikari.hellofx.game.GameController;
import com.hikari.hellofx.game.control.EntityClassPack;
import com.hikari.hellofx.game.control.PackAction;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

public class SpawnButton extends Button {
	public SpawnButton(GameController gcontroller, String label,
			EntityClassPack entityClassPack, Color color) {
		super(label);
		setBackground(new Background(new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY)));
		setBorder(new Border(new BorderStroke(color.darker(), BorderStrokeStyle.SOLID, null, null)));
		setOnMouseClicked(event -> gcontroller.act(new PackAction(GameAction.ENTER_SPAWN, event, entityClassPack)));
		
	}
}
