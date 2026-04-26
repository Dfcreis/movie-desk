package com.movieDesk.MovieDesk.controller;

import com.movieDesk.MovieDesk.controller.request.StreamingRequest;
import com.movieDesk.MovieDesk.controller.response.StreamingResponse;
import com.movieDesk.MovieDesk.entity.Streaming;
import com.movieDesk.MovieDesk.mapper.StreamingMapper;
import com.movieDesk.MovieDesk.services.StreamingServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movieDesk/streaming")
@RequiredArgsConstructor
public class StreamingController {

    private final StreamingServices streamingServices;


    @GetMapping()
    public ResponseEntity<List<StreamingResponse>> getAll() {

        return ResponseEntity.ok(streamingServices.getAll().stream()
                .map(StreamingMapper::toStreamingResponse)
                .toList());
    }

    @PostMapping("/create")
    public ResponseEntity<StreamingResponse> save(@RequestBody StreamingRequest request) {

        Streaming saveStreaming = streamingServices
                .create(StreamingMapper.toStreaming(request));

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(StreamingMapper
                        .toStreamingResponse(saveStreaming));
    }

    @GetMapping("/{id}")
    public ResponseEntity<StreamingResponse> findById(@PathVariable Long id) {

        return streamingServices.getById(id).map(streaming -> ResponseEntity
                        .ok(StreamingMapper
                        .toStreamingResponse(streaming)))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());

    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {

      streamingServices.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }





}
