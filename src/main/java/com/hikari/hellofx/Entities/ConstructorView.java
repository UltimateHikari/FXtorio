package com.hikari.hellofx.Entities;

import com.hikari.hellofx.BindingController;
import com.hikari.hellofx.GameController;
import com.hikari.hellofx.Base.BaseModel;
import com.hikari.hellofx.Base.IModelSubscriber;

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
		setOnMouseClicked((event) -> System.out.println("Constructing lol"));
		setOnMouseClicked((event) -> controller.handleClick());
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
