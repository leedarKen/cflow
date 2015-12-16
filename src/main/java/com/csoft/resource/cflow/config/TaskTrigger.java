package com.csoft.resource.cflow.config;

import org.w3c.dom.Element;
import org.xml.sax.SAXException;

public class TaskTrigger {

	private String processTask ;

	static TaskTrigger parse(Element element) throws SAXException{
		TaskTrigger taskTrigger = new TaskTrigger() ;

		if(!element.getLocalName().equalsIgnoreCase("trigger")){
			throw new SAXException("Element is not correct, it should be \"trigger\"") ;
		}

		if(element.getAttributes().getNamedItem("ref") != null){
			taskTrigger.setProcessTask(element.getAttributes().getNamedItem("ref").getNodeValue());
		}
		return taskTrigger ;
	}

	public String getProcessTask() {
		return processTask;
	}

	public void setProcessTask(String processTask) {
		this.processTask = processTask;
	}
}
