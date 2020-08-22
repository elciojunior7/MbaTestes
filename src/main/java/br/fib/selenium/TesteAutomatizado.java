package br.fib.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class TesteAutomatizado {

	public static void main(String[] args) {

		System.setProperty("webdriver.chrome.driver",
				"D:\\zUser\\Area de Trabalho\\posFIB\\13 - Testes\\selenium\\chromedriver/chromedriver.exe");

		WebDriver driver = new ChromeDriver();
		driver.get("http://www.google.com.br");
		WebElement campoDeTexto = driver.findElement(By.name("q"));
		campoDeTexto.sendKeys("brasil");
		campoDeTexto.submit();
	}

}
