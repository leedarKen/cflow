package com.csoft.resource.cflow.service.inner.business.service.executor.impl;

import com.csoft.resource.cflow.config.ProcessTask;
import com.csoft.resource.cflow.config.ProcessTaskExecutor;
import com.csoft.resource.cflow.pojo.InstanceInputValue;
import com.csoft.resource.cflow.service.inner.business.service.executor.ExecutorOthersStrategy;
import com.csoft.resource.cflow.service.inner.entry.service.InstanceInputValueManager;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("ByTravelAmount")
public class BDMTravelExecutorStrategyImpl implements ExecutorOthersStrategy {
    private ApplicationContext context ;
    @Autowired
    InstanceInputValueManager instanceInputValueManager;

    @Override
    public List<Integer> getExecutorList(ProcessTask task, JSONObject obj) {
        List<Integer> executorList = new ArrayList<Integer>();
        //TODO 3457是雪泥
        //executorList.add(3457);
        executorList.add(5237);
        InstanceInputValue inputValue = instanceInputValueManager.getInstanceInputValue(obj.getInt("instanceProcessId"), "outOfChina");
        if (inputValue == null) {
            return executorList;
        }
        if (Boolean.parseBoolean(inputValue.getCfValue())) {
            //TODO 299 是wing
            //executorList.add(299);
            executorList.add(2792);
        } else {
            //TODO  3456是jason
            //executorList.add(3456);
            executorList.add(2655);
        }
        return executorList;
    }
    public void setApplicationContext(ApplicationContext context) {
        this.context = context ;
    }
}
