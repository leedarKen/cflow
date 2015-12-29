package com.csoft.resource.cflow.service.inner.entry.service.impl;

import com.csoft.resource.cflow.dao.HistoryInstanceProcessDao;
import com.csoft.resource.cflow.pojo.HistoryInstanceProcess;
import com.csoft.resource.cflow.service.inner.entry.service.HistoryInstanceProcessManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("historyInstanceProcessManager")
public class HistoryInstanceProcessManagerImpl
		extends GenericManagerImpl<HistoryInstanceProcess,Integer>
		implements HistoryInstanceProcessManager {

	@Autowired
	private HistoryInstanceProcessDao historyInstanceProcessDao ;

	@Autowired
	public HistoryInstanceProcessManagerImpl(HistoryInstanceProcessDao historyInstanceProcessDao) {
		super(historyInstanceProcessDao);
		this.historyInstanceProcessDao = historyInstanceProcessDao;
	}
}
