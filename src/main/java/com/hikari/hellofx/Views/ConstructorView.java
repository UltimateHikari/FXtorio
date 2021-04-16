package com.hikari.hellofx.Views;

import java.util.ArrayList;

import com.hikari.hellofx.BindingController;
import com.hikari.hellofx.GameAction;
import com.hikari.hellofx.Base.BaseModel;
import com.hikari.hellofx.Base.IModelSubscriber;
import com.hikari.hellofx.Entities.ConnectableState;
import com.hikari.hellofx.Entities.ConnectionPoint;
import com.hikari.hellofx.Entities.ConstructorModel;

import javafx.scene.layout.StackPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class ConstructorView extends Pane implements IModelSubscriber{
	private class ShapePane extends StackPane{
		private final Rectangle field;
		private final Text label;
		
		public ShapePane(Rectangle field_, Text label_) {
			field = field_;
			label = label_;
			getChildren().add(field);
			getChildren().add(label);
			setPrefSize(SIZE,SIZE);
		}
		public Rectangle getField() {
			return field;
		}
		public Text getLabel() {
			return label;
		}
		
	}
	private final ShapePane shapePane;
	private final Paint colorOn = Color.BLUE;
	private final Paint colorOff = Color.RED;
	private final static int SIZE = 40;
	private final int defaultChildrenSize = 1;
	private final BindingController bController;

	
	public ConstructorView(Double x, Double y, BindingController controller) {
		bController = controller;
		Rectangle field = new Rectangle(0, 0, SIZE, SIZE);
		setLayoutX(x - SIZE/2);
		setLayoutY(y - SIZE/2);

		//System.out.println(x + " " + y);
		field.setFill(colorOff);
		Text label = new Text("off");
		
		shapePane = new ShapePane(field, label);
		shapePane.setOnMouseClicked((event) -> bController.handleClick(event, GameAction.INFO));
		placeDefaultChildren();
	}

	@Override
	public void ModelChanged(BaseModel model) {
//		if(!(model instanceof ConstructorModel)) {
//			throw new IllegalArgumentException();
//		}
		ConstructorModel cModel =(ConstructorModel)model;
		checkPower(cModel);
		showPoints(cModel);
	}
	
	private void showPoints(ConstructorModel cModel) {
		ConnectableState state = cModel.getConnectableState();
		switch(state) {
			case NO_POINTS :
				clearChildren();
				break;
			case IN_POINTS :
				clearChildren();
				showPoints(cModel.getInPoints());
				break;
			case OUT_POINTS :
				clearChildren();
				showPoints(cModel.getOutPoints());
				break;
		}
	}

	private void clearChildren() {
		if(getChildren().size() == defaultChildrenSize ) {
			return;
		}
		getChildren().stream()
			.filter(w -> w instanceof ConnectionPointView)
			.forEach(w -> ((ConnectionPointView)w).unsubscribe());
		getChildren().clear();
		placeDefaultChildren();
	}
	
	private void showPoints(ArrayList<? extends ConnectionPoint> points) {
		for( ConnectionPoint p : points) {
			getChildren().add(new ConnectionPointView(p, SIZE, bController, getLayoutX(), getLayoutY()));
		}
	}	
	
	private void placeDefaultChildren() {
		getChildren().add(shapePane);
	}

	private void checkPower(ConstructorModel model) {
		if(model.isOn()){
			shapePane.getLabel().setText("on");
			shapePane.getField().setFill(colorOn);
		}else {
			shapePane.getLabel().setText("off");
			shapePane.getField().setFill(colorOff);
		}
	}
	
}
