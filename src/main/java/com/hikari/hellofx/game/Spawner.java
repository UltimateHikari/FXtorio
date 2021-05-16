package com.hikari.hellofx.game;

import java.lang.reflect.InvocationTargetException;

import com.hikari.hellofx.base.BaseModel;
import com.hikari.hellofx.base.BaseService;
import com.hikari.hellofx.entity.BindingController;
import com.hikari.hellofx.entity.IConnectable;
import com.hikari.hellofx.entity.IConnection;
import com.hikari.hellofx.entity.IServiceNotifier;
import com.hikari.hellofx.entity.ISuspendable;
import com.hikari.hellofx.entity.model.ConnectionInPoint;
import com.hikari.hellofx.entity.model.ConnectionOutPoint;
import com.hikari.hellofx.entity.view.BasicConnectionView;
import com.hikari.hellofx.entity.view.BasicEntityView;
import com.hikari.hellofx.game.classpack.ClassPack;
import com.hikari.hellofx.game.classpack.ConnectionClassPack;
import com.hikari.hellofx.game.classpack.EntityClassPack;
import com.hikari.hellofx.game.view.GameView;

import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class Spawner {
	@Setter
	private static Game game;
	@Setter
	private static GameView view;
	
	private Spawner() {
	}

	// TODO register threads somehow
	public static void spawnConnection(ConnectionOutPoint out, ConnectionInPoint in, ClassPack pack)
			throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException,
			NoSuchMethodException, SecurityException {
		if (pack instanceof ConnectionClassPack cpack) {
			if(out.parentEquals(in)) {
				throw new IllegalArgumentException("points have same parent");
			}
			log.info("spwn " + cpack.toString());

			BaseModel model = cpack.getModel().getDeclaredConstructor(ConnectionOutPoint.class, ConnectionInPoint.class)
					.newInstance(out, in);
			BasicConnectionView spawned = cpack.getView()
					.getDeclaredConstructor(ConnectionOutPoint.class, ConnectionInPoint.class).newInstance(out, in);
			BaseService service = cpack.getService().getDeclaredConstructor(ISuspendable.class)
					.newInstance((ISuspendable) model);

			model.subscribe(spawned);
			model.notifySubs();

			game.addConnection((IConnection) model);
			view.showSpawned(spawned);
			service.start();
			log.info("connected");
		} else {
			throw new IllegalArgumentException("wrong classpack");
		}
	}

	public static void spawnEntity(Double x, Double y, ClassPack pack, GameController gcontroller)
			throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException,
			NoSuchMethodException, SecurityException {
		if (pack instanceof EntityClassPack epack) {
			BaseModel model = epack.getModel().getDeclaredConstructor().newInstance();
			var bController = new BindingController(gcontroller, model);
			BasicEntityView spawned = epack.getView()
					.getDeclaredConstructor(double.class, double.class, BindingController.class)
					.newInstance(x, y, bController);
			BaseService service = epack.getService().getDeclaredConstructor(ISuspendable.class)
					.newInstance((ISuspendable) model);

			model.subscribe(spawned);
			((IServiceNotifier) model).connectService(service);
			view.showSpawned(spawned);
			game.addEntity((IConnectable) model, spawned, service);
			service.start();
		} else {
			throw new IllegalArgumentException("wrong classpack");
		}
	}

}
