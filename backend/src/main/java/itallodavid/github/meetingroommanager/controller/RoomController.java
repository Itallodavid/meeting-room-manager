package itallodavid.github.meetingroommanager.controller;

import itallodavid.github.meetingroommanager.dto.RoomCreationDTO;
import itallodavid.github.meetingroommanager.exception.RoomNotFoundException;
import itallodavid.github.meetingroommanager.model.Room;
import itallodavid.github.meetingroommanager.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController @RequestMapping("/rooms")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RoomController {

    private final RoomService service;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Page<Room> rooms(final Pageable pageable){
        return service.rooms(pageable);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Room createRoom(@RequestBody @Valid final RoomCreationDTO dto){
        return service.createRoom(dto);
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Room room(@PathVariable final UUID id) throws RoomNotFoundException {
        return service.getRoom(id);
    }

    @PutMapping(path = "/{id}",
        consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Room updateRoom(@PathVariable final UUID id, @RequestBody @Valid final RoomCreationDTO dto)
        throws RoomNotFoundException{
        return service.updateRoom(id, dto);
    }

    @DeleteMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Room deleteRoom(@PathVariable final UUID id) throws RoomNotFoundException {
        return service.deleteRoom(id);
    }
}
