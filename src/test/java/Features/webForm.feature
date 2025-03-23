@test
Feature: Test web form

  Scenario: Verify text input, Password, Textarea
    Given I am on the web form page
    When I enter the following text
      | field      | value          |
      | Text input | Automation     |
      | Password   | Testing        |
      | Textarea   | This is a test |
    Then the following fields should contain
      | field      | value          |
      | Text input | Automation     |
      | Password   | Testing        |
      | Textarea   | This is a test |

  Scenario: Verify Disabled and Readonly fields
    Given I am on the web form page
    Then the field "Disabled input" should be disabled
    Then the field "Readonly input" should be readonly

  Scenario: Verify checkboxes
     Given I am on the web form page
     When I check the default checkbox
     Then the "default" checkbox should be "checked"
     When I uncheck the checked checkbox
     Then the "checked" checkbox should be "unchecked"

  Scenario: Verify radio buttons
    Given I am on the web form page
    When I select the default radio button
    Then the "Checked" radio button should be "unselected"
    And the "default" radio button should be "selected"

  Scenario: Verify Color picker and Date picker
    Given I am on the web form page
    When I select red color from the color picker
    Then the color picker should display red
    When I open the date picker
    And I select the date "17" from the date picker
    Then the date picker should display the selected date "03/17/2025"

   Scenario: Verify Example range
    Given I am on the web form page
    When I set the range slider value to 7
    Then the range slider should display value 7

  Scenario: Verify form submitted successfully
    Given I am on the web form page
    When I enter the following text
      | field      | value          |
      | Text input | Automation     |
      | Password   | Testing        |
      | Textarea   | This is a test |
    And the field "Disabled input" should be disabled
    And the field "Readonly input" should be readonly
    And I select option "2" from the dropdown
    And I enter partial text "San" in the datalist to select the value
    And I upload the file "Test doc.docx"
    And I check the default checkbox
    And I uncheck the checked checkbox
    And I select the default radio button
    And I select red color from the color picker
    And I open the date picker
    And I select the date "17" from the date picker
    And I set the range slider value to 7
    And I submit the form
    Then the form is submitted successfully
    And I should see the following query parameters in the url
      | param       | value          |
      | my-text     | Automation     |
      | my-password | Testing        |
      | my-textarea | This+is+a+test |
      | my-readonly | Readonly+input |
      | my-select   | 2              |
      | my-datalist | San+Francisco  |
      | my-file     | Test+doc.docx  |
      | my-colors   | %23ff0000      |
      | my-date     | 03%2F17%2F2025 |
      | my-range    | 7              |

