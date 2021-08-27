package itallodavid.github.meetingroommanager.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter @Setter @Builder
@NoArgsConstructor @AllArgsConstructor
public class RoomCreationDTO implements Serializable {

    @NotEmpty @Size(min = 3, max = 100)
    private String name;

    @NotNull @FutureOrPresent @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate date;

    @NotNull
    private LocalTime startHour;

    @NotNull
    private LocalTime endHour;
}
