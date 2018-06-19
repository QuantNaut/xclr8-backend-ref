package com.xclr8.api.service.mapper;

import com.xclr8.api.domain.*;
import com.xclr8.api.dto.NotesDTO;

import org.mapstruct.*;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Mapper for the entity Notes and its DTO NotesDTO.
 */

@Component
@Mapper(componentModel = "spring", uses = {})
public interface NotesMapper {

    NotesDTO notesToNotesDTO(Notes notes);

    List<NotesDTO> notesToNotesDTOs(List<Notes> notes);

    Notes notesDTOToNotes(NotesDTO notesDTO);

    List<Notes> notesDTOsToNotes(List<NotesDTO> notesDTOs);
}
