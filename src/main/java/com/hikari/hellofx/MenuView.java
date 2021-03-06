package com.hikari.hellofx;

import com.hikari.hellofx.game.view.NavButton;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;


public class MenuView extends GridPane{
	public MenuView(SceneController controller) {
		setAlignment(Pos.CENTER);
		setVgap(10);
		setHgap(10);
		setPadding(new Insets(25,25,25,25));

		
		add(new NavButton(controller, "Continue", SceneClass.GAME), 0, 0);
		add(new Button("New game"), 0, 1);
		add(new Button("Load game"), 0, 2);
		add(new Button("Credits"), 0, 3);
		add(new Button("Exit"), 0, 4);
		
	}
}
