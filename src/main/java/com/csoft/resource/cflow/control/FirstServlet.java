package com.csoft.resource.cflow.control;

import com.csoft.resource.cflow.config.ProcessKeyInfo;
import com.csoft.resource.cflow.service.ProcessInstance;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by ken.kang on 2015/12/23.
 */
public class FirstServlet extends HttpServlet {
    @Autowired
    private ProcessInstance processInstance ;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            todo() ;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        todo() ;
    }

    private void todo() {
        JSONObject keyInfo = new JSONObject() ; //3267
        keyInfo.put("id","bdm_travel_pre-application_process") ;
        keyInfo.put("version","1.0.0") ;
        JSONObject obj = new JSONObject() ; //3267
        obj.put("startUser",5222) ;
        obj.put("startDate","12/10/2015 12:00:00") ;
        obj.put("executeResult","Success") ;
        obj.put("startDate","12/10/2015 12:00:00") ;

        JSONArray jsonArray = new JSONArray() ;
        JSONObject columnJSON = new JSONObject() ;
        columnJSON.put("columnId","TravelAmount") ;
        columnJSON.put("columnValue","4000") ;
        columnJSON.put("columnType","RMB") ;
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
        executorArray.add(3267);
        ecpectObj.put("nextExecutor",executorArray) ;
        //processInstanceInnerManager.setApplicationContext(context);
        //execute
        try {
            String result = processInstance.startProcess(keyInfo.toString(),obj.toString()) ;
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
