package com.sintad.backend.models;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Table(name = "tb_entidad", uniqueConstraints = { @UniqueConstraint(columnNames = "nro_documento") })
public class Entidad {
    @Id
    @Column(name = "id_entidad", length = 11, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_documento", nullable = false)
    private TipoDocumento tipoDocumento;

    @Column(name = "nro_documento", length = 25, nullable = false)
    private String nroDocumento;

    @Column(name = "razon_social", length = 100, nullable = false)
    private String razonSocial;

    @Column(name = "nombre_comercial", length = 100)
    private String nombreComercial;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_contribuyente")
    private TipoContribuyente tipoContribuyente;

    @Column(name = "direccion")
    private String direccion;

    @Column(name = "telefono", length = 50)
    private String telefono;

    @Column(name = "estado", nullable = false, columnDefinition = "tinyint(1) default 1")
    private boolean estado = true;

}
