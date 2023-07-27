# Iteration 3 Worksheet

## What technical debt has been cleaned up

In the release of iteration 2, we create instances of the logic layer classes as needed in the UI layer. The variables use the interface, but the created instances were hardcoded. This was a violation of the Dependency Inversion Principle. In [this commit](https://code.cs.umanitoba.ca/3350-summer2023/segmentationfault/-/commit/95d36527f070fd75a6a6440ce39aa0955044727a), the logic class instances were moved to `Services.java` which provides a singleton reference to the logic instances.

## What technical debt did you leave?

We originally added `location` and `category` variables to our post data and logic to facilitate the search feature, but this feature was cut. However, the variables still remain in the code and database. They should be removed at some point, but they don't cause any problems as is, so they've been left in to ensure we have enough time to do everything we need to for iteration 3.

## Discuss a Feature or User Story that was cut/re-prioritized

We had originally planned to have a search bar on the homepage where the user could look for particular posts. This feature was cut as realized we wouldn't have time to do it after doing everything else that was more important. The feature is described [here](https://code.cs.umanitoba.ca/3350-summer2023/segmentationfault/-/issues/17).

## Acceptance test/end-to-end

A test written was for the Post Management Feature. These test the create post functionality as well as delete post. I made it so that before the test starts it creates a new user to prevent flakiness so I can guarantee that this user has no posts created for them, that way when a post is created I know it will be the first in the list. I also delete the user after the test (which then deletes the created post). I also create a post and manually add it before the delete test so I can guarantee that there is a post to delete. The test can be found [here](https://code.cs.umanitoba.ca/3350-summer2023/segmentationfault/-/blob/87aa3b0c659e19278fe763d274a56817408afa61/app/src/androidTest/java/com/erent/acceptance/PostManagementTest.java#L1).

## Acceptance test, untestable

A challenge faced when creating the acceptance test was interacting with the recycler view. I had trouble setting it up so that the Espresso could click on the posts and was not able to find a way to check if there were no posts being displayed.

## Velocity/teamwork

From iterations 1 to 2, our estimates generally improved. While both iterations had bad estimates, the estimate vs actual times at the end of iteration 2 was much better than it was for iteration 1. A good time estimate from iteration 2 is found in [this dev task](https://code.cs.umanitoba.ca/3350-summer2023/segmentationfault/-/work_items/98), and some bad time estimates across both iterations are: [iteration 1](https://code.cs.umanitoba.ca/3350-summer2023/segmentationfault/-/work_items/43) and [iteration 2](https://code.cs.umanitoba.ca/3350-summer2023/segmentationfault/-/issues/75). Below is a graph of estimated vs actual times.

![Velocity](Velocity Graph.png)