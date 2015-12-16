package com.csoft.resource.cflow.config;

import com.csoft.resource.cflow.context.ProcessContext;
import com.csoft.resource.cflow.context.ProcessContextFactory;
import net.sf.json.JSONObject;
import org.junit.Test;

import static org.junit.Assert.*;

public class ProcessTest {

	private ProcessContext processContext = ProcessContextFactory.getProcessContext() ;


	@Test
	public void testToString() throws Exception {

		ProcessKeyInfo key = new ProcessKeyInfo("bdm_travel_process", "1.0.0") ;
		Process bdm_travel = processContext.getProcess(key) ;
		assertNotNull(bdm_travel);
		JSONObject jsonObject = JSONObject.fromObject(bdm_travel) ;
		//assertEquals(jsonObject.toString(), bdm_travel);

		System.out.println(jsonObject.toString()) ;
	}
}