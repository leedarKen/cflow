package com.csoft.resource.cflow.config;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ProcessRule {

	private String id ;
	private TaskCondition taskCondition;
	private TaskTrigger taskTrigger;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public TaskCondition getTaskCondition() {
		return taskCondition;
	}

	public void setTaskCondition(TaskCondition taskCondition) {
		this.taskCondition = taskCondition;
	}

	public TaskTrigger getTaskTrigger() {
		return taskTrigger;
	}

	public void setTaskTrigger(TaskTrigger taskTrigger) {
		this.taskTrigger = taskTrigger;
	}

	static ProcessRule parse(Element element) throws SAXException {
		ProcessRule rule = new ProcessRule() ;
		TaskCondition taskCondition = null;
		TaskTrigger taskTrigger = null;

		NodeList childs = null ;
		Node child = null ;

		if(!element.getLocalName().equalsIgnoreCase("rule")){
			throw new SAXException("Element is not correct, it should be \"rule\"") ;
		}

		if(element.getAttributes().getNamedItem("id") != null){
			rule.setId(element.getAttributes().getNamedItem("id").getNodeValue());
		}

		childs = element.getChildNodes() ;
		for(int i = 0 ; i < childs.getLength(); i++){
			child = childs.item(i) ;

			if(child.getLocalName().equalsIgnoreCase("condition")){
				taskCondition = TaskCondition.parse((Element) child) ;
				rule.setTaskCondition(taskCondition);
			}

			if(child.getLocalName().equalsIgnoreCase("trigger")){
				taskTrigger = TaskTrigger.parse((Element) child) ;
				rule.setTaskTrigger(taskTrigger);
			}
		}
		return rule ;
	}
}
