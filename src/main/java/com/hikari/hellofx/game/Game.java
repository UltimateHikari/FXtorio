package com.hikari.hellofx.game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import com.hikari.hellofx.base.BaseModel;
import com.hikari.hellofx.base.BaseService;
import com.hikari.hellofx.entity.IConnectable;
import com.hikari.hellofx.entity.IConnection;
import com.hikari.hellofx.entity.view.BasicEntityView;


public class Game extends BaseModel{
	private final List<IConnectable> entityModels = new ArrayList<>();
	private final Map<IConnectable, BasicEntityView> entityViews = new HashMap<>();
	private final Map<IConnectable, BaseService> entityServices = new HashMap<>();
	private final List<IConnection> connections = new ArrayList<>();
	GameFieldModel gameFieldModel = new GameFieldModel();
	
	public GameFieldModel getField() {
		return gameFieldModel;
	}

	public void addEntity(IConnectable model, BasicEntityView view, BaseService service) {
		entityModels.add(model);
		entityViews.put(model, view);
		entityServices.put(model, service);
	}
	
	public BasicEntityView removeEntity(IConnectable model) {
		//TODO rework to proper interrupt and stop-the-world
		entityServices.get(model).interrupt();
		entityServices.remove(model);
		var view = entityViews.get(model);
		entityViews.remove(model);
		entityModels.remove(model);
		return view;
	}
		
	public void addConnection(IConnection connection) {
		connections.add(connection);
	}
	
	public void forEachEntity(Consumer<IConnectable> f) {
		entityModels.stream().forEach(f);
	}
}
