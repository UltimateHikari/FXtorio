package com.hikari.hellofx.Views;

import java.util.ArrayDeque;

import com.hikari.hellofx.Base.BaseModel;
import com.hikari.hellofx.Base.IModelSubscriber;
import com.hikari.hellofx.Entities.ConnectionEvent;
import com.hikari.hellofx.Entities.ConnectionInPoint;
import com.hikari.hellofx.Entities.ConnectionOutPoint;
import com.hikari.hellofx.Entities.Conveyor;

import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.util.Duration;

public class ConveyorView extends Pane implements IModelSubscriber{
	private ArrayDeque<EntityTransition> transitions = new ArrayDeque<EntityTransition>(10);
	Line road;
	Duration duration = Duration.millis(1000); //default
	private final Point2D start;
	private final Point2D end;
	
	public ConveyorView(ConnectionOutPoint out, ConnectionInPoint in) {
		road = new Line(out.getLastViewX(), out.getLastViewY(), in.getLastViewX(), in.getLastViewY());
		getChildren().add(road);
		setMouseTransparent(true);
		
		start = new Point2D(out.getLastViewX(), out.getLastViewY());
		end = new Point2D(in.getLastViewX(), in.getLastViewY());
	}
	
	@Override
	public void ModelChanged(BaseModel model) {
		duration = (Duration.millis(((Conveyor)model).getTravelTime()));
		ConnectionEvent e = ((Conveyor)model).getLastConnectionEvent();
		switch(e) {
			case DEPARTED:
				EntityTransition entityTransition = new EntityTransition(duration, start, end, this);
				transitions.add(entityTransition);
				break;
			case ARRIVED:
				transitions.remove().dismiss();
				break;
		}
	}

}
