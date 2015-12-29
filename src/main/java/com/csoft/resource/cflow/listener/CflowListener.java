package com.csoft.resource.cflow.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

class CflowListener implements  ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		try{

			//TODO when server startup, if load all process?
			//ProcessLoad.loadAllProcess() ;
		}catch(Exception e){
			sce.getServletContext().log(CflowListener.class.getName(), e);
			throw new RuntimeException(e);
		}

	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {

	}

}
