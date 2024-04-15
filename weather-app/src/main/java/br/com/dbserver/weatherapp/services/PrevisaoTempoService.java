package br.com.dbserver.weatherapp.services;

import br.com.dbserver.weatherapp.exceptions.ResourceNotFoundException;
import br.com.dbserver.weatherapp.model.PrevisaoTempo;
import br.com.dbserver.weatherapp.repository.PrevisaoTempoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class PrevisaoTempoService {
    @Autowired
    private PrevisaoTempoRepository previsaoTempoRepository;

    @Value("${openweathermap.api.key}")
    private String apiKey;

    private static final String TEMPO_ATUAL_API_URL = "http://api.openweathermap.org/data/2.5/weather";
    private static final String TEMPO_7DIAS_API_URL = "http://api.openweathermap.org/data/2.5/forecast";

    public String getPrevisaoTempoAtual(String cidade) {
        String apiUrl = TEMPO_ATUAL_API_URL + "?q=" + cidade + "&appid=" + apiKey + "&units=metric";
        try {
            RestTemplate restTemplate = new RestTemplate();
            return restTemplate.getForObject(apiUrl, String.class);
        } catch (RestClientException e) {
            return "Erro ao obter os dados meteorológicos: " + e.getMessage();
        }
    }

    public String getPrevisaoProximos7Dias(String cidade) {
        String apiUrl = TEMPO_7DIAS_API_URL + "?q=" + cidade + "&appid=" + apiKey + "&units=metric";
        try {
            RestTemplate restTemplate = new RestTemplate();
            return restTemplate.getForObject(apiUrl, String.class);
        } catch (RestClientException e) {
            return "Erro ao obter os dados meteorológicos: " + e.getMessage();
        }
    }

    public List<PrevisaoTempo> getAllPrevisoes() {
        return previsaoTempoRepository.findAll();
    }

    public void cadastrarPrevisao(PrevisaoTempo previsaoTempo) {
        previsaoTempoRepository.save(previsaoTempo);
    }

    public void editarPrevisao(Long id, PrevisaoTempo previsaoTempo) {
        PrevisaoTempo previsao = previsaoTempoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Previsão não encontrada"));
        previsao.setCidade(previsaoTempo.getCidade());
        previsao.setTempo(previsaoTempo.getTempo());
        previsaoTempoRepository.save(previsao);
    }

    public void deletarPrevisao(Long id) {
        PrevisaoTempo previsao = previsaoTempoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Previsão não encontrada"));
        previsaoTempoRepository.delete(previsao);
    }
}
