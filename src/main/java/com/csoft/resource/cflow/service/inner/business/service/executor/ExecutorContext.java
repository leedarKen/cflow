package com.csoft.resource.cflow.service.inner.business.service.executor;

import com.csoft.resource.cflow.config.ProcessTask;
import com.csoft.resource.cflow.config.ProcessTaskExecutor;
import com.csoft.resource.cflow.constants.TaskExecutorStrategy;
import net.sf.json.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExecutorContext {

    private ApplicationContext applicationContext ;


    private ExecutorStrategy executorStrategy ;


    public List<Integer> executorList(ProcessTask task, JSONObject obj){
        //update next task task executor
        List<Integer> executorList = new ArrayList<Integer>() ;
        //get the next task's executor
        ProcessTaskExecutor executor = task.getProcessTaskExecutor() ;
        TaskExecutorStrategy strategy = executor.getTaskExecutorStrategy() ;
        if(strategy == TaskExecutorStrategy.Leader){
            executorStrategy = (ExecutorStrategy) applicationContext.getBean(TaskExecutorStrategy.Leader.desc() + "ExecutorStrategy");
        }else if(strategy == TaskExecutorStrategy.Role){
            executorStrategy = (ExecutorStrategy) applicationContext.getBean(TaskExecutorStrategy.Role.desc() + "ExecutorStrategy");
        }else if(strategy == TaskExecutorStrategy.Assign){
            executorStrategy = (ExecutorStrategy) applicationContext.getBean(TaskExecutorStrategy.Assign.desc() + "ExecutorStrategy");
        }else if(strategy == TaskExecutorStrategy.Others){
            executorStrategy = (ExecutorStrategy) applicationContext.getBean(TaskExecutorStrategy.Others.desc() + "ExecutorStrategy");
        }else{
            executorList.addAll(executor.getUserList()) ;
        }
        //executorStrategy.setApplicationContext(this.applicationContext);
        return executorStrategy.executorList(task, obj) ;
    }

    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }
}
