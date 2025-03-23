package stepDefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pages.WebFormPage;
import java.util.List;
import java.util.Map;
import static org.junit.Assert.assertTrue;


public class WebFormSteps {

    private WebDriver driver = Hooks.driver;
    private WebFormPage formPage;

    @Given("I am on the web form page")
    public void iAmOnTheWebFormPage() {
        formPage = new WebFormPage(driver);
    }

    @When("I enter the following text")
    public void iEnterTheFollowingText(DataTable dataTable) {
        formPage = new WebFormPage(driver);

        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);

        for (Map<String, String> row : rows) {
            String field = row.get("field");
            String value = row.get("value");

            formPage.enterTextByLabel(field, value);
        }
    }

    @Then("the following fields should contain")
    public void theFollowingFieldsShouldContain(DataTable dataTable) {
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> row : rows) {
            String field = row.get("field");
            String expectedValue = row.get("value");
            String actualValue = formPage.getFieldValueByLabel(field);
            Assert.assertEquals("Field " + field + " does not contain the expected value", expectedValue, actualValue);
        }
    }

    @When("I submit the form")
    public void iSubmitTheForm() {
        formPage.submitForm();
    }

    @Then("the form is submitted successfully")
    public void theFormIsSubmittedSuccessfully() {
        String expectedHeading = "Form submitted";
        String expectedMessage = "Received!";

        formPage.verifyFormSubmissionSuccess(expectedHeading, expectedMessage);
    }

    @Then("I should see the following query parameters in the url")
    public void iShouldSeeTheFollowingQueryParametersInTheURL(DataTable dataTable) {
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);

        for (Map<String, String> row : rows) {
            String paramName = row.get("param");
            String expectedValue = row.get("value");

            formPage.verifyUrlQueryParameter(paramName, expectedValue);
        }
    }

    @Then("the field {string} should be disabled")
    public void theFieldShouldBeDisabled(String fieldName) {
        boolean isDisabled = formPage.isFieldDisabled(fieldName);
        assertTrue("The field " + fieldName + " should be disabled, but it is not.", isDisabled);
    }

    @Then("the field {string} should be readonly")
    public void theFieldShouldBeReadonly(String fieldName) {
        boolean isReadonly = formPage.isFieldReadonly(fieldName);
        assertTrue("The field " + fieldName + " should be readonly, but it is not.", isReadonly);
    }

    @And("I select option {string} from the dropdown")
    public void iSelectOptionFromTheDropdown(String optionValue) {
        formPage.selectDropdownOptionByValue("my-select", optionValue);
    }

    @And("I enter partial text {string} in the datalist to select the value")
    public void iEnterPartialTextInTheDatalistAndSelectTheValue(String partialValue) {
        formPage.enterAndSelectDatalistOption("my-datalist", partialValue);
    }

    @And("I upload the file {string}")
    public void iUploadTheFile(String fileName) {
        formPage.uploadFile(fileName);
    }

    @And("I check the default checkbox")
    public void iCheckTheDefaultCheckbox() {
        formPage.checkCheckbox("my-check-2");
    }

    @And("I uncheck the checked checkbox")
    public void iUncheckTheCheckedCheckbox() {
        formPage.uncheckCheckbox("my-check-1");
    }

    @Then("the {string} checkbox should be {string}")
    public void theCheckboxShouldBeSelected(String checkbox, String status) {
        String checkboxId;
        if(checkbox.equals("default")) {
            checkboxId = "my-check-2";
        } else {
            checkboxId = "my-check-1";
        }
        boolean isChecked = formPage.isCheckboxSelected(checkboxId);

        if (status.equalsIgnoreCase("checked")) {
            Assert.assertTrue("The checkbox " + checkboxId + " should be checked", isChecked);
        } else if (status.equalsIgnoreCase("unchecked")) {
            Assert.assertFalse("The checkbox " + checkboxId + " should be unchecked", isChecked);
        } else {
            Assert.fail("Invalid status provided. Use 'checked' or 'unchecked'.");
        }
    }

    @When("I select the default radio button")
    public void iSelectTheDefaultRadioButton() {
        formPage.selectRadioButton("my-radio-2");
    }

    @Then("the {string} radio button should be {string}")
    public void theRadioButtonShouldBeSelected(String radioButton, String status) {
        String radioId;
        System.out.println("radio button is "+radioButton);
        if(radioButton.equals("Checked")){
            radioId = "my-radio-1";
        } else {
            radioId = "my-radio-2";
        }
        boolean isSelected = formPage.isRadioSelected(radioId);

        if (status.equalsIgnoreCase("selected")) {
            Assert.assertTrue("The radio button " + radioId + " should be selected", isSelected);
        } else if (status.equalsIgnoreCase("unselected")) {
            Assert.assertFalse("The radio button " + radioId + " should be unselected", isSelected);
        } else {
            Assert.fail("Invalid status provided. Use 'selected' or 'unselected'.");
        }
    }

    @When("I select red color from the color picker")
    public void iSelectRedColorFromTheColorPicker() {
        formPage.setColorPicker("#FF0000");
    }

    @Then("the color picker should display red")
    public void theColorPickerShouldDisplayRed() {
        String colorValue = formPage.getColorPickerValue();
        Assert.assertEquals("The color picker should display red", "#ff0000", colorValue);
    }

    @When("I open the date picker")
    public void iOpenTheDatePicker() {
        formPage.openDatePicker();
    }

    @When("I select the date {string} from the date picker")
    public void iSelectTheDateFromTheDatePicker(String date) {
        formPage.selectDate(date);
    }

    @Then("the date picker should display the selected date {string}")
    public void theDatePickerShouldDisplayTheSelectedDate(String expectedDate) {
        String selectedDate = formPage.getSelectedDateFromDatePicker();
        Assert.assertEquals("The selected date should be displayed in the date picker", expectedDate, selectedDate);
    }

    @When("I set the range slider value to {int}")
    public void iSetTheRangeSliderValueTo(int value)  {
        formPage.setRangeValue(value);
    }

    @Then("the range slider should display value {int}")
    public void theRangeSliderShouldDisplayValue(int expectedValue) {
        int currentValue = formPage.getRangeValue();
        Assert.assertEquals("The range slider value should be " + expectedValue, expectedValue, currentValue);
    }

}
