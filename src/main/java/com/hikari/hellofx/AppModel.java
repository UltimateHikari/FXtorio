package com.hikari.hellofx;


import com.hikari.hellofx.base.BaseModel;

public class AppModel extends BaseModel {
	private boolean isStopped = false;
	private SceneClass currentSceneName = SceneClass.MENU;

	public SceneClass getCurrentScene() {
		return currentSceneName;
	}

	public void setCurrentScene(SceneClass nextSceneName) {
		currentSceneName = nextSceneName;
		super.notifySubs();
	}
	
	public boolean isStopped() {
		return isStopped;
	}
	
	public void stop() {
		isStopped = true;
	}

}
