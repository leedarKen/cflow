package com.csoft.resource.cflow.service.inner.business.service.impl;

import com.csoft.resource.cflow.config.*;
import com.csoft.resource.cflow.config.Process;
import com.csoft.resource.cflow.constants.*;
import com.csoft.resource.cflow.context.ProcessContext;
import com.csoft.resource.cflow.context.ProcessContextFactory;
import com.csoft.resource.cflow.context.SpringContextUtil;
import com.csoft.resource.cflow.exception.ProcessException;
import com.csoft.resource.cflow.pojo.*;
import com.csoft.resource.cflow.service.inner.business.service.ProcessExecuotrManager;
import com.csoft.resource.cflow.service.inner.business.service.ProcessInstanceInnerManager;
import com.csoft.resource.cflow.service.inner.business.service.executor.ExecutorContext;
import com.csoft.resource.cflow.service.inner.entry.service.*;
import com.csoft.resource.cflow.util.DateUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


@Service("processInstanceInnerManager")
public class ProcessInstanceInnerManagerImpl implements ProcessInstanceInnerManager {

    @Autowired
    private ProcessContext context = ProcessContextFactory.getProcessContext();

    private InstanceProcessManager instanceProcessManager;

    private InstanceProcessNextUserMapManager instanceProcessNextUserMapManager;

    private InstanceTaskManager instanceTaskManager;

    private InstanceInputValueManager instanceInputValueManager;

    private ProcessExecuotrManager processExecuotrManager;

    private CategoryManager categoryManager;

    private ExecutorContext executorContext;

    private ApplicationContext applicationContext;

    public ProcessInstanceInnerManagerImpl() {
    }

    private void setApplication(){
        if(null == applicationContext){
            applicationContext = new ClassPathXmlApplicationContext(
                    new String[]{"classpath:config/spring/ApplicationContext-dao.xml" ,
                            "classpath:config/spring/ApplicationContext-service.xml" ,
                            "classpath:config/hibernate/hibernate.cfg.xml"});
            instanceProcessManager = (InstanceProcessManager)applicationContext.getBean("instanceProcessManager") ;
            instanceProcessNextUserMapManager = (InstanceProcessNextUserMapManager)applicationContext.getBean("instanceProcessNextUserMapManager") ;
            instanceTaskManager = (InstanceTaskManager)applicationContext.getBean("instanceTaskManager") ;
            instanceInputValueManager = (InstanceInputValueManager)applicationContext.getBean("instanceInputValueManager") ;
            processExecuotrManager = (ProcessExecuotrManager)applicationContext.getBean("processExecuotrManager") ;
            categoryManager = (CategoryManager)applicationContext.getBean("categoryManager") ;
            instanceProcessManager = (InstanceProcessManager)applicationContext.getBean("instanceProcessManager") ;
            executorContext = (ExecutorContext)applicationContext.getBean("executorContext") ;
        }
    }
    /**
     * start one process, return process instance's id
     * after execute success, engine will be calculate next executor, but the first task's user is the creator,
     * will save in table cf_instance_process_next_user_map
     *
     * @param keyInfo {
     *                processId:   bdm_travel,
     *                processVersion:  1.0.0
     *                }
     * @param data    {
     *                startUser:   startUserId,
     *                startDate:   startDate,
     *                executeResult:{Success|Approve|Reject|Finish},
     *                inputDate:   [
     *                {columnId:TravelAmount, columnValue:1000},
     *                ]
     *                }
     * @return String processInstanceId
     * {
     * processInstanceId:   processInstanceId,
     * nextTaskId:  nextTaskId,
     * nextTaskName:    nextTaskName
     * }
     */
    @Override
    public String startProcess(ProcessKeyInfo keyInfo, String data) throws Exception {
        setApplication() ;

        Process process = context.getProcess(keyInfo);
        ProcessTaskList taskList = process.getProcessTaskList();
        ProcessTask currentTask = taskList.getBeninTask();

        JSONObject jsonObject = JSONObject.fromObject(data);
        String startDate = jsonObject.getString("startDate");

        InstanceProcess instance = new InstanceProcess();
        instance.setProcessId(process.getId());
        instance.setVersion(process.getVersion());
        instance.setStartTime(DateUtil.parse(startDate));
        instance.setStartUser(jsonObject.getInt("startUser"));
        instance.setCurrentTask(currentTask.getId());
        instance.setProcessName(process.getName());
        instance.setProcessType(1);

        InstanceProcess instanceProcess = instanceProcessManager.save(instance);

        InstanceProcessNextUserMap nextUserMap = new InstanceProcessNextUserMap();
        nextUserMap.setNextUserId(jsonObject.getInt("startUser"));
        nextUserMap.setInstanceProcess(instanceProcess);

        instanceProcessNextUserMapManager.save(nextUserMap);


        //execute first task
        JSONObject executeTaskData = new JSONObject();
        executeTaskData.put("taskId", currentTask.getId());
        executeTaskData.put("createTime", startDate);
        executeTaskData.put("createUser", jsonObject.get("startUser"));
        executeTaskData.put("executeResult", jsonObject.get("executeResult"));
        executeTaskData.put("inputData", jsonObject.get("inputData"));

        return executeTask(instanceProcess.getId(), executeTaskData.toString());
    }

