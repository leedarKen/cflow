package com.csoft.resource.cflow.service.inner.business.service;

import com.csoft.resource.cflow.config.Process;
import com.csoft.resource.cflow.config.ProcessKeyInfo;

public interface ProcessDefinitionInnerManager {

	/**
	 * retrieve process's detail information
	 *
	 * @param processKeyInfo
	 *
	 * @return  Process
	 */
	Process retrieveProcessInfo(ProcessKeyInfo processKeyInfo) ;
}
