package com.apis.elasticsearchintegration.converter;

import com.apis.elasticsearchintegration.document.AuthorDocument;
import com.apis.elasticsearchintegration.dto.Author;
import org.springframework.stereotype.Component;

@Component
public class AuthorConverter {

    public AuthorDocument toDocument(final Author author){
        if(author == null)
            return null;
        final AuthorDocument authorDocument = new AuthorDocument();
        authorDocument.setId(author.id());
        authorDocument.setName(author.name());
        return authorDocument;
    }

    public Author fromDocument(final AuthorDocument authorDocument){
        if(authorDocument == null) return null;

        return new Author(authorDocument.getId(),  authorDocument.getName());
    }
}
