package com.csoft.resource.cflow.module;

import com.csoft.resource.cflow.constants.ProcessActionConstamts;

import java.util.List;

/**
 * Created by ken.kang on 2015/11/24.
 */
public class ProcessTask {
    private Integer id ;
    private String name ;
    private ProcessActionConstamts actionConstamts ;

    private List<ProcessResult> resultList ;
    private List<ProcessColumn> inputColumn ;
}
