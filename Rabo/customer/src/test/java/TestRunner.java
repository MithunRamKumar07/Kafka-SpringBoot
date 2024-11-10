import com.intuit.karate.junit5.Karate;

public class TestRunner {

    @Karate.Test
    Karate testCustomerScenarios(){
        return Karate.run("customer.feature").relativeTo(getClass());
    }
}
