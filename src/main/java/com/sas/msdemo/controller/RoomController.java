package com.sas.msdemo.controller;

import com.sas.msdemo.dto.Room;
import com.sas.msdemo.repository.RoomRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping(value = "/rooms")
@Api(value = "rooms", tags = "rooms")
public class RoomController {
    @Autowired
    private RoomRepository roomRepository;

    @RequestMapping(method = RequestMethod.GET)
    @ApiOperation(value = "Get all rooms", notes = "Get all rooms in the system", nickname = "getRooms")
    public List<Room> findAllRooms() {
        return (List<Room>) this.roomRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    @ApiOperation(value = "Get by Room Number", notes = "Get details of a room by Room Number", nickname = "getRoomsById")
    public Room findRoomsByID(@PathVariable("id") String roomNumber) {
        return this.roomRepository.findByRoomNumber(roomNumber);
    }
}
