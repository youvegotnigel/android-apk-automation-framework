package com.youvegotnigel.automation.stepdefs;

import com.youvegotnigel.automation.pageobjects.CreateTaskPage;
import com.youvegotnigel.automation.pageobjects.TasksListPage;
import com.youvegotnigel.automation.tests.TestBase;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.net.MalformedURLException;

public class CreateNewTask extends TestBase {

    CreateTaskPage createTaskPage;
    TasksListPage tasksListPage;

    @Given("Click Add new Task")
    public void clickAddNewTask() throws MalformedURLException {
        Android_setUp();
        tasksListPage = new TasksListPage(driver);
        createTaskPage = new CreateTaskPage(driver);
        tasksListPage.clickAddTaskBtn();
    }

    @And("Enter TaskName")
    public void enterTaskName() {
        createTaskPage.enterTaskName("Task 1");
    }

    @And("Enter TaskDesc")
    public void enterTaskDesc() {
        createTaskPage.enterTaskDesc("Desc 1");
    }

    @When("Click Save")
    public void clickSave() {
        createTaskPage.clickSaveBtn();
    }

    @Then("Task added successfully")
    public void taskAddedSuccessfully() {
        driver.hideKeyboard();
        tearDown();
    }
}
