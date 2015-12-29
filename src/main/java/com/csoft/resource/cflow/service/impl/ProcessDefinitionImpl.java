package com.csoft.resource.cflow.service.impl;

import com.csoft.resource.cflow.config.Process;
import com.csoft.resource.cflow.config.ProcessKeyInfo;
import com.csoft.resource.cflow.service.ProcessDefinition;
import com.csoft.resource.cflow.service.inner.business.service.ProcessDefinitionInnerManager;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProcessDefinitionImpl implements ProcessDefinition {

	@Autowired
	private ProcessDefinitionInnerManager processDefinitionInnerManager;

	@Override
	public String saveOrUpdateProcessBasicInfo(String processDefinitonJson) {
		return null;
	}

	@Override
	public String saveOrUpdateTaskOfProcess(String processKeyInfo, String taskInfo) {
		return null;
	}

	@Override
	public String saveOrUpdateAllTaskOfProcess(String processKeyInfo, String taskInfo) {
		return null;
	}

	@Override
	public String removeTaskOFProcess(String processKeyInfo, String taskId) {
		return null;
	}

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
	@Override
	public String retrieveProcessInfo(String processKeyInfo) {
		ProcessKeyInfo key = formatProcessKeyInfo(processKeyInfo);
		Process process = processDefinitionInnerManager.retrieveProcessInfo(key) ;
		return process.toString();
	}

	private ProcessKeyInfo formatProcessKeyInfo(String processKeyInfo) {
		JSONObject jsonObject = JSONObject.fromObject(processKeyInfo) ;
		return new ProcessKeyInfo(jsonObject.getString("processId"), jsonObject.getString("processVersion"));
	}


	@Override
	public String copyProcess(String processKeyInfo) {
		return null;
	}

	@Override
	public String getAllTasks(String processKeyInfo) {
		return null;
	}

	@Override
	public String getNextTask(String ProcessKeyInfo, String currentTaskId) {
		return null;
	}

	public ProcessDefinitionInnerManager getProcessDefinitionInnerManager() {
		return processDefinitionInnerManager;
	}

	public void setProcessDefinitionInnerManager(ProcessDefinitionInnerManager processDefinitionInnerManager) {
		this.processDefinitionInnerManager = processDefinitionInnerManager;
	}
}
