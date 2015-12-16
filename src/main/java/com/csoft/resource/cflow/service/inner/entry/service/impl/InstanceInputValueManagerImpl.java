package com.csoft.resource.cflow.service.inner.entry.service.impl;

import com.csoft.resource.cflow.dao.InstanceInputValueDao;
import com.csoft.resource.cflow.pojo.InstanceInputValue;
import com.csoft.resource.cflow.service.inner.entry.service.InstanceInputValueManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InstanceInputValueManagerImpl
		extends GenericManagerImpl<InstanceInputValue, Integer>
		implements InstanceInputValueManager {

	private InstanceInputValueDao instanceInputValueDao ;

	@Autowired
	public InstanceInputValueManagerImpl(InstanceInputValueDao instanceInputValueDao){
		super(instanceInputValueDao) ;
		this.instanceInputValueDao = instanceInputValueDao ;
	}

	@Override
	public InstanceInputValue getInstanceInputValue(Integer instanceProcessId, String columnId) {
		return instanceInputValueDao.getInstanceInputValue(instanceProcessId, columnId) ;
	}
}
