Feature: User wants to sell his pet for the first time

Scenario Outline: User wants to sell his pet on the application
  Given The user has put his pet <name> available on the application
  And checks that the pet is available
  When The pet is sold
  Then The pet is in the application with status sold
  When the user removes the post on the application
  Then the pet is not in the application
  Examples:
            | name |
            | Revit |
            | miPatoPaco      |