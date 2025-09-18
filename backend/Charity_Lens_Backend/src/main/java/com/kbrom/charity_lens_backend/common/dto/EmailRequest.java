package com.kbrom.charity_lens_backend.common.dto;

import lombok.*;

import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmailRequest {
    private List<String> to;
    private String subject;
    private String body;
    private boolean html;
}
