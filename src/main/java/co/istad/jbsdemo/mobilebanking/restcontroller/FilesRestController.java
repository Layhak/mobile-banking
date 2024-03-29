package co.istad.jbsdemo.mobilebanking.restcontroller;

import co.istad.jbsdemo.mobilebanking.feature.files.FileServie;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.TreeMap;

@RestController
@RequestMapping("api/v1/files")
@RequiredArgsConstructor
public class FilesRestController {
    private final FileServie fileServie;

    @PostMapping(value = "", consumes = "multipart/form-data")
    public TreeMap<String, Object> uploadSingleFile(@RequestPart("file") MultipartFile file) {
        return new TreeMap<>() {{
            put("payload", fileServie.uploadSingleFile(file));
        }};

    }

    @PostMapping(value = "/multiple", consumes = "multipart/form-data")
    public TreeMap<String, Object> uploadMultipleFiles(@RequestPart("files")MultipartFile[] files){
        return  new TreeMap<>(){{
            put("payload",fileServie.uploadMultipleFiles(files));
        }};
    }

}
