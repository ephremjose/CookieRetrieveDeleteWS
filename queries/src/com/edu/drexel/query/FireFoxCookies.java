package com.edu.drexel.query;

import java.util.ArrayList;
import java.util.HashMap;



import org.apache.log4j.Logger;

import com.edu.drexel.crds.v1.Exception_Element1;
import com.edu.drexel.crds.v1.Exception_Element2;
import com.edu.drexel.crds.v1.ExportFireFoxCookiesRequest;
import com.edu.drexel.crds.v1.ExportFireFoxCookiesResponse;
import com.edu.drexel.crds.v1.FireFoxCookiesReport;
import com.edu.drexel.crds.v1.GetFireFoxCookiesRequest;
import com.edu.drexel.crds.v1.GetFireFoxCookiesResponse;
import com.edu.drexel.crds.v1.DeleteFireFoxCookiesRequest;
import com.edu.drexel.crds.v1.DeleteFireFoxCookiesResponse;
import com.edu.drexel.crds.v1.FireFoxCookiesList;
import com.edu.drexel.crds.v1.Exception_Element;
import com.edu.drexel.dao.ExportFireFoxCookies;
import com.edu.drexel.dao.FireFoxCookiesDao;
import com.edu.drexel.vo.FireFoxVO;

/*
 * FireFoxCookies class acts as the context layer. The DAO layer is called from this class 
 * and the response gets populated.
 */

public class FireFoxCookies {
	
