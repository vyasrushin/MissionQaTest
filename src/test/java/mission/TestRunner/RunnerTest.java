package mission.TestRunner;

import org.testng.annotations.DataProvider;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

	@CucumberOptions(
			dryRun = false,
	        features = "src/test/resources/features",
	        glue = {"mission.StepDefinition", "mission.hook"},
    		tags = "@ui or @Restapi",
	        monochrome = true,
			plugin  = {
					"pretty",
			        "html:target/cucumber-reports/cucumber.html",
			        "json:target/cucumber-reports/cucumber.json",
			        "rerun:target/cucumber-reports/rerun.txt"
	        })

	public class RunnerTest extends AbstractTestNGCucumberTests {
		@Override
		@DataProvider(parallel = true)  // Enables parallel execution without testing.xml. OR we can use testing.xml for parallel execution
		
		public Object[][] scenarios(){
			return super.scenarios();
		}
	}
