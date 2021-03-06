package pageObjects;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class AbsentMissingAttendanceReport {
  WebDriver dr;
  Utility u= new Utility();
  String r= "AbsentMissingAttendanceReport";
  
  @FindBy(id="ContentPlaceHolder1_ddlschool")WebElement schoolname;
  @FindBy(id="ContentPlaceHolder1_txtstartdate_TextBox")WebElement fromdate;
  @FindBy(id="ContentPlaceHolder1_txtenddate_TextBox")WebElement todate;
  @FindBy(id="ContentPlaceHolder1_Button1")WebElement show;
  
  public AbsentMissingAttendanceReport(WebDriver d)
  {
	  this.dr=d;   
	  PageFactory.initElements(d, this);
  }
  public void openAbsentMissingAttendanceReport()
  {
	  WebElement menu= dr.findElement(By.xpath("//img[@src='/Images/layout/Reports.png']"));
	  Actions builder= new Actions(dr);
	  builder.moveToElement(menu).build().perform();
	  dr.findElement(By.linkText("Absent / Missing Attendance Report")).click();
	  dr.switchTo().frame(dr.findElement(By.xpath("//iframe[@src='/Report/Payroll/RptAbsentMissingAttendance.aspx']")));
  }
  public void selectSchoolName(String sch)
  {
	  new Select(schoolname).selectByVisibleText(sch);
  }
  public void selectFromDate(String mm, String yy, String dd) throws InterruptedException
  {
	  u.selectDate(dr, fromdate, mm, yy, dd);
  }
  public void selectToDate(String mm, String yy, String dd) throws InterruptedException
  {
	  u.selectDate(dr, todate, mm, yy, dd);
  }
  public void selectAbsentMissing(String abms)
  {
	  List<WebElement>options= dr.findElements(By.name("ctl00$ContentPlaceHolder1$rdbList"));
	  String val;
	  for(WebElement option:options)
	  {
		  if(abms.equalsIgnoreCase("all"))
		  {
			  val="0";
			  if(option.getAttribute("value").equals(val))
				  option.click();
		  }
		  if(abms.equalsIgnoreCase("only absent"))
		  {
			  val="1";
			  if(option.getAttribute("value").equals(val))
				  option.click();
		  }
		  if(abms.equalsIgnoreCase("only missing"))
		  {
			  val="2";
			  if(option.getAttribute("value").equals(val))
				  option.click();
		  }
	  }
  }
  public void clickShow(String sch, Collection<String>sc) throws IOException, InterruptedException {
	  show.click();
	  u.verifyShow(dr, sch, r, sc);
	  Thread.sleep(4000);
	  u.captureScreenshot(dr, sch, r,sc);
 }
}
