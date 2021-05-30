package com.hikari.hellofx.game.classpack;

import com.hikari.hellofx.base.BaseModel;
import com.hikari.hellofx.base.BaseService;
import com.hikari.hellofx.entity.view.BasicEntityView;
import com.hikari.hellofx.entity.view.AssemblerView;
import com.hikari.hellofx.entity.view.ConstructorView;
import com.hikari.hellofx.entity.view.MinerView;
import com.hikari.hellofx.entity.view.SplitterView;
import com.hikari.hellofx.entity.view.StorageView;
import com.hikari.hellofx.entity.model.AssemblerModel;
import com.hikari.hellofx.entity.model.ConstructorModel;
import com.hikari.hellofx.entity.model.MinerModel;
import com.hikari.hellofx.entity.model.SplitterModel;
import com.hikari.hellofx.entity.model.StorageModel;
import com.hikari.hellofx.entity.service.AssemblerService;
import com.hikari.hellofx.entity.service.ConstructorService;
import com.hikari.hellofx.entity.service.MinerService;
import com.hikari.hellofx.entity.service.SplitterService;
import com.hikari.hellofx.entity.service.StorageService;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum EntityClassPack implements ClassPack{
	ASSEMBLER(AssemblerModel.class, AssemblerView.class, AssemblerService.class),
	CONSTRUCTOR(ConstructorModel.class, ConstructorView.class, ConstructorService.class),
	MINER(MinerModel.class, MinerView.class, MinerService.class),
	SPLITTER(SplitterModel.class, SplitterView.class, SplitterService.class),
	STORAGE(StorageModel.class, StorageView.class, StorageService.class);
	
	@Getter
	private Class<? extends BaseModel> model;
	@Getter
	private Class<? extends BasicEntityView> view;
	@Getter
	private Class<? extends BaseService> service;
}
