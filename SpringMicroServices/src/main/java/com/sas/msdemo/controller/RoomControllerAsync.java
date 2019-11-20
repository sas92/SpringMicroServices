package com.sas.msdemo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import com.sas.msdemo.service.AsyncService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.sas.msdemo.dto.Room;

@RestController
public class RoomControllerAsync {
	private static Logger log = LoggerFactory.getLogger(RoomControllerAsync.class);

	@Autowired
	AsyncService asyncService;

	@GetMapping("/testAsync/{id}")
	public Map<String, Object> testAsync(@PathVariable("id") String roomNumber) throws InterruptedException, ExecutionException {
		log.info("testAsync started");
		CompletableFuture<Iterable<Room>> allRoomsCF = asyncService.getAllRooms();
		CompletableFuture<Room> roomCF = asyncService.getRoomById(roomNumber);

		CompletableFuture.allOf(allRoomsCF, roomCF).join();

		List<Room> allRooms = (List<Room>) allRoomsCF.get();
		Room room = roomCF.get();
		log.info(allRooms.toString());
		log.info(room.toString());
		
		Map<String, Object> response=new HashMap<>();
		response.put("allRooms", allRooms);
		response.put("room", room);
		return response;
	}
}
