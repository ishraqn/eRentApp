package com.erent.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.erent.tests.logic.BookmarkLogicTest;
import com.erent.tests.logic.PostLogicTest;
import com.erent.tests.logic.RentalLogicTest;
import com.erent.tests.logic.UserLogicTest;
import com.erent.tests.objects.PostTest;
import com.erent.tests.objects.UserTest;
import com.erent.tests.persistence.PostPersistenceStubTest;
import com.erent.tests.persistence.UserPersistenceStubTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        UserTest.class,
        UserPersistenceStubTest.class,
        PostTest.class,
        PostPersistenceStubTest.class,
        PostLogicTest.class,
        UserLogicTest.class,
        BookmarkLogicTest.class,
        RentalLogicTest.class
})

public class AllUnitTests
{

}
