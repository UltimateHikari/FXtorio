package com.hikari.hellofx.entity.view;

import java.util.List;

import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import lombok.extern.log4j.Log4j2;

@Log4j2
class ShapePane extends StackPane {
	private final Rectangle field;
	private final Text label;
	private double size;

	public ShapePane(double size, Color color) {
		this(size, "off", color);
	}

	public ShapePane(double size, String text, Color color) {
		this.size = size;
		field = new Rectangle(0, 0, size, size);
		label = new Text(text);
		field.setFill(color);
		field.setStroke(color.darker());
		getChildren().add(field);
		getChildren().add(label);
		setPrefSize(size, size);
	}

	public Rectangle getField() {
		return field;
	}

	public Text getLabel() {
		return label;
	}

	public void renderSlices(List<Slice> slices) {
		//elsewhere generateSlices not defined
		//TODO add caching and re-usage here, wildly inefficient yet fancy
		if(!slices.isEmpty()) {
			var offset = 0.0;
			var pane  = new Pane();
			getChildren().clear();
			getChildren().add(field);
			getChildren().add(pane);
			for (Slice i : slices) {
				var y = size*(i.getHeight());
				log.info(offset + y);
				var slice = new Rectangle(0, offset, size, y);
				slice.setFill(i.getColor());
				pane.getChildren().add(slice);
				offset += y;
			}
			getChildren().add(label);
		}else {
			log.info("omitting slices render");
		}
	}

}
