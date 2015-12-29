package com.csoft.resource.cflow.service.inner.entry.service.impl;

import com.csoft.resource.cflow.config.Process;
import com.csoft.resource.cflow.config.ProcessTask;
import com.csoft.resource.cflow.dao.InstanceTaskDao;
import com.csoft.resource.cflow.pojo.InstanceProcess;
import com.csoft.resource.cflow.pojo.InstanceTask;
import com.csoft.resource.cflow.service.inner.business.service.impl.ProcessInstanceInnerManagerImpl;
import com.csoft.resource.cflow.service.inner.entry.service.InstanceTaskManager;
import com.csoft.resource.cflow.util.DateUtil;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("instanceTaskManager")
public class InstanceTaskManagerImpl
		extends GenericManagerImpl<InstanceTask, Integer>
		implements InstanceTaskManager {

	@Autowired
	private InstanceTaskDao instanceTaskDao ;

	@Autowired
	public InstanceTaskManagerImpl(InstanceTaskDao instanceTaskDao){
		super(instanceTaskDao) ;
		this.instanceTaskDao = instanceTaskDao ;
	}

	/**
	 * get the task's execte information by process instance id and task id.
	 * @param instanceProcessId process instance id
	 * @param TaskId task id
	 * @return InstanceTask
	 * @throws Exception
	 */
	public InstanceTask getTaskInfo(String instanceProcessId, String TaskId) throws Exception{
		return instanceTaskDao.getTaskInfo(instanceProcessId, TaskId) ;
	}
	@Override
	public int getTaskSequence(Integer processInstanceId) {
		return instanceTaskDao.getTaskSequence(processInstanceId) ;
	}

	@Override
	public void saveInstanceTask(JSONObject jsonObject, InstanceProcess instanceProcess, Process process, ProcessTask currentTask) {
		//insert into task instance
		InstanceTask task = new InstanceTask() ;
		task.setCreateUser(jsonObject.getInt("createUser"));
		task.setInstanceProcess(instanceProcess);
		task.setTaskId(currentTask.getId());
		task.setTaskName(currentTask.getName());
		task.setProcessId(process.getId());
		task.setCreateTime(DateUtil.parse(jsonObject.getString("createTime")));
		task.setExecuteResult(jsonObject.getString("executeResult"));

		int taskSequence = getTaskSequence(instanceProcess.getId()) ;
		task.setTaskSequence(taskSequence);

		save(task) ;
	}
}
