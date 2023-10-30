package com.sintad.backend.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.meilisearch.sdk.Client;
import com.meilisearch.sdk.Index;
import com.meilisearch.sdk.exceptions.MeilisearchException;
import com.meilisearch.sdk.model.SearchResult;
import com.sintad.backend.models.Entidad;
import com.sintad.backend.models.TipoContribuyente;
import com.sintad.backend.repositories.IEntidadRepository;
import com.sintad.backend.repositories.ITipoContribuyenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class SearchService {

    private final Client meilisearchClient;

    @Autowired
    private ITipoContribuyenteRepository tipoContribuyenteRepository;


    @Autowired
    public SearchService(Client meilisearchClient) {
        this.meilisearchClient = meilisearchClient;
    }

    @PostConstruct
    public void initializeMeilisearchIndex() {
        try {
            Index index = meilisearchClient.index("contribuyentes");
            // validate index
            if ( index.getPrimaryKey() == null ){
                populateMeilisearchIndex();
            }
        } catch (MeilisearchException e) {
            e.printStackTrace();
        }
    }


    public SearchResult performSearch(String indexName, String query) throws MeilisearchException {
        Index index = meilisearchClient.index(indexName);
        return index.search(query);
    }

    public void populateMeilisearchIndex() throws MeilisearchException {
        Index index = meilisearchClient.index("contribuyentes");

        List<TipoContribuyente> tiposContribuyentes = tipoContribuyenteRepository.findAllWithEntidades();
        String jsonData = convertContribuyenteToJson(tiposContribuyentes);
        index.addDocuments(jsonData);
    }

    private String convertContribuyenteToJson(List<TipoContribuyente> contribuyentes) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonData = objectMapper.writeValueAsString(contribuyentes);
            return jsonData;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }
}
