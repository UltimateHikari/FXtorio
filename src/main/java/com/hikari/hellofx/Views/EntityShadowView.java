package com.hikari.hellofx.Views;

import com.hikari.hellofx.Base.BaseModel;
import com.hikari.hellofx.Base.IModelSubscriber;
import com.hikari.hellofx.Entities.EntityShadow;

import javafx.scene.paint.Color;
import javafx.animation.TranslateTransition;
import javafx.geometry.Point2D;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class EntityShadowView extends Circle implements IModelSubscriber{
	public EntityShadowView() {
		super(0,0,40);
		setFill(Color.ALICEBLUE);
		setVisible(false);
	}
	
	@Override
	public void ModelChanged(BaseModel model) {
		setVisible(true);
		Point2D point =((EntityShadow)model).getPosition();
		TranslateTransition tt = new TranslateTransition(Duration.millis(10), this);
		tt.setToX(point.getX());
		tt.setToY(point.getY());
		tt.play();
//		setCenterX(point.getX());
//		setCenterY(point.getY());
	}

}
