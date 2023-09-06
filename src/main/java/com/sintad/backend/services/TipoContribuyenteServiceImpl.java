package com.sintad.backend.services;

import com.sintad.backend.dataTransferObjects.request.TipoContribuyenteRequest;
import com.sintad.backend.exceptions.NotFoundException;
import com.sintad.backend.models.TipoContribuyente;
import com.sintad.backend.repositories.ITipoContribuyenteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TipoContribuyenteServiceImpl implements TipoContribuyenteService {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ITipoContribuyenteRepository tipoContribuyenteRepository;

    public static final String CONTRIBUTOR_NAME = "Tipo de Contribuyente";

    @Override
    public List<TipoContribuyenteRequest> allTipoContribuyente() {
        List<TipoContribuyente> tiposContribuyente = tipoContribuyenteRepository.findAll();

        return tiposContribuyente.stream().filter(TipoContribuyente::isEstado).map(this::mapDTO)
                .collect(Collectors.toList());
    }

    @Override
    public TipoContribuyenteRequest oneTipoContribuyente(Long id) {
        TipoContribuyente tipoContribuyente = tipoContribuyenteRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(CONTRIBUTOR_NAME));

        return mapDTO(tipoContribuyente);
    }

    @Override
    public TipoContribuyenteRequest createTipoContribuyente(TipoContribuyenteRequest tipoContribuyenteRequest) {
        TipoContribuyente tipoContribuyente = mapEntity(tipoContribuyenteRequest);

        TipoContribuyente newTipoContribuyente = tipoContribuyenteRepository.save(tipoContribuyente);

        return mapDTO(newTipoContribuyente);
    }

    @Override
    public TipoContribuyenteRequest updateTipoContribuyente(
            TipoContribuyenteRequest tipoContribuyenteRequest,
            Long id
    ){
        TipoContribuyente tipoContribuyente = tipoContribuyenteRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(CONTRIBUTOR_NAME));
        tipoContribuyente.setNombre(tipoContribuyenteRequest.getNombre());

        TipoContribuyente updatedTipoContribuyente = tipoContribuyenteRepository.save(tipoContribuyente);

        return mapDTO(updatedTipoContribuyente);
    }

    @Override
    public void deleteTipoContribuyente(Long id) {
        TipoContribuyente tipoContribuyente = tipoContribuyenteRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(CONTRIBUTOR_NAME));

        tipoContribuyente.setEstado(false);
        tipoContribuyenteRepository.save(tipoContribuyente);
    }

    private TipoContribuyenteRequest mapDTO(TipoContribuyente tipoContribuyente) {
        TipoContribuyenteRequest TipoContribuyenteRequest = modelMapper.map(tipoContribuyente,
                TipoContribuyenteRequest.class);
        return TipoContribuyenteRequest;
    }

    private TipoContribuyente mapEntity(TipoContribuyenteRequest tipoContribuyenteRequest) {
        TipoContribuyente tipoContribuyente = modelMapper.map(tipoContribuyenteRequest, TipoContribuyente.class);
        return tipoContribuyente;
    }
}
