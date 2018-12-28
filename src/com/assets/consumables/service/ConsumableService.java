package com.assets.consumables.service;
import java.io.Serializable;

import org.qihuasoft.core.common.service.CommonService;

import com.assets.consumables.entity.ConsumableEntity;

public interface ConsumableService extends CommonService{
	
 	public void delete(ConsumableEntity entity) throws Exception;
 	
 	public Serializable save(ConsumableEntity entity) throws Exception;
 	
 	public void saveOrUpdate(ConsumableEntity entity) throws Exception;
 	
}
