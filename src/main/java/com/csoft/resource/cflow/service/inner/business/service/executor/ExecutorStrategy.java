package com.csoft.resource.cflow.service.inner.business.service.executor;

import com.csoft.resource.cflow.config.ProcessTask;
import net.sf.json.JSONObject;
import org.springframework.context.ApplicationContext;

import java.util.List;


public interface ExecutorStrategy {
    List<Integer> executorList(ProcessTask task, JSONObject obj) ;
    public void setApplicationContext(ApplicationContext context) ;
}
