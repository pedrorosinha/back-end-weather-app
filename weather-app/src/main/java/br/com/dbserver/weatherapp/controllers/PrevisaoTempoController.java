package br.com.dbserver.weatherapp.controllers;

import br.com.dbserver.weatherapp.dto.PrevisaoDTO;
import br.com.dbserver.weatherapp.services.interf.PrevisaoTempoService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tempo/previsao")
public class PrevisaoTempoController {

    private final PrevisaoTempoService previsaoTempoService;

    @Autowired
    public PrevisaoTempoController(PrevisaoTempoService previsaoTempoService) {
        this.previsaoTempoService = previsaoTempoService;
    }

    @GetMapping("/hoje")
    public ResponseEntity<PrevisaoDTO> obterPrevisaoHoje(@RequestParam("cidade") String cidade) {
        try {
            PrevisaoDTO previsaoDTO = previsaoTempoService.obterPrevisaoTempoAtual(cidade);
            return ResponseEntity.ok(previsaoDTO);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/semana")
    public ResponseEntity<List<PrevisaoDTO>> obterPrevisaoSemana(@RequestParam("cidade") String cidade) {
        List<PrevisaoDTO> previsoesDTO = previsaoTempoService.obterPrevisaoProximos7Dias(cidade);
        return ResponseEntity.ok(previsoesDTO);
    }

    @GetMapping("/previsoes")
    public ResponseEntity<List<PrevisaoDTO>> getAllPrevisoes() {
        List<PrevisaoDTO> previsoesDTO = previsaoTempoService.getAllPrevisoesAsDTO();
        return ResponseEntity.ok(previsoesDTO);
    }

    @PostMapping("/")
    public ResponseEntity<PrevisaoDTO> cadastrarPrevisao(@RequestBody PrevisaoDTO previsaoDTO) {
        PrevisaoDTO novaPrevisaoDTO = previsaoTempoService.cadastrarPrevisao(previsaoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaPrevisaoDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PrevisaoDTO> atualizarPrevisao(@PathVariable Long id, @RequestBody PrevisaoDTO previsaoDTO) {
        try {
            PrevisaoDTO previsaoAtualizadaDTO = previsaoTempoService.atualizarPrevisao(id, previsaoDTO);
            return ResponseEntity.ok(previsaoAtualizadaDTO);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPrevisao(@PathVariable Long id) {
        previsaoTempoService.deletarPrevisao(id);
        return ResponseEntity.noContent().build();
    }
}
