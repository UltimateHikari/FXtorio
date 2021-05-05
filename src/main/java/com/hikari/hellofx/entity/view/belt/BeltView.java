package com.hikari.hellofx.entity.view.belt;

import java.util.ArrayList;
import java.util.List;

import com.hikari.hellofx.base.BaseModel;
import com.hikari.hellofx.entity.model.ConnectionInPoint;
import com.hikari.hellofx.entity.model.ConnectionOutPoint;
import com.hikari.hellofx.entity.model.belt.Belt;
import com.hikari.hellofx.entity.model.belt.ModelItem;
import com.hikari.hellofx.entity.view.BasicConnectionView;

import javafx.geometry.Point2D;
import javafx.scene.shape.Line;
import javafx.util.Duration;

public class BeltView extends BasicConnectionView{
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
		public void modelChanged(BaseModel model) {
			if(model instanceof Belt belt) {
				double n = belt.getSlotsCount();
				var translation = new Point2D((end.getX() - start.getX())/n, (end.getY() - start.getY())/n);
				List<ModelItem> items = belt.getItemModels();
				for(ModelItem m : items) {
					var cart = new BeltCart(start, translation, Duration.millis((double)belt.getCellTravelTime() - 10.0));
					m.subscribe(cart);
					carts.add(cart);
					getChildren().add(cart);
				}
			} else {
				throw new IllegalArgumentException("not a belt");
			}
		}

}
