package com.rightsflow.config;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import java.io.File;

@Configuration
public class FileStorageConfig {
    @Value("${storage.location:uploads}")
    private String storageLocation;

    @PostConstruct
    public void init() {
        File directory = new File(storageLocation);
        if (!directory.exists()) { directory.mkdirs(); }
    }
}
