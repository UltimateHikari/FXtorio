package com.hikari.hellofx;

import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class SpawnMenu extends HBox {
	public SpawnMenu(SceneController controller) {
		getChildren().add(new Text("spawn menu here"));
		setSpacing(10);
		getChildren().add(new Text("and here"));
		getChildren().add(new NavButton(controller, "Main menu", "MenuScene"));
	}
}
