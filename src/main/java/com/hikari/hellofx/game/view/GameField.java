package com.hikari.hellofx.game.view;

import com.hikari.hellofx.base.BaseModel;
import com.hikari.hellofx.base.IModelSubscriber;
import com.hikari.hellofx.game.GameController;
import com.hikari.hellofx.game.GameFieldModel;

import javafx.event.*;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class GameField extends Pane implements IModelSubscriber {
	private static final double WIDTH = 1200;
	private static final double HEIGHT = 800;
	private final Rectangle field;
	private final GameController gcontroller;
	private final EventHandler<MouseEvent> handler = new EventHandler<MouseEvent>() {
		@Override
		public void handle(MouseEvent event) {
			gcontroller.moveShadow(event);
		}
	};

	public GameField(GameController gcontroller) {
		this.gcontroller = gcontroller;
		setPrefSize(WIDTH, HEIGHT);
		field = new Rectangle(0, 0, WIDTH, HEIGHT);
		field.setFill(Color.GAINSBORO);
		add(field);
	}

	public void add(Node child) {
		getChildren().add(child);
	}

	public void remove(Node child) {
		getChildren().remove(child);
	}

	@Override
	public void modelChanged(BaseModel model) {
		if (((GameFieldModel) model).isOn()) {
			field.setOnMouseMoved(handler);
		} else {
			field.removeEventHandler(MouseEvent.MOUSE_MOVED, handler);
		}

	}

}
