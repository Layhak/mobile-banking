package co.istad.jbsdemo.mobilebanking.feature.files;

import co.istad.jbsdemo.mobilebanking.feature.files.dto.FileResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileServie {
//    FileResponse uploadSingleFile(MultipartFile file);

    FileResponse uploadSingleFile(MultipartFile file, HttpServletRequest request);

    List<String> uploadMultipleFiles(MultipartFile[] files, HttpServletRequest request);

    ResponseEntity<Resource> serveFile(String filename, HttpServletRequest request);


    void deleteFile(String filename);

}
