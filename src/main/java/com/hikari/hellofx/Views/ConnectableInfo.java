package com.hikari.hellofx.Views;

import com.hikari.hellofx.BindingController;
import com.hikari.hellofx.Base.BaseModel;
import com.hikari.hellofx.Base.ILoggable;
import com.hikari.hellofx.Base.IModelInfo;
import com.hikari.hellofx.Entities.ISuspendable;
import com.hikari.hellofx.Views.GameScene.DespawnButton;
import com.hikari.hellofx.Views.GameScene.SuspendButton;

import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.Node;

public class ConnectableInfo extends VBox implements IModelInfo, ILoggable{
	private Text text = new Text("");
	private SuspendButton btn;
	BindingController controller;
	public ConnectableInfo(BindingController bController) {
		controller = bController;
		add(text);
		btn = new SuspendButton(bController, "");
		add(btn);
		add(new DespawnButton(bController, "deconstruct"));
	}
	@Override
	public void ModelChanged(BaseModel model) {
		String state = "My state is " + ((ISuspendable)model).isOn();
		text.setText(state + "\n I am " + model.toString());
		btn.setText("turn " + ((ISuspendable)model).isOn());
		log(state + "\n I am " + model.toString());
	}
	
	public void disable() {
		controller.breakBond(this);
	}
	private void add(Node node) {
		getChildren().add(node);
	}
}
