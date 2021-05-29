package com.hikari.hellofx.entity.view;

import java.util.List;
import java.util.stream.Collectors;

import com.hikari.hellofx.entity.BindingController;
import com.hikari.hellofx.entity.IConnectable;
import com.hikari.hellofx.entity.Item;
import com.hikari.hellofx.entity.model.StorageModel;

import javafx.scene.paint.Color;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class StorageView extends BasicEntityView {
	private static final Color STORAGECOLOR = Color.AQUAMARINE;

	public StorageView(double x, double y, BindingController controller) {
		super(x, y, controller, STORAGECOLOR);
	}

	public static Color getColor() {
		return STORAGECOLOR;
	}

	@Override
	protected List<Slice> generateSlices(IConnectable model) {
		var storage = ((StorageModel) model).getStorage();
		var storageSize = ((StorageModel) model).getStorageSize();
		List<Slice> list =  storage.keySet().stream()
				.map(s -> new Slice(((double) storage.get(s)) / ((double) storageSize), Item.valueOf(s).getColor()))
				.collect(Collectors.toList());
		log.info(list);
		return list;
	}
}
