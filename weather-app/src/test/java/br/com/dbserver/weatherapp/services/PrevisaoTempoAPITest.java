package br.com.dbserver.weatherapp.services;

import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@WireMockTest(httpsEnabled = true, httpPort = 4433)
public class PrevisaoTempoAPITest {

    @Test
    public void testPrevisaoTempoService() throws IOException, ParseException {
        stubFor(get(urlEqualTo("/previsao-tempo")).willReturn(aResponse().withStatus(200).withHeader("Content-Type", "application/json").withBody("{\"cidade\": \"São Paulo\", \"previsao\": \"Ensolarado\"}")));

        CloseableHttpClient httpClient = HttpClients.createDefault();

        HttpGet request = new HttpGet("http://localhost:4433/previsao-tempo");
        try (CloseableHttpResponse response = httpClient.execute(request)) {
            String responseBody = EntityUtils.toString(response.getEntity());

            verify(getRequestedFor(urlEqualTo("/previsao-tempo")));
            assertEquals(200, response.getCode());
            assertEquals("{\"cidade\": \"São Paulo\", \"previsao\": \"Ensolarado\"}", responseBody);
        }
    }
}