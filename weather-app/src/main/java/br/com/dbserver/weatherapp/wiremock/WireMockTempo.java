//package br.com.dbserver.weatherapp.wiremock;
//
//import com.github.tomakehurst.wiremock.WireMockServer;
//import com.google.gson.Gson;
//import com.google.gson.JsonObject;
//import org.apache.hc.client5.http.classic.methods.HttpGet;
//import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
//import org.apache.hc.client5.http.impl.classic.HttpClients;
//import org.apache.hc.core5.http.HttpResponse;
//
//import static com.github.tomakehurst.wiremock.client.WireMock.*;
//
//
//public class WireMockTempo {
//
//    public static void main(String[] args) throws Exception {
//        WireMockServer wireMockServer = new WireMockServer(8081);
//        wireMockServer.start();
//
//        configureFor("localhost", 8080);
//        stubFor(get(urlEqualTo("/previsao-tempo")).willReturn(aResponse().withStatus(200).withHeader("Content-Type", "application/json").withBody("{\"cidade\": \"São Paulo\", \"previsao\": \"Ensolarado\"}")));
//
//        CloseableHttpClient httpClient = HttpClients.createDefault();
//
//        HttpGet request = new HttpGet("http://localhost:8080/previsao-tempo");
//
//        HttpResponse httpResponse = httpClient.execute(request);
//
//        String responseString = convertResponseToJson(httpResponse);
//
//        System.out.println("Status da resposta: " + httpResponse.getCode());
//        System.out.println("Conteúdo da resposta: " + responseString);
//
//        verify(getRequestedFor(urlEqualTo("/previsao-tempo")));
//
//        assertEquals("{\"cidade\": \"São Paulo\", \"previsao\": \"Ensolarado\"}", responseString);
//
//        wireMockServer.stop();
//    }
//
//    private static JsonObject convertResponseToJson (String responseString){
//        Gson gson = new Gson();
//        JsonObject jsonObject = gson.fromJson(responseString, JsonObject.class);
//        return jsonObject;
//    }
//}