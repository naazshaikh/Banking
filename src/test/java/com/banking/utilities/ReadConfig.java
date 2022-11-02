package com.banking.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ReadConfig {

		
		Properties pro;
		
		public ReadConfig()
		{
			File f= new File("./Configurations/config.properties");
			try {
				FileInputStream fis = new FileInputStream(f);
				pro = new Properties();
				pro.load(fis);
				
			}catch(Exception e)
			{
				System.out.println("Exception :" +e.getMessage());
			}
			

			
			
			
			
		}

		public String getApplicationUrl()
		{
			String url = pro.getProperty("baseUrl");
			return url;
			}

		public String getUserName()
		{
			String uname = pro.getProperty("username");
			return uname;
			}

		public String getPassword()
		{
			String pwd = pro.getProperty("password");
			return pwd;
			}


		public String getchromepath()
		{
			String chromepath = pro.getProperty("chromepath");
			return chromepath;
			}
		
		public String getfirefoxpath()
		{
			String firefoxpath = pro.getProperty("firefoxpath");
			return firefoxpath;
			}


		



	

	
}
