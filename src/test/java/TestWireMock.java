import com.HttpGet;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.junit.Rule;
import org.junit.Test;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.Assert.*;

public class TestWireMock {

    private static String url = "http://localhost:8090";

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(options().port(8090));
    // public WireMockRule wireMockRule = new WireMockRule(8090);
    // public WireMockRule wireMockRule = new WireMockRule(); // started on default port 8080

    // if no stub mapping, to avoid VerificationException
    // public WireMockRule wireMockRule = new WireMockRule(options().port(8090).httpsPort(8091), false);

    // There could be multiple service mocking
    // @Rule
    // public WireMockRule service1 = new WireMockRule(8092);
    // @Rule
    // public WireMockRule service2 = new WireMockRule(8093);

    public void setupStubBaseConfig(){
        stubFor(get(urlEqualTo("/base/config"))
            .willReturn(aResponse()
            .withHeader("Content-Type", "text/plain")
            .withBody("setupStubBaseConfig by link /base/config")));
    }

    @Test
    public void testBaseConfig() throws Exception {
        setupStubBaseConfig();
        System.out.println(HttpGet.get(url + "/base/config"));
        assertNotNull(HttpGet.get(url + "/base/config"));
    }

    @Test
    public void testBaseConfigFail() throws Exception {
        setupStubBaseConfig();
        assertNotNull(HttpGet.get(url + "/base/config/fail"));
    }
}
