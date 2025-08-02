package kh.educat.cstad.aftmobilebankingapi.service;


import kh.educat.cstad.aftmobilebankingapi.dto.MediaResponse;
import org.springframework.web.multipart.MultipartFile;

public interface MediaService {

    MediaResponse upload(MultipartFile file);

}