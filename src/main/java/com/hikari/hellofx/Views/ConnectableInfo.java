package com.hikari.hellofx.Views;

import com.hikari.hellofx.BindingController;
import com.hikari.hellofx.InfoButton;
import com.hikari.hellofx.Base.BaseModel;
import com.hikari.hellofx.Base.IModelInfo;
import com.hikari.hellofx.Entities.Suspendable;

import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.Node;

public class ConnectableInfo extends VBox implements IModelInfo{
	private Text text = new Text("");
	private InfoButton btn;
	BindingController controller;
	public ConnectableInfo(BindingController bController) {
		controller = bController;
		add(text);
		btn = new InfoButton(bController, "");
		add(btn);
	}
	@Override
	public void ModelChanged(BaseModel model) {		
		text.setText("My state is " + ((Suspendable)model).getState());
		btn.setText("turn " + ((Suspendable)model).getState());
		System.out.println(text.getText());
	}
	
	public void disable() {
		controller.breakBond(this);
	}
	private void add(Node node) {
		getChildren().add(node);
	}
}
