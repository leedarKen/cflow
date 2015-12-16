package com.csoft.resource.cflow.config;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.util.HashMap;
import java.util.Map;

public class ProcessRuleList {
	private Map<String, ProcessRule> rules ;

	static ProcessRuleList parse(Element element) throws SAXException {
		ProcessRuleList processRuleList = new ProcessRuleList() ;
		Map<String, ProcessRule> rules = new HashMap<String, ProcessRule>(10) ;
		ProcessRule rule = new ProcessRule() ;

		NodeList childs = null ;
		Node child = null ;

		if(!element.getLocalName().equalsIgnoreCase("ruleList")){
			throw new SAXException("Element is not correct, it should be \"ruleList\"") ;
		}

		childs = element.getChildNodes() ;
		for(int i = 0 ; i < childs.getLength() ; i++){
			child = childs.item(i) ;

			if(child.getNodeType() != Node.ELEMENT_NODE){
				continue;
			}

			if(child.getLocalName().equalsIgnoreCase("rule")){
				rule = ProcessRule.parse((Element) child) ;
				rules.put(rule.getId(), rule) ;
			}
		}
		processRuleList.setRules(rules);
		return processRuleList ;
	}

	public Map<String, ProcessRule> getRules() {
		return rules;
	}

	public void setRules(Map<String, ProcessRule> rules) {
		this.rules = rules;
	}
}
