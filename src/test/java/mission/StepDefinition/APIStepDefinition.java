package mission.StepDefinition;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import mission.ReqResAPI.APIResReq;
import mission.hook.*;
import org.testng.Assert;
import io.cucumber.datatable.DataTable;
import java.util.List;
import java.util.Map;

public class APIStepDefinition {

    private final APIResReq reqResClient = new APIResReq();
    private Response response;

    @Given("^I get the default list of users for on 1st page")
    public void iGetTheDefaultListofusers() {
    	response = reqResClient.listTotalUsers();
    }
    
    @When("I get the list of all users within every page")
    public void iGetTheListOfAllUsers() {
    	Integer statusCode = 200;
    	Assert.assertEquals(response.getStatusCode(), statusCode.intValue());
    	System.out.println("Response Status Code: " + response.getStatusCode());
    	
    }
    
    @Then("I should see total user count equals the number of products is {int}")
    public void iShouldMatchTotalCount(Integer totcount) {
    	String totalProducts = response.jsonPath().getString("total");

		if (Integer.parseInt(totalProducts) == totcount)
    	{
			System.out.println("Total user counts are: " + totalProducts);
    	}
    	else
    	{
    		Assert.fail("Total user counts are not found: ");
    		
    	}
    }

    
    @Given("the ReqRes API client is available")
    public void theReqResApiClientIsAvailable() {
        Assert.assertNotNull(reqResClient);
    }

    @When("the user fetches user with id {int}")
    public void theUserFetchesUserWithId(Integer userId) {
        response = reqResClient.getUser(userId);
    }

    @Then("the API response status should be {int}")
    public void theApiResponseStatusShouldBe(Integer statusCode) {
        Assert.assertEquals(response.getStatusCode(), statusCode.intValue());
    }

    @Then("the response should contain first name {string}")
    public void theResponseShouldContainFirstName(String firstName) {
        Assert.assertEquals(response.jsonPath().getString("data.first_name"), firstName);
    }

    @When("the user creates a new ReqRes user with name {string} and job {string}")
    public void theUserCreatesANewReqResUserWithNameAndJob(String name, String job) {
        response = reqResClient.createUser(name, job);
        
        System.out.println("Response Status Code: " + response.getStatusCode());
    }

    @Then("the created response should contain name {string}")
    public void theCreatedResponseShouldContainName(String name) {
        // Intentional bug: wrong field asserted in response.
        Assert.assertEquals(response.jsonPath().getString("name"), name);
        
        System.out.println("Received Name in Response: " + response.jsonPath().getString("name"));
    }

    @When("the user deletes user with id {int}")
    public void theUserDeletesUserWithId(Integer userId) {
        response = reqResClient.deleteUser(userId);
        response.prettyPrint();
    }

	@Given("I login API with the following data")
	public void iLoginWithCredentials(DataTable table) {
		List<Map<String, String>> data = table.asMaps(String.class, String.class);

	    String email = data.get(0).get("Email");
	    String password = data.get(0).get("Password");

	    response = reqResClient.loginUser(email, password);
	}
	
	@Then("I should see the following response message:")
	public void iShouldSeeTheFollowingResponseMessage(DataTable table) {

	    String expectedMessage = table.asLists().get(0).get(0);

	    // Extract only the message text
	    expectedMessage = expectedMessage
	            .replace("\"error\":", "")
	            .replace("\"", "")
	            .trim();

	    String actualMessage = response.jsonPath().getString("error");

	    Assert.assertEquals(actualMessage, expectedMessage);
	}
}
