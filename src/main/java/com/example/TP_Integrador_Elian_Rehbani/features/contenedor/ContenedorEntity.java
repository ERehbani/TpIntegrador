package com.example.TP_Integrador_Elian_Rehbani.features.contenedor;

import com.example.TP_Integrador_Elian_Rehbani.features.buque.BuqueEntity;
import com.example.TP_Integrador_Elian_Rehbani.features.cliente.ClienteEntity;
import com.example.TP_Integrador_Elian_Rehbani.features.puerto.PuertoEntity;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "contenedores")
@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ContenedorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "codigo_identificacion", unique = true, nullable = false)
    private String codigoIdentificacion;

    @Column(name = "peso_toneladas", nullable = false)
    private Double pesoToneladas;

    @Column(name = "activo")
    private Boolean activo;

    @ManyToOne
    @JoinColumn(name = "cliente")
    private ClienteEntity cliente;

    @ManyToOne
    @JoinColumn(name = "puerto")
    private PuertoEntity puerto;

    @ManyToOne
    @JoinColumn(name = "buque")
    private BuqueEntity buque;
}
