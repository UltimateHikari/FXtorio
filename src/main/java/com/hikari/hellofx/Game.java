package com.hikari.hellofx;

import java.util.Collection;
import java.util.concurrent.CopyOnWriteArrayList;

import com.hikari.hellofx.Base.BaseModel;
import com.hikari.hellofx.Entities.ConstructorModel;


	/* TODO: decompose GameScene's model logic to here
	 * need time to comprehend
	 */
public class Game extends BaseModel{
	private final Collection<ConstructorModel> entityModels = new CopyOnWriteArrayList<ConstructorModel>();
	GameFieldModel gameFieldModel = new GameFieldModel();
	
	public GameFieldModel getField() {
		return gameFieldModel;
	}

	public void addEntity(ConstructorModel model) {
		entityModels.add(model);
	}
}
