package com.benevenuto.parkingSystem.services;

import com.benevenuto.parkingSystem.domain.Veiculo;
import com.benevenuto.parkingSystem.domain.dto.VeiculoDto;
import com.benevenuto.parkingSystem.domain.mapper.VeiculoMapper;
import com.benevenuto.parkingSystem.exceptions.VeiculoNotFoundException;
import com.benevenuto.parkingSystem.exceptions.VeiculoNotValid;
import com.benevenuto.parkingSystem.ports.VeiculoPort;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class VeiculoService {

    @Autowired
    private final VeiculoPort _veiculoPort;

    public Mono<UUID> Create(VeiculoDto veiculoDto)
    {
        if (veiculoDto == null) {
            throw new VeiculoNotValid("Veiculo nao e Valido para ser salvo");
        }

        UUID id = _veiculoPort.insert(VeiculoMapper.toEntity(veiculoDto));

        return Mono.just(id);
    }

    public Flux<Veiculo> GetAll()
    {
        return Flux.fromIterable(_veiculoPort.getAll());
    }

    public Mono<Veiculo> GetById(UUID id)
    {
        return _veiculoPort.getById(id)
                .map(Mono::just)
                .orElseThrow(() -> new VeiculoNotFoundException("Nenhum veículo encontrado."));
    }
}
