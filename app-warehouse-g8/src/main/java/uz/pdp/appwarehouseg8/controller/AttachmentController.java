package uz.pdp.appwarehouseg8.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.pdp.appwarehouseg8.payload.ApiResponse;
import uz.pdp.appwarehouseg8.service.AttachmentService;

import java.util.UUID;

/**
 * BY SIROJIDDIN on 03.12.2020
 */


@RestController
@RequestMapping("api/attachment")
public class AttachmentController {
    @Autowired
    AttachmentService attachmentService;


    @PostMapping("uploadFile")
    public HttpEntity<?> uploadFile(MultipartHttpServletRequest request) {
        ApiResponse apiResponse = attachmentService.uploadFile(request);
        return ResponseEntity.status(apiResponse.isSuccess() ? 201 : 409).body(apiResponse);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getFile(@PathVariable UUID id){
        return attachmentService.getFile(id);
    }
}
