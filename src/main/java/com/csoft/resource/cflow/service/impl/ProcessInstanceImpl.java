package com.csoft.resource.cflow.service.impl;

import com.csoft.resource.cflow.config.ProcessKeyInfo;
import com.csoft.resource.cflow.context.SpringContextUtil;
import com.csoft.resource.cflow.service.ProcessInstance;
import com.csoft.resource.cflow.service.inner.business.service.ProcessInstanceInnerManager;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


public class ProcessInstanceImpl implements ProcessInstance {

	private ProcessInstanceInnerManager processInstanceInnerManager;

	public ProcessInstanceInnerManager getProcessInstanceInnerManager() {
		return processInstanceInnerManager;
	}

	public void setProcessInstanceInnerManager(ProcessInstanceInnerManager processInstanceInnerManager) {
		this.processInstanceInnerManager = processInstanceInnerManager;
	}

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
	 * }
	 * @return String processInstanceId
	 * {
	 *     processInstanceId:   processInstanceId,
	 *     nextTaskId:  nextTaskId,
	 *     nextTaskName:    nextTaskName
	 * }
	 */
	@Override
	public String startProcess(String processDefinitionKeyInbfo, String data) {

		JSONObject jsonObj = JSONObject.fromObject(processDefinitionKeyInbfo) ;
		String id = jsonObj.getString("processId") ;
		String version = jsonObj.getString("processVersion") ;

		ProcessKeyInfo keyInfo = new ProcessKeyInfo(id, version) ;
		try {
			//ProcessInstanceInnerManager processInstanceInnerManager = (ProcessInstanceInnerManager)SpringContextUtil.getBean("processInstanceInnerManager") ;
			return processInstanceInnerManager.startProcess(keyInfo,data);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null ;
	}

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
	@Override
	public String executeTask(Integer processInstanceId, String jsonValue) {
		try {
			return processInstanceInnerManager.executeTask(processInstanceId, jsonValue );
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null ;
	}

	@Override
	public String cancelTask(String processInstanceId, String taskId) {
		return null;
	}

	@Override
	public String stoplTask(String processInstanceID) {
		return null;
	}

	@Override
	public String changeProcessToNextVersion(String currentProcessInstanceId, String newVersionProcessKeyInfo) {
		return null;
	}


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
	@Override
	public String getTaskInfo(String instanceProcessId, String taskId) throws Exception {
		return processInstanceInnerManager.getTaskInfo(instanceProcessId, taskId);
	}

}
