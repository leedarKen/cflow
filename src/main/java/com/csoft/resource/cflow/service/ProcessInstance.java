package com.csoft.resource.cflow.service;

import java.util.Map;

/**
 * Created by ken.kang on 2015/11/20.
 */
public interface ProcessInstance {

    /**
     * start one process, return process instance's id
     * @param processDefinitionID processDefinition's ID
     * @return String processInstanssID processInstance's ID
     */
    public String startProcess(String processDefinitionID) ;

    /**
     * execute task, return current task's info and next task's task name
     * if the task is the last one, return filed nextTaskName will be "Finished"
     *
     * @param processInstanceID
     * @param taskID
     * @param jsonValue
     *
     * @return Map  key:
     *  currentTaskID
     *  currentTaskName
     *  nextTaskName
     */
    public Map<String, String> executeTask(String processInstanceID, String taskID, String jsonValue) ;

    /**
     * cancel task, return previous task's info
     *
     * @param processInstanceID
     * @param taskID
     *
     *  @return Map  key:
     *  currentTaskID
     *  currentTaskName
     *  nextTaskName
     */
    public Map<String, String> cancelTask(String processInstanceID, String taskID) ;

    /**
     * if in the processing, occured some error or exception, will invovled this function to stop process,
     * return filed nextTaskName will be "Stop"
     *
     *  @return Map  key:
     *  currentTaskID
     *  currentTaskName
     *  nextTaskName
     */
    public Map<String, String> stoplTask(String processInstanceID) ;

    /**
     * change to another version of process
     */

}
