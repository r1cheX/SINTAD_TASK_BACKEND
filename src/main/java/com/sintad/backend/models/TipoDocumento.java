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
@Table(name = "tb_tipo_documento")
public class TipoDocumento {
	@Id
	@Column(name = "id_tipo_documento", length = 11, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idTipoDocumento;

	@Column(name = "codigo", length = 20, nullable = false)
	private String codigo;

	@Column(name = "nombre", length = 100, nullable = false)
	private String nombre;

	@Column(name = "descripcion", length = 200)
	private String descripcion;

	@Column(name = "estado", nullable = false, columnDefinition = "tinyint(1) default 1")
	private boolean estado;

	@OneToMany(mappedBy = "tipoDocumento")
	private List<Entidad> entidades;

}
