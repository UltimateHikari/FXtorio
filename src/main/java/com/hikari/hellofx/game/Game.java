package com.hikari.hellofx.game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.hikari.hellofx.base.BaseModel;
import com.hikari.hellofx.base.BaseService;
import com.hikari.hellofx.entity.IConnectable;
import com.hikari.hellofx.entity.IConnection;
import com.hikari.hellofx.entity.model.cpoint.ConnectionInPoint;
import com.hikari.hellofx.entity.model.cpoint.ConnectionOutPoint;
import com.hikari.hellofx.entity.model.cpoint.ConnectionPoint;
import com.hikari.hellofx.entity.view.BasicConnectionView;
import com.hikari.hellofx.entity.view.BasicEntityView;

public class Game extends BaseModel {
	private final List<IConnectable> entityModels = new ArrayList<>();
	private final Map<IConnectable, BasicEntityView> entityViews = new HashMap<>();
	private final Map<IConnectable, BaseService> entityServices = new HashMap<>();
	private final List<IConnection> connections = new ArrayList<>();
	private final Map<IConnection, BasicConnectionView> connectionViews = new HashMap<>();
	private final Map<IConnection, BaseService> connectionServices = new HashMap<>();
	GameFieldModel gameFieldModel = new GameFieldModel();

	public GameFieldModel getField() {
		return gameFieldModel;
	}

	public void addEntity(IConnectable model, BasicEntityView view, BaseService service) {
		entityModels.add(model);
		entityViews.put(model, view);
		entityServices.put(model, service);
	}

	public List<BasicConnectionView> removeConnections(IConnectable model) {
		//look at this monstrocity x)
		List<BasicConnectionView> res = new ArrayList<>();
		List<IConnection> modelCollections = Stream.concat(
				model.filterNonFreePoints(ConnectionInPoint.class).stream().map(ConnectionPoint::getConnection), 
				model.filterNonFreePoints(ConnectionOutPoint.class).stream().map(ConnectionPoint::getConnection))
				.collect(Collectors.toList());
		for(IConnection i : modelCollections) {
			i.markDetached();
			//TODO leaving trail of stopped connection refs
			connectionServices.get(i).safeStop();
			res.add(connectionViews.remove(i));
		}
		return res;
	}

	public BasicEntityView removeEntity(IConnectable model) {
		entityServices.get(model).safeStop();
		entityServices.remove(model);
		var view = entityViews.remove(model);
		entityModels.remove(model);
		return view;
	}

	public void addConnection(IConnection connection, BasicConnectionView view, BaseService service) {
		connections.add(connection);
		connectionViews.put(connection, view);
		connectionServices.put(connection, service);
	}

	public void removeAll() {
		forEachEntity(this::removeConnections);
		for(IConnectable i : entityModels) {
			entityServices.get(i).safeStop();
			entityServices.remove(i);
		}
	}
	
	public void forEachEntity(Consumer<IConnectable> f) {
			entityModels.stream().forEach(f);
	}

}
