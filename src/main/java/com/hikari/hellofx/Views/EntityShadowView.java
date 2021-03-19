package com.hikari.hellofx.Views;

import com.hikari.hellofx.GameAction;
import com.hikari.hellofx.GameController;
import com.hikari.hellofx.Base.BaseModel;
import com.hikari.hellofx.Base.IModelSubscriber;
import com.hikari.hellofx.Entities.EntityShadow;

import javafx.scene.paint.Color;
import javafx.animation.TranslateTransition;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class EntityShadowView extends Circle implements IModelSubscriber{
	public EntityShadowView(GameController gController) {
		super(0,0,20);
		setFill(Color.ALICEBLUE);
		setVisible(false);
		setOnMouseClicked((event) -> gController.act(event, GameAction.SPAWN));
	}
	
	@Override
	public void ModelChanged(BaseModel model) {
		setVisible(true);		
		TranslateTransition tt = new TranslateTransition(Duration.millis(10), this);
		tt.setToX(((EntityShadow)model).getX());
		tt.setToY(((EntityShadow)model).getY());
		tt.play();
//		setCenterX(point.getX());
//		setCenterY(point.getY());
	}

}
