package com.hikari.hellofx;

import java.util.ArrayList;
import java.util.function.Consumer;

import com.hikari.hellofx.Base.BaseModel;
import com.hikari.hellofx.Entities.ConstructorModel;
import com.hikari.hellofx.Entities.Conveyor;
import com.hikari.hellofx.Entities.IConnectable;


	/* TODO: decompose GameScene's model logic to here
	 * need time to comprehend
	 */
public class Game extends BaseModel{
	private final ArrayList<IConnectable> entityModels = new ArrayList<IConnectable>();
	private final ArrayList<Conveyor> connections = new ArrayList<Conveyor>();
	GameFieldModel gameFieldModel = new GameFieldModel();
	
	public GameFieldModel getField() {
		return gameFieldModel;
	}

	public void addEntity(IConnectable model) {
		entityModels.add(model);
	}
	
	public void addConnection(Conveyor connection) {
		connections.add(connection);
	}
	
	public void forEachEntity(Consumer<IConnectable> f) {
		entityModels.stream().forEach(f);
	}
}
