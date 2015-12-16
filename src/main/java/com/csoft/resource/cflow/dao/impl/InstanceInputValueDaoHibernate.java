package com.csoft.resource.cflow.dao.impl;

import com.csoft.resource.cflow.dao.InstanceInputValueDao;
import com.csoft.resource.cflow.pojo.InstanceInputValue;
import org.hibernate.Criteria;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class InstanceInputValueDaoHibernate
		extends GenericDaoHibernate<InstanceInputValue, Integer>
		implements InstanceInputValueDao {

	public InstanceInputValueDaoHibernate() {
		super(InstanceInputValue.class);
	}

	@Override
	public InstanceInputValue getInstanceInputValue(Integer instanceProcessId, String columnId) {
		Session session = getSessionFactory().getCurrentSession();
		Criteria cri = session.createCriteria(InstanceInputValue.class);
		cri.add(Restrictions.eq("instanceProcess.id", instanceProcessId));
		cri.add(Restrictions.eq("cfKey", columnId)) ;
		List<InstanceInputValue> instanceInputValuerList = cri.list() ;
		if(instanceInputValuerList != null && ! instanceInputValuerList.isEmpty()){
			return instanceInputValuerList.get(0) ;
		}
		return null ;
	}
}
