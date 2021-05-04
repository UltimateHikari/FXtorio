package com.hikari.hellofx.Entities.Connection.Belt;

import java.util.ArrayList;
import java.util.List;

import com.hikari.hellofx.Base.BaseModel;
import com.hikari.hellofx.Base.IModelSubscriber;
import com.hikari.hellofx.Entities.ConnectionPoint.ConnectionInPoint;
import com.hikari.hellofx.Entities.ConnectionPoint.ConnectionOutPoint;

import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.util.Duration;

public class BeltView extends Pane implements IModelSubscriber{
	//todo rework to single pool of refire-able transitions for caching
		Line road;
		Duration duration = Duration.millis(1000); //default
		List <BeltCart> carts = new ArrayList<>();
		private final Point2D start;
		private final Point2D end;
		
		public BeltView(ConnectionOutPoint out, ConnectionInPoint in) {
			road = new Line(out.getLastViewX(), out.getLastViewY(), in.getLastViewX(), in.getLastViewY());
			getChildren().add(road);
			setMouseTransparent(true);
			
			start = new Point2D(out.getLastViewX(), out.getLastViewY());
			end = new Point2D(in.getLastViewX(), in.getLastViewY());
		}
		
		@Override
		public void ModelChanged(BaseModel model) {
			if(model instanceof Belt belt) {
				double n = belt.getSlotsCount();
				Point2D translation = new Point2D((end.getX() - start.getX())/n, (end.getY() - start.getY())/n);
				List<ModelItem> items = belt.getItemModels();
				for(ModelItem m : items) {
					BeltCart cart = new BeltCart(start, translation, Duration.millis((double)belt.getCellTravelTime() - 100));
					m.subscribe(cart);
					carts.add(cart);
					getChildren().add(cart);
				}
			} else {
				throw new IllegalArgumentException("not a belt");
			}
		}

}
