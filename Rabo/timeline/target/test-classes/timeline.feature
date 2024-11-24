Feature: Timeline test cases

  Scenario: testPostMessage
    Given url 'http://localhost:8087/timeline/postMessage'
    And request {"messageContent":"Good morning. First Message to Timeline", "customerId": 2, "timelineId": 1,"eventType":"postMessage"}
    When method POST
    Then status 201
    And match response == 'The event has been successfully published'
    And print response


