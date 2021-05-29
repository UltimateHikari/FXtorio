package com.hikari.hellofx;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class SceneController {
	private final AppModel model;
	
	public SceneController(AppModel model) {
		this.model = model;
	}
	
	public void changeCurrentScene(SceneClass nextSceneName){
		try {
			model.setCurrentScene(nextSceneName);
		} catch (Exception e) {
			log.error("Error changing scene: ", e);
		}
	}
}
