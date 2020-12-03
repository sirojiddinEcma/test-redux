package uz.pdp.appwarehouseg8.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.appwarehouseg8.entity.template.AbsEntity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;

/**
 * BY SIROJIDDIN on 20.11.2020
 */


@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Attachment extends AbsEntity {

    private String name;//ketmon.jpg

    private long size;//200 000

    private String contentType;//image/jpg

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "attachment", cascade = CascadeType.ALL)
    private AttachmentContent attachmentContent;

    public Attachment(String name, long size, String contentType) {
        this.name = name;
        this.size = size;
        this.contentType = contentType;
    }
}
