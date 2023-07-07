Feature: User is able to buy a book

  Background: User is on the main page
    Given Open website "https://demoqa.com/books"

  Scenario: User can create new account
  When User navigates to Create new User page
  And User fills out all mandatory fields

#  Scenario: User can add a book into his collection
#    When User logs into website
#    And User searches for a book with keyword "Java"
#    Then User adds book number "1" with keyword "Java" into his collection
#    And The book with keyword "Java" is available in users profile

#  Scenario: User case remove all books from his collection
#    When User logs into website
#    And User searches for a book with keyword "Git"
#    And User adds book number "1" with keyword "Git" into his collection
#    Then User removes all books from his profile

#  Scenario: There is at least one book related to Git
#    When User searches for a book with keyword "Java"
#    Then Search result contains a book with keyword "Java"