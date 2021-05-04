package com.hikari.hellofx.Entities.Connection.Belt;


import com.hikari.hellofx.Base.BaseModel;
import com.hikari.hellofx.Base.IModelSubscriber;

import javafx.animation.TranslateTransition;
import javafx.geometry.Point2D;
import javafx.scene.CacheHint;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class BeltCart extends Circle implements IModelSubscriber{
	private static final int CART_RADIUS = 10;
	private final TranslateTransition tr = new TranslateTransition();
	
	public BeltCart(Point2D position, Point2D translation, Duration travelTime) {
		super(position.getX(), position.getY(), CART_RADIUS);
		setFill(Color.VIOLET);
		setCache(true);
		setCacheHint(CacheHint.SPEED);
		initTranslation(translation, travelTime);
		this.setVisible(false);
	}
	
	private void initTranslation(Point2D translation, Duration travelTime) {
		tr.setByX(translation.getX());
		tr.setByY(translation.getY());
		tr.setDuration(travelTime);
	}

	private void move() {
		this.setVisible(true);
		tr.play();
	}
	
	private void rewind() {
		this.setVisible(false);
		this.setTranslateX(0);
		this.setTranslateY(0);
	}

	@Override
	public void ModelChanged(BaseModel model) {
		if(model instanceof ModelItem m) {
			ModelItemStatus status = m.getStatus();
			if(status == ModelItemStatus.MOVED) {
				move();
			}else if(status == ModelItemStatus.DISPATCHED) {
				rewind();
			}
		}else {
			throw new IllegalArgumentException("not a modelitem");
		}
	}
}
