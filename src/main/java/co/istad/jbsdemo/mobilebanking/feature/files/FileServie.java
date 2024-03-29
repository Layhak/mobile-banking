package co.istad.jbsdemo.mobilebanking.feature.files;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileServie {
    String uploadSingleFile(MultipartFile file);

    List<String> uploadMultipleFiles(MultipartFile[] files);

    void deleteFile(String filename);

}
