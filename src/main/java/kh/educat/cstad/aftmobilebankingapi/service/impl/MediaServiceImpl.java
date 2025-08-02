package kh.educat.cstad.aftmobilebankingapi.service.impl;


import kh.educat.cstad.aftmobilebankingapi.domain.Media;
import kh.educat.cstad.aftmobilebankingapi.dto.MediaResponse;
import kh.educat.cstad.aftmobilebankingapi.repository.MediaRepository;
import kh.educat.cstad.aftmobilebankingapi.service.MediaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;


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
}
