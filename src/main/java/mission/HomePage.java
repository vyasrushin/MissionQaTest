package mission;

import mission.Base.BasePage;
import mission.utils.LoadProp;

public class HomePage extends BasePage {

    public static void homePage() {
        //driver.get(LoadProp.getProperty("url"));
        
    	BasePage.getDriver().get(LoadProp.getProperty("url"));
    }
}
