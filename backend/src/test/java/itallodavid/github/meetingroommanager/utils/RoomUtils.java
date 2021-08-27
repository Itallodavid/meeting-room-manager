package itallodavid.github.meetingroommanager.utils;

import itallodavid.github.meetingroommanager.dto.RoomCreationDTO;
import itallodavid.github.meetingroommanager.model.Room;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

public class RoomUtils {

    public static final UUID ROOM_ID = UUID.fromString("38400000-8cf0-11bd-b23e-10b96e4ef00d");
    public static final String ROOM_NAME = "test";
    public static final LocalDate ROOM_DATE = LocalDate.parse("2020-08-27");
    public static final LocalTime ROOM_START_HOUR = LocalTime.parse("12:00:00");
    public static final LocalTime ROOM_END_HOUR = LocalTime.parse("13:00:00");

    public static Room createFakeRoomWithId() {
        final Room room = createFakeRoomWithoutId();
        room.setId(ROOM_ID);
        return room;
    }

    public static Room createFakeRoomWithoutId() {
        return Room.builder()
            .name(ROOM_NAME)
            .date(ROOM_DATE)
            .startHour(ROOM_START_HOUR)
            .endHour(ROOM_END_HOUR)
            .build();
    }

    public static RoomCreationDTO createFakeRoomCreationDTO() {
        return RoomCreationDTO.builder()
            .name(ROOM_NAME)
            .date(ROOM_DATE)
            .startHour(ROOM_START_HOUR)
            .endHour(ROOM_END_HOUR)
            .build();
    }
}