    /**
     * execute task, return current task's info and next task's task name
     * <p/>
     * after execute success, engine will be calculate next executor, use the different strategy
     * <p/>
     * if executeResult is reject,the current task will turn to previous task
     * <p/>
     * for the execute result, if action is input, result will be success
     * if the task is the last one, return filed nextTaskName will be "Finished"
     *
     * @param processInstanceId
     * @param jsonValue         {
     *                          taskId:  taskId,
     *                          createTime:   createTime,
     *                          createUser:  createUser,
     *                          executeResult:{success|approve|reject|finish},
     *                          inputData:   [
     *                          {columnId:TravelAmount, columnValue:1000, columnType:String},
     *                          {columnId:TravelAmount, columnValue:1000, columnType:String,CurrencyType:CNY} if is currency
     *                          ]
     *                          }
     * @return {
     * currentTaskId:  currentTaskId,
     * currentTaskName:    currentTaskName,
     * nextTaskId: nextTaskId,
     * nextTaskName:   nextTaskName,
     * nextExecutor:   nextExecutor
     * }
     */
    @Override
    public String executeTask(Integer processInstanceId, String jsonValue) throws Exception {
        setApplication() ;

        JSONObject jsonObject = JSONObject.fromObject(jsonValue);
        JSONObject toVerifyNextTaskData = new JSONObject();

        InstanceProcess instanceProcess = instanceProcessManager.get(processInstanceId);

        JSONArray inputArray = null;

        if (jsonObject.containsKey("inputData")) {
            inputArray = jsonObject.getJSONArray("inputData");
        }

        if (inputArray != null) {
            for (int i = 0; i < inputArray.size(); i++) {
                JSONObject obj = inputArray.getJSONObject(i);
                toVerifyNextTaskData.put(obj.get("columnId"), obj.get("columnValue"));
                if (obj.getString("columnType").equals("Currency")) {
                    toVerifyNextTaskData.put("CurrencyType", obj.get("CurrencyType"));
                }
            }
        }
        toVerifyNextTaskData.put("action", jsonObject.get("executeResult"));

        InstanceProcess processInstance = instanceProcessManager.get(processInstanceId);

        Process process = context.getProcess(new ProcessKeyInfo(processInstance.getProcessId(), processInstance.getVersion()));

        ProcessTask currentTask = this.getTask(process, jsonObject.getString("taskId"));

        ProcessTask nextTask = this.getNextTask(process, currentTask, toVerifyNextTaskData.toString());

        if (nextTask == null) {
            throw new Exception("Next task is null");
        }

        JSONArray userArray = new JSONArray();

        //update process
        instanceProcessManager.updateProcessInstanceCurrentTask(processInstance, nextTask);

        instanceTaskManager.saveInstanceTask(jsonObject, instanceProcess, process, currentTask);


        //insert into the input value
        instanceInputValueManager.saveInputValue(instanceProcess, inputArray, process, this);

        //update next task task executor
        JSONObject executorJson = new JSONObject();
        executorJson.put("createUser", jsonObject.getString("createUser"));
        executorJson.put("instanceProcessId", processInstanceId);

        executorContext.setApplicationContext(this.applicationContext);
        List<Integer> executorList = new ArrayList<Integer>();
        if (!(nextTask instanceof ProcessEndTask)) {
            executorList = executorContext.executorList(nextTask, executorJson);
        }


        if (!executorList.isEmpty()) {
            userArray.addAll(executorList);
            instanceProcessNextUserMapManager.deleteProcessExecutorUser(processInstanceId);

            for (int userId : executorList) {
                InstanceProcessNextUserMap nextUserMap = new InstanceProcessNextUserMap();
                nextUserMap.setNextUserId(userId);
                nextUserMap.setInstanceProcess(instanceProcess);
                instanceProcessNextUserMapManager.save(nextUserMap);
            }
        }


        JSONObject result = new JSONObject();
        result.put("currentTaskId", currentTask.getId());
        result.put("currentTaskName", currentTask.getName());
        result.put("nextTaskId", nextTask.getId());
        result.put("nextTaskName", nextTask.getName());
        if (userArray.size() > 0) {
            result.put("nextExecutor", userArray.toString());
        } else {
            result.put("nextExecutor", "");
        }
        return result.toString();
    }


