package com.hikari.hellofx;

import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class SpawnMenu extends HBox {
	public SpawnMenu() {
		getChildren().add(new Text("spawn menu here"));
		setSpacing(10);
		getChildren().add(new Text("and here"));
	}
}
