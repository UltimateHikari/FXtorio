package com.hikari.hellofx.Views;

import com.hikari.hellofx.Base.BaseModel;
import com.hikari.hellofx.Base.IModelSubscriber;
import com.hikari.hellofx.Entities.EntityShadow;

import javafx.scene.paint.Color;
import javafx.geometry.Point2D;
import javafx.scene.shape.Circle;

public class EntityShadowView extends Circle implements IModelSubscriber{

	public EntityShadowView() {
		super(100,100,40);
		setFill(Color.ALICEBLUE);
	}
	
	@Override
	public void ModelChanged(BaseModel model) {
		Point2D point =((EntityShadow)model).getPosition();
		setCenterX(point.getX());
		setCenterY(point.getY());
	}

}
