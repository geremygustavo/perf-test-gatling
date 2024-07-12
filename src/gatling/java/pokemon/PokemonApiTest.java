import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;
public class PokemonApiTest extends Simulation {


// Define the base URL and headers

    private HttpProtocolBuilder httrProtocol = http
            .baseUrl( "https://pokeapi.co/api/v2/pokemon");
    FeederBuilder.FileBased<Object> feeder = jsonFile("data/pokemon.json").circular();
// Define the scenario

    ScenarioBuilder scn = scenario ("Pokemon API Test")
            .feed(feeder)
            .exec(http("Get Pikachu")
            .get ("/#{pokemonName}")
    .check(status().is( 200)));

// Set up the

    {

        setUp(

                scn.injectOpen(
                        rampUsers(10).during(10)

                )

        ).protocols(httrProtocol);

    }
    }
