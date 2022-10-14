package com.example.shamo.controller;

import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.shamo.dto.InsertRes;
import com.example.shamo.dto.file.InsertFileReq;
import com.example.shamo.model.Files;
import com.example.shamo.service.FileService;

@RestController
@RequestMapping("files")
public class FileController {
	
	@Autowired
    private FileService fileService;

    @GetMapping("{id}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable("id") Long id) throws Exception {
        Files file = fileService.getById(id);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=attachment." + file.getFileExtension());

        byte[] fileInBytes = Base64.getDecoder().decode(file.getFileName());
        return ResponseEntity.ok().headers(httpHeaders).contentType(MediaType.APPLICATION_OCTET_STREAM).body(fileInBytes);
    }
    
    @PostMapping
    public ResponseEntity<InsertRes> insert(@RequestBody InsertFileReq files) throws Exception {
    	InsertRes data = fileService.insert(files);
    	return new ResponseEntity<InsertRes>(data, HttpStatus.CREATED);
    }

}
