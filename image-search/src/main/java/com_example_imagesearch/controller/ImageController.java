package com.example.imagesearch.controller;

import org.apache.commons.io.FileUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ImageController {

    private static final String IMAGE_DIRECTORY = "/path/to/images";

    @GetMapping("/search")
    public ResponseEntity<List<String>> searchImages(@RequestParam("query") String query) {
        File folder = new File(IMAGE_DIRECTORY);
        if (!folder.exists() || !folder.isDirectory()) {
            return ResponseEntity.badRequest().body(Arrays.asList("Invalid directory"));
        }

        List<String> results = Arrays.stream(folder.listFiles())
                .filter(file -> file.isFile() && file.getName().toLowerCase().contains(query.toLowerCase()))
                .map(File::getName)
                .collect(Collectors.toList());

        return ResponseEntity.ok(results);
    }
}
