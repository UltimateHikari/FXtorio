package com.hikari.hellofx;

import java.util.ArrayList;
import java.util.function.Consumer;

import com.hikari.hellofx.Base.BaseModel;
import com.hikari.hellofx.Entities.ConstructorModel;


	/* TODO: decompose GameScene's model logic to here
	 * need time to comprehend
	 */
public class Game extends BaseModel{
	private final ArrayList<ConstructorModel> entityModels = new ArrayList<ConstructorModel>();
	GameFieldModel gameFieldModel = new GameFieldModel();
	
	public GameFieldModel getField() {
		return gameFieldModel;
	}

	public void addEntity(ConstructorModel model) {
		entityModels.add(model);
	}
	
	public void forEachEntity(Consumer<ConstructorModel> f) {
		entityModels.stream().forEach(f);
	}
}
