package org.sunrain.openapi.rest;

import javax.ws.rs.Path;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("request")
@Path("/qq")
public class QQResource {

}
