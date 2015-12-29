package com.csoft.resource.cflow.service.inner.entry.service.impl;

import com.csoft.resource.cflow.config.ProcessTask;
import com.csoft.resource.cflow.dao.InstanceProcessDao;
import com.csoft.resource.cflow.pojo.InstanceProcess;
import com.csoft.resource.cflow.service.inner.entry.service.InstanceProcessManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("instanceProcessManager")
public class InstanceProcessManagerImpl
		extends GenericManagerImpl<InstanceProcess,Integer>
		implements InstanceProcessManager {

	@Autowired
	private InstanceProcessDao instanceProcessDao ;

	@Autowired
	public InstanceProcessManagerImpl(InstanceProcessDao instanceProcessDao){
		super(instanceProcessDao) ;
		this.instanceProcessDao = instanceProcessDao ;
	}

	public InstanceProcessDao getInstanceProcessDao() {
		return instanceProcessDao;
	}

	public void setInstanceProcessDao(InstanceProcessDao instanceProcessDao) {
		this.instanceProcessDao = instanceProcessDao;
	}

	@Override
	public void updateProcessInstanceCurrentTask(InstanceProcess processInstance, ProcessTask nextTask) {
		processInstance.setCurrentTask(nextTask.getId());

		instanceProcessDao.update(processInstance);
	}
}
