package itallodavid.github.meetingroommanager.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RoomNotFoundException extends Exception {

    public RoomNotFoundException(UUID id){
        super(String.format("Room with id %s not found", id.toString()));
    }
}
