package com.hikari.hellofx;

import com.hikari.hellofx.Base.BaseModel;
import com.hikari.hellofx.Base.IModelInfo;
import com.hikari.hellofx.Base.IModelSubscriber;
import com.hikari.hellofx.Entities.Suspendable;

import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class ConnectableInfo extends VBox implements IModelInfo{
	private Text text = new Text("");
	BindingController controller;
	public ConnectableInfo(BindingController bController) {
		controller = bController;
		getChildren().add(text);
	}
	@Override
	public void ModelChanged(BaseModel model) {
		getChildren().remove(text);
		text = new Text("My state is " + ((Suspendable)model).getState());
		System.out.println(text.getText());
		getChildren().add(text);
	}
	
	public void disable() {
		controller.breakBond(this);
	}
	
}
