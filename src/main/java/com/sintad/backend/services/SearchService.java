package com.sintad.backend.services;

import com.meilisearch.sdk.Client;
import com.meilisearch.sdk.Index;
import com.meilisearch.sdk.exceptions.MeilisearchException;
import com.meilisearch.sdk.model.SearchResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class SearchService {

    private final Client meilisearchClient;

    @Autowired
    public SearchService(Client meilisearchClient) {
        this.meilisearchClient = meilisearchClient;
    }

    @PostConstruct
    public void initializeMeilisearchIndex() {
        try {
            Path fileName = Path.of("src/main/resources/static/movies2.json");

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(Files.newInputStream(fileName), StandardCharsets.UTF_8))) {
                StringBuilder stringBuilder = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    stringBuilder.append(line);
                }
                String moviesJson = stringBuilder.toString();

                Index index = meilisearchClient.index("movies");
                index.addDocuments(moviesJson);
            } catch (IOException | MeilisearchException e) {
                throw new RuntimeException(e);
            }
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    public SearchResult performSearch(String indexName, String query) throws MeilisearchException {
        Index index = meilisearchClient.index(indexName);
        return index.search(query);
    }
}
