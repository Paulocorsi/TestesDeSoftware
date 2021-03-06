package com.fatec.ed08;

import static org.assertj.core.api.Assertions.not;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

class REQ01MantemLivrosTest {

	private WebDriver driver;
    private Map<String, Object> vars;
    JavascriptExecutor js;
    
	  @BeforeEach
	  public void setUp() {
		System.setProperty("webdriver.chrome.driver", "browserDriver/chromedriver.exe");
	    driver = new ChromeDriver();
	    js = (JavascriptExecutor) driver;
	    vars = new HashMap<String, Object>();
	  }
	  
	  @AfterEach
	  public void tearDown() {
	    driver.quit();
	  }
	  
	  @Test
	  public void REQ01CT01_cadastrar_livro_com_sucesso() {
		//dado que o livro não esta cadastrado
		driver.get("https://ts-scel-web.herokuapp.com/login");
		driver.findElement(By.name("username")).click();
		driver.findElement(By.name("username")).sendKeys("jose");
		driver.findElement(By.name("password")).click();
		driver.findElement(By.name("password")).sendKeys("123");
		driver.findElement(By.cssSelector("button")).click();
		driver.findElement(By.linkText("Livros")).click();
		espera();
		
		// quando o usuario cadastrar um livro
		driver.findElement(By.id("isbn")).click();
		driver.findElement(By.id("isbn")).sendKeys("1910");
		driver.findElement(By.id("autor")).click();
		driver.findElement(By.id("autor")).sendKeys("Takehiko Inoue");
		driver.findElement(By.id("titulo")).click();
		driver.findElement(By.id("titulo")).sendKeys("Vagabond");
		driver.findElement(By.cssSelector(".btn:nth-child(1)")).click();
		driver.findElement(By.cssSelector("body")).click();
		
		// entao apresenta as informacoes do livro
		assertEquals(driver.findElement(By.id("paginaConsulta")).getText(), is("Lista de livros"));
		driver.findElement(By.cssSelector("tr:nth-child(2) > td:nth-child(3)")).click();
		assertEquals(driver.findElement(By.cssSelector("tr:nth-child(2) > td:nth-child(3)")).getText(), is("Vagabond"));
	    driver.findElement(By.cssSelector("tr:nth-child(2) .delete")).click();
	  }
	  
	  @Test
	  public void REQ01CT02_altera_autor_e_titulo_de_livro_ja_cadastrado() {
		//dado que o livro não esta cadastrado
	    driver.get("https://ts-scel-web.herokuapp.com/login");
	    driver.findElement(By.name("username")).click();
	    driver.findElement(By.name("username")).sendKeys("jose");
	    driver.findElement(By.name("password")).click();
	    driver.findElement(By.name("password")).sendKeys("123");
	    driver.findElement(By.cssSelector("button")).click();
	    driver.findElement(By.linkText("Livros")).click();
	    driver.findElement(By.id("isbn")).click();
		driver.findElement(By.id("isbn")).sendKeys("1910");
		driver.findElement(By.id("autor")).click();
		driver.findElement(By.id("autor")).sendKeys("Takehiko Inoue");
		driver.findElement(By.id("titulo")).click();
		driver.findElement(By.id("titulo")).sendKeys("Vagabond");
		driver.findElement(By.cssSelector(".btn:nth-child(1)")).click();
		driver.findElement(By.cssSelector("body")).click();
	    
	    // quando o usuario altera o autor e o titulo do livro
	    driver.findElement(By.linkText("Livros")).click();
	    driver.findElement(By.linkText("Lista de Livros")).click();
	    driver.findElement(By.cssSelector("tr:nth-child(2) .btn-primary")).click();
	    driver.findElement(By.id("autor")).click();
	    driver.findElement(By.id("autor")).click();
	    driver.findElement(By.id("autor")).click();
	    {
	      WebElement element = driver.findElement(By.id("autor"));
	      Actions builder = new Actions(driver);
	      builder.doubleClick(element).perform();
	    }
	    driver.findElement(By.id("autor")).click();
	    driver.findElement(By.id("autor")).click();
	    driver.findElement(By.id("autor")).sendKeys("Kazuo Koike");
	    driver.findElement(By.id("titulo")).click();
	    driver.findElement(By.cssSelector(".row:nth-child(4) > .form-group:nth-child(1)")).click();
	    driver.findElement(By.id("titulo")).sendKeys("Lobo Solitario");
	    driver.findElement(By.cssSelector(".btn")).click();
	    driver.findElement(By.cssSelector("body")).click();
	    
	    // entao o sistema apresenta as informações do livro com o titulo e autor alterado
	    assertEquals(driver.findElement(By.id("paginaConsulta")).getText(), is("Lista de livros"));
	    driver.findElement(By.cssSelector("tr:nth-child(2) > td:nth-child(3)")).click();
	    assertEquals(driver.findElement(By.cssSelector("tr:nth-child(2) > td:nth-child(3)")).getText(), is("Lobo Solitario"));
	    driver.findElement(By.cssSelector("tr:nth-child(2) .delete")).click();
	  }
	  
	  @Test
	  public void rEQ01CT03() {
		//dado que o livro ja esta cadastrado
	    driver.get("https://ts-scel-web.herokuapp.com/login");
	    driver.findElement(By.name("username")).click();
	    driver.findElement(By.name("username")).sendKeys("jose");
	    driver.findElement(By.name("password")).click();
	    driver.findElement(By.name("password")).sendKeys("123");
	    driver.findElement(By.cssSelector("button")).click();
	    driver.findElement(By.linkText("Livros")).click();
	    driver.findElement(By.id("isbn")).click();
		driver.findElement(By.id("isbn")).sendKeys("1910");
		driver.findElement(By.id("autor")).click();
		driver.findElement(By.id("autor")).sendKeys("Takehiko Inoue");
		driver.findElement(By.id("titulo")).click();
		driver.findElement(By.id("titulo")).sendKeys("Vagabond");
		driver.findElement(By.cssSelector(".btn:nth-child(1)")).click();
		driver.findElement(By.cssSelector("body")).click();
	    
	    // quando o usuario excluir o livro
	    driver.findElement(By.linkText("Livros")).click();
	    driver.findElement(By.linkText("Lista de Livros")).click();
	    driver.findElement(By.cssSelector("tr:nth-child(2) .delete")).click();
	    driver.findElement(By.id("paginaConsulta")).click();
	    
	    // entao o sistema apresenta as informações do livro sem o livro excluido
	    assertEquals(driver.findElement(By.id("paginaConsulta")).getText(), is("Lista de livros"));
	    driver.findElement(By.cssSelector("html")).click();
	    assertEquals(driver.findElement(By.cssSelector("html")).getText(), is(not("Lobo Solitario")));
	  }
	
	  
	  public void espera() {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
}
