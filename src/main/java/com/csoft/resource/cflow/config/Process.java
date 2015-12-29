package com.csoft.resource.cflow.config;

import com.csoft.resource.cflow.exception.ProcessException;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.util.Map;

public class Process {
    private String id;
    private String name;
    private String version;
    private String startUserRole;

    private ProcessForm processForm;
    private ProcessTaskList processTaskList;


  /*  @Override
    public String toString() {
        //JSONObject jsonObject = JSONObject.fromObject(this) ;
       // return jsonObject.toString() ;
    }*/

    public void setColumnInfo() throws Exception{
        // get the definition column info
        Map<String, ProcessColumn> processColumns = this.getProcessForm().getProcessColumns() ;

        // get the task's rule column
        Map<String, ProcessTask> processTaskMap = this.getProcessTaskList().getProcessTaskMap() ;
        for(Map.Entry<String, ProcessTask> taskEntry : processTaskMap.entrySet()) {
            ProcessRuleList ruleList = taskEntry.getValue().getRuleList();
            if (ruleList == null)
                throw new ProcessException("Rule List is empty, please check the rule configure.");


            Map<String, ProcessRule> rules = ruleList.getRules();
            for (Map.Entry<String, ProcessRule> entry : rules.entrySet()) {
                TaskCondition taskCondition = entry.getValue().getTaskCondition();
                Map<String, ProcessColumn> processColumnMap = taskCondition.getProcessColumnMap();
                for (Map.Entry<String, ProcessColumn> columnEntry : processColumnMap.entrySet()) {
                    String id = columnEntry.getValue().getId();
                    if(processColumns.containsKey(id)){
                        ProcessColumn processColumn = columnEntry.getValue() ;

                        ProcessColumn column = processColumns.get(id) ;
                       // column.setValue(columnEntry.getValue().getValue());
                       // column.setColumnValueTypeConstant(columnEntry.getValue().getColumnValueTypeConstant());

                        processColumn.setColumnTypeConstant(column.getColumnTypeConstant());
                        processColumn.setName(column.getName());

                        //columnEntry.getValue()  = column ;
                        processColumnMap.put(id, processColumn) ;
                    }
                }
            }
        }
    }

    public static Process parse(Element element) throws SAXException{
        Process process = new Process() ;
        NodeList childs = null ;
        Node child = null ;

        if (!element.getLocalName().equalsIgnoreCase("process")){
            throw new SAXException("Element is not correct, it should be \"process\"") ;
        }

        if(element.getAttributes().getNamedItem("id") != null){
            process.setId(element.getAttributes().getNamedItem("id").getNodeValue());
        }
        if(element.getAttributes().getNamedItem("name") != null){
            process.setName(element.getAttributes().getNamedItem("name").getNodeValue());
        }
        if(element.getAttributes().getNamedItem("startUserRole") != null){
            process.setStartUserRole(element.getAttributes().getNamedItem("startUserRole").getNodeValue());
        }
        if(element.getAttributes().getNamedItem("version") != null){
            process.setVersion(element.getAttributes().getNamedItem("version").getNodeValue());
        }


        childs = element.getChildNodes() ;
        for(int i = 0 ; i < childs.getLength() ; i++){
            child = childs.item(i) ;
            if(child.getNodeType() != Node.ELEMENT_NODE){
                continue;
            }

            if(child.getLocalName().equalsIgnoreCase("processForm")){
                process.setProcessForm(ProcessForm.parse((Element) child));
            } else if(child.getLocalName().equalsIgnoreCase("taskList")){
                process.setProcessTaskList(ProcessTaskList.parse((Element) child));
            }
        }
        return process ;
    }

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

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getStartUserRole() {
        return startUserRole;
    }

    public void setStartUserRole(String startUserRole) {
        this.startUserRole = startUserRole;
    }

    public ProcessForm getProcessForm() {
        return processForm;
    }

    public void setProcessForm(ProcessForm processForm) {
        this.processForm = processForm;
    }

    public ProcessTaskList getProcessTaskList() {
        return processTaskList;
    }

    public void setProcessTaskList(ProcessTaskList processTaskList) {
        this.processTaskList = processTaskList;
    }



}
