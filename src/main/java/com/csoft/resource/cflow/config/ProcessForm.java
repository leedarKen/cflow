package com.csoft.resource.cflow.config;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.util.HashMap;
import java.util.Map;

public class ProcessForm {
	private Map<String, ProcessColumn> processColumns ;

	public Map<String, ProcessColumn> getProcessColumns() {
		return processColumns;
	}

	public void setProcessColumns(Map<String, ProcessColumn> processColumns) {
		this.processColumns = processColumns;
	}

	static ProcessForm parse(Element element) throws SAXException{
		ProcessForm processForm = new ProcessForm() ;
		Map<String, ProcessColumn> columns = new HashMap<String, ProcessColumn>() ;
		ProcessColumn processColumn = null ;
		NodeList childs = null ;
		Node child = null ;

		if(!element.getLocalName().equalsIgnoreCase("processForm")){
			throw new SAXException("Element is not correct, it should be \"processForm\"") ;
		}

		childs = element.getChildNodes() ;
		for(int i = 0 ; i < childs.getLength() ; i++){
			child = childs.item(i) ;
			if(child.getNodeType() != Node.ELEMENT_NODE){
				continue;
			}

			if(child.getLocalName().equalsIgnoreCase("column")){
				processColumn = ProcessColumn.parse((Element) child) ;
				columns.put(processColumn.getId(), processColumn) ;
			}
		}
		processForm.setProcessColumns(columns);
		return processForm ;
	}
}
