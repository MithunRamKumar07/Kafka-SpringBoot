Feature: Customer test cases

  Scenario: createCustomer1
    Given url 'http://localhost:8085/customer'
    And request {"membershipStatus": "ACTIVE","customerName":"User1"}
    When method POST
    Then status 201
    And match response == {"customerId":#ignore,"customerName":"User1","createdAt":"#ignore","membershipStatus":"ACTIVE","eventType":"createCustomer"}
    And print response

  Scenario: createCustomer2
    Given url 'http://localhost:8085/customer'
    And request {"membershipStatus": "ACTIVE","customerName":"User2"}
    When method POST
    Then status 201
    And match response == {"customerId":#ignore,"customerName":"User2","createdAt":"#ignore","membershipStatus":"ACTIVE","eventType":"createCustomer"}
    And print response

  Scenario: createCustomer3
    Given url 'http://localhost:8085/customer'
    And request {"membershipStatus": "ACTIVE","customerName":"User3"}
    When method POST
    Then status 201
    And match response == {"customerId":#ignore,"customerName":"User3","createdAt":"#ignore","membershipStatus":"ACTIVE","eventType":"createCustomer"}
    And print response

    Scenario: updateCustomer
      Given url 'http://localhost:8085/customer/update'
      And request {"membershipStatus": "INACTIVE","customerId":1}
      When method PUT
      Then status 200
      And match response == {"customerId":#ignore,"customerName":"User1","createdAt":"#ignore","membershipStatus":"INACTIVE","eventType":"updateCustomer"}
      And print response

    Scenario: getCustomerById
      Given url 'http://localhost:8085/customer/get/1'
      When method GET
      Then status 200
      And match response == {"customerId":#ignore,"customerName":"User1","createdAt":"#ignore","membershipStatus":"ACTIVE","eventType":"#string"}
      And print response

    Scenario: deleteCustomer
      Given url 'http://localhost:8085/customer/delete/2'
      When method DELETE
      Then status 204
      And print response

    Scenario: getAllCustomers
      Given url 'http://localhost:8085/customer/get'
      When method GET
      Then status 200
      And print response

