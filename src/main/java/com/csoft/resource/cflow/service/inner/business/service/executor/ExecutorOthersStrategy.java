package com.csoft.resource.cflow.service.inner.business.service.executor;

import com.csoft.resource.cflow.config.ProcessTask;
import net.sf.json.JSONObject;
import org.springframework.context.ApplicationContext;

import java.util.List;

/**
 * Created by ken.kang on 2015/12/21.
 */
public interface ExecutorOthersStrategy {
    List<Integer> getExecutorList(ProcessTask task, JSONObject obj) ;
    public void setApplicationContext(ApplicationContext context) ;
}
