package kh.educat.cstad.aftmobilebankingapi.service.impl;


import kh.educat.cstad.aftmobilebankingapi.domain.Media;
import kh.educat.cstad.aftmobilebankingapi.dto.MediaResponse;
import kh.educat.cstad.aftmobilebankingapi.repository.MediaRepository;
import kh.educat.cstad.aftmobilebankingapi.service.MediaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.nio.file.*;
import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class MediaServiceImpl implements MediaService {

    private final MediaRepository mediaRepository;

    @Value("${media.server-path}")
    private String serverPath;

    @Value("${media.base-uri}")
    private String baseUri;

    @Override
    public MediaResponse upload(MultipartFile file) {
        if (file.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "File is empty");
        }

        String originalFilename = Optional.ofNullable(file.getOriginalFilename())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid file name"));

        int lastIndex = originalFilename.lastIndexOf(".");
        if (lastIndex == -1) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "File has no extension");
        }

        String extension = originalFilename.substring(lastIndex + 1);
        String name = UUID.randomUUID().toString();
        Path path = Path.of(serverPath, String.format("%s.%s", name, extension));

        try {
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            log.error("File copy failed: {}", e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "File upload failed");
        }

        Media media = new Media();
        media.setName(name);
        media.setExtension(extension);
        media.setMimeTypeFile(file.getContentType());
        media.setIsDeleted(false);
        media = mediaRepository.save(media);

        return MediaResponse.builder()
                .name(media.getName())
                .extension(media.getExtension())
                .mimeTypeFile(media.getMimeTypeFile())
                .uri(baseUri + String.format("%s.%s", name, extension))
                .size(file.getSize())
                .build();
    }

    @Override
    public List<MediaResponse> uploadMultiple(MultipartFile[] files) {
        List<MediaResponse> responses = new ArrayList<>();
        for (MultipartFile file : files) {
            responses.add(upload(file));
        }
        return responses;
    }

    @Override
    public ResponseEntity<Resource> download(String filename) {
        try {
            Path filePath = Path.of(serverPath).resolve(filename);
            if (!Files.exists(filePath)) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "File not found");
            }

            Resource resource = new UrlResource(filePath.toUri());
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                    .body(resource);

        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to read file");
        }
    }

    @Override
    public ResponseEntity<String> delete(String filename) {
        try {
            Path filePath = Path.of(serverPath).resolve(filename);
            if (!Files.exists(filePath)) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("File not found");
            }

            Files.delete(filePath);
            return ResponseEntity.ok("File deleted: " + filename);
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to delete file");
        }
    }
}
