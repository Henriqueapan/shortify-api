package com.shortify.api.resource;

import com.shortify.api.dto.URIRecordDTO;
import com.shortify.api.entity.URIRecordEntity;
import com.shortify.api.responseBody.SuccessResponseBody;
import com.shortify.api.service.URIRecordService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.net.URI;

@Path("/uri-record")
public class URIRecordResource {
    URIRecordService uriRecordService = new URIRecordService();

    @GET
    @Path("/test")
    @Produces(MediaType.APPLICATION_JSON)
    public String test() {
        return "OK";
    }

    @POST
    @Path("/create")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response createUriRecord(@Valid URIRecordDTO uriRecordDTO) {
        String uriHash = uriRecordService.createUriResource(uriRecordDTO.getUri());

        return Response.created(URI.create("/uri-record/" + uriHash))
                .entity(new SuccessResponseBody(Response.Status.CREATED))
                .build();
    }
}
