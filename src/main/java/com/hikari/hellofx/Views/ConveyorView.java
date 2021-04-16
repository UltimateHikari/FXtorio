package com.hikari.hellofx.Views;

import com.hikari.hellofx.Base.BaseModel;
import com.hikari.hellofx.Base.IModelSubscriber;
import com.hikari.hellofx.Entities.ConnectionInPoint;
import com.hikari.hellofx.Entities.ConnectionOutPoint;
import com.hikari.hellofx.Entities.Conveyor;

import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.util.Duration;

public class ConveyorView extends Pane implements IModelSubscriber{
	TranslateTransition tr;
	Line road;
	Circle cart;
	private final Point2D start;
	private final Point2D end;
	
	public ConveyorView(ConnectionOutPoint out, ConnectionInPoint in) {
		road = new Line(out.getLastViewX(), out.getLastViewY(), in.getLastViewX(), in.getLastViewY());
		cart = new Circle(out.getLastViewX(),out.getLastViewY(), 10);
		getChildren().add(road);
		getChildren().add(cart);
		start = new Point2D(out.getLastViewX(), out.getLastViewY());
		end = new Point2D(in.getLastViewX(), in.getLastViewY());
		initTransition();
	}
	
	@Override
	public void ModelChanged(BaseModel model) {
		tr.setDuration(Duration.millis(((Conveyor)model).getTravelTime()));
		showTransition();
	}
	
	private void initTransition() {
		cart.setFill(Color.VIOLET);
		tr = new TranslateTransition(Duration.millis(1000), cart);
		tr.setByX(end.getX() - start.getX());
		tr.setByY(end.getY() - start.getY());
		tr.setCycleCount(Timeline.INDEFINITE);
		tr.setAutoReverse(true);
	}
	
	private void showTransition() {
		tr.play();
	}

}
