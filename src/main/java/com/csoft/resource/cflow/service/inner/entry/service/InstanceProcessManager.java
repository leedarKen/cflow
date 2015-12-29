package com.csoft.resource.cflow.service.inner.entry.service;

import com.csoft.resource.cflow.config.ProcessTask;
import com.csoft.resource.cflow.pojo.InstanceProcess;

public interface InstanceProcessManager extends GenericManager<InstanceProcess,Integer>{

    void updateProcessInstanceCurrentTask(InstanceProcess processInstance, ProcessTask nextTask) ;
}
