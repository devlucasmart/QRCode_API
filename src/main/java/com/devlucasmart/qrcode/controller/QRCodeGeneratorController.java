package com.devlucasmart.qrcode.controller;

import com.devlucasmart.qrcode.dto.RequestDTO;
import com.devlucasmart.qrcode.service.QRCodeGeneratorService;
import com.google.zxing.WriterException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class QRCodeGeneratorController {
    private static final String QR_CODE_IMAGE_PATH = "./src/main/resources/static/img/QRCode.png";

    @Autowired
    private QRCodeGeneratorService qrCodeGeneratorService;
    @PostMapping("api/qrcode")
    public ResponseEntity<FileSystemResource> getQRCode(@RequestBody RequestDTO request){
        var documentUrl= request.getDocumentUrl();
        var qrCodeSize = 300;

        try {
            qrCodeGeneratorService.generateQRCodeImage(documentUrl, qrCodeSize, qrCodeSize, QR_CODE_IMAGE_PATH);
            FileSystemResource qrCodeFile = new FileSystemResource(QR_CODE_IMAGE_PATH);
            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_PNG)
                    .body(qrCodeFile);
        } catch (WriterException | IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.notFound().build();
    }
}