package com.xp.ssetest;

import org.jsmart.zerocode.core.domain.JsonTestCase;
import org.jsmart.zerocode.core.domain.TargetEnv;
import org.jsmart.zerocode.core.runner.ZeroCodeUnitRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Neeraj Sidhaye
 */

@TargetEnv("application_host.properties")
@RunWith(ZeroCodeUnitRunner.class)
public class TestEventPublish {

    @Test
    @JsonTestCase("event_publish.json")
    public void test_eventPublishToServer() throws Exception {
    }

}
