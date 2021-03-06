package com.hikari.hellofx.entity.view;

import java.util.List;

import com.hikari.hellofx.base.BaseModel;
import com.hikari.hellofx.base.IModelSubscriber;
import com.hikari.hellofx.entity.BindingController;
import com.hikari.hellofx.entity.IConnectable;
import com.hikari.hellofx.entity.ISuspendable;
import com.hikari.hellofx.entity.model.ConnectionPoint;
import com.hikari.hellofx.game.GameAction;

import javafx.application.Platform;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class BasicEntityView extends Pane implements IModelSubscriber {
	private final ShapePane shapePane;
	private static final double SIZE = 40;
	private static final int DEFAULT_CHILDREN_SIZE = 1;
	private final BindingController bController;

	public BasicEntityView(double x, double y, BindingController controller, Color color) {
		bController = controller;
		setLayoutX(x - SIZE / 2);
		setLayoutY(y - SIZE / 2);
		setPrefSize(SIZE, SIZE);

		shapePane = new ShapePane(SIZE, color);
		shapePane.setOnMouseClicked(event -> bController.handleClick(event, GameAction.INFO));
		placeDefaultChildren();
	}

	@Override
	public void modelChanged(BaseModel model) {
		if (model instanceof IConnectable cModel) {
			String labelText = checkPower(cModel) + " " + checkFill(cModel);
			Platform.runLater(() -> {
				shapePane.getLabel().setText(labelText);
				showPoints(cModel);
			});
		}
	}

	private void showPoints(IConnectable cModel) {
		var state = cModel.getConnectableState();
		clearChildren();
		switch (state) {
		case IN_POINTS:
			showPoints(cModel.getInPoints());
			break;
		case OUT_POINTS:
			showPoints(cModel.getOutPoints());
			break;
		case NO_POINTS:
			break;
		}
	}

	private void clearChildren() {
		if (getChildren().size() == DEFAULT_CHILDREN_SIZE) {
			return;
		}
		getChildren().stream().filter(ConnectionPointView.class::isInstance)
				.forEach(w -> ((ConnectionPointView) w).unsubscribe());
		getChildren().clear();
		placeDefaultChildren();
	}

	private void showPoints(List<? extends ConnectionPoint> points) {
		for (ConnectionPoint p : points) {
			getChildren().add(new ConnectionPointView(p, SIZE, bController, getLayoutX(), getLayoutY()));
		}
	}

	private void placeDefaultChildren() {
		getChildren().add(shapePane);
	}

	private String checkPower(ISuspendable model) {
		if (model.isOn()) {
			return "on";
		}
		return "off";
	}

	private String checkFill(IConnectable model) {
		return model.getFillCount().toString();
	}
}
