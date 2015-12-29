package com.csoft.resource.cflow.service.inner.entry.service;

import com.csoft.resource.cflow.config.Process;
import com.csoft.resource.cflow.config.ProcessTask;
import com.csoft.resource.cflow.pojo.InstanceProcess;
import com.csoft.resource.cflow.pojo.InstanceTask;
import com.csoft.resource.cflow.service.inner.business.service.impl.ProcessInstanceInnerManagerImpl;
import net.sf.json.JSONObject;

public interface InstanceTaskManager extends GenericManager<InstanceTask, Integer>{

	int getTaskSequence(Integer processInstanceId) ;

	/**
	 * get the task's execte information by process instance id and task id.
	 * @param instanceProcessId process instance id
	 * @param TaskId task id
	 * @return InstanceTask
	 * @throws Exception
	 */
	InstanceTask getTaskInfo(String instanceProcessId, String TaskId) throws Exception;

	void saveInstanceTask(JSONObject jsonObject, InstanceProcess instanceProcess, Process process, ProcessTask currentTask );
}
