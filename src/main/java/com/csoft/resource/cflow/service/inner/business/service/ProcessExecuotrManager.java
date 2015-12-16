package com.csoft.resource.cflow.service.inner.business.service;

import java.util.List;

public interface ProcessExecuotrManager {

	List<Integer> getUserLeadar(Long userId) ;

	List<Integer> getUserByRole(String roleName) ;

}
