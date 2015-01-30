package com.edu.drexel.dao;

import  java.io.*;
import  org.apache.poi.hssf.usermodel.HSSFSheet;
import  org.apache.poi.hssf.usermodel.HSSFWorkbook;
import  org.apache.poi.hssf.usermodel.HSSFRow;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.UUID;

/*
 * ExportFireFoxCookies is specifically for the CreateExcelFile function to access the DB and
 * create the report based on that. 
 */

public class ExportFireFoxCookies {
	
	public String CreateExcelFile()
	{
		
		String url = "";
		System.out.println(" In CreateExcelFile method in the ExportFireFoxCookies file. ");
		int numRows = 0;
		PreparedStatement pstmt = null;
		String sql = "";
		Connection connection = null; 
		ResultSet rs = null;
		String filepath="C:/Excel_Files/" ;
        String fileName = "";
        int counter = 0;
        
		try{
			
			java.util.Date date= new java.util.Date();
			Timestamp t = new Timestamp(date.getTime());
			UUID idOne = UUID.randomUUID();
			 
	        
	        
	        fileName = idOne.toString();
	        
	        
	         Class.forName("org.sqlite.JDBC");  
	         connection = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\ephre_000\\AppData\\Roaming\\Mozilla\\Firefox\\Profiles\\dqk8gcto.default\\cookies.sqlite");
	         sql = " SELECT ID, " +
	         		"BASEDOMAIN, " +
	         		"APPID, " +
	         		"INBROWSERELEMENT, " +
	         		"NAME, " +
	         		"VALUE, " +
	         		"HOST, " +
	         		"PATH, " +
	         		"EXPIRY, " +
	         		"LASTACCESSED, " +
	         		"CREATIONTIME, " +
	         		"ISSECURE, " +
	         		"ISHTTPONLY  " +
	         		"FROM MOZ_COOKIES ";
	         
	       
	         pstmt = connection.prepareStatement(sql);
	         	         
	         System.out.println(" Executing SQL : " + sql);
	         
	         rs = pstmt.executeQuery();
	      
	         
	        HSSFWorkbook workbook=new HSSFWorkbook();
	        HSSFSheet sheet =  workbook.createSheet("FirstSheet");    
         	
	        HSSFRow rowhead=   sheet.createRow((short)0);
	        rowhead.createCell((short) 0).setCellValue("ID");
	        rowhead.createCell((short) 1).setCellValue("BASEDOMAIN");
	        rowhead.createCell((short) 2).setCellValue("APPID");
	        rowhead.createCell((short) 3).setCellValue("INBROWSERELEMENT");
	        rowhead.createCell((short) 4).setCellValue("NAME");
	        rowhead.createCell((short) 5).setCellValue("VALUE");
	        rowhead.createCell((short) 6).setCellValue("HOST");
	        rowhead.createCell((short) 7).setCellValue("PATH");
	        rowhead.createCell((short) 8).setCellValue("EXPIRY");
	        rowhead.createCell((short) 9).setCellValue("LASTACCESSED");
	        rowhead.createCell((short) 10).setCellValue("CREATIONTIME");
	        rowhead.createCell((short) 11).setCellValue("ISSECURE");
	        rowhead.createCell((short) 12).setCellValue("ISHTTPONLY");
	        
	        while (rs.next()) 
	        	
	        {   
	        
	        	counter++;
		        HSSFRow row=   sheet.createRow((short) counter);
		        row.createCell((short) 0).setCellValue(rs.getString("ID"));
		        row.createCell((short) 1).setCellValue(rs.getString("BASEDOMAIN"));
		        row.createCell((short) 2).setCellValue(rs.getString("APPID"));
		        row.createCell((short) 3).setCellValue(rs.getString("INBROWSERELEMENT"));
		        row.createCell((short) 4).setCellValue(rs.getString("NAME"));
		        row.createCell((short) 5).setCellValue(rs.getString("VALUE"));
		        row.createCell((short) 6).setCellValue(rs.getString("HOST"));
		        row.createCell((short) 7).setCellValue(rs.getString("PATH"));
		        row.createCell((short) 8).setCellValue(rs.getString("EXPIRY"));
		        row.createCell((short) 9).setCellValue(rs.getString("LASTACCESSED"));
		        row.createCell((short) 10).setCellValue(rs.getString("CREATIONTIME"));
		        row.createCell((short) 11).setCellValue(rs.getString("ISSECURE"));
		        row.createCell((short) 12).setCellValue(rs.getString("ISHTTPONLY"));
	
		        	    

	        }
	        
	        FileOutputStream fileOut =  new FileOutputStream(filepath+"Drexel_"+fileName+".xls");
	        workbook.write(fileOut);
	        fileOut.close();
	        
	        System.out.println("Your excel file has been generated!");
	        
	        } catch ( Exception ex ) {
	        	System.out.println(" Caught exception while trying to generate xl sheet. ");
	            ex.printStackTrace();

	        }
		
	        if(counter > 0)
	        {
	        	url = "http://localhost:7001/Excel_Files/Drexel_"+fileName+".xls";
	        	System.out.println(" URL Generated = " + url);
	        }
	        else
	        	System.out.println(" No records retreived. URL is empty. ");
		
		
		return url;
		
	}

}
