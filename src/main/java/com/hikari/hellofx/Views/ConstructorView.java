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
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class ConstructorView extends StackPane implements IModelSubscriber{
	private final Paint colorOn = Color.BLUE;
	private final Paint colorOff = Color.RED;
	private final static int SIZE = 40;
	private final Rectangle field;
	private final Text label;
	private final int defaultChildrenSize = 2;
	private final BindingController bController;

	
	public ConstructorView(Double x, Double y, BindingController controller) {
		bController = controller;
		field = new Rectangle(0, 0, SIZE, SIZE);
		setLayoutX(x - SIZE/2);
		setLayoutY(y - SIZE/2);

		setPrefSize(SIZE,SIZE);
		//System.out.println(x + " " + y);
		field.setFill(colorOff);
		label = new Text("off");
		placeDefaultChildren();
		setOnMouseClicked((event) -> bController.handleClick(event, GameAction.INFO));
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
				showPoints(cModel.getInPoints());
				break;
			case OUT_POINTS :
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
		getChildren().removeAll();
		placeDefaultChildren();
	}
	
	private void showPoints(ArrayList<? extends ConnectionPoint> points) {
		for( ConnectionPoint p : points) {
			getChildren().add(new ConnectionPointView(p, SIZE, bController));
		}
	}	
	
	private void placeDefaultChildren() {
		getChildren().add(field);
		getChildren().add(label);
	}

	private void checkPower(ConstructorModel model) {
		if(model.getState()){
			label.setText("on");
			field.setFill(colorOn);
		}else {
			label.setText("off");
			field.setFill(colorOff);
		}
	}
	
}
