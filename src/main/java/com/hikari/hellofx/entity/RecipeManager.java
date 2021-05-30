package com.hikari.hellofx.entity;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.hikari.hellofx.base.BaseModel;
import com.hikari.hellofx.game.control.EntityClassPack;

public class RecipeManager {
	private HashMap<Class<? extends BaseModel>, List<Recipe>> recipes = new HashMap<>();
	private static RecipeManager instance = null;
	private RecipeManager() {
		recipes.put(
				EntityClassPack.MINER.getModel(), 
				Arrays.asList(
						new Recipe(Item.IRON),
						new Recipe(Item.COPPER),
						new Recipe(Item.POLYMER)
						));
		recipes.put(
				EntityClassPack.CONSTRUCTOR.getModel(), 
				Arrays.asList(
						new Recipe(Set.of(Item.IRON), Item.IRON_PLATE),
						new Recipe(Set.of(Item.IRON), Item.IRON_ROD),
						new Recipe(Set.of(Item.IRON_ROD), Item.SCREW),
						new Recipe(Set.of(Item.COPPER), Item.WIRE)	
						));
		recipes.put(EntityClassPack.ASSEMBLER.getModel(),
				Arrays.asList(
						new Recipe(Set.of(Item.IRON_ROD, Item.POLYMER), Item.SCREW),
						new Recipe(Set.of(Item.WIRE, Item.POLYMER), Item.CABLE),
						new Recipe(Set.of(Item.SCREW, Item.IRON_PLATE), Item.FRAME)
						));
	}
	public static RecipeManager instance() {
		if(instance == null) {
			instance = new RecipeManager();
		}
		return instance;
	}
	public List<Recipe> getRecipeList(Class<? extends IProducer> producer) {
		return recipes.get(producer);
	}
	
	public List<Item> getAllPossibleProducables(Class<? extends IProducer> producer){
		return getRecipeList(producer).stream().map(Recipe::produce).collect(Collectors.toList());
	}
}
