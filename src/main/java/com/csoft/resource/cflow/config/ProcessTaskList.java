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

	/**
	 *
	 * @param jsonData
	 * {
	 *      id(action):value(approve)
	 *      id(TravelAmount):value(4000)
	 *      id(Currency):Rmb
	 * }
	 *
	 * @return
	 */
	//TODO implement
	public ProcessTask getNextTask(ProcessTask task, String jsonData) throws Exception{

		JSONObject jsonObject = JSONObject.fromObject(jsonData) ;

		if(this.getProcessTaskMap() == null || this.getProcessTaskMap().isEmpty())
			return null ;

		ProcessRuleList ruleList = task.getRuleList() ;
		if(ruleList == null)
			throw new ProcessException("Rule List is empty, please check the rule configure.") ;


		Map<String, ProcessRule> rules = task.getRuleList().getRules() ;
		for(Map.Entry<String,ProcessRule> entry : rules.entrySet()){
			TaskCondition taskCondition = entry.getValue().getTaskCondition() ;
			Map<String, ProcessColumn> processColumnMap = taskCondition.getProcessColumnMap() ;
			boolean isPass = false ;
			for(Map.Entry<String, ProcessColumn> columnEntry : processColumnMap.entrySet()){
				String id = columnEntry.getValue().getId() ;
				String configValue = columnEntry.getValue().getValue() ;
				ProcessColumnTypeConstant columnTypeConstant = columnEntry.getValue().getColumnTypeConstant() ;
				ColumnValueTypeConstant columnValueTypeConstant = columnEntry.getValue().getColumnValueTypeConstant() ;

				//judge the column whether exist in json object. if not exist, will continue;
				if(!jsonObject.containsKey(id))
					continue;

				String resultValue = jsonObject.getString(id) ;
				if(configValue.equals("Success")){
					if(configValue.equalsIgnoreCase(resultValue)){
						isPass = true ;
						continue;
					}else{
						break ;
					}
				}
				if(columnTypeConstant == ProcessColumnTypeConstant.Date){
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:MM:ss") ;
					Date resultValueDate = null ;
					Date configeValueDate = null ;
					try {
						resultValueDate = sdf.parse(resultValue) ;
						configeValueDate = sdf.parse(configValue) ;
					} catch (ParseException e) {
						e.printStackTrace();
					}

					if(columnValueTypeConstant == ColumnValueTypeConstant.Equal){
						if(resultValueDate.compareTo(configeValueDate) == 0){
							isPass = true ;
						}
					}
					if(columnValueTypeConstant == ColumnValueTypeConstant.Greater){
						if(resultValueDate.compareTo(configeValueDate) > 0){
							isPass = true ;
						}
					}
					if(columnValueTypeConstant == ColumnValueTypeConstant.Smaller){
						if(resultValueDate.compareTo(configeValueDate) < 0){
							isPass = true ;
						}
					}
				}
				if(columnTypeConstant == ProcessColumnTypeConstant.String){
					if(columnValueTypeConstant == ColumnValueTypeConstant.Equal){
						if(resultValue.compareTo(configValue) == 0){
							isPass = true ;
						}
					}
					if(columnValueTypeConstant == ColumnValueTypeConstant.Greater){
						if(resultValue.compareTo(configValue) > 0){
							isPass = true ;
						}
					}
					if(columnValueTypeConstant == ColumnValueTypeConstant.Smaller){
						if(resultValue.compareTo(configValue) < 0){
							isPass = true ;
						}
					}
				}
				if(columnTypeConstant == ProcessColumnTypeConstant.Number){
					int resultValueInt = Integer.parseInt(resultValue) ;
					int configValueInt = Integer.parseInt(configValue) ;

					if(columnValueTypeConstant == ColumnValueTypeConstant.Equal){
						if(resultValueInt == configValueInt ){
							isPass = true ;
						}
					}
					if(columnValueTypeConstant == ColumnValueTypeConstant.Greater){
						if(resultValueInt > configValueInt){
							isPass = true ;
						}
					}
					if(columnValueTypeConstant == ColumnValueTypeConstant.Smaller){
						if(resultValueInt < configValueInt){
							isPass = true ;
						}
					}
				}
				//TODO CURRENCY

			}

			if(isPass == true){
				TaskTrigger trigger = entry.getValue().getTaskTrigger() ;
				if(trigger.getProcessTask().equalsIgnoreCase("end")){
					return null ;
				}else{
					return this.getTask(trigger.getProcessTask()) ;
				}
			}
		}
		return null ;
	}



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
	public ProcessTask getTask(String taskId){
		if(this.getProcessTaskMap() == null || this.getProcessTaskMap().isEmpty())
			return null ;

		for(Map.Entry<String, ProcessTask> entry: this.getProcessTaskMap().entrySet()){
			if(entry.getValue().getId().equals(taskId)){
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
