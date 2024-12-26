package com.apis.elasticsearchintegration.service;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.mapping.TypeMapping;
import co.elastic.clients.elasticsearch.indices.CreateIndexRequest;
import co.elastic.clients.transport.endpoints.BooleanResponse;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

//read the mappings like json and find all the documents and get mappings for them
@Service
public class IndexService {

    private MappingService mappingService;
    // to build clientconfiguration to create indices for the dto we created
    private ElasticsearchClient client;
    private DocumentService documentService;

    public IndexService(ElasticsearchClient client, MappingService mappingService, DocumentService documentService) {
        this.mappingService = mappingService;
        this.documentService = documentService;
        this.client = client;
    }

    //delete indices and create
    @PostConstruct
    public void setUpIndices(){
        final Map<String, String> documentInfo = documentService.getDocumentInfo();
        for (final Map.Entry<String, String> entry : documentInfo.entrySet()){
            final String indexName = entry.getKey();
            final String indexMapping = entry.getValue();

            try {
                final BooleanResponse exists = client.indices().exists(r -> r.index(indexName));
                if(exists.value()) continue;

                createIndex(indexName, indexMapping);
            }
            catch (IOException e){
                throw  new RuntimeException(e);
            }
        }
    }

    public void recreateIndices(){
        final Map<String, String> documentInfo = documentService.getDocumentInfo();
        for (final Map.Entry<String, String> entry : documentInfo.entrySet()) {
            final String indexName = entry.getKey();
            final String indexMapping = entry.getValue();

            try {
                client.indices().delete(d -> d.index(indexName));
                createIndex(indexName, indexMapping);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void createIndex(String indexName, String indexMapping) throws IOException{
        client.indices().create(buildCreateIndexRequest(indexName, indexMapping));
    }

    private CreateIndexRequest buildCreateIndexRequest(final String indexName, final String indexMapping){
        return new CreateIndexRequest.Builder()
                .index(indexName)
                .mappings(buildMappings(indexMapping))
                .build();
    }

    private TypeMapping buildMappings(final String indexMapping){
        return  new TypeMapping.Builder()
                .withJson(mappingService.read(indexMapping))
                .build();
    }

}

