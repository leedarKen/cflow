package com.csoft.resource.cflow.dao;

import com.csoft.resource.cflow.pojo.InstanceTask;

public interface InstanceTaskDao extends GenericDao<InstanceTask, Integer>{
	int getTaskSequence(Integer processInstanceId);
}
