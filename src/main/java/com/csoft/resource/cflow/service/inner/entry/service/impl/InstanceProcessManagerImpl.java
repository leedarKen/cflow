package com.csoft.resource.cflow.service.inner.entry.service.impl;

import com.csoft.resource.cflow.dao.InstanceProcessDao;
import com.csoft.resource.cflow.pojo.InstanceProcess;
import com.csoft.resource.cflow.service.inner.entry.service.InstanceProcessManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InstanceProcessManagerImpl
		extends GenericManagerImpl<InstanceProcess,Integer>
		implements InstanceProcessManager {

	private InstanceProcessDao instanceProcessDao ;

	@Autowired
	public InstanceProcessManagerImpl(InstanceProcessDao instanceProcessDao){
		super(instanceProcessDao) ;
		this.instanceProcessDao = instanceProcessDao ;
	}
}
