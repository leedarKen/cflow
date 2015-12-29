package com.csoft.resource.cflow.service.inner.business.service.executor.impl;

import com.csoft.resource.cflow.config.ProcessTask;
import com.csoft.resource.cflow.config.ProcessTaskExecutor;
import com.csoft.resource.cflow.context.SpringContextUtil;
import com.csoft.resource.cflow.service.inner.business.service.executor.ExecutorOthersStrategy;
import com.csoft.resource.cflow.service.inner.business.service.executor.ExecutorStrategy;
import net.sf.json.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("OthersExecutorStrategy")
public class ExecutorStrategyForOthersImpl implements ExecutorStrategy {
    private ApplicationContext context ;
    @Override
    public List<Integer> executorList(ProcessTask task, JSONObject obj) {
        ProcessTaskExecutor executor = task.getProcessTaskExecutor() ;
        String executorStrategy = executor.getStrategyValue() ;
        ExecutorOthersStrategy userService = (ExecutorOthersStrategy) context.getBean(executorStrategy);
        return userService.getExecutorList(task,obj);
    }
   public void setApplicationContext(ApplicationContext context) {
        this.context = context ;
    }
}
