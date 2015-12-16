package com.csoft.resource.cflow.dao.impl;

import com.csoft.resource.cflow.dao.InstanceTaskDao;
import com.csoft.resource.cflow.pojo.InstanceTask;
import org.hibernate.Criteria;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class InstanceTaskDaoHibernate
		extends GenericDaoHibernate<InstanceTask, Integer>
		implements InstanceTaskDao {

	public InstanceTaskDaoHibernate() {
		super(InstanceTask.class);
	}

	@Override
	public int getTaskSequence(Integer processInstanceId) {
		Session session = getSessionFactory().getCurrentSession();
		Criteria cri = session.createCriteria(InstanceTask.class);
		cri.add(Restrictions.eq("instanceProcess.id", processInstanceId));
		List<InstanceTask> userList = cri.list() ;

		if(userList!= null && !userList.isEmpty()){
			return userList.size() + 1 ;
		}

		return 1 ;
	}
}
