package br.com.dbserver.weatherapp.services;

import br.com.dbserver.weatherapp.exceptions.ResourceNotFoundException;
import br.com.dbserver.weatherapp.model.PrevisaoTempo;
import br.com.dbserver.weatherapp.repository.PrevisaoTempoRepository;
import br.com.dbserver.weatherapp.services.interf.PrevisaoTempoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class PrevisaoTempoServiceImpl implements PrevisaoTempoService {

    @Autowired
    private PrevisaoTempoRepository previsaoTempoRepository;

    @Value("${openweathermap.api.key}")
    private String apiKey;

    private String TEMPO_ATUAL_API_URL = "http://api.openweathermap.org/data/2.5/weather";
    private String TEMPO_7DIAS_API_URL = "http://api.openweathermap.org/data/2.5/forecast";

    @Override
    public String getPrevisaoTempoAtual(String cidade) {
        return obterPrevisaoTempoDaAPI(TEMPO_ATUAL_API_URL, cidade);
    }

    @Override
    public String getPrevisaoProximos7Dias(String cidade) {
        return obterPrevisaoTempoDaAPI(TEMPO_7DIAS_API_URL, cidade);
    }

    @Override
    public List<PrevisaoTempo> getAllPrevisoes() {
        return previsaoTempoRepository.findAll();
    }

    @Override
    public void cadastrarPrevisao(PrevisaoTempo previsaoTempo) {
        previsaoTempoRepository.save(previsaoTempo);
    }

    @Override
    public void editarPrevisao(Long id, PrevisaoTempo previsaoTempo) {
        PrevisaoTempo previsao = previsaoTempoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Previsão não encontrada"));
        previsao.setCidade(previsaoTempo.getCidade());
        previsao.setTempo(previsaoTempo.getTempo());
        previsaoTempoRepository.save(previsao);
    }

    @Override
    public void deletarPrevisao(Long id) {
        PrevisaoTempo previsao = previsaoTempoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Previsão não encontrada"));
        previsaoTempoRepository.delete(previsao);
    }

    private String obterPrevisaoTempoDaAPI(String apiUrl, String cidade) {
        String url = apiUrl + "?q=" + cidade + "&appid=" + apiKey + "&units=metric";
        try {
            RestTemplate restTemplate = new RestTemplate();
            return restTemplate.getForObject(url, String.class);
        } catch (RestClientException e) {
            return "Erro ao obter os dados meteorológicos: " + e.getMessage();
        }
    }
}