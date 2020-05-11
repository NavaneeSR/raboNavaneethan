Feature: Employee Service Validation with employee creation, updation and deletion

  Background: 
    Given employee service request is configured

  Scenario Outline: Validate the response message for creating an employee
    When this client making a POST call with employee details "<id>", "<employeeName>", "<firstName>", "<lastName>", "<email>", "<password>", "<phone>", "<employeeStatus>"
    Then the employee service status code is 200
    Then validate the response for the employee details

    Examples: 
      | id | employeeName | firstName | lastName | email | password | phone | employeeStatus |

  Scenario: Validate the response message for creating an multiple employee records
    When this client making a POST call with employees detail <empDetails>
      | id | employeeName | firstName | lastName | email | password | phone | employeeStatus |
    Then the employee service status code is 200
    Then validate the response for the employee details

  Scenario: Validate the response message to get employee details by Name
    When this client retrieves employee by name employee1
    Then the employee service status code is 200
    Then validate the response for the employee details

  Scenario: Validate the error message to get employee details by invalid name
    When this client retrieves employee by name employee
    Then the employee service status code is 400
    Then validate the error message "<errorMessage>" for invalid employee name
      | errorMessage                  |
      | Invalid employeename supplied |

  Scenario: Validate the error message to get employee details its not in system
    When this client retrieves employee by name employee
    Then the employee service status code is 404
    Then validate the error message "<errorMessage>" for invalid employee name
      | errorMessage       |
      | employee not found |

  Scenario: Validate the response message when an employee is logged into system
    When this client making GET call with <employeeName> and <password>
    Then the employee service status code is 200
    Then validate the response message <successMessage> for valid employee details
      | employeeName | password | successMessage   |
      |              |          | Login successful |

  Scenario: Validate the error message when an employee is logged into system with invalid password
    When this client making GET call with <employeeName> and <password>
    Then the employee service status code is 400
    Then validate the error message "<errorMessage>" for invalid employee details
      | employeeName | password | errorMessage                           |
      |              |          | Invalid employeename/password supplied |

  Scenario: Validate the error message when an employee is logged into system with invalid employeeName
    When this client making GET call with <employeeName> and <password>
    Then the employee service status code is 400
    Then validate the error message "<errorMessage>" for invalid employee details
      | employeeName | password | errorMessage                           |
      |              |          | Invalid employeename/password supplied |

  Scenario: Validate the response when updating the employee name
    When validating the employee logged in <employeeName> and <password>
    Then the employee service status code is 200
    When this client making PUT call with <updatedEmployeeName>, "<id>", "<employeeName>", "<firstName>", "<lastName>", "<email>", "<password>", "<phone>", "<employeeStatus>"
    Then validate the response with updated employee name
      | updatedEmployeeName | id | employeeName | firstName | lastName | email | password | phone | employeeStatus |
      |                     |    |              |           |          |       |          |       |                |

  Scenario: Validate the response when deleting the employee by name
    When validating the employee logged in <employeeName> and <password>
    Then the employee service status code is 200
    When this client making DELETE call with "<employeeName>"
    Then validate the response message "<successMessage>" employee details removed from system
      | successMessage |
      |                |
