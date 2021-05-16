package com.hikari.hellofx.game.control;

import com.hikari.hellofx.base.BaseModel;
import com.hikari.hellofx.base.BaseService;
import com.hikari.hellofx.entity.model.conveyor.Conveyor;
import com.hikari.hellofx.entity.view.conveyor.ConveyorView;
import com.hikari.hellofx.entity.service.ConveyorService;
import com.hikari.hellofx.entity.model.belt.Belt;
import com.hikari.hellofx.entity.view.belt.BeltView;
import com.hikari.hellofx.entity.service.BeltService;
import com.hikari.hellofx.entity.view.BasicConnectionView;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum ConnectionClassPack implements ClassPack {
	BELT(Belt.class, BeltView.class, BeltService.class),
	CONVEYOR(Conveyor.class, ConveyorView.class, ConveyorService.class);

	@Getter
	private Class<? extends BaseModel> model;
	@Getter
	private Class<? extends BasicConnectionView> view;
	@Getter
	private Class<? extends BaseService> service;
}
