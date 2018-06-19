package com.xclr8.api.dto;

import com.xclr8.api.domain.VideoSession;

/**
 * Created by shenju on 23/6/17.
 */
public class APIResponseVideoSessionDTO extends APIResponseDTO {
    private VideoSession data;

    public VideoSession getVideoSession() {
        return data;
    }

    public void setVideoSession(VideoSession data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "APIResponseVideoSessionDTO{" +
            "data=" + data +
            '}';
    }
}
