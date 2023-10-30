package com.sintad.backend.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.meilisearch.sdk.Client;
import com.meilisearch.sdk.Index;
import com.meilisearch.sdk.exceptions.MeilisearchException;
import com.sintad.backend.models.Entidad;
import com.meilisearch.sdk.model.SearchResult;
import com.sintad.backend.models.TipoContribuyente;
import com.sintad.backend.repositories.IEntidadRepository;
import com.sintad.backend.repositories.ITipoContribuyenteRepository;
import org.hibernate.sql.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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
            Index index = meilisearchClient.index("usuarios");
            // validate index
            populateMeilisearchIndex(index);
        } catch (MeilisearchException | JsonProcessingException e) {
            e.printStackTrace();
        }
    }


    public SearchResult performSearch(String indexName, String query) throws MeilisearchException {
        Index index = meilisearchClient.index(indexName);
        return index.search(query);
    }

    public void populateMeilisearchIndex(Index index) throws MeilisearchException, JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        String apiUrl = "https://api.github.com/search/users?q=a"; // Reemplaza esto con la URL de la API de películas
        ResponseEntity<String> response = restTemplate.getForEntity(apiUrl, String.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            String jsonData = response.getBody();
            System.out.println("jsonData: " + jsonData);

            // Analizar el JSON en un objeto JsonNode
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(jsonData);

            // Obtener el nodo "items" del JSON
            JsonNode itemsNode = rootNode.get("items");

            if (itemsNode != null && itemsNode.isArray()) {
                // Iterar a través de los elementos "items"
                for (JsonNode item : itemsNode) {
                    String login = item.get("login").asText();
                    int id = item.get("id").asInt();
                    String nodeId = item.get("node_id").asText();
                    String avatarUrl = item.get("avatar_url").asText();

                    System.out.println("Usuario: " + login);
                    System.out.println("ID: " + id);
                }
                index.addDocuments(String.valueOf(itemsNode));
            } else {
                System.err.println("Error al obtener datos de la API de películas. Código de estado: " + response.getStatusCode());
            }
        }
    }
}
