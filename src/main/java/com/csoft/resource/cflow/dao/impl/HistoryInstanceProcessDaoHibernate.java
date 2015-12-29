package com.csoft.resource.cflow.dao.impl;

import com.csoft.resource.cflow.dao.HistoryInstanceProcessDao;
import com.csoft.resource.cflow.pojo.HistoryInstanceProcess;
import org.springframework.stereotype.Repository;

@Repository("historyInstanceProcessDao")
public class HistoryInstanceProcessDaoHibernate
		extends GenericDaoHibernate<HistoryInstanceProcess,Integer>
		implements HistoryInstanceProcessDao {

	public HistoryInstanceProcessDaoHibernate(){
		super(HistoryInstanceProcess.class) ;
	}
}
