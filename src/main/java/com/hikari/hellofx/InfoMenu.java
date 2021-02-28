package com.hikari.hellofx;

import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class InfoMenu extends VBox {
	public InfoMenu(SceneController controller) {
		getChildren().add(new Text("infomenu here"));
		setSpacing(10);
		getChildren().add(new Text("and here"));
	}
}
