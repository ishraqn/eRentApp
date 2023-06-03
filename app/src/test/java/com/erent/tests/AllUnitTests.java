package com.erent.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.erent.tests.objects.UserTest;
import com.erent.tests.persistence.UserPersistenceStubTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        UserTest.class,
        UserPersistenceStubTest.class,
})

public class AllUnitTests
{

}
