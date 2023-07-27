package com.erent.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.erent.tests.logic.BookmarkLogicIT;
import com.erent.tests.logic.PostLogicIT;
import com.erent.tests.logic.RentalLogicIT;
import com.erent.tests.logic.UserLogicIT;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        PostLogicIT.class,
        UserLogicIT.class,
        BookmarkLogicIT.class,
        RentalLogicIT.class
})

public class AllIntegrationTests {
}
