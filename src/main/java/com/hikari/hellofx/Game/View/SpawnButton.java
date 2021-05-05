package com.hikari.hellofx.Game.View;

import com.hikari.hellofx.Entities.ClassPairs.EntityClassPair;
import com.hikari.hellofx.Game.GameAction;
import com.hikari.hellofx.Game.GameController;

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
			EntityClassPair entityClassPair, Color color) {
		super(label);
		setBackground(new Background(new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY)));
		//TODO pass this nightmare to css..?
		setBorder(new Border(new BorderStroke(color.darker(), BorderStrokeStyle.SOLID, null, null)));
		setOnMouseClicked((event) -> gcontroller.act(event, GameAction.ENTER_SPAWN, entityClassPair));
		
	}
}
