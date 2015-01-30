package com.edu.drexel.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.edu.drexel.crds.v1.DeleteFireFoxCookiesRequest;
import com.edu.drexel.crds.v1.GetFireFoxCookiesRequest;
import com.edu.drexel.dao.ExportFireFoxCookies;
import com.edu.drexel.vo.FireFoxVO;

/*
 * FireFoxCookiesDao class is the Data Access Layer (DAO). The SQLite Database is connected
 * from this class and the queries executed.
 */

public class FireFoxCookiesDao {
	
	/*
	 * getFireFoxCookieDetails is for GetFireFoxCookiesRequest. 
	 * 
	 */
	
	public ArrayList<FireFoxVO> getFireFoxCookieDetails (GetFireFoxCookiesRequest requestObject)
	{
		System.out.println(" In getFireFoxCookieDetails method in the DAO file. ");
		ArrayList<FireFoxVO> fireFxList = new ArrayList<FireFoxVO>();
		PreparedStatement pstmt = null;
		String sql = "";
		ResultSet resultSet = null;  
		Connection connection = null;   
		String whereClause = "";
		String baseDomain = requestObject.getBaseDomain();
		
		  try 
	     {  
	         Class.forName("org.sqlite.JDBC");  
	         connection = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\ephre_000\\AppData\\Roaming\\Mozilla\\Firefox\\Profiles\\dqk8gcto.default\\cookies.sqlite");
	         sql = " SELECT ID, " +
	         		"BASEDOMAIN, " +
	         		"NAME , " +
	         		"VALUE, " +
	         		"HOST, " +
	         		"CREATIONTIME, " +
	         		"LASTACCESSED, " +
	         		"EXPIRY " +
	         		"FROM MOZ_COOKIES " +
	         		"WHERE 1=1 ";
	         
	         if(baseDomain!=null && baseDomain.length()>0 && !"ALL".equalsIgnoreCase(baseDomain))
	         {
	        	 whereClause += " AND BASEDOMAIN = '" + baseDomain + "' ";
	         }
	         
	         System.out.println(" where clause : " + whereClause);
	         sql += whereClause;
	         pstmt = connection.prepareStatement(sql);
	         
	         System.out.println(" Executing SQL : " + sql);
	         resultSet = pstmt.executeQuery();
	         
	         int counter = 0;
	         
	         while (resultSet.next() && counter <100) 
	         {  
	             FireFoxVO ffVO = new FireFoxVO();
	             ffVO.setId(resultSet.getString("ID"));
	             ffVO.setBaseDomain(resultSet.getString("BASEDOMAIN"));
	             ffVO.setName(resultSet.getString("NAME"));
	             ffVO.setValue(resultSet.getString("VALUE"));
	             ffVO.setHost(resultSet.getString("HOST"));
	             ffVO.setCreationTime(resultSet.getString("CREATIONTIME"));
	             ffVO.setLastAccessed(resultSet.getString("LASTACCESSED"));
	             ffVO.setExpiry(resultSet.getString("EXPIRY"));
	             
	             fireFxList.add(ffVO);
	             counter++;
	             
	         }  
	         
	         
	     } 
	     catch (Exception e) 
	     {  
	    	 System.out.println(" Caught exception in getFireFoxCookieDetails method. ");
	         e.printStackTrace();  
	     }
	     finally 
	     {  
	         try 
	         {  
	             resultSet.close();  
	             pstmt.close();  
	             connection.close();  
	         } 
	         catch (Exception e) 
	         {  
	             e.printStackTrace();  
	         }  
	     }  
		
	     System.out.println(" Leaving getFireFoxCookieDetails method. ");
		
		return fireFxList;
	}
	
	/*
	 * deleteFireFoxCookieDetails is for DeleteFireFoxCookiesRequest
	 * 
	 */
	public int deleteFireFoxCookieDetails (DeleteFireFoxCookiesRequest requestObject)
	{
		System.out.println(" In deleteFireFoxCookieDetails method in the DAO file. ");
		int numRows = 0;
		PreparedStatement pstmt = null;
		String sql = "";
		Connection connection = null;   
		String whereClause = "";
		String baseDomain = requestObject.getBaseDomain();
		
		try 
	     {  
	         Class.forName("org.sqlite.JDBC");  

	         connection = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\ephre_000\\AppData\\Roaming\\Mozilla\\Firefox\\Profiles\\dqk8gcto.default\\cookies.sqlite");
	         
	         sql = " DELETE " +
	         		"FROM MOZ_COOKIES " +
	         		"WHERE 1=1 ";
	         
	         connection.setAutoCommit(false);
	         
	         if(baseDomain!=null && baseDomain.length()>0 && !"ALL".equalsIgnoreCase(baseDomain))
	         {
	        	 whereClause += " AND BASEDOMAIN = '" + baseDomain + "' ";
	         }
	         
	         System.out.println(" where clause : " + whereClause);
	         sql += whereClause;
	         pstmt = connection.prepareStatement(sql);
	         
	         System.out.println(" Executing SQL : " + sql);
	         
	         numRows = pstmt.executeUpdate();
	         connection.commit();
	         
	     } 
	     catch (Exception e) 
	     {  
	    	 System.out.println(" Caught exception in getFireFoxCookieDetails method. ");
	         e.printStackTrace();  
	     }
	     finally 
	     {  
	         try 
	         {  
	             //resultSet.close();  
	             pstmt.close();  
	             connection.close();  
	         } 
	         catch (Exception e) 
	         {  
	             e.printStackTrace();  
	         }  
	     }  
		
	     System.out.println(" Leaving getFireFoxCookieDetails method. ");
		
		return numRows;
	}

}
