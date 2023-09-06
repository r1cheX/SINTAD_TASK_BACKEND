package com.sintad.backend.models;

import java.util.List;

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
@Table(name ="tb_tipo_contribuyente")
public class TipoContribuyente {

    @Id
    @Column(name = "id_tipo_contribuyente", length = 11, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idTipoContribuyente;
    
    @Column(name = "nombre", length = 50, nullable = false)
    private String nombre;

    @Column(name = "estado", nullable = false, columnDefinition = "tinyint(1) default 1")
    private boolean estado;

	@OneToMany(mappedBy = "tipoContribuyente")
	private List<Entidad> entidades;
    
}
