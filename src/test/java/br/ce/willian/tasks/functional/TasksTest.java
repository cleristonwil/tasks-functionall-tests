package br.ce.willian.tasks.functional;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class TasksTest {
	
	public WebDriver acessarAplicacao() throws MalformedURLException {
//		WebDriver driver = new ChromeDriver();
		DesiredCapabilities cap = DesiredCapabilities.chrome();
		WebDriver driver = new RemoteWebDriver(new URL("http://192.168.51.19:4444"), cap);
		driver.navigate().to("http://192.168.51.13:8001/tasks");
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		return driver;
	}

	@Test
	public void deveSalvarTarefaComSucesso() throws MalformedURLException {
		WebDriver driver = acessarAplicacao();
		try {
		
			//Clicar em Add Todo
			driver.findElement(By.id("addTodo")).click();
			
			//Escrever a descri??o
			driver.findElement(By.id("task")).sendKeys("Teste via Selenium");
			
			//Escrever a data
			driver.findElement(By.id("dueDate")).sendKeys("19/10/2030");
			
			//Clicar em Salvar
			driver.findElement(By.id("saveButton")).click();
			
			//Validar mensagem de sucesso
			String message = driver.findElement(By.id("message")).getText();
			Assert.assertEquals("Success!", message);
		} finally {
			//Fechar o Browser
			driver.quit();
		}
	}
		@Test
		public void naoDeveSalvarTarefaSemDescricao() throws MalformedURLException {
			WebDriver driver = acessarAplicacao();
			try {
			
				//Clicar em Add Todo
				driver.findElement(By.id("addTodo")).click();
											
				//Escrever a data
				driver.findElement(By.id("dueDate")).sendKeys("19/10/2030");
				
				//Clicar em Salvar
				driver.findElement(By.id("saveButton")).click();
				
				//Validar mensagem de sucesso
				String message = driver.findElement(By.id("message")).getText();
				Assert.assertEquals("Fill the task description", message);
			} finally {
				//Fechar o Browser
				driver.quit();
			}
		}
		
		@Test
		public void naoDeveSalvarTarefaSemData() throws MalformedURLException {
			WebDriver driver = acessarAplicacao();
			try {
			
				//Clicar em Add Todo
				driver.findElement(By.id("addTodo")).click();
				
				//Escrever a descri??o
				driver.findElement(By.id("task")).sendKeys("Teste via Selenium");
				
				//Clicar em Salvar
				driver.findElement(By.id("saveButton")).click();
				
				//Validar mensagem de sucesso
				String message = driver.findElement(By.id("message")).getText();
				Assert.assertEquals("Fill the due date", message);
			} finally {
				//Fechar o Browser
				driver.quit();
			}
		}
		
		@Test
		public void naoDeveSalvarTarefaComDataPassada() throws MalformedURLException {
			WebDriver driver = acessarAplicacao();
			try {
			
				//Clicar em Add Todo
				driver.findElement(By.id("addTodo")).click();
				
				//Escrever a descri??o
				driver.findElement(By.id("task")).sendKeys("Teste via Selenium");
				
				//Escrever a data
				driver.findElement(By.id("dueDate")).sendKeys("16/10/2010");
				
				//Clicar em Salvar
				driver.findElement(By.id("saveButton")).click();
				
				//Validar mensagem de sucesso
				String message = driver.findElement(By.id("message")).getText();
				Assert.assertEquals("Due date must not be in past", message);
			} finally {
				//Fechar o Browser
				driver.quit();
			}
		}
}

