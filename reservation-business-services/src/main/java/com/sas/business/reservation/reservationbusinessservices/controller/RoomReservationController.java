package com.sas.business.reservation.reservationbusinessservices.controller;

import com.sas.business.reservation.reservationbusinessservices.domain.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping(value = "/rooms")
public class RoomReservationController {
    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping(method = RequestMethod.GET)
    public List<Room> getAllRooms() {
        ResponseEntity<List<Room>> roomsResponse = this.restTemplate.exchange(
                "http://MSDEMO/rooms", HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Room>>() {
                });
        return roomsResponse.getBody();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public Room getRoomByID(@PathVariable("id") String roomNumber) {
        ResponseEntity<Room> roomsResponse = this.restTemplate.exchange(
                "http://MSDEMO/rooms/" + roomNumber, HttpMethod.GET, null,
                new ParameterizedTypeReference<Room>() {
                });
        return roomsResponse.getBody();
    }
}
