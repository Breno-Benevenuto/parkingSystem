package com.benevenuto.parkingSystem.ports;

import com.benevenuto.parkingSystem.domain.Veiculo;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface VeiculoPort{
    List<Veiculo> getAll();
    Optional<Veiculo> getById(UUID id);
    UUID insert(Veiculo veiculo);
}
