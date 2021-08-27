package itallodavid.github.meetingroommanager.controller;

import itallodavid.github.meetingroommanager.model.Room;
import itallodavid.github.meetingroommanager.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController @RequestMapping("/rooms")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RoomController {

    private final RoomService service;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Page<Room> rooms(final Pageable pageable){
        return service.rooms(pageable);
    }
}
