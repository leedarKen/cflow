package com.csoft.resource.cflow.config;

import com.csoft.resource.cflow.constants.TaskExecutorStrategy;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.util.ArrayList;
import java.util.List;

public class ProcessTaskExecutor {
	private TaskExecutorStrategy taskExecutorStrategy ;
	private String strategyValue ;

	private List<Integer> userList ;

	static ProcessTaskExecutor parse(Element element) throws SAXException {
		ProcessTaskExecutor executor = new ProcessTaskExecutor() ;
		TaskExecutorStrategy strategy = null ;
		List<Integer> userList = new ArrayList<Integer>(5) ;

		NodeList childs = null;
		Node child = null ;

		if(!element.getLocalName().equalsIgnoreCase("executor")){
			throw new SAXException("Element is not correct, it should be \"executor\"") ;
		}

		if(element.getAttributes().getNamedItem("strategy") != null){
			String strategyValue = element.getAttributes().getNamedItem("strategy").getNodeValue() ;
			strategy = TaskExecutorStrategy.constantByCode(strategyValue) ;
			executor.setTaskExecutorStrategy(strategy) ;
		}
		if(element.getAttributes().getNamedItem("value") != null){
			executor.setStrategyValue(element.getAttributes().getNamedItem("value").getNodeValue());
		}

		childs = element.getChildNodes() ;
		for(int i = 0 ; i < childs.getLength() ; i++){
			child = childs.item(i) ;

			if(child.getNodeType() != Node.ELEMENT_NODE){
				continue ;
			}

			if(child.getLocalName().equalsIgnoreCase("user_id")){
				Element userElement = (Element) child ;

				if(userElement.getAttributes().getNamedItem("id") != null){
					userList.add(Integer.parseInt(userElement.getAttributes().getNamedItem("id").getNodeValue()));
				}
			}
		}
		executor.setUserList(userList);

		return executor ;
	}

	public TaskExecutorStrategy getTaskExecutorStrategy() {
		return taskExecutorStrategy;
	}

	public void setTaskExecutorStrategy(TaskExecutorStrategy taskExecutorStrategy) {
		this.taskExecutorStrategy = taskExecutorStrategy;
	}

	public String getStrategyValue() {
		return strategyValue;
	}

	public void setStrategyValue(String strategyValue) {
		this.strategyValue = strategyValue;
	}

	public List<Integer> getUserList() {
		return userList;
	}

	public void setUserList(List<Integer> userList) {
		this.userList = userList;
	}
}
