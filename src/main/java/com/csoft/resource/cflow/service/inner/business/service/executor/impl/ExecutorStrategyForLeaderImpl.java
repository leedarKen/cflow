package com.csoft.resource.cflow.service.inner.business.service.executor.impl;

import com.csoft.resource.cflow.config.ProcessTask;
import com.csoft.resource.cflow.service.inner.business.service.ProcessExecuotrManager;
import com.csoft.resource.cflow.service.inner.business.service.executor.ExecutorStrategy;
import net.sf.json.JSON;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("LeaderExecutorStrategy")
public class ExecutorStrategyForLeaderImpl implements ExecutorStrategy {

    private ApplicationContext context ;
    @Autowired
    private ProcessExecuotrManager processExecuotrManager;

    @Override
    public List<Integer> executorList(ProcessTask task, JSONObject obj) {
        return processExecuotrManager.getUserLeadar(Long.parseLong(obj.getString("createUser"))) ;
    }
    public void setApplicationContext(ApplicationContext context) {
        this.context = context ;
    }
}
