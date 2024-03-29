package co.istad.jbsdemo.mobilebanking.feature.files;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileServie {
    @Value("${file.storage-dir}")
    String fileStorageDir;

    private String uploadFile(MultipartFile file) {
        try {
            Path fileStoragePath = Path.of(fileStorageDir);
            if (!Files.exists(fileStoragePath)) {
                Files.createDirectories(fileStoragePath);
            }
            String filename = UUID.randomUUID() + "." + Objects.requireNonNull(file.getOriginalFilename()).split("\\.")[1];
            Files.copy(file.getInputStream(), fileStoragePath.resolve(filename), StandardCopyOption.REPLACE_EXISTING);
            return filename;
        } catch (IOException exception) {
            throw new RuntimeException();
        }
    }

    @Override
    public String uploadSingleFile(MultipartFile file) {
        return uploadFile(file);
    }

    @Override
    public List<String> uploadMultipleFiles(MultipartFile[] files) {
        return new ArrayList<>() {{
            for (MultipartFile file : files) {
                uploadSingleFile(file);
            }
        }};
    }

    @Override
    public void deleteFile(String filename) {

    }
}
