package com.xclr8.api.dto;

import com.xclr8.api.domain.Notes;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;


/**
 * A DTO for the Notes entity.
 */
public class APIResponseNotesDTO extends APIResponseDTO {

    List<Notes> notesList;

    public List<Notes> getNotesList() {
        return notesList;
    }

    public void setNotesList(List<Notes> notesList) {
        this.notesList = notesList;
    }

    @Override
    public String toString() {
        return "APIResponseNotesDTO{" +
            "notesList=" + notesList +
            '}';
    }
}
