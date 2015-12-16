package com.csoft.resource.cflow.pojo;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "cf_history_instance_task")
public class HistoryInstanceTask {
	private Integer id;
	private Integer processTaskId;
	private Integer processInstanceId;
	private String taskId;
	private String taskName;
	private Integer processId;
	private Date startTime;
	private Integer createUser;
	private String executeResult;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Basic
	@Column(name = "process_task_id")
	public Integer getProcessTaskId() {
		return processTaskId;
	}

	public void setProcessTaskId(Integer processTaskId) {
		this.processTaskId = processTaskId;
	}

	@Basic
	@Column(name = "process_instance_id")
	public Integer getProcessInstanceId() {
		return processInstanceId;
	}

	public void setProcessInstanceId(Integer processInstanceId) {
		this.processInstanceId = processInstanceId;
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
	public Integer getProcessId() {
		return processId;
	}

	public void setProcessId(Integer processId) {
		this.processId = processId;
	}

	@Basic
	@Column(name = "start_time")
	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		HistoryInstanceTask that = (HistoryInstanceTask) o;

		if (! id.equals(that.id)) return false;
		if (! processTaskId.equals(that.processTaskId)) return false;
		if (! processInstanceId.equals(that.processInstanceId)) return false;
		if (! processId.equals(that.processId)) return false;
		if (! createUser.equals(that.createUser)) return false;
		if (taskId != null ? ! taskId.equals(that.taskId) : that.taskId != null) return false;
		if (taskName != null ? ! taskName.equals(that.taskName) : that.taskName != null) return false;
		if (startTime != null ? ! startTime.equals(that.startTime) : that.startTime != null) return false;
		if (executeResult != null ? ! executeResult.equals(that.executeResult) : that.executeResult != null)
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = (id ^ (id >>> 32));
		result = 31 * result + (processTaskId ^ (processTaskId >>> 32));
		result = 31 * result + (processInstanceId ^ (processInstanceId >>> 32));
		result = 31 * result + (taskId != null ? taskId.hashCode() : 0);
		result = 31 * result + (taskName != null ? taskName.hashCode() : 0);
		result = 31 * result + (processId ^ (processId >>> 32));
		result = 31 * result + (startTime != null ? startTime.hashCode() : 0);
		result = 31 * result + (createUser ^ (createUser >>> 32));
		result = 31 * result + (executeResult != null ? executeResult.hashCode() : 0);
		return result;
	}
}
