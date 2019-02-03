package com.xp.ssetest;

import org.jsmart.zerocode.core.domain.LoadWith;
import org.jsmart.zerocode.core.domain.TestMapping;
import org.jsmart.zerocode.core.domain.TestMappings;
import org.jsmart.zerocode.core.runner.parallel.ZeroCodeMultiLoadRunner;
import org.junit.runner.RunWith;

/**
 * @author Neeraj Sidhaye
 */

@LoadWith("parallel_load.properties")
@TestMappings(
        {
                @TestMapping(testClass = TestClient1.class, testMethod = "test_client1"),
                @TestMapping(testClass = TestClient2.class, testMethod = "test_client2"),
                @TestMapping(testClass = TestEventPublish.class, testMethod = "test_eventPublishToServer")
        }
)
@RunWith(ZeroCodeMultiLoadRunner.class)
public class TestSuite {

}