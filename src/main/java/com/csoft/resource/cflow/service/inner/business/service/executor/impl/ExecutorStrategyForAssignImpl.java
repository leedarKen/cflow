package com.csoft.resource.cflow.service.inner.business.service.executor.impl;

import com.csoft.resource.cflow.config.ProcessTask;
import com.csoft.resource.cflow.config.ProcessTaskExecutor;
import com.csoft.resource.cflow.pojo.InstanceInputValue;
import com.csoft.resource.cflow.service.inner.business.service.executor.ExecutorStrategy;
import com.csoft.resource.cflow.service.inner.entry.service.InstanceInputValueManager;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("AssignExecutorStrategy")
public class ExecutorStrategyForAssignImpl implements ExecutorStrategy {
    private ApplicationContext context ;
    @Autowired
    InstanceInputValueManager instanceInputValueManager ;
    @Override
    public List<Integer> executorList(ProcessTask task, JSONObject obj) {
        List<Integer> executorList = new ArrayList<Integer>() ;
        ProcessTaskExecutor executor = task.getProcessTaskExecutor() ;
        String columnId = executor.getStrategyValue() ;
        InstanceInputValue inputValue = instanceInputValueManager.getInstanceInputValue(obj.getInt("instanceProcessId"), columnId) ;
        executorList.add(Integer.getInteger(inputValue.getCfValue()));
        return executorList ;
    }
    public void setApplicationContext(ApplicationContext context) {
        this.context = context ;
    }
}
