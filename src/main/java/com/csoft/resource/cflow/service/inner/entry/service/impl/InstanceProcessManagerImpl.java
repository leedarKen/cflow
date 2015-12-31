package com.csoft.resource.cflow.service.inner.entry.service.impl;

import com.csoft.resource.cflow.config.Process;
import com.csoft.resource.cflow.config.ProcessBenginTask;
import com.csoft.resource.cflow.config.ProcessEndTask;
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
	public void updateProcessInstanceCurrentTask(InstanceProcess processInstance, ProcessTask currentTask, ProcessTask nextTask) {

		processInstance.setCurrentTask(currentTask.getId());
		if(currentTask instanceof ProcessBenginTask){
			processInstance.setProcessStatus(Process.STATUS_BEGIN);
			processInstance.setNextTask(nextTask.getId());
		}else if(currentTask instanceof ProcessEndTask){
			processInstance.setProcessStatus(Process.STATUS_END);
			processInstance.setCurrentTask("END");
		}else{
			processInstance.setProcessStatus(Process.STATUS_PROCESSING);
			processInstance.setNextTask(nextTask.getId());
		}


		instanceProcessDao.update(processInstance);
	}
}
