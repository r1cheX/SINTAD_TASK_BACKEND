package com.sintad.backend.services;

import com.sintad.backend.dataTransferObjects.request.EntidadRequest;

import java.util.List;

public interface EntidadService {
    public List<EntidadRequest> allEntidad();

    public EntidadRequest oneEntidad(Long id);

    public EntidadRequest createEntidad(EntidadRequest entidadDTO);

    public EntidadRequest updateEntidad(EntidadRequest entidadDTO, Long id);

    public void deleteEntidad(Long id);
}
