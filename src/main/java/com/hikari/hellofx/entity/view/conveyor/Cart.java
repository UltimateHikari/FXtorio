package com.hikari.hellofx.entity.view.conveyor;

import com.hikari.hellofx.entity.Item;

import javafx.application.Platform;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

public class Cart extends StackPane {
	Pane view;
	private static final int CART_RADIUS = 10;
	

	public Cart(Point2D p, Pane view, Item item) {
		setLayoutX(p.getX() - CART_RADIUS);
		setLayoutY(p.getY() - CART_RADIUS);
		var circle = new Circle(CART_RADIUS);
		circle.setFill(item.getColor());
		var text = new Text(item.getTag());
		this.view = view;

		Platform.runLater(() -> {
			view.getChildren().add(this);
			this.getChildren().add(circle);
			this.getChildren().add(text);
		});
	}

	public void dismiss() {
		Platform.runLater(() -> view.getChildren().remove(this));
	}
}
