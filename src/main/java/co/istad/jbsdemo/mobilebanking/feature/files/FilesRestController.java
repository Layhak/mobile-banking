package co.istad.jbsdemo.mobilebanking.feature.files;

import co.istad.jbsdemo.mobilebanking.feature.files.dto.FileResponse;
import co.istad.jbsdemo.mobilebanking.utilities.BaseResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.TreeMap;

@RestController
@RequestMapping("api/v1/files")
@RequiredArgsConstructor
public class FilesRestController {
    private final FileServie fileService;

    @PostMapping(value = "", consumes = "multipart/form-data")
    @ResponseStatus(HttpStatus.CREATED)
    public BaseResponse<FileResponse> uploadSingleFile(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        return BaseResponse.<FileResponse>createSuccess()
                .setPayload(
                        fileService.uploadSingleFile(file, request)
                )
                .setStatus(HttpStatus.CREATED.value());
    }

    @GetMapping("/download/{fileName}")
    public ResponseEntity<?> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
        return fileService.serveFile(fileName, request);
    }

    @DeleteMapping("{filename}")
    public TreeMap<String, Object> deleteSingleFile(@RequestParam("filename") String filename) {
        fileService.deleteFile(filename);
        return new TreeMap<>() {{
            put("payload", filename);
        }};
    }

}
