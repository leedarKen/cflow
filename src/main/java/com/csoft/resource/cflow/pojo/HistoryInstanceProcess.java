package com.csoft.resource.cflow.pojo;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "cf_history_instance_process")
public class HistoryInstanceProcess {
	private Integer id;
	private Integer processInstanceId;
	private String processId;
	private String processName;
	private String version;
	private Date startTime;
	private Integer startUser;
	private String currentTask;

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
	@Column(name = "process_instance_id")
	public Integer getProcessInstanceId() {
		return processInstanceId;
	}

	public void setProcessInstanceId(Integer processInstanceId) {
		this.processInstanceId = processInstanceId;
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
	@Column(name = "process_name")
	public String getProcessName() {
		return processName;
	}

	public void setProcessName(String processName) {
		this.processName = processName;
	}

	@Basic
	@Column(name = "version")
	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
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
	@Column(name = "start_user")
	public Integer getStartUser() {
		return startUser;
	}

	public void setStartUser(Integer startUser) {
		this.startUser = startUser;
	}

	@Basic
	@Column(name = "currentTask")
	public String getCurrentTask() {
		return currentTask;
	}

	public void setCurrentTask(String currentTask) {
		this.currentTask = currentTask;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		HistoryInstanceProcess that = (HistoryInstanceProcess) o;

		if (! id.equals(that.id)) return false;
		if (! processInstanceId.equals(that.processInstanceId)) return false;
		if (! startUser.equals(that.startUser)) return false;
		if (processId != null ? ! processId.equals(that.processId) : that.processId != null) return false;
		if (processName != null ? ! processName.equals(that.processName) : that.processName != null) return false;
		if (version != null ? ! version.equals(that.version) : that.version != null) return false;
		if (startTime != null ? ! startTime.equals(that.startTime) : that.startTime != null) return false;
		if (currentTask != null ? ! currentTask.equals(that.currentTask) : that.currentTask != null) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result =  (id ^ (id >>> 32));
		result = 31 * result + (processInstanceId ^ (processInstanceId >>> 32));
		result = 31 * result + (processId != null ? processId.hashCode() : 0);
		result = 31 * result + (processName != null ? processName.hashCode() : 0);
		result = 31 * result + (version != null ? version.hashCode() : 0);
		result = 31 * result + (startTime != null ? startTime.hashCode() : 0);
		result = 31 * result + (startUser ^ (startUser >>> 32));
		result = 31 * result + (currentTask != null ? currentTask.hashCode() : 0);
		return result;
	}
}
