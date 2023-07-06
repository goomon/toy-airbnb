package com.clone.airbnb.domain.room.controller;

import com.clone.airbnb.domain.contents.dto.RoomDto;
import com.clone.airbnb.domain.room.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@Transactional
@RequestMapping("/room")
@RequiredArgsConstructor
public class RoomController {

    private final RoomService roomService;

    @GetMapping
    public List<RoomDto> findAll() {
        return roomService.findAll().stream()
                .map(RoomDto::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public RoomDto findById(@PathVariable("id") Long id) {
        return new RoomDto(roomService.findById(id));
    }

    @PostMapping
    public void save(@RequestBody RoomDto requestData) {
        roomService.save(requestData);
    }
}
