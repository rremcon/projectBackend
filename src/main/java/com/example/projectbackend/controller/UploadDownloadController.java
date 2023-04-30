package com.example.projectbackend.controller;
import com.example.projectbackend.model.FileUploadDownload;
import com.example.projectbackend.service.FileStorageDatabaseService;
import com.example.projectbackend.service.FileStorageService;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.zip.ZipOutputStream;

@CrossOrigin
@RestController
public class UploadDownloadController {
    private final FileStorageService fileStorageService;
    private final FileStorageDatabaseService fileStorageDatabaseService;
    public UploadDownloadController(FileStorageService fileStorageService, FileStorageDatabaseService fileStorageDatabaseService) {
        this.fileStorageService = fileStorageService;
        this.fileStorageDatabaseService = fileStorageDatabaseService;
    }

    @PostMapping("/upload")
//    @PostMapping("/accounts/{id}/photo")
    public FileUploadDownload uploadFile(@RequestParam("file") MultipartFile file) {

        String fileName = fileStorageService.uploadFile(file);
        String url = ServletUriComponentsBuilder.fromCurrentContextPath().path("/download/").path(fileName).toUriString();
        String contentType = file.getContentType();

        return new FileUploadDownload(fileName, contentType, url );
    }

    @GetMapping("/download/{fileName}")
        //    @GetMapping("/download/image0.jpeg")
    ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {

        Resource resource = fileStorageService.downloadFile(fileName);
        String mimeType;
        try{
            mimeType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException e) {
            mimeType = MediaType.APPLICATION_OCTET_STREAM_VALUE;
        }
//        for download attachment
//        return ResponseEntity.ok().contentType(contentType).header(HttpHeaders.CONTENT_DISPOSITION, "attachment;fileName=" + resource.getFilename()).body(resource);
//        for showing image in browser
        return ResponseEntity.ok().contentType(MediaType.parseMediaType(mimeType)).header(HttpHeaders.CONTENT_DISPOSITION, "inline;fileName=" + resource.getFilename()).body(resource);
    }

    @PostMapping("/multiple/upload")
    List<FileUploadDownload> multipleUpload(@RequestParam("files") MultipartFile[] files) {

        if(files.length > 7) {
            throw new RuntimeException("to many files");
        }
        List<FileUploadDownload> uploadResponseList = new ArrayList<>();
        Arrays.stream(files).forEach(file -> {
            String fileName = fileStorageService.uploadFile(file);
            String url = ServletUriComponentsBuilder.fromCurrentContextPath().path("/download/").path(fileName).toUriString();
            String contentType = file.getContentType();

            FileUploadDownload response = new FileUploadDownload(fileName, contentType, url );
            uploadResponseList.add(response);
        });

        return uploadResponseList;
    }

    @GetMapping("/download/allNames")
    List<String> downLoadMultipleFile() {

        return fileStorageService.downLoad();
    }

    @GetMapping("zipDownload")
    public void zipDownload(@RequestParam("fileName") String[] files, HttpServletResponse response) throws IOException {

        try(ZipOutputStream zos = new ZipOutputStream(response.getOutputStream())){
            Arrays.stream(files).forEach(file -> {
                try {
                    fileStorageDatabaseService.createZipEntry(file, zos);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            zos.finish();
        }
        response.setStatus(200);
        response.addHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment;fileName=zipfile");
    }

}
