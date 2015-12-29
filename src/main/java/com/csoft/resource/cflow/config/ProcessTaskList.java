package com.csoft.resource.cflow.config;

import com.csoft.resource.cflow.constants.ColumnValueTypeConstant;
import com.csoft.resource.cflow.constants.ProcessColumnTypeConstant;
import com.csoft.resource.cflow.constants.ProcessTasktypeConstamts;
import com.csoft.resource.cflow.exception.ProcessException;
import net.sf.json.JSONObject;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

@SuppressWarnings("ALL")
public class ProcessTaskList {
	private Map<String, ProcessTask> processTaskMap ;
	private ProcessTask beginTask ;
	private ProcessTask endTask ;
	private ProcessTask nextTask ;


	public ProcessTask getBeninTask(){
		if(beginTask == null){
			beginTask = getProcessTask(ProcessTasktypeConstamts.Begin);
		}
		return beginTask ;
	}

	public ProcessTask getEndTask(){
		if(endTask == null){
			endTask = getProcessTask(ProcessTasktypeConstamts.End);
		}
		return endTask ;
	}

	private ProcessTask getProcessTask(ProcessTasktypeConstamts tasktype) {
		if(this.getProcessTaskMap() == null || this.getProcessTaskMap().isEmpty())
			return null ;

		for(Map.Entry<String, ProcessTask> entry: this.getProcessTaskMap().entrySet()){
			if(entry.getValue().getProcessTasktypeConstamts() == tasktype){
				return entry.getValue() ;
			}
		}
		return null ;
	}

	static ProcessTaskList parse(Element element) throws SAXException{
		ProcessTaskList processTaskList = new ProcessTaskList() ;
		Map<String, ProcessTask> processTaskMap = new TreeMap<String, ProcessTask>() ;
		ProcessTask task = null ;
		NodeList childs = null ;
		Node child = null ;

		if(!element.getLocalName().equalsIgnoreCase("taskList")){
			throw new SAXException("Element is not correct, it should be \"taskList\"") ;
		}

		childs = element.getChildNodes() ;
		for(int i = 0 ; i < childs.getLength() ; i++){
			child = childs.item(i) ;
			if(child.getNodeType() != Node.ELEMENT_NODE){
				continue;
			}
			if(child.getLocalName().equalsIgnoreCase("task")){
				task = ProcessTask.parse((Element) child) ;
				processTaskMap.put(task.getId(), task) ;
			}
		}
		processTaskList.setProcessTaskMap(processTaskMap);
		return processTaskList ;
	}

	public Map<String, ProcessTask> getProcessTaskMap() {
		return processTaskMap;
	}

	public void setProcessTaskMap(Map<String, ProcessTask> processTaskMap) {
		this.processTaskMap = processTaskMap;
	}
}
