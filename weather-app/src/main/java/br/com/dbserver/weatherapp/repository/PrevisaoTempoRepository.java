package br.com.dbserver.weatherapp.repository;

import br.com.dbserver.weatherapp.model.PrevisaoTempo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrevisaoTempoRepository extends JpaRepository<PrevisaoTempo, Long> {
    List<PrevisaoTempo> findByCidade(String cidade);
}
