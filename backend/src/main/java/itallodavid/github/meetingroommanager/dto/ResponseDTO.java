package itallodavid.github.meetingroommanager.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter @Setter @Builder
public class ResponseDTO implements Serializable {
    private String description;

    private int statusCodeSeries;
}
