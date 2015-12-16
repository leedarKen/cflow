package com.csoft.resource.cflow.service.inner.business.service.impl;

import com.csoft.resource.cflow.config.*;
import com.csoft.resource.cflow.config.Process;
import com.csoft.resource.cflow.constants.TaskExecutorStrategy;
import com.csoft.resource.cflow.context.ProcessContext;
import com.csoft.resource.cflow.context.ProcessContextFactory;
import com.csoft.resource.cflow.pojo.InstanceInputValue;
import com.csoft.resource.cflow.pojo.InstanceProcess;
import com.csoft.resource.cflow.pojo.InstanceProcessNextUserMap;
import com.csoft.resource.cflow.pojo.InstanceTask;
import com.csoft.resource.cflow.service.inner.business.service.ProcessExecuotrManager;
import com.csoft.resource.cflow.service.inner.business.service.ProcessInstanceInnerManager;
import com.csoft.resource.cflow.service.inner.entry.service.InstanceInputValueManager;
import com.csoft.resource.cflow.service.inner.entry.service.InstanceProcessNextUserMapManager;
import com.csoft.resource.cflow.service.inner.entry.service.InstanceProcessManager;
import com.csoft.resource.cflow.service.inner.entry.service.InstanceTaskManager;
import com.csoft.resource.cflow.util.DateUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service("processInstanceInnerService")
public class ProcessInstanceInnerManagerImpl implements ProcessInstanceInnerManager {

