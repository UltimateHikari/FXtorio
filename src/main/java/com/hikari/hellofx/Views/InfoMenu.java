package com.hikari.hellofx.Views;

import com.hikari.hellofx.Base.BaseModel;
import com.hikari.hellofx.Base.IModelSubscriber;

import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class InfoMenu extends VBox{
	public InfoMenu() {
		getChildren().add(new Text("infomenu here"));
		setSpacing(10);
		getChildren().add(new Text("and here"));
	}
}
