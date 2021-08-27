package itallodavid.github.meetingroommanager.service;

import itallodavid.github.meetingroommanager.dto.RoomCreationDTO;
import itallodavid.github.meetingroommanager.mapper.RoomMapper;
import itallodavid.github.meetingroommanager.model.Room;
import itallodavid.github.meetingroommanager.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
