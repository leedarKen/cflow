package com.csoft.resource.cflow.service.inner.entry.service.impl;

import com.csoft.resource.cflow.dao.HistoryInstanceTaskDao;
import com.csoft.resource.cflow.pojo.HistoryInstanceTask;
import com.csoft.resource.cflow.service.inner.entry.service.HistoryInstanceTaskManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("historyInstanceTaskManager")
public class HistoryInstanceTaskManagerImpl
		extends GenericManagerImpl<HistoryInstanceTask, Integer>
		implements HistoryInstanceTaskManager {

	@Autowired
	private HistoryInstanceTaskDao historyInstanceTaskDao ;

	@Autowired
	public HistoryInstanceTaskManagerImpl(HistoryInstanceTaskDao historyInstanceTaskDao){
		super(historyInstanceTaskDao) ;
		this.historyInstanceTaskDao = historyInstanceTaskDao ;
	}
}
