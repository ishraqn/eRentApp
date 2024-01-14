# eRent Android App

An app which is based off the old saying *"Try-before-you-buy"*, where reducing waste is priority number 1.

## Running the App

- Clone the project
- Open the project in Android Studio (make sure you've installed JDK-17)
- Wait for the gradle to build
- Make sure you have an emulator installed and setup
- Press the "Run 'app'" button (green play button) near the top right of Android Studio to run the app
- The emulator should open and, once as the app has been installed onto the phone, the app should open
- You can login to an existing account:
    - Username: Shaun
    - Password: 1234

Note: You may need to clear the cache before running the app. Currently, any old version of the app messes with out persistence layer (until a new build is thrown on the phone, then it works fine). So, just use 'clear cache' (by holding down on the app on the phone's homescreen, selecting 'i', then 'storage', then 'clear data') when running the app for the first time.
Also you can login to an existing account:

## Running Tests

- Follow the above steps to setup the project on your computer
- Go to `app\src\test\java\com\erent\tests` (`app/java/com/erent/tests/AllUnitTests.java` in Android Studio's 'Android' view)
- To run the unit tests, right click on `AllUnitTests.java` and select "Run 'AllUnitTests'" from the options
- To run the integration tests, right click on `AllIntergrationTests.java` and select "Run 'AllIntergrationTests'" from the options

## Group Contribution

This project was a collaborative effort by four students for COMP 3350 at The University of Manitoba, CA.

## Vision Statement

- Our [vision statement](docs/VISION.md)

## Architecture

- Our [architecture](docs/ARCHITECTURE.md) diagram

## Worksheets

- [Iteration 2 Worksheet](docs/Iteration%202%20Worksheet.md)
- [Iteration 3 Worksheet](docs/Iteration%203%20Worksheet.md)

## Retrospective

- Our iteration 2 [retrospective](docs/RETROSPECTIVE.md) document
