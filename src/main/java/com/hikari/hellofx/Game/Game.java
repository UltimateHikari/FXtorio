package com.hikari.hellofx.Game;

import java.util.ArrayList;
import java.util.function.Consumer;

import com.hikari.hellofx.Base.BaseModel;
import com.hikari.hellofx.Entities.Connectable.IConnectable;
import com.hikari.hellofx.Entities.Connection.IConnection;


	/* TODO: decompose GameScene's model logic to here
	 * need time to comprehend
	 */
public class Game extends BaseModel{
	private final ArrayList<IConnectable> entityModels = new ArrayList<IConnectable>();
	private final ArrayList<IConnection> connections = new ArrayList<IConnection>();
	GameFieldModel gameFieldModel = new GameFieldModel();
	
	public GameFieldModel getField() {
		return gameFieldModel;
	}

	public void addEntity(IConnectable model) {
		entityModels.add(model);
	}
	
	public void addConnection(IConnection connection) {
		connections.add(connection);
	}
	
	public void forEachEntity(Consumer<IConnectable> f) {
		entityModels.stream().forEach(f);
	}
}
