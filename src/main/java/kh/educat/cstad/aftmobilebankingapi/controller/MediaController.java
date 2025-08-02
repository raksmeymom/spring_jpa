package kh.educat.cstad.aftmobilebankingapi.controller;


import kh.educat.cstad.aftmobilebankingapi.dto.MediaResponse;
import kh.educat.cstad.aftmobilebankingapi.service.MediaService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/medias")
@RequiredArgsConstructor
public class MediaController {

    private final MediaService mediaService;

    // Upload single file (already exists)
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public MediaResponse upload(@RequestPart MultipartFile file) {
        return mediaService.upload(file);
    }

    // ✅ Upload multiple files
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/multiple")
    public List<MediaResponse> uploadMultiple(@RequestPart MultipartFile[] files) {
        return mediaService.uploadMultiple(files);
    }

    // ✅ Download file by name
    @GetMapping("/download/{filename:.+}")
    public ResponseEntity<Resource> download(@PathVariable String filename) {
        return mediaService.download(filename);
    }

    // ✅ Delete file by name
    @DeleteMapping("/delete/{filename:.+}")
    public ResponseEntity<String> delete(@PathVariable String filename) {
        return mediaService.delete(filename);
    }
}
