package com.hikari.hellofx.Views;

import com.hikari.hellofx.BindingController;
import com.hikari.hellofx.GameAction;
import com.hikari.hellofx.Base.BaseModel;
import com.hikari.hellofx.Base.IModelSubscriber;
import com.hikari.hellofx.Entities.ConstructorModel;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class ConstructorView extends Rectangle implements IModelSubscriber{
	private final Paint colorOn = Color.BLUE;
	private final Paint colorOff = Color.RED;
	
	public ConstructorView(Double x, Double y, BindingController controller) {
		super(x,y,40,40);
		System.out.println(x + " " + y);
		setFill(colorOff);
		setOnMouseClicked((event) -> controller.handleClick(event, GameAction.INFO));
	}

	@Override
	public void ModelChanged(BaseModel model) {
//		if(!(model instanceof ConstructorModel)) {
//			throw new IllegalArgumentException();
//		}
		if(((ConstructorModel)model).getState()){
			setFill(colorOn);
		}else {
			setFill(colorOff);
		}
		
	}
	
}
