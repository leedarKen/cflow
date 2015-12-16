package com.csoft.resource.cflow.service.inner.entry.service;

import com.csoft.resource.cflow.pojo.InstanceProcessNextUserMap;

public interface InstanceProcessNextUserMapManager extends GenericManager<InstanceProcessNextUserMap,Integer>{
	void deleteProcessExecutorUser(Integer processInstanceId);
}
