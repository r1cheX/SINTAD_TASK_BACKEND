package com.sintad.backend.services;

import com.sintad.backend.dataTransferObjects.request.EntidadRequest;
import com.sintad.backend.exceptions.NotFoundException;
import com.sintad.backend.models.Entidad;
import com.sintad.backend.models.TipoContribuyente;
import com.sintad.backend.models.TipoDocumento;
import com.sintad.backend.repositories.IEntidadRepository;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EntidadServiceImpl implements EntidadService{
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private IEntidadRepository entidadRepository;

    @Override
    public List<EntidadRequest> allEntidad() {
        List<Entidad> entidades = entidadRepository.findAll();
        return entidades.stream().filter(Entidad::isEstado).map(this::mapDTO).collect(Collectors.toList());
    }

    @Override
    public List<EntidadRequest> allEntidadWithDetails() {
        List<Entidad> entidades = entidadRepository.findAll();
        return entidades.stream()
                .filter(Entidad::isEstado)
                .map(this::mapEntityToRequestWithDetails)
                .collect(Collectors.toList());
    }

    @Override
    public EntidadRequest oneEntidad(Long id) {
        Entidad entidad = entidadRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Entidad"));

        return mapDTO(entidad);
    }

    @Override
    public EntidadRequest createEntidad(EntidadRequest entidadDTO) {
        Entidad entidad = mapEntity(entidadDTO);

        Entidad newEntidad = entidadRepository.save(entidad);

        return mapDTO(newEntidad);
    }

    @Override
    public EntidadRequest updateEntidad(EntidadRequest entidadDTO, Long id) {
        Entidad entidad = entidadRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Entidad"));

        entidad.setNroDocumento(entidadDTO.getNroDocumento());
        entidad.setRazonSocial(entidadDTO.getRazonSocial());
        entidad.setNombreComercial(entidadDTO.getNombreComercial());
        entidad.setDireccion(entidadDTO.getDireccion());
        entidad.setTelefono(entidadDTO.getTelefono());

        TipoDocumento tipoDocumento = new TipoDocumento();
        tipoDocumento.setIdTipoDocumento(entidadDTO.getIdTipoDocumento());

        TipoContribuyente tipoContribuyente = new TipoContribuyente();
        tipoContribuyente.setIdTipoContribuyente(entidadDTO.getIdTipoContribuyente());

        entidad.setTipoDocumento(tipoDocumento);
        entidad.setTipoContribuyente(tipoContribuyente);

        Entidad updatedEntidad = entidadRepository.save(entidad);

        return mapDTO(updatedEntidad);
    }

    @Override
    public void deleteEntidad(Long id) {
        Entidad entidad = entidadRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Entidad"));

        entidad.setEstado(false);
        entidadRepository.save(entidad);
    }

    private EntidadRequest mapEntityToRequestWithDetails(Entidad entidad) {
        EntidadRequest entidadRequest = mapEntityToRequest(entidad);
        entidadRequest.setTipoDocumentoNombre(entidad.getTipoDocumento().getNombre());
        entidadRequest.setTipoContribuyenteNombre(entidad.getTipoContribuyente().getNombre());
        return entidadRequest;
    }

    private EntidadRequest mapEntityToRequest(Entidad entidad) {
        return modelMapper.map(entidad, EntidadRequest.class);
    }

    private EntidadRequest mapDTO(Entidad entidad){
        EntidadRequest entidadDTO = modelMapper.map(entidad, EntidadRequest.class);
        return entidadDTO;
    }

    private Entidad mapEntity(EntidadRequest entidadDTO){
        Entidad entidad = modelMapper.map(entidadDTO, Entidad.class);
        return entidad;
    }
}