	private ProcessContext context = ProcessContextFactory.getProcessContext() ;
	@Autowired
	private InstanceProcessManager instanceProcessManager;
	@Autowired
	private InstanceProcessNextUserMapManager instanceProcessNextUserMapManager;
	@Autowired
	private InstanceTaskManager instanceTaskManager;
	@Autowired
	private InstanceInputValueManager instanceInputValueManager;
	@Autowired
	private ProcessExecuotrManager processExecuotrManager;
	/**
	 * start one process, return process instance's id
	 * after execute success, engine will be calculate next executor, but the first task's user is the creator,
	 * will save in table cf_instance_process_next_user_map
	 *
	 * @param keyInfo
	 * {
	 *     processId:   bdm_travel,
	 *     processVersion:  1.0.0
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
	@Override
	public String startProcess(ProcessKeyInfo keyInfo, String data) throws Exception{
		Process process = context.getProcess(keyInfo) ;
		ProcessTaskList  taskList = process.getProcessTaskList() ;
		ProcessTask currentTask = taskList.getBeninTask() ;

		JSONObject jsonObject = JSONObject.fromObject(data) ;
		String startDate = jsonObject.getString("startDate") ;

		InstanceProcess instance = new InstanceProcess() ;
		instance.setProcessId(process.getId());
		instance.setVersion(process.getVersion());
		instance.setStartTime(DateUtil.parse(startDate));
		instance.setStartUser(jsonObject.getInt("startUser"));
		instance.setCurrentTask(currentTask.getId());
		instance.setProcessName(process.getName());
		instance.setProcessType(1);

		InstanceProcess instanceProcess = instanceProcessManager.save(instance) ;

		InstanceProcessNextUserMap nextUserMap = new InstanceProcessNextUserMap() ;
		nextUserMap.setNextUserId(jsonObject.getInt("startUser"));
		nextUserMap.setInstanceProcess(instanceProcess);

		instanceProcessNextUserMapManager.save(nextUserMap) ;


		//execute first task
		JSONObject executeTaskData = new JSONObject() ;
		executeTaskData.put("taskId", currentTask.getId()) ;
		executeTaskData.put("createTime", startDate) ;
		executeTaskData.put("createUser", jsonObject.get("startUser")) ;
		executeTaskData.put("executeResult", jsonObject.get("executeResult")) ;
		executeTaskData.put("inputData", jsonObject.get("inputData")) ;

		return executeTask(instanceProcess.getId(),  executeTaskData.toString()) ;
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
	 *     inputData:   [
	 *         {columnId:TravelAmount, columnValue:1000, columnType:String},
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
	public String executeTask(Integer processInstanceId, String jsonValue) throws Exception {
		JSONObject jsonObject = JSONObject.fromObject(jsonValue) ;
		JSONObject toVerifyNextTaskData = new JSONObject() ;


		InstanceProcess instanceProcess = instanceProcessManager.get(processInstanceId) ;

		JSONArray inputArray = null ;

		if(jsonObject.containsKey("inputData")){
			inputArray = jsonObject.getJSONArray("inputData") ;
		}

		if(inputArray != null){
			for(int i = 0 ; i < inputArray.size() ; i++){
				JSONObject obj = inputArray.getJSONObject(i) ;
				toVerifyNextTaskData.put(obj.get("columnId"), obj.get("columnValue")) ;
			}
		}
		toVerifyNextTaskData.put("action", jsonObject.get("executeResult")) ;

		InstanceProcess  processInstance = instanceProcessManager.get(processInstanceId) ;

		Process process = context.getProcess(new ProcessKeyInfo(processInstance.getProcessId(), processInstance.getVersion())) ;

		ProcessTask currentTask = process.getProcessTaskList().getTask(jsonObject.getString("taskId")) ;

		ProcessTask nextTask = process.getProcessTaskList().getNextTask(currentTask,toVerifyNextTaskData.toString()) ;

		if(nextTask == null){
			throw new Exception("Next task is null") ;
		}

		JSONArray userArray = new JSONArray() ;

		//update process
		processInstance.setCurrentTask(nextTask.getId());

		instanceProcessManager.update(processInstance);

		//insert into task instance
		InstanceTask task = new InstanceTask() ;
		task.setCreateUser(jsonObject.getInt("createUser"));
		task.setInstanceProcess(instanceProcess);
		task.setTaskId(currentTask.getId());
		task.setTaskName(currentTask.getName());
		task.setProcessId(process.getId());
		task.setCreateTime(DateUtil.parse(jsonObject.getString("createTime")));
		task.setExecuteResult(jsonObject.getString("executeResult"));

		int taskSequence = instanceTaskManager.getTaskSequence(instanceProcess.getId()) ;
		task.setTaskSequence(taskSequence);

		instanceTaskManager.save(task) ;


		//insert into the input value
		if(inputArray != null){
			for(int i = 0 ; i < inputArray.size() ; i++){
				JSONObject obj = inputArray.getJSONObject(i) ;
				InstanceInputValue input =  new InstanceInputValue() ;
				input.setCfKey(obj.getString("columnId"));
				input.setCfValue(obj.getString("columnValue"));
				input.setCfKeyType(obj.getString("columnType"));
				input.setProcessId(process.getId());
				input.setInstanceProcess(instanceProcess);
				input.setProcessName(process.getName());

				instanceInputValueManager.save(input) ;
			}

			//update next task task executor
			List<Integer> executorList = new ArrayList<Integer>() ;
			//get the next task's executor
			ProcessTaskExecutor executor = nextTask.getProcessTaskExecutor() ;
			TaskExecutorStrategy strategy = executor.getTaskExecutorStrategy() ;
			if(strategy == TaskExecutorStrategy.Leader){
				InstanceInputValue inputValue = instanceInputValueManager.getInstanceInputValue(instanceProcess.getId(), "TravelAmount") ;
				executorList = processExecuotrManager.getUserLeadar(Long.parseLong(jsonObject.getString("createUser"))) ;
			}else if(strategy == TaskExecutorStrategy.Role){
				executorList = processExecuotrManager.getUserByRole(executor.getStrategyValue());
			}else if(strategy == TaskExecutorStrategy.Assign){
				String columnId = executor.getStrategyValue() ;
				InstanceInputValue inputValue = instanceInputValueManager.getInstanceInputValue(instanceProcess.getId(), columnId) ;
				executorList.add(Integer.getInteger(inputValue.getCfValue()));
			}else{
				executorList.addAll(executor.getUserList()) ;
			}

			if(!executorList.isEmpty()){
				userArray.addAll(executorList) ;
				instanceProcessNextUserMapManager.deleteProcessExecutorUser(processInstanceId) ;

				for(int userId: executorList){
					InstanceProcessNextUserMap nextUserMap = new InstanceProcessNextUserMap() ;
					nextUserMap.setNextUserId(userId);
					nextUserMap.setInstanceProcess(instanceProcess);
					instanceProcessNextUserMapManager.save(nextUserMap) ;
				}
			}
		}

		JSONObject result = new JSONObject() ;
		result.put("currentTaskId", currentTask.getId()) ;
		result.put("currentTaskName", currentTask.getName()) ;
		result.put("nextTaskId", nextTask.getId()) ;
		result.put("nextTaskName", nextTask.getName()) ;
		if(userArray.size() > 0){
			result.put("nextExecutor", userArray.toString()) ;
		}else{
			result.put("nextExecutor", "") ;
		}
		return result.toString() ;
	}

	public InstanceProcessManager getInstanceProcessManager() {
		return instanceProcessManager;
	}

	public void setInstanceProcessManager(InstanceProcessManager instanceProcessManager) {
		this.instanceProcessManager = instanceProcessManager;
	}

	public InstanceProcessNextUserMapManager getInstanceProcessNextUserMapManager() {
		return instanceProcessNextUserMapManager;
	}

	public void setInstanceProcessNextUserMapManager(InstanceProcessNextUserMapManager instanceProcessNextUserMapManager) {
		this.instanceProcessNextUserMapManager = instanceProcessNextUserMapManager;
	}

	public InstanceTaskManager getInstanceTaskManager() {
		return instanceTaskManager;
	}

	public void setInstanceTaskManager(InstanceTaskManager instanceTaskManager) {
		this.instanceTaskManager = instanceTaskManager;
	}

	public InstanceInputValueManager getInstanceInputValueManager() {
		return instanceInputValueManager;
	}

	public void setInstanceInputValueManager(InstanceInputValueManager instanceInputValueManager) {
		this.instanceInputValueManager = instanceInputValueManager;
	}

	public ProcessExecuotrManager getProcessExecuotrManager() {
		return processExecuotrManager;
	}

	public void setProcessExecuotrManager(ProcessExecuotrManager processExecuotrManager) {
		this.processExecuotrManager = processExecuotrManager;
	}
}
