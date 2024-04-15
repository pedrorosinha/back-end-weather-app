package br.com.dbserver.weatherapp.controllers;

import br.com.dbserver.weatherapp.exceptions.ResourceNotFoundException;
import br.com.dbserver.weatherapp.model.PrevisaoTempo;
import br.com.dbserver.weatherapp.repository.PrevisaoTempoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class PrevisaoTempoController {

    @Autowired
    private PrevisaoTempoRepository previsaoTempoRepository;

    @Value("${openweathermap.api.key}")
    private String apiKey;

    private static final String API_URL = "http://api.openweathermap.org/data/2.5/weather";

    @GetMapping("/tempo-atual")
    public String getTempo(@RequestParam String cidade) {
        String apiUrl = API_URL + "?q=" + cidade + "&appid=" + apiKey + "&units=metric";

        try {
            RestTemplate restTemplate = new RestTemplate();
            return restTemplate.getForObject(apiUrl, String.class);
        } catch (RestClientException e) {
            return "Erro ao obter os dados meteorológicos" + e.getMessage();
        }
    }

    @GetMapping("/tempo-7dias")
    public String getTempo7dias(@RequestParam String cidade) {
        String apiUrl = "http://api.openweathermap.org/data/2.5/forecast?q=" + cidade + "&appid=" + apiKey + "&units=metric";

        try {
            RestTemplate restTemplate = new RestTemplate();
            return restTemplate.getForObject(apiUrl, String.class);
        } catch (RestClientException e) {
            return "Erro ao obter os dados meteorológicos para os 7 dias" + e.getMessage();
        }
    }

    @GetMapping("/tempo")
    public List<PrevisaoTempo> getAllPrevisaoTempo() {
        return previsaoTempoRepository.findAll();
    }

    @PostMapping("/previsao")
    public ResponseEntity<String> cadastrarPrevisaoTempo(@RequestBody PrevisaoTempo previsaoTempo) {
        previsaoTempoRepository.save(previsaoTempo);
        return ResponseEntity.ok("Previsão cadastrada com sucesso");
    }

    @PutMapping("/previsao/{id}")
    public ResponseEntity<String> editarPrevisao(@PathVariable Long id, @RequestBody PrevisaoTempo previsaoTempo) {
        PrevisaoTempo previsao = previsaoTempoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Previsão não encontrada"));

        previsao.setCidade(previsaoTempo.getCidade());
        previsao.setTempo(previsaoTempo.getTempo());
        previsaoTempoRepository.save(previsao);

        return ResponseEntity.ok("Previsão atualizada");
    }

    @DeleteMapping("/previsao/{id}")
    public ResponseEntity<String> deletarPrevisao(@PathVariable Long id) {
        PrevisaoTempo previsao = previsaoTempoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Previsão não encontrada"));

        previsaoTempoRepository.delete(previsao);

        return ResponseEntity.ok("Previsão excluída com sucesso");
    }
}
