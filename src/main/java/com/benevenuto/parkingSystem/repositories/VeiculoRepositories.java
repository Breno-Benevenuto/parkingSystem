package com.benevenuto.parkingSystem.repositories;

import com.benevenuto.parkingSystem.domain.Veiculo;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface VeiculoRepositories extends ListCrudRepository<Veiculo, UUID> {
}
