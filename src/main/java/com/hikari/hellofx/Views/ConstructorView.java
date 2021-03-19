package com.hikari.hellofx.Views;

import com.hikari.hellofx.BindingController;
import com.hikari.hellofx.GameAction;
import com.hikari.hellofx.Base.BaseModel;
import com.hikari.hellofx.Base.IModelSubscriber;
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

	
	public ConstructorView(Double x, Double y, BindingController controller) {
		field = new Rectangle(0, 0, SIZE, SIZE);
		setLayoutX(x - SIZE/2);
		setLayoutY(y - SIZE/2);

		setPrefSize(SIZE,SIZE);
		//System.out.println(x + " " + y);
		field.setFill(colorOff);
		label = new Text("off");
		getChildren().add(field);
		getChildren().add(label);
		setOnMouseClicked((event) -> controller.handleClick(event, GameAction.INFO));
	}

	@Override
	public void ModelChanged(BaseModel model) {
//		if(!(model instanceof ConstructorModel)) {
//			throw new IllegalArgumentException();
//		}
		if(((ConstructorModel)model).getState()){
			label.setText("on");
			field.setFill(colorOn);
		}else {
			label.setText("off");
			field.setFill(colorOff);
		}
		
	}
	
}
