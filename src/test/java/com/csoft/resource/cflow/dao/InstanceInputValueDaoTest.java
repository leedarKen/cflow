package com.csoft.resource.cflow.dao;

import com.csoft.resource.cflow.BaseJunit4Test;
import com.csoft.resource.cflow.pojo.InstanceInputValue;
import com.csoft.resource.cflow.pojo.InstanceProcess;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.annotation.Resource;

import static org.junit.Assert.*;


public class InstanceInputValueDaoTest extends BaseJunit4Test {

    @Resource
    private InstanceInputValueDao instanceInputValueDao ;

    @Test
    @Transactional
    @Rollback(false)
    public void testGetInstanceInputValue() throws Exception {
        InstanceInputValue saveModel = new InstanceInputValue() ;

        InstanceProcess process = new InstanceProcess() ;
        process.setProcessId("BDM_Travel");
        process.setProcessName("BDM Travel");
        process.setId(78);
       // saveModel.setId(106);
        saveModel.setCfKey("TravelAmount");
        saveModel.setCfValue("100");
        saveModel.setCfKeyType("Currency");
        saveModel.setInstanceProcess(process);
        saveModel.setProcessId(process.getProcessId());
        saveModel.setProcessName(process.getProcessName());

        instanceInputValueDao.save(saveModel) ;

        InstanceInputValue instanceInputValue = instanceInputValueDao.getInstanceInputValue(1,saveModel.getCfKey()) ;

        Assert.isTrue(instanceInputValue.getCfKey().equals(saveModel.getCfKey()));
        Assert.isTrue(instanceInputValue.getCfKeyType().equals(saveModel.getCfKeyType()));
        Assert.isTrue(instanceInputValue.getCfValue().equals(saveModel.getCfValue()));
    }
}