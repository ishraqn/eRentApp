package com.erent.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.erent.tests.logic.PostLogicIT;
import com.erent.tests.logic.UserLogicIT;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        PostLogicIT.class,
        UserLogicIT.class
})

public class AllIntegrationTests {
}
