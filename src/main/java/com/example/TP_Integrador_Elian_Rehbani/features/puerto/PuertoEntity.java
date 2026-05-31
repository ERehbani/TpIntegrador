package com.example.TP_Integrador_Elian_Rehbani.features.puerto;

import com.example.TP_Integrador_Elian_Rehbani.features.buque.BuqueEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Table(name = "puertos")
@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PuertoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "pais")
    private String pais;

    @Column(name = "activo")
    private Boolean activo;

    @ManyToMany(mappedBy = "puertos")
    private List<BuqueEntity> buques = new ArrayList<>();
}
