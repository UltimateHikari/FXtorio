package com.hikari.hellofx;


import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class GameField extends AnchorPane{
	public GameField(GameController gcontroller){
		setPrefSize(640,480);
		Rectangle field = new Rectangle(0,0, 640,480);
		field.setFill(Color.GAINSBORO);
		field.setOnMouseClicked((event) -> gcontroller.handleFieldClick(event));
		add(field, 0.0, 0.0);
	}
	public void add(Node child, Double x, Double y) {
		getChildren().add(child);
		setTopAnchor(child, y);
		setLeftAnchor(child, x);
	}
}
