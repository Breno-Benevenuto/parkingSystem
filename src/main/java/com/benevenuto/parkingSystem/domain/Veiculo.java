package com.benevenuto.parkingSystem.domain;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity(name = "vehicles")
@Table(name = "vehicles")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "Id")
public class Veiculo {
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID Id;

    @Column(unique = true)
    private String placa;

    private VehiclesType type;

    public Veiculo(String placa, VehiclesType type)
    {
        this.placa = placa;
        this.type = type;
    }

}
