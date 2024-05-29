package com.benevenuto.parkingSystem.controllers;


import com.benevenuto.parkingSystem.domain.Veiculo;
import com.benevenuto.parkingSystem.domain.dto.VeiculoDto;
import com.benevenuto.parkingSystem.services.VeiculoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping("/veiculo")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class VeiculoController {

    @Autowired
    private final VeiculoService _veiculoService;

    @PostMapping("/create")
    @Operation(summary = "Cria um veiculo", responses = @ApiResponse(responseCode = "201", description = "Successfully",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))))
    public ResponseEntity<String> create(@RequestBody VeiculoDto veiculoDto)
    {
        var result = _veiculoService.Create(veiculoDto);

        return ResponseEntity.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(String.format("{\"Id\": \"%s\"}", result.block()));
    }

    @GetMapping("/GetAll")
    @Operation(summary = "Retorna todos os veiculos", responses = @ApiResponse(responseCode = "201", description = "Successfully",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Veiculo.class))))
    public ResponseEntity<Flux<Veiculo>> getAll()
    {
        var result = _veiculoService.GetAll();

        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(result);
    }

    @GetMapping("/GetById/{id}")
    @Operation(summary = "Retorna os veiculos por id", responses = @ApiResponse(responseCode = "201", description = "Successfully",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Veiculo.class))))
    public ResponseEntity<Mono<Veiculo>> getAll(@PathVariable("id")UUID id)
    {
        var result = _veiculoService.GetById(id);

        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(result);
    }
}
