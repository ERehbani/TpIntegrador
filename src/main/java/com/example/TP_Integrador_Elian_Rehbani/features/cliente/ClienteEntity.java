package com.example.TP_Integrador_Elian_Rehbani.features.cliente;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Table(name = "clientes")
@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClienteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "razon_social")
    private String razonSocial;

    @Column(name = "dni", unique = true, nullable = false)
    private String dni;

    @Column(name = "es_vip", nullable = false)
    private Boolean esVip;

    @Column(name = "activo", nullable = false)
    private Boolean activo;
}
