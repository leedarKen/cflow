package com.csoft.resource.cflow.dao.impl;

import com.csoft.resource.cflow.dao.HistoryInstanceInputValueDao;
import com.csoft.resource.cflow.pojo.HistoryInstanceInputValue;
import org.springframework.stereotype.Repository;

@Repository
public class HistoryInstanceInputValueDaoHibernate
		extends GenericDaoHibernate<HistoryInstanceInputValue,Integer>
		implements HistoryInstanceInputValueDao {

	public HistoryInstanceInputValueDaoHibernate(){
		super(HistoryInstanceInputValue.class);
	}
}
