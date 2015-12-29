package com.csoft.resource.cflow.config;

import com.csoft.resource.cflow.constants.ColumnValueTypeConstant;
import com.csoft.resource.cflow.constants.ProcessColumnTypeConstant;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

public class ProcessColumn {
    private String id ;
    private ProcessColumnTypeConstant columnTypeConstant ;
    private String name ;
    private boolean require ;
    private String value ;
    private ColumnValueTypeConstant columnValueTypeConstant ;

    static ProcessColumn parse(Element element) throws SAXException{
        ProcessColumn processColumn = new ProcessColumn() ;
        ProcessColumnTypeConstant columnType = null ;
        ColumnValueTypeConstant columnValueTypeConstant  = null ;

        if(element.getLocalName().equalsIgnoreCase("column")){
            if(element.getAttributes().getNamedItem("id") != null){
                processColumn.setId(element.getAttributes().getNamedItem("id").getNodeValue());
            }
            if(element.getAttributes().getNamedItem("type") != null){
                columnType = ProcessColumnTypeConstant.constantByCode(element.getAttributes().getNamedItem("type").getNodeValue()) ;
                processColumn.setColumnTypeConstant(columnType);
            }
            if(element.getAttributes().getNamedItem("name") != null){
                processColumn.setName(element.getAttributes().getNamedItem("name").getNodeValue());
            }
            if(element.getAttributes().getNamedItem("isRequire") != null){
                processColumn.setRequire(Boolean.getBoolean(element.getAttributes().getNamedItem("isRequire").getNodeValue()));
            }
            if(element.getAttributes().getNamedItem("value") != null){
                processColumn.setValue(element.getAttributes().getNamedItem("value").getNodeValue());
            }
            if(element.getAttributes().getNamedItem("valueCondition") != null){
                columnValueTypeConstant = ColumnValueTypeConstant.constantByCode(element.getAttributes().getNamedItem("valueCondition").getNodeValue()) ;
                processColumn.setColumnValueTypeConstant(columnValueTypeConstant);
            }
        }
        return processColumn ;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ProcessColumnTypeConstant getColumnTypeConstant() {
        return columnTypeConstant;
    }

    public void setColumnTypeConstant(ProcessColumnTypeConstant columnTypeConstant) {
        this.columnTypeConstant = columnTypeConstant;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isRequire() {
        return require;
    }

    public void setRequire(boolean require) {
        this.require = require;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public ColumnValueTypeConstant getColumnValueTypeConstant() {
        return columnValueTypeConstant;
    }

    public void setColumnValueTypeConstant(ColumnValueTypeConstant columnValueTypeConstant) {
        this.columnValueTypeConstant = columnValueTypeConstant;
    }
}
