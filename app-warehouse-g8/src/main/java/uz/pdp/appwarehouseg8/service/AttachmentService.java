package uz.pdp.appwarehouseg8.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.pdp.appwarehouseg8.entity.Attachment;
import uz.pdp.appwarehouseg8.entity.AttachmentContent;
import uz.pdp.appwarehouseg8.payload.ApiResponse;
import uz.pdp.appwarehouseg8.repository.AttachmentContentRepository;
import uz.pdp.appwarehouseg8.repository.AttachmentRepository;

import java.io.IOException;
import java.util.UUID;

/**
 * BY SIROJIDDIN on 03.12.2020
 */


@Service
public class AttachmentService {
    @Autowired
    AttachmentRepository attachmentRepository;
    @Autowired
    AttachmentContentRepository attachmentContentRepository;

    public ApiResponse uploadFile(MultipartHttpServletRequest request) {
        try {
            MultipartFile file = request.getFile("file");
            assert file != null;
            Attachment attachment = attachmentRepository.save(new Attachment(
                    file.getOriginalFilename(),
                    file.getSize(),
                    file.getContentType()
            ));
            try {
                attachmentContentRepository.save(new AttachmentContent(
                        attachment,
                        file.getBytes()
                ));
            } catch (IOException e) {
                e.printStackTrace();
            }
            return new ApiResponse("Mana fayl id", true, attachment.getId());
        } catch (Exception e) {
            return new ApiResponse("Xato", false);
        }
    }

    public HttpEntity<?> getFile(UUID id) {
        Attachment attachment = attachmentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("attachment"));
        AttachmentContent attachmentContent = attachmentContentRepository.findByAttachmentId(id).orElseThrow(() -> new ResourceNotFoundException("attachmentContent"));
        return ResponseEntity
                .ok()
                .contentType(MediaType.valueOf(attachment.getContentType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; fileName=\"" + attachment.getName() + "\"")
                .body(attachmentContent.getContent());
    }
}
