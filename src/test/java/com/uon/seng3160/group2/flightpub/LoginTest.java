package com.uon.seng3160.group2.flightpub;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@SpringBootTest
public class LoginTest {

    private WebDriver driver;

    @BeforeEach
    public void setup() {
        // Set the path to your ChromeDriver executable
        System.setProperty("webdriver.gecko.driver", "/Users/anton/Downloads/geckodriver");
        driver = new FirefoxDriver();
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

    @AfterEach
    public void teardown() {
        // Close the browser
        driver.quit();
    }
}
