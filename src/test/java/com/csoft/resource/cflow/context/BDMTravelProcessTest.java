package com.csoft.resource.cflow.context;

import com.csoft.resource.cflow.config.Process;
import com.csoft.resource.cflow.config.ProcessColumn;
import com.csoft.resource.cflow.config.ProcessForm;
import com.csoft.resource.cflow.config.ProcessKeyInfo;
import com.csoft.resource.cflow.constants.ProcessColumnTypeConstant;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class BDMTravelProcessTest {
	private ProcessKeyInfo keyInfo = null ;
	@Before
	public void setUp() throws Exception {
		String id = "bdm_travel_process" ;
		String version = "1.0.0" ;
		keyInfo = new ProcessKeyInfo(id, version) ;
	}
	@Test
	public void testLoadForProcessAttribute() throws  Exception{
		Process process = ProcessLoad.load(keyInfo) ;
		assertEquals("bdm_travel", process.getId());
		assertEquals("1.0.0", process.getVersion());
		assertEquals("BDM", process.getStartUserRole());
	}
	@Test
	public void testLoadForProcessChild() throws Exception{
		Process process = ProcessLoad.load(keyInfo) ;
		assertNotNull(process.getProcessForm());
		assertNotNull(process.getProcessTaskList());
	}
	@Test
	public void testLoadForProcessForm() throws  Exception{
		Map<String, ProcessColumn> map = getStringProcessColumnMap();
		assertEquals(6, map.size());

	}

	@Test
	public void testLoadForProcessColumnForTravelAmount(){
		Map<String, ProcessColumn> map = getStringProcessColumnMap();

		assertEquals("TravelAmount",map.get("TravelAmount").getId());
		assertEquals(ProcessColumnTypeConstant.Number,map.get("TravelAmount").getColumnTypeConstant());
		assertEquals("Travel Amount",map.get("TravelAmount").getName());
	}

	@Test
	public void testLoadForProcessColumnForDepartureDate(){
		Map<String, ProcessColumn> map = getStringProcessColumnMap();

		assertEquals("DepartureDate",map.get("DepartureDate").getId());
		assertEquals(ProcessColumnTypeConstant.Date,map.get("DepartureDate").getColumnTypeConstant());
		assertEquals("Departure Date",map.get("DepartureDate").getName());
	}

	private Map<String, ProcessColumn> getStringProcessColumnMap() {
		Process process = ProcessLoad.load(keyInfo) ;
		ProcessForm form = process.getProcessForm() ;
		return form.getProcessColumns();
	}

	@Test
	public void testLoadForProcessColumnForReturnDate(){
		Map<String, ProcessColumn> map = getStringProcessColumnMap();

		assertEquals("ReturnDate",map.get("ReturnDate").getId());
		assertEquals(ProcessColumnTypeConstant.Date,map.get("ReturnDate").getColumnTypeConstant());
		assertEquals("Return Date",map.get("ReturnDate").getName());
	}
	@Test
	public void testLoadForProcessColumnForDestination(){
		Map<String, ProcessColumn> map = getStringProcessColumnMap();

		assertEquals("Destination",map.get("Destination").getId());
		assertEquals(ProcessColumnTypeConstant.String,map.get("Destination").getColumnTypeConstant());
		assertEquals("Destination",map.get("Destination").getName());
	}
	@Test
	public void testLoadForProcessColumnForReason(){
		Map<String, ProcessColumn> map = getStringProcessColumnMap();

		assertEquals("Reason",map.get("Reason").getId());
		assertEquals(ProcessColumnTypeConstant.String,map.get("Reason").getColumnTypeConstant());
		assertEquals("Reason",map.get("Reason").getName());
	}
	@Test
	public void testLoadForProcessColumnForoutOfChina(){
		Map<String, ProcessColumn> map = getStringProcessColumnMap();

		assertEquals("outOfChina",map.get("outOfChina").getId());
		assertEquals(ProcessColumnTypeConstant.String,map.get("outOfChina").getColumnTypeConstant());
		assertEquals("Out Of China",map.get("outOfChina").getName());
	}
}
