package com.csoft.resource.cflow.service.inner.business.service.impl;

import com.csoft.resource.cflow.config.ProcessKeyInfo;
import com.csoft.resource.cflow.service.inner.business.service.ProcessInstanceInnerManager;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.*;


public class ProcessInstanceInnerManagerImplTest {
	@Autowired
	private ProcessInstanceInnerManager processInstanceInnerManager;
	ApplicationContext context ;
	@Before
	public void setUp() throws Exception {
		context = new ClassPathXmlApplicationContext(
				new String[]{"classpath:config/spring/ApplicationContext-dao.xml" ,
						"classpath:config/spring/ApplicationContext-service.xml" ,
						"classpath:config/hibernate/hibernate.cfg.xml"});
		System.out.println("===========================================================================") ;
		processInstanceInnerManager = (ProcessInstanceInnerManager)context.getBean("processInstanceInnerManager") ;
	}

	@Test
	public void testStartProcess() throws Exception {

		/*
		*
		 processInstanceId:   processInstanceId,
	  *     nextTaskId:  nextTaskId,
	  *     nextTaskName:    nextTaskName
		*/
		//bdm_travel_pre-application_process_1.0.0
		//prepare data
		ProcessKeyInfo keyInfo = new ProcessKeyInfo("bdm_travel_pre-application_process","1.0.0") ;

		JSONObject obj = new JSONObject() ; //3267
		obj.put("startUser",5222) ;
		obj.put("startDate","12/10/2015 12:00:00") ;
		obj.put("executeResult","Success") ;
		obj.put("startDate","12/10/2015 12:00:00") ;

		JSONArray jsonArray = new JSONArray() ;
		JSONObject columnJSON = new JSONObject() ;
		columnJSON.put("columnId","TravelAmount") ;
		columnJSON.put("columnValue","3500") ;
		columnJSON.put("columnType","Currency") ;
		columnJSON.put("CurrencyType","3") ;
		jsonArray.add(columnJSON);
		columnJSON = new JSONObject() ;
		columnJSON.put("columnId","outOfChina") ;
		columnJSON.put("columnValue","False") ;
		columnJSON.put("columnType","Boolean") ;
		jsonArray.add(columnJSON) ;

		obj.put("inputData",jsonArray) ;
		/*  {
			*      currentTaskId:  currentTaskId,
			*      currentTaskName:    currentTaskName,
			*      nextTaskId: nextTaskId,
			*      nextTaskName:   nextTaskName,
			*      nextExecutor:   nextExecutor
					*  } */
		//prepare expect data
		JSONObject ecpectObj = new JSONObject() ;
		ecpectObj.put("currentTaskId","apply_travel") ;
		ecpectObj.put("currentTaskName","Apply Travel") ;
		ecpectObj.put("nextTaskId","bdm_leader_approve") ;
		ecpectObj.put("nextTaskName","BDM Leader Approve") ;

		JSONArray executorArray = new JSONArray() ;
		executorArray.add(4736);
		ecpectObj.put("nextExecutor",executorArray) ;
		processInstanceInnerManager.setApplicationContext(context);
		//execute
		String result = processInstanceInnerManager.startProcess(keyInfo,obj.toString()) ;

		assertEquals(ecpectObj.toString(), result );

	}


	@Test
	public void testExecuteSecondTask() throws Exception {
		/* @param processInstanceId
				* @param taskId
				* @param jsonValue
				* {
				*     taskId:  taskId,
		*     createTime:   createTime,
		*     createUser:  createUser,
		*     executeResult:{success|approve|reject|finish},
		*     inputData:   [
		*         {columnId:TravelAmount, columnValue:1000, columnType:String},
		*     ]
		* }
		*
		*  @return
		*  {
			*      currentTaskId:  currentTaskId,
			*      currentTaskName:    currentTaskName,
			*      nextTaskId: nextTaskId,
			*      nextTaskName:   nextTaskName,
			*      nextExecutor:   nextExecutor
					*  }
		*/
		//prepare data
		Integer processInstanceId = 64 ;
		//String taskId = "apply_travel" ;
		JSONObject jsonObject = new JSONObject() ;
		jsonObject.put("taskId","bdm_leader_approve") ;
		jsonObject.put("createTime","12/14/2015 12:00:00") ;
		jsonObject.put("createUser",3267) ;
		jsonObject.put("executeResult","Approve") ;

		JSONArray jsonArray = new JSONArray() ;
		JSONObject columnJSON = new JSONObject() ;
		columnJSON.put("columnId","TravelAmount") ;
		columnJSON.put("columnValue","3500") ;
		columnJSON.put("columnType","Currency") ;
		columnJSON.put("CurrencyType","3") ;
		jsonArray.add(columnJSON);
		columnJSON = new JSONObject() ;
		columnJSON.put("columnId","outOfChina") ;
		columnJSON.put("columnValue","False") ;
		columnJSON.put("columnType","Boolean") ;
		jsonArray.add(columnJSON) ;

		jsonObject.put("inputData",jsonArray) ;

		// prepare expect data
		JSONObject expectObj = new JSONObject() ;
		expectObj.put("currentTaskId","bdm_leader_approve") ;
		expectObj.put("currentTaskName","BDM Leader Approve") ;
		expectObj.put("nextTaskId","end") ;
		expectObj.put("nextTaskName","end") ;

		JSONArray executorArray = new JSONArray() ;
		executorArray.add(3267);
		expectObj.put("nextExecutor",executorArray) ;

		//processInstanceInnerManager.setApplicationContext(context);

		String jsonValue = jsonObject.toString() ;
		String result = processInstanceInnerManager.executeTask( processInstanceId, jsonValue) ;

		assertEquals(expectObj.toString(), result);
	}
}