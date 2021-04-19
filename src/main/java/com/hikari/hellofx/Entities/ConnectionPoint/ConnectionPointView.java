package com.hikari.hellofx.Entities.ConnectionPoint;

import com.hikari.hellofx.Base.BaseModel;
import com.hikari.hellofx.Base.IModelSubscriber;
import com.hikari.hellofx.Entities.BindingController;
import com.hikari.hellofx.Game.GameAction;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class ConnectionPointView extends Circle implements IModelSubscriber {
	private final ConnectionPoint model;
	private static final int SIZE = 10;
	
	public ConnectionPointView(
			ConnectionPoint model_, 
			int PARENT_SIZE, 
			BindingController controller,
			Double parentX,
			Double parentY
			) {
		super(
				PARENT_SIZE/2 + PARENT_SIZE*model_.getOffsetX(), 
				PARENT_SIZE/2 + PARENT_SIZE*model_.getOffsetY(),
				SIZE
				);
		setFill(Color.AQUA);
		model = model_;
		
		model.subscribe(this);
		model.setLastViewX(
				parentX + PARENT_SIZE/2 + PARENT_SIZE*model_.getOffsetX()
				); // acturally its controller work
		model.setLastViewY(
				parentY + PARENT_SIZE/2 + PARENT_SIZE*model_.getOffsetY()
				); // anyway
		//arr super constructor forces copypaste
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
