package com.shortify.api.responseBody;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.ws.rs.core.Response;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SuccessResponseBody {
    private Response.Status status;

    private String message;

    public SuccessResponseBody(Response.Status status) {
        this.status = status;
        this.message = "Successful request";
    }
}
