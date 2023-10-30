package com.sintad.backend;

import com.meilisearch.sdk.Client;
import com.meilisearch.sdk.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MeilisearchConfig {

    @Value("${meilisearch.url}")
    private String meilisearchUrl;

    @Value("${meilisearch.masterKey}")
    private String meilisearchMasterKey;

    @Bean
    public Client meilisearchClient() {
        return  new Client(new Config(meilisearchUrl, meilisearchMasterKey));
    }
}
