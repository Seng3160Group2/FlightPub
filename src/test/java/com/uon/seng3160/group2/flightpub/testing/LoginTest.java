package com.uon.seng3160.group2.flightpub.testing;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LoginTest {

    private WebDriver driver;

    @BeforeEach
    public void setup() {
        // Set the path to your Chrome/Edge/Firefox/Safari Driver executable
        System.setProperty("webdriver.chrome.driver", "C:\\WebDriver\\chromedriver.exe");
        driver = new ChromeDriver();
        // driver = new EdgeDriver();
        // driver = new FirefoxDriver();
        // driver = new SafariDriver();
    }

    @Test
    public void testLogin() {
        // Navigate to the login page of your web application
        driver.get("http://localhost:8080/login");

        // Find the username and password input fields and the login button
        WebElement usernameInput = driver.findElement(By.id("username"));
        WebElement passwordInput = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.className("c-button"));

        // Enter the username and password
        usernameInput.sendKeys("your_username");
        passwordInput.sendKeys("your_password");

        // Click the login button
        loginButton.click();

    }

    @Test
    public void testInvalidLogin() {
        // Navigate to the login page of your web application
        driver.get("http://localhost:8080/login");

        // Find the username and password input fields and the login button
        WebElement usernameInput = driver.findElement(By.id("username"));
        WebElement passwordInput = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.className("c-button"));

        // Enter invalid username and password
        usernameInput.sendKeys("incorrect_username");
        passwordInput.sendKeys("incorrect_password");

        // Click the login button
        loginButton.click();

        // Add an assertion to verify that the login was not successful and an error message is displayed.
        WebElement errorMessage = driver.findElement(By.id("error-message"));
        String errorText = errorMessage.getText();
        
        // Check if the error message contains a specific text indicating an invalid login
        assert(errorText.contains("Invalid credentials"));
    }

    @AfterEach
    public void teardown() {
        // Close the browser
        driver.quit();
    }
}