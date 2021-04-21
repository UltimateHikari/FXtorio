package com.hikari.hellofx.Entities.Shadow;

import com.hikari.hellofx.Base.BaseModel;
import com.hikari.hellofx.Base.IModelSubscriber;
import com.hikari.hellofx.Game.GameAction;
import com.hikari.hellofx.Game.GameController;

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
	}

}
