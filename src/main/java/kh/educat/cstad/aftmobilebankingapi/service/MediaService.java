package kh.educat.cstad.aftmobilebankingapi.service;


import kh.educat.cstad.aftmobilebankingapi.dto.MediaResponse;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;


import java.util.List;

public interface MediaService {

    // Upload single file
    MediaResponse upload(MultipartFile file);
    List<MediaResponse> uploadMultiple(MultipartFile[] files);
    ResponseEntity<Resource> download(String filename);
    ResponseEntity<String> delete(String filename);
}
