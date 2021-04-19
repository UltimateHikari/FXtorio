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
	private Rectangle field;
	private GameController gcontroller = null;
	private EventHandler<MouseEvent> handler = new EventHandler<MouseEvent>(){
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
//		field.setOnMouseClicked((event) -> gcontroller.handleFieldClick(event));
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
		if(((GameFieldModel)model).isOn() == true) {
			//field.setOnMouseMoved((event) -> gcontroller.moveShadow(event));
			field.setOnMouseMoved(handler);
		}else {
			field.removeEventHandler(MouseEvent.MOUSE_MOVED, handler);
		}
		
	}
	
}
