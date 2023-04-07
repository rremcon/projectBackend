package com.example.projectbackend.controller;
import com.example.projectbackend.model.FileDocument;
import com.example.projectbackend.model.FileUploadDownload;
import com.example.projectbackend.service.FileStorageDatabaseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class UploadDownloadDatabaseController {

    private final FileStorageDatabaseService fileStorageDatabaseService;

    public UploadDownloadDatabaseController(FileStorageDatabaseService fileStorageDatabaseService) {
        this.fileStorageDatabaseService = fileStorageDatabaseService;
    }

    @PostMapping("single/uploadDb")
    public FileUploadDownload singleFileUpload(@RequestParam("file") MultipartFile file) throws IOException {

        FileDocument fileDocument = fileStorageDatabaseService.uploadFileDocument(file);
        String url = ServletUriComponentsBuilder.fromCurrentContextPath().path("/downloadFromDB/").path(Objects.requireNonNull(file.getOriginalFilename())).toUriString();
        String contentType = file.getContentType();

        return new FileUploadDownload(fileDocument.getFileName(), url, contentType );
    }

    @GetMapping("/downloadFromDB/{fileName}")
    ResponseEntity<byte[]> downLoadSingleFile(@PathVariable String fileName, HttpServletRequest request) {

        return fileStorageDatabaseService.singleFileDownload(fileName, request);
    }

    @PostMapping("/multiple/upload/db")
    List<FileUploadDownload> multipleUpload(@RequestParam("files") MultipartFile [] files) {

        if(files.length > 7) {
            throw new RuntimeException("to many files selected");
        }

        return fileStorageDatabaseService.createMultipleUpload(files);
    }

    @GetMapping("zipDownload/db")
    public void zipDownload(@RequestBody String[] files, HttpServletResponse response) throws IOException {

        fileStorageDatabaseService.getZipDownload(files, response);
    }

    @GetMapping("/getAll/db")
    public Collection<FileDocument> getAllFromDB(){
        return fileStorageDatabaseService.getALlFromDB();
    }
}
