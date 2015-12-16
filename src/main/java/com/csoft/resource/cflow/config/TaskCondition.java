package com.csoft.resource.cflow.config;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.util.HashMap;
import java.util.Map;

public class TaskCondition {

	private Map<String, ProcessColumn> processColumnMap ;

	public Map<String, ProcessColumn> getProcessColumnMap() {
		return processColumnMap;
	}

	public void setProcessColumnMap(Map<String, ProcessColumn> processColumnMap) {
		this.processColumnMap = processColumnMap;
	}

	static TaskCondition parse(Element element) throws SAXException {
		TaskCondition taskCondition = new TaskCondition() ;
		Map<String, ProcessColumn> processColumnMap = new HashMap<String, ProcessColumn>(5) ;
		ProcessColumn processColumn = null ;

		NodeList childs = null ;
		Node child = null ;

		if(!element.getLocalName().equalsIgnoreCase("condition")){
			throw new SAXException("Element is not correct, it should be\"condition\"") ;
		}

		childs = element.getChildNodes() ;
		for(int i = 0 ; i < childs.getLength() ; i++){
			child = childs.item(i) ;

			if(child.getLocalName().equalsIgnoreCase("column")){
				processColumn = ProcessColumn.parse((Element) child) ;
				processColumnMap.put(processColumn.getId(), processColumn) ;
			}
		}
		taskCondition.setProcessColumnMap(processColumnMap);

		return taskCondition ;
	}
}
