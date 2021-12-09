@Login
  Feature: I want to log into the book store so that I can access all of the features
    @Scrapped
    Scenario: Log into the book store website
        When navigate to the LogIn page
        And  enter "spriteCloudUser" and "Indominus1*"
        Then verify that the Profile page is displayed