package com.hikari.hellofx.Entities.Connectable.Basic;

import java.util.ArrayList;

import com.hikari.hellofx.Base.BaseModel;
import com.hikari.hellofx.Base.IModelSubscriber;
import com.hikari.hellofx.Entities.BindingController;
import com.hikari.hellofx.Entities.Connectable.ConnectableState;
import com.hikari.hellofx.Entities.Connectable.IConnectable;
import com.hikari.hellofx.Entities.Connectable.ISuspendable;
import com.hikari.hellofx.Entities.ConnectionPoint.ConnectionPoint;
import com.hikari.hellofx.Entities.ConnectionPoint.ConnectionPointView;
import com.hikari.hellofx.Game.GameAction;

import javafx.application.Platform;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class BasicEntityView extends Pane implements IModelSubscriber {
	private final ShapePane shapePane;
	private final static int SIZE = 40;
	private final int DEFAULT_CHILDREN_SIZE = 1;
	private final BindingController bController;

	public BasicEntityView(double x, double y, BindingController controller, Color color) {
		bController = controller;
		setLayoutX(x - SIZE / 2);
		setLayoutY(y - SIZE / 2);
		setPrefSize(SIZE, SIZE);

		shapePane = new ShapePane(SIZE, color);
		shapePane.setOnMouseClicked((event) -> bController.handleClick(event, GameAction.INFO));
		placeDefaultChildren();
	}

	@Override
	public void ModelChanged(BaseModel model) {
//		TODO if(!(model instanceof ConstructorModel)) {
		String labelText = checkPower((ISuspendable) model) + 
				" " + 
				checkFill((IConnectable) model);
		Platform.runLater(
			() -> { 
			shapePane.getLabel().setText(labelText);
			showPoints((IConnectable) model);
			}
		);
	}

	private void showPoints(IConnectable cModel) {
		ConnectableState state = cModel.getConnectableState();
		switch (state) {
		case NO_POINTS:
			clearChildren();
			break;
		case IN_POINTS:
			clearChildren();
			showPoints(cModel.getInPoints());
			break;
		case OUT_POINTS:
			clearChildren();
			showPoints(cModel.getOutPoints());
			break;
		}
	}

	private void clearChildren() {
		if (getChildren().size() == DEFAULT_CHILDREN_SIZE) {
			return;
		}
		getChildren().stream().filter(w -> w instanceof ConnectionPointView)
				.forEach(w -> ((ConnectionPointView) w).unsubscribe());
		getChildren().clear();
		placeDefaultChildren();
	}

	private void showPoints(ArrayList<? extends ConnectionPoint> points) {
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