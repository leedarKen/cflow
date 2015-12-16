package com.csoft.resource.cflow.dao;

import com.csoft.resource.cflow.pojo.InstanceInputValue;

public interface InstanceInputValueDao extends GenericDao<InstanceInputValue, Integer>{
	InstanceInputValue getInstanceInputValue(Integer instanceProcessId, String columnId);
}
