package com.hikari.hellofx.Entities.Connection;

import javafx.application.Platform;
import javafx.geometry.Point2D;
import javafx.scene.CacheHint;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Cart extends Circle {
	Pane view;

	public Cart(Point2D p, Pane view_) {
		super(p.getX(), p.getY(), 10);
		view = view_;
		setFill(Color.VIOLET);
		//setCache(true);
		//setCacheHint(CacheHint.SPEED);
		Platform.runLater(() -> view.getChildren().add(this));
	}

	public void dismiss() {
		Platform.runLater(() -> view.getChildren().remove(this));
	}
}