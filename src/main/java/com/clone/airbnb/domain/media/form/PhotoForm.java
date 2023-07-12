package com.clone.airbnb.domain.media.form;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter @Setter
@NoArgsConstructor
public class PhotoForm {

    private Long id;

    private MultipartFile file;

    private String description;

    private Long roomId;

    private Long experienceId;
}
