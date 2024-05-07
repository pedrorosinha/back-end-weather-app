package br.com.dbserver.weatherapp;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class WireMockConfig {

    private static WireMockServer wireMockServer;

    public static void startServer(int port) {
        WireMockConfiguration config = WireMockConfiguration.wireMockConfig().port(port);
        wireMockServer = new WireMockServer(config);
        wireMockServer.start();
    }

    public static void stopServer() {
        if (wireMockServer != null) {
            wireMockServer.stop();
        }
    }

    public static void resetServer() {
        if (wireMockServer != null) {
            wireMockServer.resetAll();
        }
    }

    public static void stubEndpoint(String url, String responseBody) {
        wireMockServer.stubFor(get(WireMock.urlEqualTo(url))
                .willReturn(WireMock.aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(responseBody)));
    }
}