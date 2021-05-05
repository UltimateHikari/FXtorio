package com.hikari.hellofx.entity.view.belt;

import com.hikari.hellofx.base.BaseModel;
import com.hikari.hellofx.base.IModelSubscriber;
import com.hikari.hellofx.entity.model.belt.ModelItem;
import com.hikari.hellofx.entity.model.belt.ModelItemStatus;

import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.geometry.Point2D;
import javafx.scene.CacheHint;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class BeltCart extends Circle implements IModelSubscriber {
	private static final int CART_RADIUS = 10;
	private final TranslateTransition tr = new TranslateTransition();
	private String payloadName;

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
		tr.setNode(this);
		tr.setDuration(travelTime);
	}

	private void move() {
		this.setVisible(true);
		tr.play();
		log.trace("moved " + payloadName);
	}

	private void rewind() {
		this.setVisible(false);
		this.setTranslateX(0);
		this.setTranslateY(0);
		log.trace("rewinded " + payloadName);
	}

	@Override
	public void modelChanged(BaseModel model) {
		if (model instanceof ModelItem m) {
			ModelItemStatus status = m.getStatus();
			payloadName = m.getPayloadName();
			if (status == ModelItemStatus.MOVED) {
				Platform.runLater(this::move);
			} else if (status == ModelItemStatus.DISPATCHED) {
				Platform.runLater(this::rewind);
			}
		} else {
			throw new IllegalArgumentException("not a modelitem");
		}
	}
}
