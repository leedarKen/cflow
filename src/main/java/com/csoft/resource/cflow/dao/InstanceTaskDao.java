package com.csoft.resource.cflow.dao;

import com.csoft.resource.cflow.pojo.InstanceTask;

public interface InstanceTaskDao extends GenericDao<InstanceTask, Integer>{
	int getTaskSequence(Integer processInstanceId);

	/**
	 * get the task's execte information by process instance id and task id.
	 * @param instanceProcessId process instance id
	 * @param TaskId task id
	 * @return InstanceTask
	 * @throws Exception
	 */
	InstanceTask getTaskInfo(String instanceProcessId, String TaskId) throws Exception;
}
