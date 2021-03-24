package com.hikari.hellofx.Views;

import com.hikari.hellofx.Base.BaseModel;
import com.hikari.hellofx.Base.IModelSubscriber;
import com.hikari.hellofx.Entities.ConnectionInPoint;
import com.hikari.hellofx.Entities.ConnectionOutPoint;

import javafx.scene.shape.Line;

public class ConveyorView extends Line implements IModelSubscriber{

	public ConveyorView(ConnectionOutPoint out, ConnectionInPoint in) {
		super(out.getLastViewX(), out.getLastViewY(), in.getLastViewX(), in.getLastViewY());
	}
	
	@Override
	public void ModelChanged(BaseModel model) {
		// TODO Auto-generated method stub
		
	}

}
