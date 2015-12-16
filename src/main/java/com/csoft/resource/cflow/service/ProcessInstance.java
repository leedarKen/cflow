package com.csoft.resource.cflow.service;



public interface ProcessInstance {

    /**
     * start one process, return process instance's id
     * after execute success, engine will be calculate next executor, but the first task's user is the creator,
     * will save in table cf_instance_process_next_user_map
     *
     * @param processDefinitionKeyInbfo
     * {
     *     processId:   bdm_travel,
     *     processVersion:  1.0
     * }
     * @param data
     * {
     *     startUser:   startUserId,
     *     startDate:   startDate,
     *     executeResult:{success|approve|reject|finish},
     *     inputDate:   [
     *         {columnId:TravelAmount, columnValue:1000},
     *     ]
     * }
     * @return String processInstanceId
     * {
     *      currentTaskId:  currentTaskId,
     *      currentTaskName:    currentTaskName,
     *      nextTaskId: nextTaskId,
     *      nextTaskName:   nextTaskName,
     *      nextExecutor:   [nextExecutor]
     *  }
     */
    String startProcess(String processDefinitionKeyInbfo, String data) throws Exception  ;

    /**
     * execute task, return current task's info and next task's task name
     *
     * after execute success, engine will be calculate next executor, use the different strategy
     *
     * if executeResult is reject,the current task will turn to previous task
     *
     * for the execute result, if action is input, result will be success
     * if the task is the last one, return filed nextTaskName will be "Finished"
     *
     *
     * @param processInstanceId
     * @param taskId
     * @param jsonValue
     * {
     *     taskId:  taskId,
     *     createTime:   createTime,
     *     createUser:  createUser,
     *     executeResult:{success|approve|reject|finish},
     *     inputDate:   [
     *         {columnId:TravelAmount, columnValue:1000},
     *     ]
     * }
     *
     *  @return
     *  {
     *      currentTaskId:  currentTaskId,
     *      currentTaskName:    currentTaskName,
     *      nextTaskId: nextTaskId,
     *      nextTaskName:   nextTaskName,
     *      nextExecutor:   nextExecutor
     *  }
     */
    String executeTask(Integer processInstanceId, String taskId, String jsonValue) throws Exception;

    /**
     * cancel task, return previous task's info
     * engine will be calculate next executor, use the different strategy
     * @param processInstanceId
     * @param taskId
     *
     *  @return
     *  {
     *      currentTaskId:  currentTaskId,
     *      currentTaskName:    currentTaskName,
     *      nextTaskId: nextTaskId,
     *      nextTaskName:   nextTaskName
     *  }
     */
    String cancelTask(String processInstanceId, String taskId) ;

    /**
     * if in the processing, occured some error or exception, will invovled this function to stop process,
     * return filed nextTaskName will be "Stop"
     *
     *  @return
     *  {
     *      currentTaskId:  currentTaskId,
     *      currentTaskName:    currentTaskName,
     *      nextTaskId: null,
     *      nextTaskName:   stop
     *  }
     */
    String stoplTask(String processInstanceID) ;

	/**
     * change to another version of process
     * when change, will change current process instance's process type to 2(new version process), don't delete
     * the new instnace's is noremal and process type is 1
     *
     * @param currentProcessInstanceId
     * @param newVersionProcessKeyInfo
     * {
     *     processId:   bdm_travel,
     *     processVersion: 2.0;
     * }
     * @return
     * {
     *     processInstanceId:   processInstanceId,
     *     processId:           processId,
     *     processVersion:      version
     * }
     */
    String changeProcessToNextVersion(String currentProcessInstanceId, String newVersionProcessKeyInfo) ;

}
