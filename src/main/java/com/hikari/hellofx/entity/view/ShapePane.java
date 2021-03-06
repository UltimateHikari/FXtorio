package com.hikari.hellofx.entity.view;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

class ShapePane extends StackPane{
	private final Rectangle field;
	private final Text label;
	
	public ShapePane(double size, Color color) {
		this(size, "off", color);
	}
	
	public ShapePane(double size, String text, Color color) {
		field = new Rectangle(0,0,size,size);
		label = new Text(text);
		field.setFill(color);
		field.setStroke(color.darker());
		getChildren().add(field);
		getChildren().add(label);
		setPrefSize(size,size);
	}
	public Rectangle getField() {
		return field;
	}
	public Text getLabel() {
		return label;
	}
	
}
