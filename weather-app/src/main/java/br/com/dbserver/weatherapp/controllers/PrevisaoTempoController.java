package br.com.dbserver.weatherapp.controllers;

import br.com.dbserver.weatherapp.dto.PrevisaoTempoDTO;
import br.com.dbserver.weatherapp.model.PrevisaoTempo;
import br.com.dbserver.weatherapp.services.PrevisaoTempoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/previsaoTempo")
public class PrevisaoTempoController {

    @Autowired
    private PrevisaoTempoServiceImpl previsaoTempoServiceImpl;

    @GetMapping("/tempo-atual")
    public String getTempo(@RequestParam String cidade) {
        return previsaoTempoServiceImpl.getPrevisaoTempoAtual(cidade);
    }

    @GetMapping("/tempo-7dias")
    public String getTempo7dias(@RequestParam String cidade) {
        return previsaoTempoServiceImpl.getPrevisaoProximos7Dias(cidade);
    }

    @GetMapping("/tempo")
    public List<PrevisaoTempo> getAllPrevisaoTempo() {
        return previsaoTempoServiceImpl.getAllPrevisoes();
    }

    @PostMapping("/previsao")
    public ResponseEntity<String> cadastrarPrevisaoTempo(@RequestBody PrevisaoTempoDTO previsaoTempoDTO) {
        PrevisaoTempo previsaoTempo = new PrevisaoTempo();
        previsaoTempo.setCidade(previsaoTempoDTO.getCidade());
        previsaoTempo.setTempo(previsaoTempoDTO.getTempo());

        previsaoTempoServiceImpl.cadastrarPrevisao(previsaoTempo);
        return ResponseEntity.ok("Previsão cadastrada com sucesso");
    }

    @PutMapping("/previsao/{id}")
    public ResponseEntity<String> editarPrevisao(@PathVariable Long id, @RequestBody PrevisaoTempoDTO previsaoTempoDTO) {
        PrevisaoTempo previsaoTempo = new PrevisaoTempo();
        previsaoTempo.setCidade(previsaoTempoDTO.getCidade());
        previsaoTempo.setTempo(previsaoTempoDTO.getTempo());

        previsaoTempoServiceImpl.editarPrevisao(id, previsaoTempo);
        return ResponseEntity.ok("Previsão atualizada");
    }

    @DeleteMapping("/previsao/{id}")
    public ResponseEntity<String> deletarPrevisao(@PathVariable Long id) {
        previsaoTempoServiceImpl.deletarPrevisao(id);
        return ResponseEntity.ok("Previsão excluída com sucesso");
    }
}
