package com.hikari.hellofx;


import com.hikari.hellofx.Base.BaseModel;
import com.hikari.hellofx.Base.IModelSubscriber;
import javafx.event.*;

import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class GameField extends AnchorPane implements IModelSubscriber{
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
		add(field, 0.0, 0.0);
	}
	public void add(Shape child, Double x, Double y) {
		getChildren().add(child);
		setTopAnchor(child, y);
		setLeftAnchor(child, x);
	}
	@Override
	public void ModelChanged(BaseModel model) {
		System.out.println("hello there");
		if(((GameFieldModel)model).getSpawningState() == true) {
			//field.setOnMouseMoved((event) -> gcontroller.moveShadow(event));
			field.setOnMouseMoved(handler);
		}else {
			field.removeEventHandler(MouseEvent.MOUSE_MOVED, handler);
		}
		
	}
}
