@SearchForABook
  Feature: As a user I want to search for a book

    Background:
      Given that the Book Store Demo site has been accessed

    Scenario: I want to search for a book with the title of "Speaking Javascript"
      Then verify that the book displayed has a title of "Speaking Javascript"
