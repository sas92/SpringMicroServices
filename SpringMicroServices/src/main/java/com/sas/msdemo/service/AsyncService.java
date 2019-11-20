package com.sas.msdemo.service;

import com.sas.msdemo.dto.Room;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

/**
 * @author sas on 11/20/2019
 * @project MSDemo
 */

public interface AsyncService {
    public CompletableFuture<Iterable<Room>> getAllRooms() throws InterruptedException;

    public CompletableFuture<Room> getRoomById(String roomNumber) throws InterruptedException;
}
