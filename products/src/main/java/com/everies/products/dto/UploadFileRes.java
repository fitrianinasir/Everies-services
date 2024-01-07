package com.everies.products.dto;

import lombok.*;

@Data
@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UploadFileRes {
    private String fileName;
    private String fileDownloadUri;
}
