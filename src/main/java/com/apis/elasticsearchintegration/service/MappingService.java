package com.apis.elasticsearchintegration.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;

@Service
public class MappingService {
    
    private static final Logger LOG = LoggerFactory.getLogger(MappingService.class);

    private final ResourceLoader resourceLoader;

    public MappingService(ResourceLoader resourceLoader){
        this.resourceLoader = resourceLoader;
    }

    public InputStream read(final String filePath){
        Resource resource = resourceLoader.getResource("classpath:" +filePath);
        try {
            return resource.getInputStream();
        } catch (final IOException e){
            LOG.error("{}", e.getMessage(), e);
            return null;
        }
    }
}
