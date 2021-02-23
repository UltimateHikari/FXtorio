package com.hikari.hellofx;

import java.util.ArrayList;
import java.util.Arrays;

public class Model {
	private final ArrayList<String> sceneClasses;
	private String currentSceneName = "MenuScene";
	
	public Model(){
		sceneClasses = new ArrayList<String>(Arrays.asList(
				"GameScene", "MenuScene"/*, "LoadScene", "SaveScene"*/
				));
	}
	
	public String getCurrentScene() {
		return currentSceneName;
	}
	
	public ArrayList<String> getSceneClasses() {
		return sceneClasses;
	}

	public void setCurrentScene(String nextSceneName) throws Exception {
		if(sceneClasses.indexOf(nextSceneName) == -1) {
			throw new Exception("badNextSceneName");
		}
		currentSceneName = nextSceneName;
	}
}
