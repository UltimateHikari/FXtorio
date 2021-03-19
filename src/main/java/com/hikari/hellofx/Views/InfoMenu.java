package com.hikari.hellofx.Views;

import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class InfoMenu extends VBox{
	public InfoMenu() {
		getChildren().add(new Text("infomenu here"));
		setSpacing(10);
		getChildren().add(new Text("and here"));
	}
}
