package com.csoft.resource.cflow.config;

import com.csoft.resource.cflow.constants.ProcessActionConstamts;
import com.csoft.resource.cflow.constants.ProcessTasktypeConstamts;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.util.List;

public class ProcessTask {
    private String id ;
    private String name ;
    private ProcessActionConstamts actionConstamts ;
    private ProcessTasktypeConstamts processTasktypeConstamts ;
    private ProcessTaskExecutor processTaskExecutor ;
    private ProcessRuleList ruleList ;
    private ProcessInput processInput ;
    private ProcessSubmit submitColumn ;

    static ProcessTask parse(Element element) throws SAXException{
        ProcessTask processTask = null ;
        ProcessActionConstamts actionConstamts = null ;
        ProcessTasktypeConstamts processTasktypeConstamts = null ;

        ProcessTaskExecutor processTaskExecutor = null ;
        ProcessRuleList ruleList = null ;
        ProcessInput processInput = null ;
        ProcessSubmit submitColumn = null ;

        NodeList childs = null ;
        Node child = null ;

        if(!element.getLocalName().equalsIgnoreCase("task")){
            throw new SAXException("Element is not correct, it should be \"task\"") ;
        }

        if(element.getAttributes().getNamedItem("type") != null){
            String typeValue = element.getAttributes().getNamedItem("type").getNodeValue() ;
            processTasktypeConstamts = ProcessTasktypeConstamts.constantByCode(typeValue) ;
            if(processTasktypeConstamts == ProcessTasktypeConstamts.Begin){
                processTask = new ProcessBenginTask() ;
            }else if(processTasktypeConstamts == ProcessTasktypeConstamts.End){
                processTask = new ProcessEndTask() ;
            }else{
                processTask = new ProcessProcessingTask() ;
            }
            processTask.setProcessTasktypeConstamts(processTasktypeConstamts);
        }

        if(element.getAttributes().getNamedItem("id") != null){
            processTask.setId(element.getAttributes().getNamedItem("id").getNodeValue());
        }
        if(element.getAttributes().getNamedItem("name") != null){
            processTask.setName(element.getAttributes().getNamedItem("name").getNodeValue());
        }
        if(element.getAttributes().getNamedItem("action") != null){
            String actionValue = element.getAttributes().getNamedItem("action").getNodeValue() ;
            actionConstamts = ProcessActionConstamts.constantByCode(actionValue) ;
            processTask.setActionConstamts(actionConstamts);
        }


        childs = element.getChildNodes() ;
        for(int i = 0 ; i < childs.getLength() ; i++){
            child = childs.item(i) ;

            if(child.getNodeType() != Node.ELEMENT_NODE){
                continue;
            }

            if(child.getLocalName().equalsIgnoreCase("executor")){
                processTaskExecutor = ProcessTaskExecutor.parse((Element) child) ;
            }
            if(child.getLocalName().equalsIgnoreCase("input")){
                processInput = ProcessInput.parse((Element) child) ;
            }
            if(child.getLocalName().equalsIgnoreCase("submit")){
                submitColumn = ProcessSubmit.parse((Element) child) ;
            }
            if(child.getLocalName().equalsIgnoreCase("ruleList")){
                ruleList = ProcessRuleList.parse((Element) child) ;
            }

        }
        processTask.setProcessTaskExecutor(processTaskExecutor);
        processTask.setProcessInput(processInput);
        processTask.setRuleList(ruleList);
        processTask.setSubmitColumn(submitColumn);

        return processTask ;
    }
    private List<ProcessRule> processRuleList ;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ProcessActionConstamts getActionConstamts() {
        return actionConstamts;
    }

    public void setActionConstamts(ProcessActionConstamts actionConstamts) {
        this.actionConstamts = actionConstamts;
    }

    public ProcessTaskExecutor getProcessTaskExecutor() {
        return processTaskExecutor;
    }

    public void setProcessTaskExecutor(ProcessTaskExecutor processTaskExecutor) {
        this.processTaskExecutor = processTaskExecutor;
    }

    public ProcessTasktypeConstamts getProcessTasktypeConstamts() {
        return processTasktypeConstamts;
    }

    public void setProcessTasktypeConstamts(ProcessTasktypeConstamts processTasktypeConstamts) {
        this.processTasktypeConstamts = processTasktypeConstamts;
    }

    public ProcessRuleList getRuleList() {
        return ruleList;
    }

    public void setRuleList(ProcessRuleList ruleList) {
        this.ruleList = ruleList;
    }

    public ProcessInput getProcessInput() {
        return processInput;
    }

    public void setProcessInput(ProcessInput processInput) {
        this.processInput = processInput;
    }

    public ProcessSubmit getSubmitColumn() {
        return submitColumn;
    }

    public void setSubmitColumn(ProcessSubmit submitColumn) {
        this.submitColumn = submitColumn;
    }
}
