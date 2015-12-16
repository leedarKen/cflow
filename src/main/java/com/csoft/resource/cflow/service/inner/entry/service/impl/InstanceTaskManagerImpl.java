package com.csoft.resource.cflow.service.inner.entry.service.impl;

import com.csoft.resource.cflow.dao.InstanceTaskDao;
import com.csoft.resource.cflow.pojo.InstanceTask;
import com.csoft.resource.cflow.service.inner.entry.service.InstanceTaskManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InstanceTaskManagerImpl
		extends GenericManagerImpl<InstanceTask, Integer>
		implements InstanceTaskManager {

	private InstanceTaskDao instanceTaskDao ;

	@Autowired
	public InstanceTaskManagerImpl(InstanceTaskDao instanceTaskDao){
		super(instanceTaskDao) ;
		this.instanceTaskDao = instanceTaskDao ;
	}

	@Override
	public int getTaskSequence(Integer processInstanceId) {
		return instanceTaskDao.getTaskSequence(processInstanceId) ;
	}
}
