package com.xclr8.api.dto;

import com.xclr8.api.domain.BodyPart;

import java.util.List;

/**
 * Created by shenju on 9/5/17.
 */
public class APIResponseBodyPartDTO extends  APIResponseDTO {

    List<BodyPart> bodyParts;

    public List<BodyPart> getBodyParts() {
        return bodyParts;
    }

    public void setBodyParts(List<BodyPart> bodyParts) {
        this.bodyParts = bodyParts;
    }

    @Override
    public String toString() {
        return "APIResponseBodyPartDTO{" +
            "bodyParts=" + bodyParts +
            '}';
    }
}
