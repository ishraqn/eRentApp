package com.erent;

import androidx.test.filters.LargeTest;

import com.erent.acceptance.BookmarkTest;
import com.erent.acceptance.PostManagementTest;
import com.erent.acceptance.RentalTest;
import com.erent.acceptance.UserAccountManagementTest;
import com.erent.acceptance.ViewPostTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@LargeTest
@RunWith(Suite.class)
@Suite.SuiteClasses({
        BookmarkTest.class,
        PostManagementTest.class,
        UserAccountManagementTest.class,
        ViewPostTest.class,
        RentalTest.class
})

public class AllAcceptanceTests {
}
