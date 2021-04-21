package com.hikari.hellofx;

public class SceneController {
	private final AppModel model;
	
	public SceneController(AppModel model_) {
		model = model_;
	}
	
	public void changeCurrentScene(String nextSceneName){
		try {
			model.setCurrentScene(nextSceneName);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error changing scene: " + e.getMessage());
		}
	}
}
