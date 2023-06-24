package com.devlucasmart.qrcode.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestDTO {
    private String documentUrl;
    private String foregroundColor;
    private String backgroundColor;
}
