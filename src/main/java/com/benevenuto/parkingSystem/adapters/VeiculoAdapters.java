package com.benevenuto.parkingSystem.adapters;

import com.benevenuto.parkingSystem.domain.Veiculo;
import com.benevenuto.parkingSystem.ports.VeiculoPort;
import com.benevenuto.parkingSystem.repositories.VeiculoRepositories;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class VeiculoAdapters implements VeiculoPort {

    @Autowired
    private final VeiculoRepositories _veiculoRepositories;

    @Override
    public List<Veiculo> getAll() {
        return _veiculoRepositories.findAll();
    }

    @Override
    public Optional<Veiculo> getById(UUID id) {
        return _veiculoRepositories.findById(id);
    }

    @Override
    public UUID insert(Veiculo veiculo) {
        return _veiculoRepositories.save(veiculo)
                .getId();
    }
}
