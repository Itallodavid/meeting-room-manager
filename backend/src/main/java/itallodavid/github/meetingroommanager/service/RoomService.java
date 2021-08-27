package itallodavid.github.meetingroommanager.service;

import itallodavid.github.meetingroommanager.dto.RoomCreationDTO;
import itallodavid.github.meetingroommanager.exception.RoomNotFoundException;
import itallodavid.github.meetingroommanager.mapper.RoomMapper;
import itallodavid.github.meetingroommanager.model.Room;
import itallodavid.github.meetingroommanager.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RoomService {

    private final RoomRepository repository;
    private final RoomMapper mapper;

    @Transactional(readOnly = true)
    public Page<Room> rooms(final Pageable pageable){
        return repository.findAll(pageable);
    }

    @Transactional
    public Room createRoom(final RoomCreationDTO dto) {
        return repository.save(mapper.toEntity(dto));
    }

    @Transactional(readOnly = true)
    public Room getRoom(final UUID id) throws RoomNotFoundException{
        return repository.findById(id).orElseThrow(() -> new RoomNotFoundException(id));
    }

    @Transactional
    public Room updateRoom(final UUID id, final RoomCreationDTO dto) throws RoomNotFoundException {
        this.getRoom(id);
        final Room roomToUpdate = mapper.toEntity(dto);
        roomToUpdate.setId(id);
        return repository.save(roomToUpdate);
    }

    @Transactional
    public Room deleteRoom(final UUID id) throws RoomNotFoundException {
        Room room = this.getRoom(id);
        repository.delete(room);
        return room;
    }
}
