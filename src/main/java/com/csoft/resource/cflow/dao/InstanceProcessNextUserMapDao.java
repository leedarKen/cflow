package com.csoft.resource.cflow.dao;

import com.csoft.resource.cflow.pojo.InstanceProcessNextUserMap;

public interface InstanceProcessNextUserMapDao extends GenericDao<InstanceProcessNextUserMap,Integer>{
	void deleteProcessExecutorUser(Integer processInstanceId);
}
