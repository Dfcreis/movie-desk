package com.movieDesk.MovieDesk.services;

import com.movieDesk.MovieDesk.entity.Streaming;
import com.movieDesk.MovieDesk.repository.StreamingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StreamingServices {

    private final StreamingRepository streamingRepository;


    public List<Streaming> getAll() {
        return streamingRepository.findAll();
    }

    public Streaming create(Streaming streaming){
        return streamingRepository.save(streaming);
    }

    public Optional<Streaming> getById(Long id){
        return streamingRepository.findById(id);
    }

    public void deleteById(Long id){
        streamingRepository.deleteById(id);
    }

}
