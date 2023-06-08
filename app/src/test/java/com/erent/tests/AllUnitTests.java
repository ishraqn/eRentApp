package com.erent.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.erent.tests.logic.PostLogicTest;
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
})

public class AllUnitTests
{

}
