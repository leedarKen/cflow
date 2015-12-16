package com.csoft.resource.cflow.pojo;

import javax.persistence.*;

@Entity
@Table(name = "cf_instance_process_next_user_map")
public class InstanceProcessNextUserMap {
	private Integer id;
	private InstanceProcess instanceProcess;
	private Integer nextUserId;

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
	@Column(name = "next_user_id")
	public Integer getNextUserId() {
		return nextUserId;
	}

	public void setNextUserId(Integer nextUserId) {
		this.nextUserId = nextUserId;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		InstanceProcessNextUserMap that = (InstanceProcessNextUserMap) o;

		if (! id.equals(that.id)) return false;
		if (!instanceProcess.equals(that.instanceProcess)) return false;
		return nextUserId.equals(that.nextUserId);

	}

	@Override
	public int hashCode() {
		int result = (id ^ (id >>> 32));
		result = 31 * result + (instanceProcess != null ? instanceProcess.hashCode() : 0);
		result = 31 * result + (nextUserId ^ (nextUserId >>> 32));
		return result;
	}

}
