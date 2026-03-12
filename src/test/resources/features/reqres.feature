Feature: ReqRes API automation

Scenario Outline: Login scenarios
  Given the user prepares login request
  When the user logs in with email "<email>" and password "<password>"
  Then the login response status should be <status>
  And the response should contain "<message>"

Examples:
  | email              | password   | status | message          |
  | eve.holt@reqres.in | cityslicka | 200    | token            |
  | eve.holt@reqres.in |            | 400    | Missing password |

Scenario: Get list of colors
  When the user requests colors
  Then the response status should be 200
  And the response should contain 6 colors
  And the first color name should be "cerulean"
  And the color "tigerlily" should have year 2004