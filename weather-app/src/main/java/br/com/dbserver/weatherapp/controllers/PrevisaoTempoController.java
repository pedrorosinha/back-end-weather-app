package br.com.dbserver.weatherapp.controllers;

import br.com.dbserver.weatherapp.model.PrevisaoTempo;
import br.com.dbserver.weatherapp.repository.PrevisaoTempoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @GetMapping("/tempo")
    public String getTempo(@RequestParam String cidade) {
        String apiUrl = API_URL + "?q=" + cidade + "&appid=" + apiKey + "&units=metric";

        try {
            RestTemplate restTemplate = new RestTemplate();
            return restTemplate.getForObject(apiUrl, String.class);
        } catch (RestClientException e) {
            return "Erro ao obter os dados meteorológicos" + e.getMessage();
        }
    }

//    @GetMapping("/tempo")
//    public List<PrevisaoTempo> getAllPrevisaoTempo() {
//        return previsaoTempoRepository.findAll();
//    }
//
//    @PostMapping("/tempo")
//    public PrevisaoTempo cadastrarTempo(@RequestBody PrevisaoTempo previsaoTempo) {
//        return previsaoTempoRepository.save(previsaoTempo);
//    }
//
//    @PutMapping("/tempo/{id}")
//    public PrevisaoTempo atualizarTempo(@PathVariable Long id, @RequestBody PrevisaoTempo previsaoTempo) {
//        PrevisaoTempo tempo = PrevisaoTempoRepository.findById(id);
//        tempo.setTempo(tempo.getTempo)
//    }

}
