package com.sas.business.reservation.reservationbusinessservices.client;

import com.sas.business.reservation.reservationbusinessservices.domain.Room;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(value = "MSDEMO")
public interface RoomService {
    @RequestMapping(method = RequestMethod.GET, value = "/rooms")
    public List<Room> findAllRooms();

    @RequestMapping(method = RequestMethod.GET, value = "/rooms/{id}")
    public Room findRoomsByID(@PathVariable("id") String roomNumber);
}
