package utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Calendar;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.apache.commons.io.FileUtils;

public class Reports{
	 private final WebDriver webdriver = GlobalDriver.webdriver;
	    public static String imgname;
		private static String PATH;
		public static String nomePastaEvidencia = null;

	    public static void takeScreenShotPasta() throws Exception{
			Calendar data = Calendar.getInstance();
			String nomePastaTime =  ("Time_"+data.get(Calendar.HOUR_OF_DAY)+"."+data.get(Calendar.MINUTE)+"."+data.get(Calendar.SECOND));
			String nomePastaDate =  ("Date_" +data.get(Calendar.DAY_OF_MONTH)+"-"+data.get(Calendar.MONTH) +"-" + data.get(Calendar.YEAR) +"_");
			nomePastaEvidencia = (nomePastaDate + nomePastaTime);
			PATH="./target/screenshots/" + File.separator; 
			Files.createDirectory(Paths.get(PATH, nomePastaEvidencia));
		}
		
		public void takeScreenShot(String fileName){
			File srcFile=((TakesScreenshot)webdriver).getScreenshotAs(OutputType.FILE);
			try {
				imgname = PATH+"\\"+nomePastaEvidencia+"\\"+fileName+".png";
				File destFile = new File(imgname);
				FileUtils.copyFile(srcFile, destFile);
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
}
