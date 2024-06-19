package br.com.dbserver.weatherapp.controllers;

import br.com.dbserver.weatherapp.dto.PrevisaoDTO;
import br.com.dbserver.weatherapp.services.interf.PrevisaoTempoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:5173")
@RestController
@RequestMapping("/tempo/previsao")
public class PrevisaoTempoController {

    private final PrevisaoTempoService previsaoTempoService;

    @Autowired
    public PrevisaoTempoController(PrevisaoTempoService previsaoTempoService) {
        this.previsaoTempoService = previsaoTempoService;
    }

    @GetMapping("/todasPorCidade")
    public ResponseEntity<List<PrevisaoDTO>> obterTodasPrevisoesPorCidade(@RequestParam("cidade") String cidade) {
        List<PrevisaoDTO> previsoesDTO = previsaoTempoService.obterTodasPrevisoesPorCidade(cidade);
        return ResponseEntity.ok(previsoesDTO);
    }

    @GetMapping("/todas")
    public ResponseEntity<List<PrevisaoDTO>> obterTodasPrevisoes() {
        List<PrevisaoDTO> previsoesDTO = previsaoTempoService.getAllPrevisoes();
        return ResponseEntity.ok(previsoesDTO);
    }

    @PostMapping("/")
    public ResponseEntity<PrevisaoDTO> cadastrarPrevisao(@RequestBody PrevisaoDTO previsaoDTO) {
        PrevisaoDTO novaPrevisaoDTO = previsaoTempoService.cadastrarPrevisao(previsaoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaPrevisaoDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PrevisaoDTO> atualizarPrevisao(@PathVariable Long id, @RequestBody PrevisaoDTO previsaoDTO) {
        PrevisaoDTO previsaoAtualizadaDTO = previsaoTempoService.atualizarPrevisao(id, previsaoDTO);
        return ResponseEntity.ok(previsaoAtualizadaDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPrevisao(@PathVariable Long id) {
        previsaoTempoService.deletarPrevisao(id);
        return ResponseEntity.noContent().build();
    }
}
