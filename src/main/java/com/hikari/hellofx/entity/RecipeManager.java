package com.hikari.hellofx.entity;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import com.hikari.hellofx.base.BaseModel;
import com.hikari.hellofx.game.classpack.EntityClassPack;

public class RecipeManager {
	private HashMap<Class<? extends BaseModel>, List<Recipe>> recipes = new HashMap<>();
	private static RecipeManager instance = null;
	private RecipeManager() {
		recipes.put(
				EntityClassPack.MINER.getModel(), 
				Arrays.asList(
						new Recipe(Items.IRON),
						new Recipe(Items.COPPER)
						));
		recipes.put(
				EntityClassPack.CONSTRUCTOR.getModel(), 
				Arrays.asList(
						new Recipe(Set.of(Items.IRON), Items.IRON_PLATE),
						new Recipe(Set.of(Items.IRON), Items.IRON_ROD),
						new Recipe(Set.of(Items.IRON_ROD), Items.SCREW),
						new Recipe(Set.of(Items.COPPER), Items.WIRE)	
						));
	}
	public static RecipeManager instance() {
		if(instance == null) {
			instance = new RecipeManager();
		}
		return instance;
	}
	public List<Recipe> getRecipeList(Class<? extends IConnectable> producer) {
		return null;
	}
}