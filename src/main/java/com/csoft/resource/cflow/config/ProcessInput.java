package com.csoft.resource.cflow.config;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.util.HashMap;
import java.util.Map;

public class ProcessInput {
	private Map<String, ProcessColumn> processColumns ;

	static ProcessInput parse(Element element) throws SAXException{
		ProcessInput processInput = new ProcessInput() ;
		ProcessColumn processColumn = null ;
		Map<String, ProcessColumn> columns = new HashMap<String, ProcessColumn>() ;

		NodeList childs = null ;

		if(!element.getLocalName().equalsIgnoreCase("input")){
			throw new SAXException("Element is not correct, it should be \"input\"") ;
		}

		childs = element.getChildNodes() ;
		for(int i = 0 ; i < childs.getLength() ; i++){
			Node child = childs.item(i);

			if(child.getNodeType() != Node.ELEMENT_NODE){
				continue;
			}

			if(child.getLocalName().equalsIgnoreCase("column")){
				processColumn = ProcessColumn.parse((Element) child) ;
				columns.put(processColumn.getId(), processColumn) ;
			}
		}
		processInput.setProcessColumns(columns);

		return processInput ;
	}

	public Map<String, ProcessColumn> getProcessColumns() {
		return processColumns;
	}

	public void setProcessColumns(Map<String, ProcessColumn> processColumns) {
		this.processColumns = processColumns;
	}
}
