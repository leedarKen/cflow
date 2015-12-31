package com.csoft.resource.cflow.service.inner.entry.service.impl;

import com.csoft.resource.cflow.config.Process;
import com.csoft.resource.cflow.dao.InstanceInputValueDao;
import com.csoft.resource.cflow.pojo.InstanceInputValue;
import com.csoft.resource.cflow.pojo.InstanceProcess;
import com.csoft.resource.cflow.service.inner.business.service.impl.ProcessInstanceInnerManagerImpl;
import com.csoft.resource.cflow.service.inner.entry.service.InstanceInputValueManager;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("instanceInputValueManager")
public class InstanceInputValueManagerImpl
		extends GenericManagerImpl<InstanceInputValue, Integer>
		implements InstanceInputValueManager {

    @Autowired
	private InstanceInputValueDao instanceInputValueDao ;

	@Autowired
	public InstanceInputValueManagerImpl(InstanceInputValueDao instanceInputValueDao){
		super(instanceInputValueDao) ;
		this.instanceInputValueDao = instanceInputValueDao ;
	}

	@Override
	public InstanceInputValue getInstanceInputValue(Integer instanceProcessId, String columnId) {
		return instanceInputValueDao.getInstanceInputValue(instanceProcessId, columnId) ;
	}

    @Override
    public void saveInputValue(InstanceProcess instanceProcess, JSONArray inputArray, Process process) {
        if (inputArray != null) {
            for (int i = 0; i < inputArray.size(); i++) {
                JSONObject obj = inputArray.getJSONObject(i);

                //if exist in the database, update it
                InstanceInputValue inputData = instanceInputValueDao.getInstanceInputValue(instanceProcess.getId(), obj.getString("columnId")) ;
                if(inputData != null){
                    inputData.setCfValue(obj.getString("columnValue"));
                    update(inputData);
                    continue;
                }
                inputData = new InstanceInputValue() ;
                inputData.setCfKey(obj.getString("columnId"));
                inputData.setCfValue(obj.getString("columnValue"));
                inputData.setCfKeyType(obj.getString("columnType"));
                inputData.setProcessId(process.getId());
                inputData.setInstanceProcess(instanceProcess);
                inputData.setProcessName(process.getName());
                save(inputData);
            }
        }
    }
}
