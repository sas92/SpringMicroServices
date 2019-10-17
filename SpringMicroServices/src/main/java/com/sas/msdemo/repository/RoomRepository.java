package com.sas.msdemo.repository;

import com.sas.msdemo.dto.Room;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends CrudRepository<Room, Long> {
    Room findByRoomNumber(String roomNumber);
}
