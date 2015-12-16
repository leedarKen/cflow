package com.csoft.resource.cflow.service;

import com.csoft.resource.cflow.context.ProcessContext;
import com.csoft.resource.cflow.context.ProcessContextFactory;
import com.csoft.resource.cflow.config.Process;
import com.csoft.resource.cflow.config.ProcessKeyInfo;
import net.sf.json.JSONObject;

public class FileProcessDefinition implements ProcessDefinition {
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
		ProcessContext processContext = ProcessContextFactory.getProcessContext() ;
		JSONObject jsonbejct =  JSONObject.fromObject(processKeyInfo) ;

		ProcessKeyInfo keyInbfo = new ProcessKeyInfo() ;
		keyInbfo.setId((String)jsonbejct.get("id"));
		keyInbfo.setVersion((String)jsonbejct.get("version"));

		Process process = processContext.getProcess(keyInbfo) ;
		return process.toString();
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
}
