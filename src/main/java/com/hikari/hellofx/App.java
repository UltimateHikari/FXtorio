package com.hikari.hellofx;

import java.lang.reflect.*;
import java.util.HashMap;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {
	
	private Model model;
	private Controller controller;
	private HashMap<String, Scene> scenes = new HashMap<String, Scene>();
	private final Integer width = 1280;
	private final Integer height = 720;

	/**
	 * extrafat view without subscription;
	 * with cycle polling instead (?)
	 * ok mb tomorrow i will change to subscribtion + updates model
	 */
	
    @Override
    public void start(@SuppressWarnings("exports") Stage stage) {
    	stage.setTitle("fxtorio");
    	model = new Model();
    	controller = new Controller(model);
    	
    	for(String sceneClassName : model.getSceneClasses()) {
    		try{
    			Class<?> clazz = Class.forName("com.hikari.hellofx." + sceneClassName);
    			GridPane newPane = (GridPane) clazz.getDeclaredConstructor(controller.getClass()).newInstance(controller);
    			scenes.put(sceneClassName, new Scene(newPane, width, height));
    		}catch (Exception e) {
    			// oh no tons of exceptions.. anyway
    			System.out.println("oh no " + e.getMessage());
    		}
    	}
    	
        stage.setScene(getCurrentScene()); 
        stage.show();
    }
    
    private Scene getCurrentScene() {
    	return scenes.get(model.getCurrentScene());
    }

    public static void main(String[] args) {
        launch();
    }

}