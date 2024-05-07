package br.com.dbserver.weatherapp.services;

import br.com.dbserver.weatherapp.exceptions.ResourceNotFoundException;
import br.com.dbserver.weatherapp.model.PrevisaoTempo;
import br.com.dbserver.weatherapp.repository.PrevisaoTempoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PrevisaoTempoServiceImplTest {
    @Mock
    private PrevisaoTempoRepository previsaoTempoRepository;

    @InjectMocks
    private PrevisaoTempoServiceImpl previsaoTempoService;

    private String cidade1;
    private String previsao1;
    private String cidade2;
    private String previsao2;

    @BeforeEach
    void setUp() {
        cidade1 = "São Paulo";
        previsao1 = "Ensolarado";
        cidade2 = "Rio de Janeiro";
        previsao2 = "Chuvoso";
    }

    @Test
    void getAllPrevisoes() {
        List<PrevisaoTempo> previsoes = new ArrayList<>();
        previsoes.add(new PrevisaoTempo(1L, cidade1, previsao1));
        previsoes.add(new PrevisaoTempo(2L, cidade2, previsao2));

        when(previsaoTempoRepository.findAll()).thenReturn(previsoes);

        List<PrevisaoTempo> resultado = previsaoTempoService.getAllPrevisoes();

        assertEquals(2, previsoes.size());
        assertEquals(cidade1, resultado.get(0).getCidade());
        assertEquals(previsao1, resultado.get(0).getTempo());
        assertEquals(cidade2, resultado.get(1).getCidade());
        assertEquals(previsao2, resultado.get(1).getTempo());
    }

    @Test
    void cadastrarPrevisao() {
        PrevisaoTempo previsaoTempo = new PrevisaoTempo(null, cidade1, previsao1);

        previsaoTempoService.cadastrarPrevisao(previsaoTempo);

        verify(previsaoTempoRepository, times(1)).save(previsaoTempo);
    }

    @Test
    void cadastrarPrevisaoErro() {
        doThrow(RuntimeException.class).when(previsaoTempoRepository).save(any());

        PrevisaoTempo previsaoTempo = new PrevisaoTempo(null, cidade1, previsao1);

        assertThrows(RuntimeException.class, () -> previsaoTempoService.cadastrarPrevisao(previsaoTempo));

        verify(previsaoTempoRepository, times(1)).save(previsaoTempo);
    }

    @Test
    void editarPrevisao() {
        PrevisaoTempo previsaoTempo = new PrevisaoTempo(1L, cidade1, previsao1);

        when(previsaoTempoRepository.findById(1L)).thenReturn(Optional.of(previsaoTempo));

        previsaoTempoService.editarPrevisao(1L, previsaoTempo);

        verify(previsaoTempoRepository, times(1)).save(previsaoTempo);
    }

    @Test
    void editarPrevisaoErro() {
        when(previsaoTempoRepository.findById(1L)).thenReturn(Optional.empty());

        PrevisaoTempo previsaoTempo = new PrevisaoTempo(1L, cidade1, previsao1);

        assertThrows(ResourceNotFoundException.class, () -> previsaoTempoService.editarPrevisao(1L, previsaoTempo));

        verify(previsaoTempoRepository, never()).save(any());
    }

    @Test
    void deletarPrevisao() {
        when(previsaoTempoRepository.findById(1L)).thenReturn(Optional.of(new PrevisaoTempo()));

        previsaoTempoService.deletarPrevisao(1L);

        verify(previsaoTempoRepository, times(1)).delete(any());
    }

    @Test
    void deletarPrevisaoErro() {
        when(previsaoTempoRepository.findById(1L)).thenReturn(Optional.of(new PrevisaoTempo()));

        doThrow(RuntimeException.class).when(previsaoTempoRepository).delete(any());

        assertThrows(RuntimeException.class, () -> previsaoTempoService.deletarPrevisao(1L));

        verify(previsaoTempoRepository, times(1)).delete(any());
    }
}