package org.petStore.runners;

import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;


@RunWith(CucumberWithSerenity.class)
@CucumberOptions(features = { "src/test/resources/features/" }, glue = {
        "org.petStore.steps" })

public class PetStoreTest {
}
