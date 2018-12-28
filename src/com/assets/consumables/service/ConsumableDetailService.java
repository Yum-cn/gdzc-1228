package com.assets.consumables.service;
import java.io.Serializable;

import org.qihuasoft.core.common.service.CommonService;

import com.assets.consumables.entity.ConsumableDetailEntity;

public interface ConsumableDetailService extends CommonService{
	
 	public void delete(ConsumableDetailEntity entity) throws Exception;
 	
 	public Serializable save(ConsumableDetailEntity entity) throws Exception;
 	
 	public void saveOrUpdate(ConsumableDetailEntity entity) throws Exception;
 	
}
