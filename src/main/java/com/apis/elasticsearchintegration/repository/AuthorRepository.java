package com.apis.elasticsearchintegration.repository;

import com.apis.elasticsearchintegration.document.AuthorDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends ElasticsearchRepository<AuthorDocument, String> {


}
