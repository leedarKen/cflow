package com.csoft.resource.cflow.service.inner.entry.service;

import com.csoft.resource.cflow.pojo.InstanceTask;

public interface InstanceTaskManager extends GenericManager<InstanceTask, Integer>{

	int getTaskSequence(Integer processInstanceId) ;
}