	/*
	 * constructResponse method is called for the GetFireFoxCookiesRequest.
	 * 
	 */
	public GetFireFoxCookiesResponse constructResponse(GetFireFoxCookiesRequest requestObject)
	{
    	System.out.println("Reached the GetMapDataSummary Class for Sync call.. ");
    	
    	String serviceName = "CRDService";
		String operationName = "GetFireFoxCookies";
		System.out.println("inside GetFireFoxCookies.constructResponse method");
		
		
		GetFireFoxCookiesResponse response = new GetFireFoxCookiesResponse();
		HashMap resHm = new HashMap();
		String inputBaseDomain = 	requestObject.getBaseDomain();
		
		System.out.println(" Input Base Domain : " + inputBaseDomain);
		
		ArrayList<FireFoxVO> fireFoxListVO = new ArrayList<FireFoxVO>();
		Exception_Element ex = new Exception_Element();
		
		String baseDomain = requestObject.getBaseDomain();
		
		//Checking for error condition.
		if(baseDomain == null || "".equalsIgnoreCase(baseDomain))
		{
			
			System.out.println(" Got request with no baseDomain. It has to be either ALL or with a value. ");
			
			ex.setError("001");
			ex.setMessage("BaseDomain in request cannot be empty.");
			response.setException(ex);
			
			return response;
			
		}
		
		//Initializing the DAO class.
		FireFoxCookiesDao ffCookiesDao = new FireFoxCookiesDao();
		
		try
		{
				
			//Calling the getFireFoxCookieDetails method of the DAO class. 
			fireFoxListVO = ffCookiesDao.getFireFoxCookieDetails(requestObject);
			
			if(fireFoxListVO!=null && fireFoxListVO.size()>0)
			{
				//Response is not null. Populating it. 
				
				FireFoxCookiesList[] ffCookieArray = new FireFoxCookiesList[fireFoxListVO.size()];				
				
				System.out.println("ffCookieArray.length : " + ffCookieArray.length);
									
				int listIndex = 0;
				for(FireFoxVO ffCookieVO : fireFoxListVO)
				{
					ffCookieArray[listIndex] = new FireFoxCookiesList();
					
					ffCookieArray[listIndex].setID(ffCookieVO.getId());
					ffCookieArray[listIndex].setBaseDomain(ffCookieVO.getBaseDomain());
					ffCookieArray[listIndex].setName(ffCookieVO.getName());
					ffCookieArray[listIndex].setValue(ffCookieVO.getValue());
					ffCookieArray[listIndex].setCreationTime(ffCookieVO.getCreationTime());
					ffCookieArray[listIndex].setExpiry(ffCookieVO.getExpiry());
					ffCookieArray[listIndex].setHost(ffCookieVO.getHost());
					ffCookieArray[listIndex].setLastAccessed(ffCookieVO.getLastAccessed());
					
					listIndex++;
										
				}
				response.setFireFoxCookiesList(ffCookieArray);
			}
			
			else
			{
				System.out.println(" No records found for input Base Domain. ");
				
				ex.setError("000");
				ex.setMessage("No records found for input Base Domain.");
				response.setException(ex);
				
				return response;
				
			}

		}catch(Exception e){
			System.out.println("Exception caught in FireFoxCookies.constructResponse method ");
			System.out.println(e.getMessage());
		}finally{        	        
			
		}
		
		System.out.println(" Leaving FireFoxCookies.constructResponse method. ");
		return response;
}
	/*
	 * deleteFireFoxCookies method is called for DeleteFireFoxCookiesRequest.
	 * 
	 */
	public DeleteFireFoxCookiesResponse deleteFireFoxCookies(DeleteFireFoxCookiesRequest requestObject)
	{
		
		System.out.println("Reached the deleteFireFoxCookies Class for Sync call.. ");
    	
    	String serviceName = "CRDService";
		String operationName = "DeleteFireFoxCookies";
		System.out.println("inside FireFoxCookies.deleteFireFoxCookies method");
		
		int no_of_records_deleted = 0;
		
		DeleteFireFoxCookiesResponse response = new DeleteFireFoxCookiesResponse();
		HashMap resHm = new HashMap();
		
		Exception_Element2 ex = new Exception_Element2();
		
		String baseDomain = requestObject.getBaseDomain();
		System.out.println(" Input Base Domain : " + baseDomain);
		
		//Checking for error condition.
		if(baseDomain == null || "".equalsIgnoreCase(baseDomain))
		{
			
			System.out.println(" Got request with no baseDomain. It has to be either ALL or with a value. ");
			ex.setError("001");
			ex.setMessage("BaseDomain in request cannot be empty.");
			
			response.setException(ex);
			return response;
			
		}
		
		FireFoxCookiesDao ffCookiesDao = new FireFoxCookiesDao();
		
		try
		{
						
			no_of_records_deleted = ffCookiesDao.deleteFireFoxCookieDetails(requestObject);
			
			if(no_of_records_deleted>0)
			{
				//Deletion successful. Setting response.
				response.setStatus("SUCCESS");
				response.setNumberOfRowsDeleted(String.valueOf(no_of_records_deleted));
			}
			
			else
			{
				//No records deleted. 
				
				System.out.println(" No records deleted for input Base Domain. ");
				ex.setError("000");
				ex.setMessage("No records deleted for input Base Domain.");
				
				response.setException(ex);
				return response;
				
			}

		}catch(Exception e){
			
			System.out.println("Exception caught in FireFoxCookies.constructResponse method ");
			System.out.println(e.getMessage());
			
		}finally{        	        
			
		}
		
		System.out.println(" Leaving FireFoxCookies.constructResponse method. ");
		return response;
		
		
		
	}
	
	
	/*
	 * exportFireFoxCookies is called for ExportFireFoxCookiesRequest. 
	 * 
	 */
	public ExportFireFoxCookiesResponse exportFireFoxCookies(ExportFireFoxCookiesRequest requestObject)
	{
		
		System.out.println("Reached the exportFireFoxCookies Class for Sync call.. ");
    	
    	String serviceName = "CRDService";
		String operationName = "ExportFireFoxCookies";
		System.out.println("inside FireFoxCookies.exportFireFoxCookies method");
		
		String url = "";
		ExportFireFoxCookiesResponse response = new ExportFireFoxCookiesResponse();
		HashMap resHm = new HashMap();
					
		Exception_Element1 ex = new Exception_Element1();
		FireFoxCookiesReport ffCookieReport = new FireFoxCookiesReport();
		
		//Initializing the DAO file. 
		ExportFireFoxCookies expFFCookies = new ExportFireFoxCookies();
		
		
		try
		{
			
			//Calling the CreateExcelFile method from DAO file. 
			url = expFFCookies.CreateExcelFile();
			
			if(url!=null && url.length()>0)
			{
				System.out.println(" Received url from DAO file. Setting in the response. " + url);				
				ffCookieReport.setUrl(url);
			}
			
			else
			{
				//Setting error response. 
				
				System.out.println(" No records exported. Target Cookie table is empty. ");
				ex.setError("000");
				ex.setMessage(" No records exported. Target Cookie table is empty.");
				
				response.setException(ex);
				return response;
				
			}
			
			response.setFireFoxCookiesReport(ffCookieReport);

		}catch(Exception e){
			System.out.println("Exception caught in FireFoxCookies.constructResponse method ");
			System.out.println(e.getMessage());
		}finally{        	        
			
		}
		
		System.out.println(" Leaving FireFoxCookies.constructResponse method. ");
		return response;
		
		
		
	}
	
    
}
