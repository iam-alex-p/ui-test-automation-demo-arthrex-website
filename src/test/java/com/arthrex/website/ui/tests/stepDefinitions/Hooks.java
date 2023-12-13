package com.arthrex.website.ui.tests.stepDefinitions;

import com.arthrex.website.ui.tests.TestContext;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks {
    private final TestContext testContext;

    public Hooks(TestContext testContext) {
        this.testContext = testContext;
    }

    @Before
    public void beforeSteps() {
        this.testContext.setupWebDriver();
    }

    @After
    public void afterSteps() {
        this.testContext.teardownWebDriver();
    }
}
