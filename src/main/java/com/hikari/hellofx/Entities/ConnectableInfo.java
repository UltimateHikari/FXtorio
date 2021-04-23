package com.hikari.hellofx.Entities;

import com.hikari.hellofx.Base.BaseModel;
import com.hikari.hellofx.Base.ILoggable;
import com.hikari.hellofx.Base.IModelInfo;
import com.hikari.hellofx.Entities.Connectable.ISuspendable;
import com.hikari.hellofx.Game.View.DespawnButton;
import com.hikari.hellofx.Game.View.SuspendButton;

import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.application.Platform;
import javafx.scene.Node;

public class ConnectableInfo extends VBox implements IModelInfo, ILoggable{
	private final Text text = new Text("");
	private final SuspendButton btn;
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
		Platform.runLater(() -> { 
			text.setText(state + "\n I am " + model);
			btn.setText("turn " + stringifyReversePowerState((ISuspendable)model));
			} 
		);
	}
	
	private String stringifyReversePowerState(ISuspendable model) {
		if(model.isOn()) {
			return "off";
		}
		return "on";
	}
	
	public void disable() {
		controller.breakBond(this);
	}
	private void add(Node node) {
		getChildren().add(node);
	}
}
