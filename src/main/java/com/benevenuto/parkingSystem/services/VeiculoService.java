package com.benevenuto.parkingSystem.services;

import com.benevenuto.parkingSystem.domain.Veiculo;
import com.benevenuto.parkingSystem.domain.dto.VeiculoDto;
import com.benevenuto.parkingSystem.domain.mapper.VeiculoMapper;
import com.benevenuto.parkingSystem.exceptions.VeiculoNotFoundException;
import com.benevenuto.parkingSystem.exceptions.VeiculoNotValid;
import com.benevenuto.parkingSystem.ports.VeiculoPort;
import com.benevenuto.parkingSystem.queue.SQSPublishVehiclesQueue;
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

    @Autowired
    private final SQSPublishVehiclesQueue _publishVehicles;

    public Mono<String> CreateQueueInsert(VeiculoDto veiculoDto)
    {
        this.sendMessage(veiculoDto);

        return Mono.just("Pedido esta na fila de processamento");
    }
    private void sendMessage(VeiculoDto veiculoDto)
    {
        _publishVehicles.publish(veiculoDto);
    }

    public UUID Insert(VeiculoDto veiculoDto)
    {
        if (veiculoDto == null) {
            throw new VeiculoNotValid("Veiculo nao e Valido para ser salvo");
        }

        return _veiculoPort.insert(VeiculoMapper.toEntity(veiculoDto));
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
