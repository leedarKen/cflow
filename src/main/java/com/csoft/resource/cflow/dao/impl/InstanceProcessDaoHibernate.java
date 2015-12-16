package com.csoft.resource.cflow.dao.impl;

import com.csoft.resource.cflow.dao.InstanceProcessDao;
import com.csoft.resource.cflow.pojo.InstanceProcess;
import org.springframework.stereotype.Repository;

@Repository
public class InstanceProcessDaoHibernate
		extends GenericDaoHibernate<InstanceProcess,Integer>
		implements InstanceProcessDao {

	public InstanceProcessDaoHibernate() {
		super(InstanceProcess.class);
	}
}
