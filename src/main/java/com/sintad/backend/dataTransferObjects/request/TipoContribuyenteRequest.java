package com.sintad.backend.dataTransferObjects.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class TipoContribuyenteRequest {
    private long id;
    @NotBlank
    @Size(min = 4)
    private String nombre;

    private Boolean estado=true;
}
