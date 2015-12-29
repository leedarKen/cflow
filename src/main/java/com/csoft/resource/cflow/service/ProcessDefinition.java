package com.csoft.resource.cflow.service;

/**
 * Definition Process
 * About add the Process Task
 */
public interface ProcessDefinition {


    /**
     * add or edit the process basic info
	 * if return null, save or update action was failure.
	 *
	 * @param processDefinitonJson  process basic info with json format
	 *
	 * @return  process id and process version to json data
	 * {
	 *     processId:	bdm_travel,
	 *     processVersion:	1.0
	 * }
     */
	String saveOrUpdateProcessBasicInfo(String processDefinitonJson) ;

    /**
     * add or edit process's Task one by one
	 * if is the first task, will set the executor form process's startUserRole.
	 * if return null, save or update failure.
	 *
	 * @param processKeyInfo it's the process two key info, processId and version
	 *          {
	 *    				 processId:	bdm_travel,
	 *     				 processVersion:	1.0
	 *			 }
	 *	@param taskInfo task info with the json format
	 *
	 * @retur process's info and task's id
	 * {
	 *     processId: bdm_travel,
	 *     processVersion:	1.0,
	 *     taskId	bdm_apply
	 * }
	 *
     */
	String saveOrUpdateTaskOfProcess(String processKeyInfo, String taskInfo) ;

    /**
     * add or edit process's all Task
	 * if return null, save or update failure.
	 *
	 * @param processKeyInfo it's the process two key info, processId and version
	 *          {
	 *    				 processId:	bdm_travel,
	 *     				 processVersion:	1.0
	 *			 }
	 *	@param taskInfo task info with the json format
	 *
	 * @retur process's info and task's id
	 * {
	 *     processId: bdm_travel,
	 *     processVersion:	1.0,
	 * }
	 */
	String saveOrUpdateAllTaskOfProcess(String processKeyInfo, String taskInfo) ;

    /**
     * remove process's task
	 *
	 * @param processKeyInfo
	 * {
	 *     processId:	bdm_travel,
	 *     processVersion:	1.0
	 * }
	 * @param taskId
     */
	String removeTaskOFProcess(String processKeyInfo, String taskId) ;

	/**
     * retrieve process's detail information
	 *
	 * @param processKeyInfo
	 * {
	 *     processId:	bdm_travel,
	 *     processVersion:	1.0
	 * }
	 *
	 * @return  process all info winth jsonObject
     */
	String retrieveProcessInfo(String processKeyInfo) ;

    /**
     * copy one process
     *
     * modify the process definition to suite special flow, change the version, add or remove one ore more task's.
     *
	 * @param processKeyInfo
	 * {
	 *     processId:	bdm_trvael,
	 *     processVersion:	1.0
	 * }
	 *
	 * @return new process all info winth jsonObject
	 *
     */
	String copyProcess(String processKeyInfo) ;

	/**
	 *  return all tasks of process
	 * @param processKeyInfo
	 * @return
	 */
    String getAllTasks(String processKeyInfo) ;

	/**
	 * return the next Task's info use the current task id of process
	 * @param ProcessKeyInfo process key info,include processId and processVersion,like below:
	 * {
	 *     processId:	bdm_travel,
	 *     processVersion:	1.0
	 * }
	 * @param currentTaskId current task's id
	 * @return String
	 */
	String getNextTask(String ProcessKeyInfo, String currentTaskId) ;
}
