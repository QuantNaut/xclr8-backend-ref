package com.xclr8.api.dto;

import com.xclr8.api.domain.BodySection;

import java.util.List;

/**
 * Created by shenju on 9/5/17.
 */
public class APIResponseBodySectionDTO extends APIResponseDTO {

    List<BodySection> bodySections;

    public List<BodySection> getBodySections() {
        return bodySections;
    }

    public void setBodySections(List<BodySection> bodySections) {
        this.bodySections = bodySections;
    }

    @Override
    public String toString() {
        return "APIResponseBodySectionDTO{" +
            "bodySections=" + bodySections +
            '}';
    }
}
