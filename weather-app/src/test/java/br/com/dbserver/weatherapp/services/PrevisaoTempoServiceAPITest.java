//package br.com.dbserver.weatherapp.services;
//
//import br.com.dbserver.weatherapp.WireMockConfig;
//import br.com.dbserver.weatherapp.repository.PrevisaoTempoRepository;
//import com.github.tomakehurst.wiremock.WireMockServer;
//import com.github.tomakehurst.wiremock.client.WireMock;
//import org.junit.jupiter.api.AfterAll;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import static com.github.tomakehurst.wiremock.client.WireMock.*;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//@ExtendWith(MockitoExtension.class)
//public class PrevisaoTempoServiceAPITest {
//
//    private static WireMockServer wireMockServer;
//
//    @Mock
//    private PrevisaoTempoRepository previsaoTempoRepository;
//
//    @InjectMocks
//    private PrevisaoTempoServiceImpl previsaoTempoService;
//
//    @BeforeAll
//    static void setUpWireMock() {
//        WireMockConfig.startServer(8081);
//    }
//
//    @AfterAll
//    static void tearDownWireMock() {
//        WireMockConfig.stopServer();
//    }
//
//    @Test
//    public void getTempoAtual_SaoPaulo() {
//        WireMockConfig.stubEndpoint("/previsao-tempo", "{\"cidade\": \"São Paulo\", \"previsao\": \"Ensolarado\"}");
//
//        String resultado = previsaoTempoService.getPrevisaoTempoAtual("São Paulo");
//
//        assertEquals("{\"cidade\": \"São Paulo\", \"previsao\": \"Ensolarado\"}", resultado);
//    }
//}
