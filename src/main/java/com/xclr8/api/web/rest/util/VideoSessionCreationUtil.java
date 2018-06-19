package com.xclr8.api.web.rest.util;

import com.opentok.exception.OpenTokException;
import com.xclr8.api.domain.VideoSession;
import com.opentok.OpenTok;
import com.xclr8.api.service.util.XCLR8ConfigurationConstants;
import com.opentok.Session;
import com.opentok.TokenOptions;

/**
 * Created by shenju on 23/6/17.
 */
public class VideoSessionCreationUtil {
    public static VideoSession createSession() throws OpenTokException {
        int apiKey = XCLR8ConfigurationConstants.OPEN_TOK_API_KEY; //API KEY
        String apiSecret = XCLR8ConfigurationConstants.OPEN_TOK_API_SECRET; //API SECRET
        OpenTok opentok = new OpenTok(apiKey, apiSecret);
        Session session = opentok.createSession();
        String sessionId = session.getSessionId();
        String token = session.generateToken(new TokenOptions.Builder()
            .expireTime((System.currentTimeMillis() / 1000L) + (7 * 24 * 60 * 60)) // in one week
            .build());
        VideoSession videoSession = new VideoSession();
        videoSession.setVideoSessionId(sessionId);
        videoSession.setVideoSessionToken(token);
        return videoSession;
    }
}
