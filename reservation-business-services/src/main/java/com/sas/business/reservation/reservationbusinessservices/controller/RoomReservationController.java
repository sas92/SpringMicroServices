package com.sas.business.reservation.reservationbusinessservices.controller;

import com.sas.business.reservation.reservationbusinessservices.client.RoomService;
import com.sas.business.reservation.reservationbusinessservices.domain.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/rooms")
public class RoomReservationController {
    @Autowired
    private RoomService roomService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Room> getAllRooms() {
        return this.roomService.findAllRooms();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public Room getRoomByID(@PathVariable("id") String roomNumber) {
        return this.roomService.findRoomsByID(roomNumber);
    }
}
