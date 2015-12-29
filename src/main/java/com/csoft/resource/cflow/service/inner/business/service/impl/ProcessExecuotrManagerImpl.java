package com.csoft.resource.cflow.service.inner.business.service.impl;

import com.csoft.resource.cflow.service.inner.business.service.ProcessExecuotrManager;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("processExecuotrManager")
public class ProcessExecuotrManagerImpl implements ProcessExecuotrManager {

	//TODO implement the code here is the test.
	@Override
	public List<Integer> getUserLeadar(Long userId) {
		List<Integer> list = new ArrayList<Integer>() ;
		list.add(4736) ;
		return list ;
	}
	//TODO implement the code , here is the test.
	@Override
	public List<Integer> getUserByRole(String roleName) {
		List<Integer> list = new ArrayList<Integer>() ;
		list.add(4736) ;
		return list ;
	}
}
