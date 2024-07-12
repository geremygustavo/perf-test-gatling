package restfullapi;

import io.gatling.javaapi.core.FeederBuilder;
import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;
import io.gatling.javaapi.http.HttpProtocolBuilder;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.http;
import static io.gatling.javaapi.http.HttpDsl.status;

public class RestFullApiTest extends Simulation {


// Define the base URL and headers

    private HttpProtocolBuilder httrProtocol = http
            .baseUrl( "https://api.restful-api.dev");
    FeederBuilder.FileBased<Object> feeder = jsonFile("data/rest-api-test.json").circular();
// Define the scenario
    ScenarioBuilder scn = scenario ("Rest API Test")
            .feed(feeder)
            .exec(http("Add Onject")
            .post ("/objects").header("content-type", "application/json")
//                    .body(StringBody("{ \"name\": \"#{name}\",  \"data\": {\"year\":#{year},\"price\":#{price},\"CPU model\":\"#{CPU model}\",\"Hard disk size\":\"#{Hard disk size}\"}}"))
                    .body(StringBody("{\"name\": \"Apple iPad Air\", \"data\": { \"Generation\": \"4th\", \"Price\": \"519.99\", \"Capacity\": \"256 GB\" }}"))
    .check(status().is( 200))
                            .check(jmesPath("id").find().saveAs("ability"))
            )
        .exec(
                session -> {
                    System.out.println("Pokemon: " + session.getString("ability"));
                    return session;
                }
        );

// Set up the

    {

        setUp(

                scn.injectOpen(
                        rampUsers(10).during(10)

                )

        ).protocols(httrProtocol);

    }
    }
