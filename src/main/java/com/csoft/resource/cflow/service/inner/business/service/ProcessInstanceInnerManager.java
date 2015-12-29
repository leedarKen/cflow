package com.csoft.resource.cflow.service.inner.business.service;

import com.csoft.resource.cflow.config.Process;
import com.csoft.resource.cflow.config.ProcessKeyInfo;
import com.csoft.resource.cflow.config.ProcessTask;
import org.springframework.context.ApplicationContext;

public interface ProcessInstanceInnerManager {

	/**
	 * start one process, return process instance's id
	 * after execute success, engine will be calculate next executor, but the first task's user is the creator,
	 * will save in table cf_instance_process_next_user_map
	 *
	 * @param keyInfo
	 * {
	 *     processId:   bdm_travel,
	 *     processVersion:  1.0
	 * }
	 * @param data
	 * {
	 *     startUser:   startUserId,
	 *     startDate:   startDate,
	 *     executeResult:{Success|Approve|Reject|Finish},
	 *     inputDate:   [
	 *         {columnId:TravelAmount, columnValue:1000},
	 *     ]
	 * }
	 * @return String processInstanceId
	 * {
	 *     processInstanceId:   processInstanceId,
	 *     nextTaskId:  nextTaskId,
	 *     nextTaskName:    nextTaskName
	 * }
	 */
	String startProcess(ProcessKeyInfo keyInfo, String data) throws Exception;

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
	String executeTask(Integer processInstanceId, String jsonValue) throws Exception;

	/**
	 * get the task's execte information by process instance id and task id.
	 * @param instanceProcessId process instance id
	 * @param taskId task id
	 * @return task info
	 * {
	 *     taskId: taskId,
	 *     taskName: taskName,
	 *     createTime: 12/05/2015 10:23:22,
	 *     createUser : 5222,
	 *     executeResult: Approve,
	 *
	 * }
	 * @throws Exception
	 */
	String getTaskInfo(String instanceProcessId, String taskId) ;

	/**
	 *
	 * @param jsonData
	 * {
	 *      id(action):value(approve)
	 *      id(TravelAmount):value(4000)
	 *      id(Currency):Rmb
	 * }
	 *
	 * @return
	 */
	public ProcessTask getNextTask(Process process, ProcessTask task, String jsonData)throws Exception ;

	void setApplicationContext(ApplicationContext context);

	//public void setApplicationContext(ApplicationContext context) ;
}
