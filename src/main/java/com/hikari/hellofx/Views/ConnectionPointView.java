package com.hikari.hellofx.Views;

import com.hikari.hellofx.BindingController;
import com.hikari.hellofx.GameAction;
import com.hikari.hellofx.Base.BaseModel;
import com.hikari.hellofx.Base.IModelSubscriber;
import com.hikari.hellofx.Entities.ConnectionInPoint;
import com.hikari.hellofx.Entities.ConnectionPoint;

import javafx.scene.shape.Circle;

public class ConnectionPointView extends Circle implements IModelSubscriber {
	private final ConnectionPoint model;
	private static final int SIZE = 20;
	public ConnectionPointView(ConnectionPoint model_, int PARENT_SIZE, BindingController controller) {
		super(
				PARENT_SIZE/2 + model_.getOffsetX(), 
				PARENT_SIZE/2 + model_.getOffsetY(),
				SIZE);
		model = model_;
		model.subscribe(this);
		GameAction action = (model_ instanceof ConnectionInPoint ? GameAction.CONNECT_IN : GameAction.CONNECT_OUT);
		setOnMouseClicked((event) -> controller.handleConnection(event, action, model));
	}
	@Override
	public void ModelChanged(BaseModel model_) {
		// chill for now
	}
	public void unsubscribe() {
		model.unsubscribe(this);
	}

}
