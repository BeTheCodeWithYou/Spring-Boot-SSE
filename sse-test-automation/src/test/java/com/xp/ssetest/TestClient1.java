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
public class TestClient1 {

    @Test
    @JsonTestCase("client1.json")
    public void test_client1() throws Exception {

    }

}
