//package br.com.dbserver.weatherapp.controller;
//
//import br.com.dbserver.weatherapp.WeatherAppApplication;
//import br.com.dbserver.weatherapp.dto.PrevisaoDTO;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.web.client.TestRestTemplate;
//import org.springframework.boot.test.web.server.LocalServerPort;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = WeatherAppApplication.class)
//class PrevisaoTempoControllerTest {
//
//    @LocalServerPort
//    private int port;
//
//    @Autowired
//    private TestRestTemplate restTemplate;
//
//    private String cidade = "São Paulo";
//
//    @Test
//    void testObterPrevisaoTempoAtual() {
//        String url = buildUrl("/previsao-tempo/atual");
//
//        ResponseEntity<PrevisaoDTO> response = restTemplate.getForEntity(url, PrevisaoDTO.class);
//
//        assertResponse(HttpStatus.OK, response);
//        PrevisaoDTO resultado = response.getBody();
//        assertNotNull(resultado);
//        assertEquals(cidade, resultado.getCidade());
//    }
//
//    @Test
//    void testObterPrevisaoProximos7Dias() {
//        String url = buildUrl("/previsao-tempo/proximos-7-dias");
//
//        PrevisaoDTO[] previsoes = restTemplate.getForObject(url, PrevisaoDTO[].class);
//
//        assertNotNull(previsoes);
//        assertEquals(7, previsoes.length);
//    }
//
//    private String buildUrl(String path) {
//        return "http://localhost:" + port + path + "?cidade=" + cidade;
//    }
//
//    private void assertResponse(HttpStatus expectedHttpStatus, ResponseEntity<?> response) {
//        assertEquals(expectedHttpStatus, response.getStatusCode());
//    }
//}
