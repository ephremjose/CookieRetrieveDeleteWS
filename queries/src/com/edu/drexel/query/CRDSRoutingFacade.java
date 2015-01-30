package com.edu.drexel.query;


import java.util.HashMap;


import com.edu.drexel.crds.v1.ExportFireFoxCookiesRequest;
import com.edu.drexel.crds.v1.ExportFireFoxCookiesResponse;
import com.edu.drexel.crds.v1.GetFireFoxCookiesRequest;
import com.edu.drexel.crds.v1.GetFireFoxCookiesResponse;
import com.edu.drexel.crds.v1.DeleteFireFoxCookiesRequest;
import com.edu.drexel.crds.v1.DeleteFireFoxCookiesResponse;

/*
 * CRDSRoutingFacade class acts as the router class that checks for the input request and 
 * calls the appropriate methods.
 */

public class CRDSRoutingFacade {
	
	/*
	 * processFireFoxCookies is called for checking input request.
	 * 
	 */
	public HashMap processFireFoxCookies(HashMap hm)
    {
		System.out.println("\n***In CRDSRoutingFacade - execute***");
        
        HashMap result = new HashMap();
        HashMap requestHash = (HashMap)hm.get("Request");
        HashMap responseHM = new HashMap();
        Object request = (Object) requestHash.get("requestObject");
        String transaction = (String)requestHash.get("transaction"); //transactionName set from the JWS
        System.out.println("transaction name is :" + transaction);
            
        
        
        GetFireFoxCookiesResponse getFFCookies = new GetFireFoxCookiesResponse();
        DeleteFireFoxCookiesResponse deleteFFCookies = new DeleteFireFoxCookiesResponse();
        ExportFireFoxCookiesResponse expFFCookies = new ExportFireFoxCookiesResponse();
        
        
       if (transaction!=null && !"".equals(transaction) )
       {
    	   if (transaction.equalsIgnoreCase("getFireFoxCookies"))
    	   {
    		   
    		   System.out.println("About to call the getFireFoxCookies class");
    		   
    		   FireFoxCookies ffCookies = new FireFoxCookies();
    		   getFFCookies = ffCookies.constructResponse((GetFireFoxCookiesRequest) request);
    		   responseHM.put("Response", getFFCookies);
    		   
    	   }
    	   
    	   else if (transaction.equalsIgnoreCase("deleteFireFoxCookies"))
    	   {
    		   
    		   System.out.println("About to call the deleteFireFoxCookies class");
    		   
    		   FireFoxCookies ffCookies = new FireFoxCookies();
    		   deleteFFCookies = ffCookies.deleteFireFoxCookies((DeleteFireFoxCookiesRequest) request);
    		   responseHM.put("Response", deleteFFCookies);
    		   
    	   }
    	   
    	   else if(transaction.equalsIgnoreCase("exportFireFoxCookies"))
    	   {
    		   
    		   System.out.println("About to call the exportFireFoxCookies class");
    		   
    		   FireFoxCookies ffCookies = new FireFoxCookies();
    		   expFFCookies = ffCookies.exportFireFoxCookies((ExportFireFoxCookiesRequest)request);
    		   responseHM.put("Response", expFFCookies);
    		   
    	   }
    	   
    	   
    	   
         
    }
       System.out.println(" returning result from the facade. ");
       return responseHM;
       
    }
	

}
