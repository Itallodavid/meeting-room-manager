package itallodavid.github.meetingroommanager.service;

import itallodavid.github.meetingroommanager.dto.RoomCreationDTO;
import itallodavid.github.meetingroommanager.exception.RoomNotFoundException;
import itallodavid.github.meetingroommanager.mapper.RoomMapper;
import itallodavid.github.meetingroommanager.model.Room;
import itallodavid.github.meetingroommanager.repository.RoomRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import static itallodavid.github.meetingroommanager.utils.RoomUtils.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RoomServiceTest {

    @Mock
    private RoomRepository repository;

    @Mock
    private RoomMapper mapper;

    @InjectMocks
    private RoomService service;

    @Test
    void rooms() {
        // setup
        final List<Room> contentPage = List.of(createFakeRoomWithId());
        final int totalItems = contentPage.size();

        // given
        Pageable pageable = PageRequest.of(0, totalItems);

        // when
        Page<Room> expectedPage = new PageImpl<>(contentPage, pageable, totalItems);
        when(repository.findAll(pageable)).thenReturn(expectedPage);

        // then
        Page<Room> actual = service.rooms(pageable);

        assertThat(actual).isEqualTo(expectedPage);
        assertThat(actual.getContent()).isEqualTo(contentPage);
        assertThat(actual.isFirst()).isTrue();
    }

    @Test
    void testGivenValidRoomCreationDTOForSaveThenReturnARoomEntity(){
        // setup
        final Room mapperReturn = createFakeRoomWithoutId();
        final Room expectedRoom = createFakeRoomWithId();

        // given
        final RoomCreationDTO roomCreationDTO = createFakeRoomCreationDTO();

        // when
        when(mapper.toEntity(roomCreationDTO)).thenReturn(mapperReturn);
        when(repository.save(mapperReturn)).thenReturn(expectedRoom);

        // then
        Room actual = service.createRoom(roomCreationDTO);
        assertThat(actual).isEqualTo(expectedRoom);
    }

    @Test
    void testGivenValidIdThenReturnARoomEntity() throws RoomNotFoundException {
        // setup
        final Room expectedRoom = createFakeRoomWithId();

        // when
        when(repository.findById(ROOM_ID)).thenReturn(Optional.of(expectedRoom));

        // then
        Room actual = service.getRoom(ROOM_ID);

        assertEquals(expectedRoom, actual);
    }

    @Test
    void testGivenInvalidIdThenThrowRoomNotFoundException() {
        // when
        when(repository.findById(ROOM_ID)).thenReturn(Optional.empty());

        // then
        assertThrows(RoomNotFoundException.class, () -> service.getRoom(ROOM_ID));
    }

    @Test
    void testGivenValidIdToUpdateThenReturnUpdatedRoomEntity() throws RoomNotFoundException {
        // setup
        final Room mapperReturn = createFakeRoomWithoutId();

        final Room findByIdReturn = Room.builder()
            .id(ROOM_ID)
            .name("random")
            .date(LocalDate.now())
            .endHour(LocalTime.now())
            .startHour(LocalTime.now())
            .build();

        final Room expectedRoom = createFakeRoomWithId();

        // given
        final RoomCreationDTO roomCreationDTO = createFakeRoomCreationDTO();

        // when
        when(repository.findById(ROOM_ID)).thenReturn(Optional.of(findByIdReturn));
        when(mapper.toEntity(roomCreationDTO)).thenReturn(mapperReturn);
        when(repository.save(mapperReturn)).thenReturn(expectedRoom);

        // then
        final Room actual = service.updateRoom(ROOM_ID, roomCreationDTO);

        assertEquals(expectedRoom, actual);
        assertEquals(expectedRoom.getDate(), actual.getDate());
        assertEquals(expectedRoom.getName(), actual.getName());
        assertEquals(expectedRoom.getStartHour(), actual.getStartHour());
        assertEquals(expectedRoom.getEndHour(), actual.getEndHour());
    }

    @Test
    void testGivenInvalidIdToUpdateThenThrowRoomNotFoundException() {
        // given
        final RoomCreationDTO roomCreationDTO = createFakeRoomCreationDTO();

        // when
        when(repository.findById(ROOM_ID)).thenReturn(Optional.empty());

        // then
        assertThrows(RoomNotFoundException.class, () -> service.updateRoom(ROOM_ID, roomCreationDTO));
    }
}