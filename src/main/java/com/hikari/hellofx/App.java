package com.hikari.hellofx;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

import com.hikari.hellofx.base.BaseModel;
import com.hikari.hellofx.base.IModelSubscriber;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import lombok.extern.log4j.Log4j2;

/**
 * JavaFX App
 */
@Log4j2
public class App extends Application implements IModelSubscriber {

	private Stage stage;
	private final AppModel appModel = new AppModel();
	private final SceneController sceneController = new SceneController(appModel);
	private final HashMap<SceneClass, Scene> scenes = new HashMap<>();
	private static final Integer WIDTH = 1600;
	private static final Integer HEIGHT = 900;
	private static final int STOP_ERROR_CODE = -1;

	@Override
	public void start(Stage stage) throws Exception {
		this.stage = stage;
		stage.setTitle("fxtorio");
		appModel.subscribe(this);

		try {
			prepareScenes();
		} catch (Exception e) {
			log.error("Failed to get instance of " + e.getMessage());
			this.stop();
		}

		modelChanged(appModel);
		stage.show();
	}

	/*
	 * Mandatory pairs Model + View Name + NameView
	 */

	private void prepareScenes() throws InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		for (SceneClass s : SceneClass.values()) {
			GridPane newPane = s.getSceneClass().getDeclaredConstructor(SceneController.class)
					.newInstance(sceneController);
			scenes.put(s, new Scene(newPane, WIDTH, HEIGHT));
		}
	}

	public static void main(String[] args) {
		launch();
	}

	@Override
	public void modelChanged(BaseModel model) {
		if (model instanceof AppModel amodel) {
			if(amodel.isStopped()) {
				try {
					stop();
				} catch (Exception e) {
					//dont want to propagate  non-informative "throws exception" everywhere
					//just for exiting program in the end anyways
					log.error("error while stopping", e);
					System.exit(STOP_ERROR_CODE);
				}
			}
			stage.setScene(scenes.get(appModel.getCurrentScene()));
		} else {
			throw new IllegalArgumentException("wrong appmodel");
		}
	}

}