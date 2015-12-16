package com.csoft.resource.cflow.config;


public class ProcessKeyInfo {
	private String id ;
	private String version ;

	public ProcessKeyInfo(){}
	public ProcessKeyInfo(String id, String version){
		this.id = id;
		this.version = version ;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		int result = 17;
		result = 37 * result + id.hashCode();
		result = 37 * result + version.hashCode();
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if(! (obj instanceof  ProcessKeyInfo)){
			return false ;
		}
		ProcessKeyInfo keyInfo = (ProcessKeyInfo) obj ;
		return keyInfo.getId().equals(this.getId())
				&& keyInfo.getVersion().equals( version );
	}
}
