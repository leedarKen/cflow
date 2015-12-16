package com.csoft.resource.cflow.context;

import com.csoft.resource.cflow.common.xml.XMLParser;
import com.csoft.resource.cflow.config.Process;
import com.csoft.resource.cflow.config.ProcessKeyInfo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;

public class ProcessLoad {

	public final static String PROCESS_CONFIG_PATH = Package.class.getResource("/process/").getPath() ;

	public static Process load(ProcessKeyInfo processKeyInfo){
		String fileName = processKeyInfo.getId() + "_" + processKeyInfo.getVersion() + ".xml" ;
		File processFile = new File(PROCESS_CONFIG_PATH +  fileName ) ;
		return load(processFile) ;
	}

	private static Process load(File file){
		try{
			File xsdFile = new File(PROCESS_CONFIG_PATH + "process.xsd") ;
			//URL url = new URL(PROCESS_CONFIG_PATH + "process.xsd") ;
			XMLParser<Process> parser  = XMLParser.getInstance(xsdFile.toURL());
			Process process = parser.parse(file.toURL(), Process.class);
			process.setColumnInfo() ;
			return process ;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null ;
	}

	//private static String filePath = ServletContext.getRealPath("/" ) ;
	public static void loadAllProcess() {
		try {
			ProcessContext context = ProcessContextFactory.getProcessContext() ;
			File file = new File(PROCESS_CONFIG_PATH) ;
			File[] files = file.listFiles(new FilenameFilter(){

				@Override
				public boolean accept(File dir, String name) {
					return name.endsWith(".xml");
				}
			});
			System.out.println("files size = " + files.length) ;
			for(File processFile : files){
				Process process = load(processFile) ;
				context.getProcessInstanceMap().put(getProcessKeyInfo(processFile), process) ;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static ProcessKeyInfo getProcessKeyInfo(File file) throws FileNotFoundException{
		if(file == null){
			throw new FileNotFoundException("file Not Find") ;
		}
		String fileName = file.getName() ;
		String id = fileName.substring(0, fileName.lastIndexOf("_")) ;
		String version = fileName.substring(fileName.lastIndexOf("_") + 1, fileName.length()-4) ;
		return new ProcessKeyInfo(id,version) ;
	}
}
