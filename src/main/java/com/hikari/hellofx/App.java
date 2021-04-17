package com.hikari.hellofx;

import java.util.HashMap;

import com.hikari.hellofx.Base.BaseModel;
import com.hikari.hellofx.Base.IModelSubscriber;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application implements IModelSubscriber{
	
	private Stage stage;
	private AppModel appModel = new AppModel();
	private SceneController sceneController = new SceneController(appModel);
	private HashMap<String, Scene> scenes = new HashMap<String, Scene>();
	private final Integer width = 1280;
	private final Integer height = 720;
	
    @Override
    public void start(Stage stage_) throws Exception {
    	stage = stage_;
    	stage.setTitle("fxtorio");
    	appModel.subscribe(this);
    	
    	try {
    		prepareScenes();
    	}catch(Exception e) {
    		System.out.println("Failed to get instance of " + e.getMessage());
    		this.stop();
    	}
    	
        ModelChanged(appModel); 
        stage.show();
    }
  
    /*
     * Mandatory pairs
     * Model + View
     * Name + NameView
     */
    
    private void prepareScenes() throws Exception {
    	for(String sceneClassName : appModel.getSceneClasses()) {
			Class<?> clazz = Class.forName(sceneClassName);
			GridPane newPane = (GridPane) clazz.getDeclaredConstructor(sceneController.getClass()).newInstance(sceneController);
			scenes.put(sceneClassName, new Scene(newPane, width, height));
    	}
    }

    public static void main(String[] args) {
        launch();
    }

	@Override
	public void ModelChanged(BaseModel model) {
//		if(!(model instanceof AppModel)) {
//			throw new IllegalArgumentException("wrong appmodel");
//		}
		stage.setScene(scenes.get(appModel.getCurrentScene()));
	}

}