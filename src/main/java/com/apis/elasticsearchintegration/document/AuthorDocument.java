package com.apis.elasticsearchintegration.document;

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Mapping;

@Data
@Document(indexName = "author")
@Mapping(mappingPath = "static/author.json")
public class AuthorDocument extends AbstractDocument{

    //instead create a custom json file and use @mapping at class level to import that json file
    //@Field(type = FieldType.Text)
    private String name;
}
