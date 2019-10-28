package com.sas.msdemo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sas.msdemo.aspect.Loggable;
import com.sas.msdemo.dto.Room;
import com.sas.msdemo.repository.RoomRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping(value = "/rooms")
@Api(value = "rooms", tags = "rooms")
public class RoomController {
    @Value("${amqp.queue.name}")
    private String queueName;

    @Autowired
    private RoomRepository roomRepository;

    private RestTemplate restTemplate;
    private final RabbitTemplate rabbitTemplate;
    private final ConfigurableApplicationContext context;
    private final ObjectMapper objectMapper;

    public RoomController(RabbitTemplate rabbitTemplate, ConfigurableApplicationContext context, ObjectMapper objectMapper) {
        super();
        this.restTemplate = new RestTemplate();
        this.rabbitTemplate = rabbitTemplate;
        this.context = context;
        this.objectMapper = objectMapper;
    }

    @RequestMapping(method = RequestMethod.GET)
    @ApiOperation(value = "Get all rooms", notes = "Get all rooms in the system", nickname = "findAllRooms")
    @Loggable
    public List<Room> findAllRooms() {
        return (List<Room>) this.roomRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    @ApiOperation(value = "Get by Room Number", notes = "Get details of a room by Room Number", nickname = "findRoomsByID")
    @Loggable
    public Room findRoomsByID(@PathVariable("id") String roomNumber) {
        return this.roomRepository.findByRoomNumber(roomNumber);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/clean/{id}")
    @ApiOperation(value = "Clean room by Room Number", notes = "Order to clean a room by Room Number", nickname = "cleanRoomById")
    @Loggable
    public Object cleanRoomByID(@PathVariable("id") String roomNumber) {
        String message = null;
        try {
            Room room = roomRepository.findByRoomNumber(roomNumber);
            if (null != room && null != room.getRoomNumber()) {
                String jsonString = objectMapper.writeValueAsString(room);
                rabbitTemplate.convertAndSend(queueName, jsonString);
                message = "Room is scheduled for cleaning.";
            } else {
                message = "Room with this ID doesn't exist!";
            }
        } catch (Exception e) {
            message = "There is problem. Please try again.";
            e.printStackTrace();
        } finally {
            context.close();
        }
        return message;
    }
}
