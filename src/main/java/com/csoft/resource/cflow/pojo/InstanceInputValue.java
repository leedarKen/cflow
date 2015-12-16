package com.csoft.resource.cflow.pojo;

import javax.persistence.*;

@Entity
@Table(name = "cf_instance_input_value")
public class InstanceInputValue {
	private Integer id;
	private InstanceProcess instanceProcess ;
	private String processId;
	private String processName;
	private String cfKey;
	private String cfKeyType;
	private String cfValue;

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
	@Column(name = "cf_key")
	public String getCfKey() {
		return cfKey;
	}

	public void setCfKey(String cfKey) {
		this.cfKey = cfKey;
	}

	@Basic
	@Column(name = "cf_key_type")
	public String getCfKeyType() {
		return cfKeyType;
	}

	public void setCfKeyType(String cfKeyType) {
		this.cfKeyType = cfKeyType;
	}

	@Basic
	@Column(name = "cf_value")
	public String getCfValue() {
		return cfValue;
	}

	public void setCfValue(String cfValue) {
		this.cfValue = cfValue;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		InstanceInputValue that = (InstanceInputValue) o;

		if (! id.equals(that.id)) return false;
		if (!instanceProcess.equals(that.instanceProcess)) return false;
		if (processId != null ? ! processId.equals(that.processId) : that.processId != null) return false;
		if (processName != null ? ! processName.equals(that.processName) : that.processName != null) return false;
		if (cfKey != null ? ! cfKey.equals(that.cfKey) : that.cfKey != null) return false;
		if (cfKeyType != null ? ! cfKeyType.equals(that.cfKeyType) : that.cfKeyType != null) return false;
		if (cfValue != null ? ! cfValue.equals(that.cfValue) : that.cfValue != null) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = (id ^ (id >>> 32));
		result = 31 * result +  (instanceProcess != null ? instanceProcess.hashCode() : 0);
		result = 31 * result + (processId != null ? processId.hashCode() : 0);
		result = 31 * result + (processName != null ? processName.hashCode() : 0);
		result = 31 * result + (cfKey != null ? cfKey.hashCode() : 0);
		result = 31 * result + (cfKeyType != null ? cfKeyType.hashCode() : 0);
		result = 31 * result + (cfValue != null ? cfValue.hashCode() : 0);
		return result;
	}


}
