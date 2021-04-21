package com.hikari.hellofx.Game.View;


import com.hikari.hellofx.Base.BaseModel;
import com.hikari.hellofx.Base.IModelSubscriber;
import com.hikari.hellofx.Game.GameController;
import com.hikari.hellofx.Game.GameFieldModel;

import javafx.event.*;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class GameField extends Pane implements IModelSubscriber{
	private final Rectangle field;
	private final GameController gcontroller;
	private final EventHandler<MouseEvent> handler = new EventHandler<MouseEvent>(){
		@Override
		public void handle(MouseEvent event) {
			gcontroller.moveShadow(event);
		}
	};
	public GameField(GameController gcontroller_){
		gcontroller = gcontroller_;
		setPrefSize(640,480);
		field = new Rectangle(0,0, 640,480);
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
	public void ModelChanged(BaseModel model) {
		if(((GameFieldModel) model).isOn()) {
			field.setOnMouseMoved(handler);
		}else {
			field.removeEventHandler(MouseEvent.MOUSE_MOVED, handler);
		}
		
	}
	
}
