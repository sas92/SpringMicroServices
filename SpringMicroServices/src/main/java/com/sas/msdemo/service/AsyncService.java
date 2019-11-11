package com.sas.msdemo.service;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.sas.msdemo.dto.Room;
import com.sas.msdemo.repository.RoomRepository;

@Service
public class AsyncService {
	@Autowired
	private RoomRepository roomRepository;

	@Async("asyncExecutor")
	public CompletableFuture<Iterable<Room>> getAllRooms() throws InterruptedException {
		Thread.sleep(2000); // Intentional delay
		return CompletableFuture.completedFuture(roomRepository.findAll());
	}

	@Async("asyncExecutor")
	public CompletableFuture<Room> getRoomById(String roomNumber) throws InterruptedException {
		Thread.sleep(1000); // Intentional delay
		return CompletableFuture.completedFuture(roomRepository.findByRoomNumber(roomNumber));
	}
}
