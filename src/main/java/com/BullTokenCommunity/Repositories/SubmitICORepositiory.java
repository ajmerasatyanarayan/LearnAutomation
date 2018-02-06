package com.BullTokenCommunity.Repositories;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import com.BullTokenCommunity.ExcelReader.Excel_Reader;
import com.BullTokenCommunity.Model.ICOBasicDetailModel;
//import com.BullTokenCommunity.Model.LoginModel;

public class SubmitICORepositiory {
	ICOBasicDetailModel ICOData;
	Excel_Reader elib;
	String sheetName="ICOBasicDetail";
	
	public SubmitICORepositiory()
	{
		ICOData= new ICOBasicDetailModel();
		elib=new Excel_Reader();
		
	}
	public ICOBasicDetailModel Get() throws InvalidFormatException, IOException
	{
		ICOData.ICOName=elib.getExcelData(sheetName, 1, 1);
		ICOData.ICOURL=elib.getExcelData(sheetName, 1, 2);
		ICOData.ICOcategory=elib.getExcelData(sheetName, 1, 3);
		ICOData.ICOCountry=elib.getExcelData(sheetName, 1, 4);
		ICOData.IcoDeescription=elib.getExcelData(sheetName, 1, 5);
		ICOData.CompanyName=elib.getExcelData(sheetName, 1, 6);
		ICOData.LightpaperLink=elib.getExcelData(sheetName, 1, 7);
		ICOData.WhitepaperLink=elib.getExcelData(sheetName, 1, 8);
		ICOData.FacebookUrl=elib.getExcelData(sheetName, 1, 9);
		ICOData.LinkdinUrl=elib.getExcelData(sheetName, 1, 10);
		ICOData.GithubUrl=elib.getExcelData(sheetName, 1, 11);
		ICOData.ContactEmail=elib.getExcelData(sheetName, 1, 12);
		ICOData.ContactMobile=elib.getExcelData(sheetName, 1, 13);
		ICOData.ContactAddress=elib.getExcelData(sheetName, 1, 14);
		return ICOData;
	}


}
