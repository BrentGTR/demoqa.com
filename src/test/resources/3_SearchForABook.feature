@SearchForABook
  Feature: As a user I want to search for a book
    Scenario: I want to search for a book with the title of "Speaking Javascript"
      When searching for a book with the title of "Speaking JavaScript"
      Then verify that the book displayed has a title of "Speaking JavaScript"
