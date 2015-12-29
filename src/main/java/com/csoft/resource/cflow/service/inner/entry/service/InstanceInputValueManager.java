package com.csoft.resource.cflow.service.inner.entry.service;

import com.csoft.resource.cflow.config.Process;
import com.csoft.resource.cflow.pojo.InstanceInputValue;
import com.csoft.resource.cflow.pojo.InstanceProcess;
import com.csoft.resource.cflow.service.inner.business.service.impl.ProcessInstanceInnerManagerImpl;
import net.sf.json.JSONArray;

public interface InstanceInputValueManager extends GenericManager<InstanceInputValue, Integer>{

	InstanceInputValue getInstanceInputValue(Integer instanceProcessId, String columnId) ;

	void saveInputValue(InstanceProcess instanceProcess, JSONArray inputArray, Process process, ProcessInstanceInnerManagerImpl processInstanceInnerManager);
}
