package com.csoft.resource.cflow.service.inner.business.service.impl;

import com.csoft.resource.cflow.config.Process;
import com.csoft.resource.cflow.config.ProcessKeyInfo;
import com.csoft.resource.cflow.context.ProcessContext;
import com.csoft.resource.cflow.context.ProcessContextFactory;
import com.csoft.resource.cflow.service.inner.business.service.ProcessDefinitionInnerManager;
import org.springframework.stereotype.Service;

@Service("processDefinitionInnerService")
public class ProcessDefinitionInnerManagerImpl implements ProcessDefinitionInnerManager {

	private ProcessContext context = ProcessContextFactory.getProcessContext() ;

	@Override
	public Process retrieveProcessInfo(ProcessKeyInfo processKeyInfo) {
		return context.getProcess(processKeyInfo);
	}
}
