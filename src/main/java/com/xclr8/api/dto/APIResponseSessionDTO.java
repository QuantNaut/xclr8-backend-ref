package com.xclr8.api.dto;

import com.xclr8.api.domain.Session;

import java.util.List;

/**
 * Created by shenju on 9/5/17.
 */
public class APIResponseSessionDTO extends APIResponseDTO{

    private List<Session> sessions;

    public List<Session> getSessions() {
        return sessions;
    }

    public void setSessions(List<Session> sessions) {
        this.sessions = sessions;
    }

    @Override
    public String toString() {
        return "APIResponseSessionDTO{" +
            "sessions=" + sessions +
            '}';
    }
}
