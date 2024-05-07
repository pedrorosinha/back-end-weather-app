//package br.com.dbserver.weatherapp.controller;
//
//import br.com.dbserver.weatherapp.controllers.PrevisaoTempoController;
//import br.com.dbserver.weatherapp.dto.PrevisaoDTO;
//import br.com.dbserver.weatherapp.model.PrevisaoTempo;
//import br.com.dbserver.weatherapp.services.interf.PrevisaoTempoService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
//import java.util.Arrays;
//import java.util.List;
//
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//public class PrevisaoTempoControllerTest {
//
//    private MockMvc mockMvc;
//
//    @Mock
//    private PrevisaoTempoService previsaoTempoService;
//
//    @InjectMocks
//    private PrevisaoTempoController previsaoTempoController;
//
//    @BeforeEach
//    void setup() {
//        mockMvc = MockMvcBuilders.standaloneSetup(previsaoTempoController).build();
//    }
//
//    @Test
//    void getTempoAtual() throws Exception {
//        String cidade = "São Paulo";
//        PrevisaoDTO previsaoDTO = new PrevisaoDTO(cidade, "Ensolarado");
//
//        when(previsaoTempoService.obterPrevisaoTempoAtual(cidade)).thenReturn(previsaoDTO);
//
//        mockMvc.perform(get("/tempo/previsao/hoje").param("cidade", cidade).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON)).andExpect(jsonPath("$.cidade").value(cidade)).andExpect(jsonPath("$.tempo").value("Ensolarado"));
//    }
//
//    @Test
//    void getTempo7dias() throws Exception {
//        String cidade = "São Paulo";
//        List<PrevisaoDTO> previsoesDTO = Arrays.asList(new PrevisaoDTO(cidade, "Chuvoso"), new PrevisaoDTO(cidade, "Nublado"));
//
//        when(previsaoTempoService.obterPrevisaoProximos7Dias(cidade)).thenReturn(previsoesDTO);
//
//        mockMvc.perform(get("/tempo/previsao/semana").param("cidade", cidade).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON)).andExpect(jsonPath("$[0].cidade").value(cidade)).andExpect(jsonPath("$[0].tempo").value("Chuvoso")).andExpect(jsonPath("$[1].cidade").value(cidade)).andExpect(jsonPath("$[1].tempo").value("Nublado"));
//    }
//
//    @Test
//    void getAllPrevisaoTempo() throws Exception {
//        List<PrevisaoTempo> previsoes = Arrays.asList(new PrevisaoTempo(1L, "São Paulo", "Chuvoso"), new PrevisaoTempo(2L, "Porto Alegre", "Chuvoso"));
//
//        when(previsaoTempoService.getAllPrevisoes()).thenReturn(previsoes);
//
//        mockMvc.perform(get("/tempo/previsao/previsoes").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON)).andExpect(jsonPath("$[0].cidade").value("São Paulo")).andExpect(jsonPath("$[0].tempo").value("Chuvoso")).andExpect(jsonPath("$[1].cidade").value("Porto Alegre")).andExpect(jsonPath("$[1].tempo").value("Chuvoso"));
//    }
//}