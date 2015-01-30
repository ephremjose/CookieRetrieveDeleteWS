package webservice;

import java.util.HashMap;

import javax.jws.WebService;

import weblogic.jws.*;



/**
 * CRDSPortTypeImpl class implements web service endpoint interface CRDSPortType
 */

@WebService(serviceName = "CRDSService", targetNamespace = "http://drexel.edu.com/CRDS/v1", endpointInterface = "webservice.CRDSPortType")
@WLHttpTransport(contextPath = "/", serviceUri = "CookieRetreiveDeleteServiceImpl", portName = "CRDSSoapHttpPort")
public class CRDSPortTypeImpl implements CRDSPortType {

	public CRDSPortTypeImpl() {

	}

	static String type = "CRDSFacade";

	/*
	 * (non-Javadoc)
	 * @see webservice.CRDSPortType#getFireFoxCookies(com.edu.drexel.crds.v1.GetFireFoxCookiesRequest)
	 */
	
	public com.edu.drexel.crds.v1.GetFireFoxCookiesResponse getFireFoxCookies(
			com.edu.drexel.crds.v1.GetFireFoxCookiesRequest getFireFoxCookiesRequest)

	{
		
		com.edu.drexel.crds.v1.GetFireFoxCookiesResponse response = null;
		HashMap responseHM = new HashMap();
		try {

			System.out
					.println(" reached inside the method to get FireFox Cookies. ");
			HashMap hm = new HashMap();
			HashMap requestHash = new HashMap();
			String transactionStr = "getFireFoxCookies";
			requestHash.put("requestObject", getFireFoxCookiesRequest);
			requestHash.put("transaction", transactionStr);

			com.edu.drexel.query.CRDSRoutingFacade facade = new com.edu.drexel.query.CRDSRoutingFacade();
			System.out.println(" Calling execute method. ");

			hm.put("Request", requestHash);
			System.out.println(" Calling getFireFoxCookies from impl. ");
			responseHM = facade.processFireFoxCookies(hm);
			response = (com.edu.drexel.crds.v1.GetFireFoxCookiesResponse) responseHM
					.get("Response");
			System.out.println(" response from facade : " + response);

		} catch (Exception e) {

			System.out.println(" Caught exception. ");
			e.printStackTrace();
		}

		return response;
	}
	
	
	/*
	 * (non-Javadoc)
	 * @see webservice.CRDSPortType#exportFireFoxCookies(com.edu.drexel.crds.v1.ExportFireFoxCookiesRequest)
	 */
	
	public com.edu.drexel.crds.v1.ExportFireFoxCookiesResponse exportFireFoxCookies(
			com.edu.drexel.crds.v1.ExportFireFoxCookiesRequest requestObject) {
		com.edu.drexel.crds.v1.ExportFireFoxCookiesResponse response = null;
		HashMap responseHM = new HashMap();
		try {

			System.out
					.println(" reached inside the method to export FireFox Cookies. ");
			HashMap hm = new HashMap();
			HashMap requestHash = new HashMap();
			String transactionStr = "exportFireFoxCookies";
			requestHash.put("requestObject", requestObject);
			requestHash.put("transaction", transactionStr);

			com.edu.drexel.query.CRDSRoutingFacade facade = new com.edu.drexel.query.CRDSRoutingFacade();
			
			hm.put("Request", requestHash);
			System.out.println(" Calling processFireFoxCookies from impl. ");
			responseHM = facade.processFireFoxCookies(hm);
			response = (com.edu.drexel.crds.v1.ExportFireFoxCookiesResponse) responseHM.get("Response");
			System.out.println(" response from facade : " + response);

		} catch (Exception e) {

			System.out.println(" Caught exception. ");
			e.printStackTrace();
		}

		return response;

	}

	/*
	 * (non-Javadoc)
	 * @see webservice.CRDSPortType#deleteFireFoxCookies(com.edu.drexel.crds.v1.DeleteFireFoxCookiesRequest)
	 */
	
	public com.edu.drexel.crds.v1.DeleteFireFoxCookiesResponse deleteFireFoxCookies(
			com.edu.drexel.crds.v1.DeleteFireFoxCookiesRequest requestObject) {
		com.edu.drexel.crds.v1.DeleteFireFoxCookiesResponse response = null;
		HashMap responseHM = new HashMap();
		try {

			System.out
					.println(" reached inside the method to delete FireFox Cookies. ");
			HashMap hm = new HashMap();
			HashMap requestHash = new HashMap();
			String transactionStr = "deleteFireFoxCookies";
			requestHash.put("requestObject", requestObject);
			requestHash.put("transaction", transactionStr);

			com.edu.drexel.query.CRDSRoutingFacade facade = new com.edu.drexel.query.CRDSRoutingFacade();
			System.out.println(" Calling execute method. ");

			hm.put("Request", requestHash);
			System.out.println(" Calling processFireFoxCookies from impl. ");
			responseHM = facade.processFireFoxCookies(hm);
			response = (com.edu.drexel.crds.v1.DeleteFireFoxCookiesResponse) responseHM
					.get("Response");
			System.out.println(" response from facade : " + response);

		} catch (Exception e) {
			
			System.out.println(" Caught exception. ");
			e.printStackTrace();
		}

		return response;

	}

}