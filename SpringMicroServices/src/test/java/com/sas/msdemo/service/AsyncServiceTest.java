package com.sas.msdemo.service;

import com.sas.msdemo.dto.Room;
import com.sas.msdemo.repository.RoomRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author sas on 11/20/2019
 * @project MSDemo
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AsyncServiceTest {
    @Mock
    private RoomRepository roomRepository;

    @InjectMocks
    private AsyncServiceImpl asyncService;

    private List<Room> roomList = new ArrayList<>();

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        roomList.add(new Room(1, "Piccadilly", "P1", "1Q"));
        roomList.add(new Room(1, "Piccadilly", "P2", "1Q"));
        roomList.add(new Room(1, "Piccadilly", "P3", "1Q"));
    }

    @Test
    public void getAllRooms() throws Exception {
        when(roomRepository.findAll())
                .thenReturn(roomList);
        List<Room> roomList = (List<Room>) asyncService.getAllRooms().get();
        assertEquals(3, roomList.size());
    }

    @Test
    public void getRoomById() {
    }
}