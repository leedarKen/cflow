package com.csoft.resource.cflow.pojo;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "cf_instance_task")
public class InstanceTask {
	private Integer id;
	private InstanceProcess instanceProcess;
	private String taskId;
	private String taskName;
	private String processId;
	private Date createTime;
	private Integer createUser;
	private String executeResult;
	private Integer taskSequence ;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "process_instance_id")
	public InstanceProcess getInstanceProcess() {
		return instanceProcess;
	}

	public void setInstanceProcess(InstanceProcess instanceProcess) {
		this.instanceProcess = instanceProcess;
	}
	@Basic
	@Column(name = "task_id")
	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	@Basic
	@Column(name = "task_name")
	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	@Basic
	@Column(name = "process_id")
	public String getProcessId() {
		return processId;
	}

	public void setProcessId(String processId) {
		this.processId = processId;
	}

	@Basic
	@Column(name = "create_time")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Basic
	@Column(name = "create_user")
	public Integer getCreateUser() {
		return createUser;
	}

	public void setCreateUser(Integer createUser) {
		this.createUser = createUser;
	}

	@Basic
	@Column(name = "execute_result")
	public String getExecuteResult() {
		return executeResult;
	}

	public void setExecuteResult(String executeResult) {
		this.executeResult = executeResult;
	}

	@Basic
	@Column(name = "task_sequence")
	public Integer getTaskSequence() {
		return taskSequence;
	}

	public void setTaskSequence(Integer taskSequence) {
		this.taskSequence = taskSequence;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		InstanceTask that = (InstanceTask) o;

		if (! id.equals(that.id)) return false;
		if (!instanceProcess.equals(that.instanceProcess)) return false;
		if (! processId.equals(that.processId)) return false;
		if (! createUser.equals(that.createUser)) return false;
		if (taskId != null ? ! taskId.equals(that.taskId) : that.taskId != null) return false;
		if (taskName != null ? ! taskName.equals(that.taskName) : that.taskName != null) return false;
		if (createTime != null ? ! createTime.equals(that.createTime) : that.createTime != null) return false;
		if (executeResult != null ? ! executeResult.equals(that.executeResult) : that.executeResult != null)
			return false;
		if(! taskSequence.equals(that.taskSequence)) return false ;

		return true;
	}

	@Override
	public int hashCode() {
		int result = (id ^ (id >>> 32));
		result = 31 * result + (instanceProcess != null ? instanceProcess.hashCode() : 0);
		result = 31 * result + (taskId != null ? taskId.hashCode() : 0);
		result = 31 * result + (taskName != null ? taskName.hashCode() : 0);
		result = 31 * result + (processId != null ? processId.hashCode() : 0);
		result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
		result = 31 * result + (createUser ^ (createUser >>> 32));
		result = 31 * result + (executeResult != null ? executeResult.hashCode() : 0);
		result = 31 * result + taskSequence ^ (taskSequence >>> 32) ;
		return result;
	}

}
