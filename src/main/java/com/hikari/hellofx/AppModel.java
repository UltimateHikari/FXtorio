package com.hikari.hellofx;

import java.util.ArrayList;
import java.util.Arrays;

import com.hikari.hellofx.base.BaseModel;
import com.hikari.hellofx.game.view.GameView;

public class AppModel extends BaseModel {
	private final ArrayList<String> sceneClasses;
	// private final Game game = new Game();
	private String currentSceneName = MenuView.class.getName();

	public AppModel() {
		sceneClasses = new ArrayList<String>(
				Arrays.asList(GameView.class.getName(), MenuView.class.getName()/* , "LoadScene", "SaveScene" */
				));
	}

	public String getCurrentScene() {
		return currentSceneName;
	}

	public ArrayList<String> getSceneClasses() {
		return sceneClasses;
	}

	public void setCurrentScene(String nextSceneName) throws Exception {
		if (!sceneClasses.contains(nextSceneName)) {
			throw new Exception("badNextSceneName: " + nextSceneName);
		}
		currentSceneName = nextSceneName;
		super.notifySubs();
	}

}
