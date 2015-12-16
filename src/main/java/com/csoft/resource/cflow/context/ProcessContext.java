package com.csoft.resource.cflow.context;

import com.csoft.resource.cflow.config.Process;
import com.csoft.resource.cflow.config.ProcessKeyInfo;

import java.util.HashMap;
import java.util.Map;

public class ProcessContext {
	private Map<ProcessKeyInfo, Process> processInstanceMap = new HashMap<ProcessKeyInfo, Process>();

	public Process getProcess(ProcessKeyInfo processKeyInfo) {
		Process process = processInstanceMap.get(processKeyInfo) ;
		if(process == null){
			process = ProcessLoad.load(processKeyInfo)  ;
		}
		processInstanceMap.put(processKeyInfo, process) ;
		return process ;
	}

	private ProcessContext (){}

	private static ProcessContext processContext = null ;
	protected static ProcessContext getInstance(){
		if(processContext == null){
			synchronized (ProcessContext.class){
				processContext =  new ProcessContext() ;
			}
		}
		return processContext ;
	}

	public Map<ProcessKeyInfo, Process> getProcessInstanceMap() {
		return processInstanceMap;
	}

}
