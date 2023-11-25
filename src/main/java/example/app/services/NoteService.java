package example.app.services;

import example.app.dto.NoteStatsDTO;
import example.app.dto.NoteSummaryDTO;
import example.app.entities.Note;
import example.app.entities.Tag;
import example.app.repositories.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class NoteService {

    @Autowired
    NoteRepository noteRepository;

    public List<NoteSummaryDTO> findAllNoteSummaries(){
        return noteRepository.findAllNoteSummaries(Sort.by(Sort.Direction.DESC, "createdDate"));
    }

    public List<NoteStatsDTO> findAllNoteStats(){
        return noteRepository.findAllNoteStats();
    }

    public List<Note> findAll(){
        return noteRepository.findAll();
    }

    public void deleteById(String id){
        noteRepository.deleteById(id);
    }

    public void save(Note note){
        note.setCreatedDate(new Date());
        note.updateStats();
        noteRepository.save(note);
    }

    public Note findById(String id){
        return noteRepository.findById(id).orElse(null);
    }


    public List<Note> findByTag(Tag tag){
        return noteRepository.findByTag(tag);
    }

    public List<Note> findNotesByCursor(Date cursor, Pageable pageable) {
        return noteRepository.findNotesByCursor(cursor, pageable);
    }
}
