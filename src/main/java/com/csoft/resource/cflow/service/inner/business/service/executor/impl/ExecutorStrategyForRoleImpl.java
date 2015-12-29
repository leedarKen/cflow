package com.csoft.resource.cflow.service.inner.business.service.executor.impl;

import com.csoft.resource.cflow.config.ProcessTask;
import com.csoft.resource.cflow.service.inner.business.service.executor.ExecutorStrategy;
import net.sf.json.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("RoleExecutorStrategy")
public class ExecutorStrategyForRoleImpl implements ExecutorStrategy {
    private ApplicationContext context ;

    @Override
    public List<Integer> executorList(ProcessTask task, JSONObject obj) {
        return null;
    }
    public void setApplicationContext(ApplicationContext context) {
        this.context = context ;
    }
}
