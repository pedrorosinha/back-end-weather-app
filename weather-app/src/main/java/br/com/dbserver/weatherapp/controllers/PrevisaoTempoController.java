package br.com.dbserver.weatherapp.controllers;

import br.com.dbserver.weatherapp.exceptions.ResourceNotFoundException;
import br.com.dbserver.weatherapp.model.PrevisaoTempo;
import br.com.dbserver.weatherapp.repository.PrevisaoTempoRepository;
import br.com.dbserver.weatherapp.services.PrevisaoTempoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/previsaoTempo")
public class PrevisaoTempoController {

    @Autowired
    private PrevisaoTempoService previsaoTempoService;

    @GetMapping("/tempo-atual")
    public String getTempo(@RequestParam String cidade) {
        return previsaoTempoService.getPrevisaoTempoAtual(cidade);
    }

    @GetMapping("/tempo-7dias")
    public String getTempo7dias(@RequestParam String cidade) {
        return previsaoTempoService.getPrevisaoProximos7Dias(cidade);
    }

    @GetMapping("/tempo")
    public List<PrevisaoTempo> getAllPrevisaoTempo() {
        return previsaoTempoService.getAllPrevisoes();
    }

    @PostMapping("/previsao")
    public ResponseEntity<String> cadastrarPrevisaoTempo(@RequestBody PrevisaoTempo previsaoTempo) {
        previsaoTempoService.cadastrarPrevisao(previsaoTempo);
        return ResponseEntity.ok("Previsão cadastrada com sucesso");
    }

    @PutMapping("/previsao/{id}")
    public ResponseEntity<String> editarPrevisao(@PathVariable Long id, @RequestBody PrevisaoTempo previsaoTempo) {
        previsaoTempoService.editarPrevisao(id, previsaoTempo);
        return ResponseEntity.ok("Previsão atualizada");
    }

    @DeleteMapping("/previsao/{id}")
    public ResponseEntity<String> deletarPrevisao(@PathVariable Long id) {
        previsaoTempoService.deletarPrevisao(id);
        return ResponseEntity.ok("Previsão excluída com sucesso");
    }
}