    /**
     * get the task's execte information by process instance id and task id.
     *
     * @param instanceProcessId process instance id
     * @param taskId            task id
     * @return task info
     * {
     * taskId: taskId,
     * taskName: taskName,
     * createTime: 12/05/2015 10:23:22,
     * createUser : 5222,
     * executeResult: Approve,
     * }
     * @throws Exception
     */
    @Override
    public String getTaskInfo(String instanceProcessId, String taskId) {
        try {
            InstanceTask task = instanceTaskManager.getTaskInfo(instanceProcessId, taskId);
            JSONObject obj = new JSONObject();
            obj.put("id", task.getId());
            obj.put("taskId", task.getTaskId());
            obj.put("taskName", task.getTaskName());
            obj.put("processId", task.getProcessId());
            obj.put("createTime", DateUtil.formatDate(task.getCreateTime()));
            obj.put("createUser", task.getCreateUser());
            obj.put("executeResult", task.getExecuteResult());
            return obj.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * @param jsonData {
     *                 id(action):value(approve)
     *                 id(TravelAmount):value(4000)
     *                 id(Currency):CNY
     *                 }
     * @return
     */
    //TODO implement
    public ProcessTask getNextTask(Process process, ProcessTask task, String jsonData) throws Exception {

        JSONObject jsonObject = JSONObject.fromObject(jsonData);

        if (process.getProcessTaskList().getProcessTaskMap() == null || process.getProcessTaskList().getProcessTaskMap().isEmpty())
            return null;

        ProcessRuleList ruleList = task.getRuleList();
        if (ruleList == null)
            throw new ProcessException("Rule List is empty, please check the rule configure.");


        Map<String, ProcessRule> rules = task.getRuleList().getRules();
        for (Map.Entry<String, ProcessRule> entry : rules.entrySet()) {
            TaskCondition taskCondition = entry.getValue().getTaskCondition();
            Map<String, ProcessColumn> processColumnMap = taskCondition.getProcessColumnMap();
            boolean isPass = false;
            for (Map.Entry<String, ProcessColumn> columnEntry : processColumnMap.entrySet()) {
                String id = columnEntry.getValue().getId();
                String configValue = columnEntry.getValue().getValue();

                ProcessColumnTypeConstant columnTypeConstant = columnEntry.getValue().getColumnTypeConstant();
                ColumnValueTypeConstant columnValueTypeConstant = columnEntry.getValue().getColumnValueTypeConstant();

                //judge the column whether exist in json object. if not exist, will continue;
                if (!jsonObject.containsKey(id))
                    continue;

                String resultValue = jsonObject.getString(id);
                if (configValue.equals(ExecuteResultConstant.Success.desc()) || configValue.equals(ExecuteResultConstant.Approve.desc())) {
                    if (configValue.equalsIgnoreCase(resultValue)) {
                        isPass = true;
                        continue;
                    } else {
                        break;
                    }
                }

                if (configValue.equals(ExecuteResultConstant.Reject)) {
                    isPass = true;
                    break;
                }

                if (configValue.startsWith("FromDataBase")) {
                    if (columnTypeConstant == ProcessColumnTypeConstant.Currency) {
                        CurrencyConstant currencyConstant = CurrencyConstant.constantByCode(jsonObject.getString("CurrencyType"));
                        Category category = categoryManager.getCategory(id, columnTypeConstant.desc(), currencyConstant.desc());
                        configValue = category.getValue();
                    } else {
                        Category category = categoryManager.getCategory(id, columnTypeConstant.desc());
                        configValue = category.getValue();
                    }
                }
                if (columnTypeConstant == ProcessColumnTypeConstant.Date) {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:MM:ss");
                    Date resultValueDate = null;
                    Date configeValueDate = null;
                    try {
                        resultValueDate = sdf.parse(resultValue);
                        configeValueDate = sdf.parse(configValue);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    if (columnValueTypeConstant == ColumnValueTypeConstant.Equal) {
                        if (resultValueDate.compareTo(configeValueDate) == 0) {
                            isPass = true;
                            continue;
                        } else {
                            isPass = false;
                            continue;
                        }
                    }
                    if (columnValueTypeConstant == ColumnValueTypeConstant.Greater) {
                        if (resultValueDate.compareTo(configeValueDate) > 0) {
                            isPass = true;
                            continue;
                        } else {
                            isPass = false;
                            continue;
                        }
                    }
                    if (columnValueTypeConstant == ColumnValueTypeConstant.Smaller) {
                        if (resultValueDate.compareTo(configeValueDate) < 0) {
                            isPass = true;
                            continue;
                        } else {
                            isPass = false;
                            continue;
                        }
                    }
                }
                if (columnTypeConstant == ProcessColumnTypeConstant.String) {
                    if (columnValueTypeConstant == ColumnValueTypeConstant.Equal) {
                        if (resultValue.compareTo(configValue) == 0) {
                            isPass = true;
                            continue;
                        } else {
                            isPass = false;
                            continue;
                        }
                    }
                    if (columnValueTypeConstant == ColumnValueTypeConstant.Greater) {
                        if (resultValue.compareTo(configValue) > 0) {
                            isPass = true;
                            continue;
                        } else {
                            isPass = false;
                            continue;
                        }
                    }
                    if (columnValueTypeConstant == ColumnValueTypeConstant.Smaller) {
                        if (resultValue.compareTo(configValue) < 0) {
                            isPass = true;
                            continue;
                        } else {
                            isPass = false;
                            continue;
                        }
                    }
                }
                if (columnTypeConstant == ProcessColumnTypeConstant.Currency) {
                    int resultValueInt = Integer.parseInt(resultValue);
                    int configValueInt = Integer.parseInt(configValue);

                    if (columnValueTypeConstant == ColumnValueTypeConstant.Equal) {
                        if (resultValueInt == configValueInt) {
                            isPass = true;
                            continue;
                        } else {
                            isPass = false;
                            continue;
                        }
                    }
                    if (columnValueTypeConstant == ColumnValueTypeConstant.Greater) {
                        if (resultValueInt > configValueInt) {
                            isPass = true;
                            continue;
                        } else {
                            isPass = false;
                            continue;
                        }
                    }
                    if (columnValueTypeConstant == ColumnValueTypeConstant.Smaller) {
                        if (resultValueInt < configValueInt) {
                            isPass = true;
                            continue;
                        } else {
                            isPass = false;
                            continue;
                        }
                    }
                }
                if (columnTypeConstant == ProcessColumnTypeConstant.Number) {
                    int resultValueInt = Integer.parseInt(resultValue);
                    int configValueInt = Integer.parseInt(configValue);

                    if (columnValueTypeConstant == ColumnValueTypeConstant.Equal) {
                        if (resultValueInt == configValueInt) {
                            isPass = true;
                            continue;
                        } else {
                            isPass = false;
                            continue;
                        }
                    }
                    if (columnValueTypeConstant == ColumnValueTypeConstant.Greater) {
                        if (resultValueInt > configValueInt) {
                            isPass = true;
                            continue;
                        } else {
                            isPass = false;
                            continue;
                        }
                    }
                    if (columnValueTypeConstant == ColumnValueTypeConstant.Smaller) {
                        if (resultValueInt < configValueInt) {
                            isPass = true;
                            continue;
                        } else {
                            isPass = false;
                            continue;
                        }
                    }
                }

            }

            if (isPass == true) {
                TaskTrigger trigger = entry.getValue().getTaskTrigger();
                if (trigger.getProcessTask().equalsIgnoreCase("end")) {
                    return new ProcessEndTask();
                } else {
                    return this.getTask(process, trigger.getProcessTask());
                }
            }
        }
        return null;
    }

    public ProcessTask getTask(Process process, String taskId) {
        if (process.getProcessTaskList().getProcessTaskMap() == null || process.getProcessTaskList().getProcessTaskMap().isEmpty())
            return null;

        for (Map.Entry<String, ProcessTask> entry : process.getProcessTaskList().getProcessTaskMap().entrySet()) {
            if (entry.getValue().getId().equals(taskId)) {
                return entry.getValue();
            }
        }
        return null;
    }

    public InstanceProcessManager getInstanceProcessManager() {
        return instanceProcessManager;
    }

    public void setInstanceProcessManager(InstanceProcessManager instanceProcessManager) {
        this.instanceProcessManager = instanceProcessManager;
    }

    public InstanceProcessNextUserMapManager getInstanceProcessNextUserMapManager() {
        return instanceProcessNextUserMapManager;
    }

    public void setInstanceProcessNextUserMapManager(InstanceProcessNextUserMapManager instanceProcessNextUserMapManager) {
        this.instanceProcessNextUserMapManager = instanceProcessNextUserMapManager;
    }

    public InstanceTaskManager getInstanceTaskManager() {
        return instanceTaskManager;
    }

    public void setInstanceTaskManager(InstanceTaskManager instanceTaskManager) {
        this.instanceTaskManager = instanceTaskManager;
    }

    public InstanceInputValueManager getInstanceInputValueManager() {
        return instanceInputValueManager;
    }

    public void setInstanceInputValueManager(InstanceInputValueManager instanceInputValueManager) {
        this.instanceInputValueManager = instanceInputValueManager;
    }

    public ProcessExecuotrManager getProcessExecuotrManager() {
        return processExecuotrManager;
    }

    public void setProcessExecuotrManager(ProcessExecuotrManager processExecuotrManager) {
        this.processExecuotrManager = processExecuotrManager;
    }

    public CategoryManager getCategoryManager() {
        return categoryManager;
    }

    public void setCategoryManager(CategoryManager categoryManager) {
        this.categoryManager = categoryManager;
    }

    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

}
