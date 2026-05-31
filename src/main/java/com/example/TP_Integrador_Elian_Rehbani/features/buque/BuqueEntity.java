package com.example.TP_Integrador_Elian_Rehbani.features.buque;

import com.example.TP_Integrador_Elian_Rehbani.features.puerto.PuertoEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Table(name = "buques")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "puertos")
@Builder
public class BuqueEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "matricula_imo", unique = true, nullable = false)
    private String matriculaImo;

    @Column(name = "capacidad_maxima_toneladas", nullable = false)
    private Double capacidadMaximaToneladas;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado_operativo")
    private EBuqueEstado estadoOperativo;

    @Column(name = "activo")
    private Boolean activo;

    @ManyToMany
    @JoinTable(
            name = "buque_puerto",
            joinColumns = @JoinColumn(name = "buque_id"),
            inverseJoinColumns = @JoinColumn(name = "puerto_id")
    )
    private List<PuertoEntity> puertos = new ArrayList<>();

}
