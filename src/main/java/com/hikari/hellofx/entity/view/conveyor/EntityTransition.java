package com.hikari.hellofx.entity.view.conveyor;

import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class EntityTransition {
	private final Cart cart;

	public EntityTransition(Duration duration, Point2D start, Point2D end, Pane parent) {
		cart = new Cart(start, parent);
		TranslateTransition tr = new TranslateTransition(duration, cart);
		tr.setByX(end.getX() - start.getX());
		tr.setByY(end.getY() - start.getY());
		Platform.runLater(() -> tr.play());
	}

	public void dismiss() {
		cart.dismiss();
	}

}
