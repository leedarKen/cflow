package com.csoft.resource.cflow.dao.impl;

import com.csoft.resource.cflow.dao.InstanceProcessNextUserMapDao;
import com.csoft.resource.cflow.pojo.InstanceProcessNextUserMap;
import org.hibernate.Criteria;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class InstanceProcessNextUserMapDaoHibernate
		extends GenericDaoHibernate<InstanceProcessNextUserMap,Integer>
		implements InstanceProcessNextUserMapDao {

	public InstanceProcessNextUserMapDaoHibernate() {
		super(InstanceProcessNextUserMap.class);
	}

	public void deleteProcessExecutorUser(Integer processInstanceId){
		Session session = getSessionFactory().getCurrentSession();
		Criteria cri = session.createCriteria(InstanceProcessNextUserMap.class);
		cri.add(Restrictions.eq("instanceProcess.id", processInstanceId));
		List<InstanceProcessNextUserMap> userList = cri.list();
		for(InstanceProcessNextUserMap userMap : userList){
			session.delete(userMap);
		}
	}
}
