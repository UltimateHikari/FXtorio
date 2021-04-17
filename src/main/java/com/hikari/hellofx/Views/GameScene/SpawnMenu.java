package com.hikari.hellofx.Views.GameScene;

import com.hikari.hellofx.GameController;
import com.hikari.hellofx.MenuView;
import com.hikari.hellofx.SceneController;
import com.hikari.hellofx.Entities.ConstructorModel;

import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class SpawnMenu extends HBox {
	public SpawnMenu(SceneController controller, GameController gcontroller) {
		add(new Text("spawn menu here"));
		setSpacing(10);
		add(new Text("and here"));
		add(new SpawnButton(gcontroller, "Constructor", ConstructorModel.class.getName()));
		add(new ConnectionButton(gcontroller, "Connect"));
		add(new NavButton(controller, "Main menu", MenuView.class.getName()));
		add(new CancelButton(gcontroller, "LazyCancel"));
	}

	/*
	 * Syntax sugar
	 */
	public void add(Node child) {
		getChildren().add(child);
	}
}
