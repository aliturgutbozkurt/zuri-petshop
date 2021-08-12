package com.turkninja.petshop.v1;

import com.turkninja.petshop.file.FirebaseFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

/**
 * @author ali turgut bozkurt
 * Created at 8/12/2021
 */

@RestController
@RequestMapping("/api/v1/file")
public class FileResource {

    @Autowired
    FirebaseFileService firebaseFileService;

    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<String> uploadFile(@RequestPart("file") MultipartFile file) throws IOException {
        String extension = getExtension(file.getOriginalFilename()).get();
        String filename = UUID.randomUUID().toString().concat("." + extension);
        String test = firebaseFileService.uploadFile(file.getInputStream(), file.getContentType(), "test", filename);
        return new ResponseEntity<>(test, HttpStatus.CREATED);
    }

    private Optional<String> getExtension(String filename) {
        return Optional.ofNullable(filename)
                .filter(f -> f.contains("."))
                .map(f -> f.substring(filename.lastIndexOf(".") + 1));
    }
}
