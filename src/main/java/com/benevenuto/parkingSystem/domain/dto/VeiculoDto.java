package com.benevenuto.parkingSystem.domain.dto;

import com.benevenuto.parkingSystem.domain.VehiclesType;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VeiculoDto {

    private String placa;

    private VehiclesType type;
}
