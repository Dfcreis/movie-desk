package com.movieDesk.MovieDesk.mapper;

import com.movieDesk.MovieDesk.controller.request.StreamingRequest;
import com.movieDesk.MovieDesk.controller.response.StreamingResponse;
import com.movieDesk.MovieDesk.entity.Streaming;
import lombok.experimental.UtilityClass;

@UtilityClass
public class StreamingMapper {

    public static Streaming toStreaming(StreamingRequest streamingRequest){
        return Streaming.builder()
                .name(streamingRequest.name())
                .build();
    }

    public static StreamingResponse toStreamingResponse(Streaming streaming){
        return StreamingResponse.builder()
                .id(streaming.getId())
                .name(streaming.getName())
                .build();
    }
}
