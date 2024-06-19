package br.com.dbserver.weatherapp.utils;

import br.com.dbserver.weatherapp.dto.PrevisaoDTO;
import br.com.dbserver.weatherapp.enums.Clima;
import br.com.dbserver.weatherapp.enums.Turno;
import br.com.dbserver.weatherapp.model.PrevisaoTempo;
import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PrevisaoFixture {

    private static final Faker faker = new Faker(new Locale("pt-BR"));

    private static <T extends Enum<?>> T getRandomEnumValue(Class<T> clazz) {
        int x = ThreadLocalRandom.current().nextInt(clazz.getEnumConstants().length);
        return clazz.getEnumConstants()[x];
    }

    public static PrevisaoDTO criarPrevisaoDTO() {
        return new PrevisaoDTO(faker.number().randomNumber(), faker.address().city(), getRandomEnumValue(Turno.class), getRandomEnumValue(Clima.class), faker.number().numberBetween(0, 20), faker.number().numberBetween(21, 40), faker.number().numberBetween(0, 100), faker.number().numberBetween(0, 100), faker.number().numberBetween(0, 100), LocalDate.now().plusDays(faker.number().numberBetween(0, 30)));
    }

    public static PrevisaoTempo criarPrevisaoTempo() {
        return new PrevisaoTempo(faker.number().randomNumber(), faker.address().city(), getRandomEnumValue(Turno.class), getRandomEnumValue(Clima.class), faker.number().numberBetween(0, 20), faker.number().numberBetween(21, 40), faker.number().numberBetween(0, 100), faker.number().numberBetween(0, 100), faker.number().numberBetween(0, 100), LocalDate.now().plusDays(faker.number().numberBetween(0, 30)));
    }

    public static List<PrevisaoTempo> criarListaPrevisaoTempo(int quantidade) {
        List<PrevisaoTempo> previsoes = new ArrayList<>();
        for (int i = 0; i < quantidade; i++) {
            previsoes.add(criarPrevisaoTempo());
        }
        return previsoes;
    }

    public static List<PrevisaoDTO> criarListaPrevisaoDTO(int quantidade) {
        return IntStream.range(0, quantidade)
                .mapToObj(i -> criarPrevisaoDTO())
                .collect(Collectors.toList());
    }
}
