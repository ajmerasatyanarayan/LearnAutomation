package com.BullTokenCommunity.Repositories;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import com.BullTokenCommunity.ExcelReader.Excel_Reader;
import com.BullTokenCommunity.Model.LoginModel;

public class LoginRepository {
	LoginModel LoginData;
	Excel_Reader elib;
	String sheetName="LoginTest";
	
	public LoginRepository()
	{
		LoginData= new LoginModel();
		elib=new Excel_Reader();
		
	}
	public LoginModel Get() throws InvalidFormatException, IOException
	{
		LoginData.UserName=elib.getExcelData(sheetName, 1, 1);
		LoginData.Password=elib.getExcelData(sheetName, 1, 2);
		LoginData.Invalidusername=elib.getExcelData(sheetName, 2, 1);
		LoginData.invalidpassword=elib.getExcelData(sheetName, 2, 2);
		LoginData.UsernameNotexist=elib.getExcelData(sheetName, 3, 1);
		LoginData.passwordNotexist=elib.getExcelData(sheetName, 3, 2);
		return LoginData;
	}

}
