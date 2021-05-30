package com.hikari.hellofx;

import javafx.scene.layout.GridPane;
import lombok.AllArgsConstructor;
import lombok.Getter;

import com.hikari.hellofx.game.view.GameView;

@AllArgsConstructor
public enum SceneClass {
	GAME(GameView.class),
	MENU(MenuView.class);
	//LOAD(LoadView.class)
	//SAVE(SaveView.class)
	
	@Getter
	private final Class<? extends GridPane> sceneClass;
}
