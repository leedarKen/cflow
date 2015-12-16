package com.csoft.resource.cflow.dao.impl;

import com.csoft.resource.cflow.dao.HistoryInstanceTaskDao;
import com.csoft.resource.cflow.pojo.HistoryInstanceTask;
import org.springframework.stereotype.Repository;

@Repository
public class HistoryInstanceTaskDaoHibernate
		extends  GenericDaoHibernate<HistoryInstanceTask, Integer>
		implements HistoryInstanceTaskDao {


	public HistoryInstanceTaskDaoHibernate() {
		super(HistoryInstanceTask.class);
	}

}
