package pages;

import org.openqa.selenium.*;
import org.junit.Assert;
import org.openqa.selenium.support.ui.Select;

import java.nio.file.Paths;
import java.util.List;


public class WebFormPage extends BasePage {

    public WebFormPage(WebDriver driver) {
        super(driver);
    }

    public void enterTextByLabel(String field, String value) {
        WebElement label = driver.findElement(By.xpath("//label[contains(text(), '" + field + "')]"));

        WebElement inputField;
        if (label.findElements(By.xpath(".//input")).size() > 0) {
            inputField = label.findElement(By.xpath(".//input"));
        } else if (label.findElements(By.xpath(".//textarea")).size() > 0) {
            inputField = label.findElement(By.xpath(".//textarea"));
        } else {
            throw new RuntimeException("No valid input or textarea elements found for label: " + field);
        }

        inputField.clear();
        inputField.sendKeys(value);
    }

    public String getFieldValueByLabel(String field) {
        WebElement label = driver.findElement(By.xpath("//label[contains(text(), '" + field + "')]"));

        WebElement inputField;
        if (label.findElements(By.xpath(".//input")).size() > 0) {
            inputField = label.findElement(By.xpath(".//input"));
        } else if (label.findElements(By.xpath(".//textarea")).size() > 0) {
            inputField = label.findElement(By.xpath(".//textarea"));
        } else {
            throw new RuntimeException("No valid input or textarea elements found for label: " + field);
        }

        return inputField.getAttribute("value");
    }

    public void submitForm() {
        WebElement submitButton = driver.findElement(By.xpath("//button[@type='submit']"));
        click(submitButton);
    }

    public void verifyFormSubmissionSuccess(String expectedHeading, String expectedMessage) {
        WebElement successHeader = driver.findElement(By.tagName("h1"));
        String headerText = successHeader.getText();

        WebElement successMessage = driver.findElement(By.id("message"));
        String messageText = successMessage.getText();

        Assert.assertEquals("Heading is incorrect!", expectedHeading, headerText);
        Assert.assertEquals("Message is incorrect!", expectedMessage, messageText);
    }

    public void verifyUrlQueryParameter(String paramName, String expectedValue) {
        String currentUrl = driver.getCurrentUrl();

        Assert.assertTrue("URL does not contain the expected query parameter: " + paramName,
                currentUrl.contains(paramName + "=" + expectedValue));
    }

    public boolean isFieldDisabled(String field) {
        WebElement inputField = driver.findElement(By.xpath(".//input[@name='my-disabled']"));

        return inputField.getAttribute("disabled") != null;
    }

    public boolean isFieldReadonly(String field) {
        WebElement inputField = driver.findElement(By.xpath(".//input[@name='my-readonly']"));

        return inputField.getAttribute("readonly") != null;
    }

    public void selectDropdownOptionByValue(String selectName, String value) {
        WebElement selectElement = driver.findElement(By.name(selectName));

        Select select = new Select(selectElement);
        select.selectByValue(value);
    }

    public void enterAndSelectDatalistOption(String datalistName, String partialValue) {
        WebElement inputField = driver.findElement(By.name(datalistName));
        inputField.sendKeys(partialValue);

        List<WebElement> options = driver.findElements(By.cssSelector("option"));

        for (WebElement option : options) {
            if (option.getAttribute("value").toLowerCase().contains(partialValue.toLowerCase())) {
                inputField.clear();
                inputField.sendKeys(option.getAttribute("value"));
                break;
            }
        }
    }

    public void uploadFile(String fileName) {
        String filePath = Paths.get("src", "test", "resources", fileName).toAbsolutePath().toString();
        WebElement fileInput = driver.findElement(By.name("my-file"));

        fileInput.sendKeys(filePath);
    }

    public void checkCheckbox(String checkboxId) {
        WebElement checkbox = driver.findElement(By.id(checkboxId));
        if (!checkbox.isSelected()) {
            checkbox.click();
        }
    }

    public void uncheckCheckbox(String checkboxId) {
        WebElement checkbox = driver.findElement(By.id(checkboxId));
        if (checkbox.isSelected()) {
            checkbox.click();
        }
    }

    public boolean isCheckboxSelected(String checkboxId) {
        WebElement checkbox = driver.findElement(By.id(checkboxId));
        return checkbox.isSelected();
    }

    public boolean isRadioSelected(String radioId) {
        WebElement radioButton = driver.findElement(By.id(radioId));
        return radioButton.isSelected();
    }

    public void selectRadioButton(String radioId) {
        WebElement radioButton = driver.findElement(By.id(radioId));
        if (!radioButton.isSelected()) {
            radioButton.click();
        }
    }

    public void setColorPicker(String colorValue) {
        WebElement colorPicker = driver.findElement(By.xpath("//input[@type='color']"));
        colorPicker.clear();
        colorPicker.sendKeys(colorValue);
    }

    public String getColorPickerValue() {
        WebElement colorPicker = driver.findElement(By.xpath("//input[@type='color']"));
        return colorPicker.getAttribute("value");
    }

    public void openDatePicker() {
        WebElement datePickerInput = driver.findElement(By.name("my-date"));
        datePickerInput.click();
    }

    public void selectDate(String date) {
        WebElement dayElement = driver.findElement(By.xpath("//td[@class='day' and text()='" + date + "']"));
        dayElement.click();
    }

    public String getSelectedDateFromDatePicker() {
        WebElement datePickerInput = driver.findElement(By.name("my-date"));
        return datePickerInput.getAttribute("value");
    }

    public void setRangeValue(int value) {
        WebElement rangeInput = driver.findElement(By.name("my-range"));

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].setAttribute('value', arguments[1])", rangeInput, value);

        js.executeScript("arguments[0].dispatchEvent(new Event('input'))", rangeInput);
    }

    public int getRangeValue() {
        WebElement rangeInput = driver.findElement(By.name("my-range"));
        String value = rangeInput.getAttribute("value");
        return Integer.parseInt(value);
    }


}
