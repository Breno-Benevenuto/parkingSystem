package com.benevenuto.parkingSystem.domain.mapper;

import com.benevenuto.parkingSystem.domain.Veiculo;
import com.benevenuto.parkingSystem.domain.dto.VeiculoDto;

public class VeiculoMapper {

    public static Veiculo toEntity(VeiculoDto dto){
        return new Veiculo(dto.getPlaca(),dto.getType());
    }

    public static VeiculoDto toDto(Veiculo entity){
        return new VeiculoDto(entity.getPlaca(),entity.getType());
    }
}
