package com.csoft.resource.cflow.context;

import com.csoft.resource.cflow.config.Process;
import com.csoft.resource.cflow.config.ProcessKeyInfo;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.Map;

import static org.junit.Assert.*;


public class ProcessLoadTest {
	private ProcessKeyInfo keyInfo = null ;
	private ProcessContext context = null ;

	@Before
	public void setUp() throws Exception {
		String id = "bdm_travel_process" ;
		String version = "1.0.0" ;
		keyInfo = new ProcessKeyInfo(id, version) ;
	}

	@After
	public void tearDown() throws Exception {

	}

	@Test
	public void testLoad() throws Exception {

		Process process = ProcessLoad.load(keyInfo) ;
		assertEquals("bdm_travel", process.getId());
	}



	@Test
	public void testLoadAllProcess() throws Exception {
		ProcessLoad.loadAllProcess();
		ProcessContext context = ProcessContextFactory.getProcessContext() ;
		Map<ProcessKeyInfo, Process>  map = context.getProcessInstanceMap() ;
		assertEquals(1,context.getProcessInstanceMap().size());
	}
	@Test
	public void testGetProcessKeyInfo() throws  Exception{
		String id = "bdm_travel_process" ;
		String version = "1.0.0" ;
		File file = new File("bdm_travel_process_1.0.0.xml") ;

		ProcessKeyInfo keyInfo = new ProcessKeyInfo(id, version) ;

		ProcessKeyInfo resultKey = ProcessLoad.getProcessKeyInfo(file) ;

		assertEquals(keyInfo.getId(), resultKey.getId());
		assertEquals(keyInfo.getVersion(), resultKey.getVersion());
	}


	public void testProcessConifgPath(){
		//assertNull(Package.class);
		//System.out.print(ProcessLoad.PROCESS_CONFIG_PATH);
		//System.out.print(Process.class.getResource("\\target\\classes\\com\\csoft\\resource\\cflow\\xml\\").getPath());
		//assertEquals("/D:/workspace_ken/cflow/target/test-classes/com/csoft/resource/cflow/xml/",
		//		ProcessLoad.PROCESS_CONFIG_PATH);
	}
}