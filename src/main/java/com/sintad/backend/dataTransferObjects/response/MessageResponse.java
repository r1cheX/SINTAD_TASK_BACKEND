package com.sintad.backend.dataTransferObjects.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MessageResponse {
    public String status;

    public String message;
}
