package com.hikari.hellofx;

import java.util.ArrayList;
import java.util.Arrays;

import com.hikari.hellofx.Base.BaseModel;

public class AppModel extends BaseModel{
	private final ArrayList<String> sceneClasses;
	//private final Game game = new Game();
	private String currentSceneName = "MenuView";
	
	public AppModel(){
		sceneClasses = new ArrayList<String>(Arrays.asList(
				"GameView", "MenuView"/*, "LoadScene", "SaveScene"*/
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
		super.notifySubs();
	}
	
}
