package com.csoft.resource.cflow.service.inner.entry.service;

import com.csoft.resource.cflow.pojo.InstanceInputValue;

public interface InstanceInputValueManager extends GenericManager<InstanceInputValue, Integer>{

	InstanceInputValue getInstanceInputValue(Integer instanceProcessId, String columnId) ;
}
