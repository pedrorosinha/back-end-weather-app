package br.com.dbserver.weatherapp.controller;

import br.com.dbserver.weatherapp.WeatherAppApplication;
import br.com.dbserver.weatherapp.controllers.PrevisaoTempoController;
import br.com.dbserver.weatherapp.dto.PrevisaoDTO;
import br.com.dbserver.weatherapp.services.interf.PrevisaoTempoService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = WeatherAppApplication.class)
class PrevisaoTempoControSllerTest {

    @Mock
    private PrevisaoTempoService previsaoTempoService;

    @InjectMocks
    private PrevisaoTempoController previsaoTempoController;

    private String cidade;

    @BeforeEach
    void setUp() {
        cidade = "São Paulo";
    }

    @Test
    void testObterPrevisaoHojeSucesso() {
        PrevisaoDTO previsaoDTO = new PrevisaoDTO(cidade, "Ensolarado");

        when(previsaoTempoService.obterPrevisaoTempoAtual(cidade)).thenReturn(previsaoDTO);

        ResponseEntity<PrevisaoDTO> response = previsaoTempoController.obterPrevisaoHoje(cidade);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(previsaoDTO, response.getBody());
    }

    @Test
    void testObterPrevisaoHojeErro() {
        when(previsaoTempoService.obterPrevisaoTempoAtual(cidade)).thenThrow(new EntityNotFoundException());

        ResponseEntity<PrevisaoDTO> response = previsaoTempoController.obterPrevisaoHoje(cidade);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void testObterPrevisaoHojeErroServidor() {
        when(previsaoTempoService.obterPrevisaoTempoAtual(cidade)).thenThrow(new RuntimeException());

        ResponseEntity<PrevisaoDTO> response = previsaoTempoController.obterPrevisaoHoje(cidade);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    void testObterPrevisaoSemana() {
        List<PrevisaoDTO> previsoesDTO = Arrays.asList(new PrevisaoDTO(cidade, "Chuvoso"), new PrevisaoDTO(cidade, "Nublado"));

        when(previsaoTempoService.obterPrevisaoProximos7Dias(cidade)).thenReturn(previsoesDTO);

        ResponseEntity<List<PrevisaoDTO>> response = previsaoTempoController.obterPrevisaoSemana(cidade);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(previsoesDTO, response.getBody());
    }

    @Test
    void testGetAllPrevisoes() {
        List<PrevisaoDTO> previsoesDTO = Arrays.asList(new PrevisaoDTO("São Paulo", "Ensolarado"), new PrevisaoDTO("Porto Alegre", "Chuvoso"));

        when(previsaoTempoService.getAllPrevisoesAsDTO()).thenReturn(previsoesDTO);

        ResponseEntity<List<PrevisaoDTO>> response = previsaoTempoController.getAllPrevisoes();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(previsoesDTO, response.getBody());
    }

    @Test
    void testCadastrarPrevisaoSucesso() {
        PrevisaoDTO previsaoDTO = new PrevisaoDTO(cidade, "Chuvoso");

        when(previsaoTempoService.cadastrarPrevisao(any(PrevisaoDTO.class))).thenReturn(previsaoDTO);

        ResponseEntity<PrevisaoDTO> response = previsaoTempoController.cadastrarPrevisao(previsaoDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(previsaoDTO, response.getBody());
    }

    @Test
    void testAtualizarPrevisaoSucesso() {
        Long id = 1L;
        PrevisaoDTO previsaoDTO = new PrevisaoDTO(cidade, "Nublado");

        when(previsaoTempoService.atualizarPrevisao(eq(id), any(PrevisaoDTO.class))).thenReturn(previsaoDTO);

        ResponseEntity<PrevisaoDTO> response = previsaoTempoController.atualizarPrevisao(id, previsaoDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(previsaoDTO, response.getBody());
    }

    @Test
    void testAtualizarPrevisaoErro() {
        Long id = 1L;
        PrevisaoDTO previsaoDTO = new PrevisaoDTO(cidade, "Nublado");

        when(previsaoTempoService.atualizarPrevisao(eq(id), any(PrevisaoDTO.class))).thenThrow(new EntityNotFoundException());

        ResponseEntity<PrevisaoDTO> response = previsaoTempoController.atualizarPrevisao(id, previsaoDTO);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}