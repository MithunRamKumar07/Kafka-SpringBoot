import com.intuit.karate.junit5.Karate;

public class TestRunner {

    @Karate.Test
    Karate testTimelineScenarios(){
        return Karate.run("timeline.feature").relativeTo(getClass());
    }
}
