package com.csoft.resource.cflow.service.inner.entry.service.impl;

import com.csoft.resource.cflow.dao.InstanceProcessNextUserMapDao;
import com.csoft.resource.cflow.pojo.InstanceProcessNextUserMap;
import com.csoft.resource.cflow.service.inner.entry.service.InstanceProcessNextUserMapManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InstanceProcessNextUserMapManagerImpl
		extends GenericManagerImpl<InstanceProcessNextUserMap,Integer>
		implements InstanceProcessNextUserMapManager {

	private InstanceProcessNextUserMapDao instanceProcessNextUserMapDao ;

	@Autowired
	public InstanceProcessNextUserMapManagerImpl(InstanceProcessNextUserMapDao instanceProcessNextUserMapDao){
		super(instanceProcessNextUserMapDao) ;
		this.instanceProcessNextUserMapDao = instanceProcessNextUserMapDao ;
	}

	@Override
	public void deleteProcessExecutorUser(Integer processInstanceId) {
		instanceProcessNextUserMapDao.deleteProcessExecutorUser(processInstanceId);
	}
}
