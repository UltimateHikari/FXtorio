package com.hikari.hellofx.entity.view.conveyor;

import java.util.ArrayDeque;

import com.hikari.hellofx.base.BaseModel;
import com.hikari.hellofx.entity.Item;
import com.hikari.hellofx.entity.model.ConnectionInPoint;
import com.hikari.hellofx.entity.model.ConnectionOutPoint;
import com.hikari.hellofx.entity.model.conveyor.Conveyor;
import com.hikari.hellofx.entity.view.BasicConnectionView;

import javafx.geometry.Point2D;
import javafx.scene.shape.Line;
import javafx.util.Duration;

public class ConveyorView extends BasicConnectionView {
	// todo rework to single pool of refire-able transitions for caching
	private final ArrayDeque<EntityTransition> transitions = new ArrayDeque<EntityTransition>(10);
	Line road;
	Duration duration = Duration.millis(1000); // default
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
	public void modelChanged(BaseModel model) {
		duration = (Duration.millis(((Conveyor) model).getTravelTime()));
		var e = ((Conveyor) model).getLastConnectionEvent();
		Item item = (Item)((Conveyor) model).getPayload();
		switch (e) {
		case DEPARTED:
			var entityTransition = new EntityTransition(duration, start, end, this, item);
			transitions.add(entityTransition);
			break;
		case ARRIVED:
			transitions.remove().dismiss();
			break;
		case NOTHING:
			break;
		}
	}

}
