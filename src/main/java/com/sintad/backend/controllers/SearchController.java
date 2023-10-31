package com.sintad.backend.controllers;

import com.meilisearch.sdk.exceptions.MeilisearchException;
import com.meilisearch.sdk.model.SearchResult;
import com.sintad.backend.services.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api-sintad/meilisearch")
public class SearchController {
    private final SearchService searchService;
    @Autowired
    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping("/search")
    public SearchResult search(@RequestParam("query") String query) throws MeilisearchException {
        System.out.println("query: " + query);
        String index_name = "movies";
        return searchService.performSearch(index_name, query);
    }





}
