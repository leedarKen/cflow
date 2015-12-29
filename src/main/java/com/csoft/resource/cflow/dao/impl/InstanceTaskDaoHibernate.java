package com.csoft.resource.cflow.dao.impl;

import com.csoft.resource.cflow.dao.InstanceTaskDao;
import com.csoft.resource.cflow.pojo.InstanceTask;
import org.hibernate.Criteria;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("instanceTaskDao")
public class InstanceTaskDaoHibernate
		extends GenericDaoHibernate<InstanceTask, Integer>
		implements InstanceTaskDao {

	public InstanceTaskDaoHibernate() {
		super(InstanceTask.class);
	}

	/**
	 * get the task's execte information by process instance id and task id.
	 * @param instanceProcessId process instance id
	 * @param taskId task id
	 * @return InstanceTask
	 * @throws Exception
	 */
	public InstanceTask getTaskInfo(String instanceProcessId, String taskId) throws Exception{
		Session session = getSessionFactory().getCurrentSession();
		Criteria cri = session.createCriteria(InstanceTask.class);
		cri.add(Restrictions.eq("instanceProcess.id", instanceProcessId));
		cri.add(Restrictions.eq("taskId",taskId)) ;
		List<InstanceTask> taskrList = cri.list() ;
		if(taskrList != null && !taskrList.isEmpty()){
			return taskrList.get(0) ;
		}
		return null ;
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
