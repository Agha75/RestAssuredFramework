package com.spotify.oauth2.pojos;

import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter @Setter
@Jacksonized
@Generated("jsonschema2pojo")
public class Error {

    @JsonProperty("error")
    private InnerError error;

}
