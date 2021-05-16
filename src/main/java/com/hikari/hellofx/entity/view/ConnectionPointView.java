package com.hikari.hellofx.entity.view;

import com.hikari.hellofx.base.BaseModel;
import com.hikari.hellofx.base.IModelSubscriber;
import com.hikari.hellofx.entity.BindingController;
import com.hikari.hellofx.entity.model.ConnectionInPoint;
import com.hikari.hellofx.entity.model.ConnectionPoint;
import com.hikari.hellofx.game.GameAction;
import com.hikari.hellofx.game.control.FollowUpAction;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class ConnectionPointView extends Circle implements IModelSubscriber {
	private final ConnectionPoint model;
	private static final int SIZE = 10;
	
	public ConnectionPointView(
			ConnectionPoint model, 
			double parentSize, 
			BindingController controller,
			Double parentX,
			Double parentY
			) {
		super(
				parentSize/2 + parentSize*model.getOffsetX(), 
				parentSize/2 + parentSize*model.getOffsetY(),
				SIZE
				);
		setFill(Color.AQUA);
		this.model = model;
		
		model.subscribe(this);
		model.setLastViewX(
				parentX + parentSize/2 + parentSize*model.getOffsetX()
				); // acturally its controller work
		model.setLastViewY(
				parentY + parentSize/2 + parentSize*model.getOffsetY()
				); // anyway
		//arr super constructor forces copypaste
		GameAction action = (model instanceof ConnectionInPoint ? GameAction.CONNECT_IN : GameAction.CONNECT_OUT);
		setOnMouseClicked(event -> controller.handleConnection(new FollowUpAction(action, event), model));
	}
	@Override
	public void modelChanged(BaseModel model) {
		// chill for now
	}
	public void unsubscribe() {
		model.unsubscribe(this);
	}

}
