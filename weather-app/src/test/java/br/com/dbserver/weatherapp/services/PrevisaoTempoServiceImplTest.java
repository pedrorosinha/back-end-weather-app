//package br.com.dbserver.weatherapp.services;
//
//import br.com.dbserver.weatherapp.exceptions.ResourceNotFoundException;
//import br.com.dbserver.weatherapp.model.PrevisaoTempo;
//import br.com.dbserver.weatherapp.repository.PrevisaoTempoRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//class PrevisaoTempoServiceImplTest {
//
//    @Mock
//    private PrevisaoTempoRepository previsaoTempoRepository;
//
//    @InjectMocks
//    private PrevisaoTempoServiceImpl previsaoTempoService;
//
//    private String cidade1;
//    private String previsao1;
//    private String cidade2;
//    private String previsao2;
//
//    @BeforeEach
//    void setUp() {
//        cidade1 = "São Paulo";
//        previsao1 = "Ensolarado";
//        cidade2 = "Rio de Janeiro";
//        previsao2 = "Chuvoso";
//    }
//
//    @Test
//    void getAllPrevisoes() {
//        // Arrange
//        List<PrevisaoTempo> previsoes = new ArrayList<>();
//        previsoes.add(new PrevisaoTempo(1L, cidade1, previsao1));
//        previsoes.add(new PrevisaoTempo(2L, cidade2, previsao2));
//
//        when(previsaoTempoRepository.findAll()).thenReturn(previsoes);
//
//        // Act
//        List<PrevisaoTempo> resultado = previsaoTempoService.getAllPrevisoes();
//
//        // Assert
//        assertEquals(2, resultado.size());
//        assertEquals(cidade1, resultado.get(0).getCidade());
//        assertEquals(previsao1, resultado.get(0).getTempo());
//        assertEquals(cidade2, resultado.get(1).getCidade());
//        assertEquals(previsao2, resultado.get(1).getTempo());
//    }
//
//    @Test
//    void cadastrarPrevisao() {
//        // Arrange
//        PrevisaoTempo previsaoTempo = new PrevisaoTempo(1L, cidade1, previsao1);
//
//        when(previsaoTempoRepository.save(previsaoTempo)).thenReturn(new PrevisaoTempo(1L, cidade1, previsao1));
//
//        // Act
//        PrevisaoTempo resultado = previsaoTempoService.cadastrarPrevisao(previsaoTempo);
//
//        // Assert
//        assertNotNull(resultado.getId());
//        assertEquals(cidade1, resultado.getCidade());
//        assertEquals(previsao1, resultado.getTempo());
//    }
//
//    @Test
//    void cadastrarPrevisaoErro() {
//        // Arrange
//        PrevisaoTempo previsaoTempo = new PrevisaoTempo(null, cidade1, previsao1);
//
//        when(previsaoTempoRepository.save(previsaoTempo)).thenThrow(RuntimeException.class);
//
//        // Act + Assert
//        assertThrows(RuntimeException.class, () -> previsaoTempoService.cadastrarPrevisao(previsaoTempo));
//    }
//
//    @Test
//    void editarPrevisao() {
//        // Arrange
//        Long id = 1L;
//        PrevisaoTempo previsaoTempo = new PrevisaoTempo(id, cidade1, previsao1);
//
//        when(previsaoTempoRepository.findById(id)).thenReturn(Optional.of(previsaoTempo));
//        when(previsaoTempoRepository.save(previsaoTempo)).thenReturn(previsaoTempo);
//
//        // Act
//        PrevisaoTempo resultado = previsaoTempoService.editarPrevisao(id, previsaoTempo);
//
//        // Assert
//        assertEquals(cidade1, resultado.getCidade());
//        assertEquals(previsao1, resultado.getTempo());
//    }
//
//    @Test
//    void editarPrevisaoErro() {
//        // Arrange
//        Long id = 1L;
//
//        when(previsaoTempoRepository.findById(id)).thenReturn(Optional.empty());
//
//        // Act + Assert
//        assertThrows(ResourceNotFoundException.class, () -> previsaoTempoService.editarPrevisao(id, new PrevisaoTempo()));
//    }
//
//    @Test
//    void deletarPrevisao() {
//        // Arrange
//        Long id = 1L;
//        PrevisaoTempo previsaoTempo = new PrevisaoTempo(id, cidade1, previsao1);
//
//        when(previsaoTempoRepository.findById(id)).thenReturn(Optional.of(previsaoTempo));
//
//        // Act
//        previsaoTempoService.deletarPrevisao(id);
//
//        // Assert
//        verify(previsaoTempoRepository, times(1)).delete(previsaoTempo);
//    }
//
//    @Test
//    void deletarPrevisaoErro() {
//        // Arrange
//        Long id = 1L;
//
//        when(previsaoTempoRepository.findById(id)).thenReturn(Optional.of(new PrevisaoTempo()));
//        doThrow(RuntimeException.class).when(previsaoTempoRepository).delete(any());
//
//        // Act + Assert
//        assertThrows(RuntimeException.class, () -> previsaoTempoService.deletarPrevisao(id));
//    }
//}