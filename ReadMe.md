Pre-requisite:
Chrome browser - version 134

This framework is configured to run tests only in chrome browser in Mac.

To Run tests:
`mvn clean test`

To exclude any test with a specific tag:

Terminal:
`mvn clean test -Dcucumber.filter.tags="not @one"`

Junit:
`@CucumberOptions(tags = "not @one")`