package example.app.controllers;

import example.app.dto.NoteStatsDTO;
import example.app.dto.NoteSummaryDTO;
import example.app.entities.Note;
import example.app.entities.Tag;
import example.app.services.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/notes")
public class NoteContriller {

    @Autowired
    NoteService noteService;

    @GetMapping
    public ResponseEntity<List<Note>> getNotes(){
        return ResponseEntity.ok(noteService.findAll());
    }

    @GetMapping("/summaries")
    public ResponseEntity<List<NoteSummaryDTO>> getSummaries(){
        List<NoteSummaryDTO> allNoteSummaries = noteService.findAllNoteSummaries();
        allNoteSummaries.forEach(e->e.setNoteLink("/api/notes/"+e.getId()));
        return ResponseEntity.ok(allNoteSummaries);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNoteById(@PathVariable String id) {
        noteService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(value = "", consumes = "application/json")
    public ResponseEntity<Void> createNote(@RequestBody Note note) {
        noteService.save(note);
        return ResponseEntity.ok().build();
    }


    @PutMapping
    public ResponseEntity<Void> updateNote(@RequestBody Note note) {
        Note byId = noteService.findById(note.getId());
        if(byId != null){
            noteService.save(note);
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping("/stats")
    public List<NoteStatsDTO> getAllNoteStats() {
        return noteService.findAllNoteStats();
    }


    @GetMapping("/{id}")
    public ResponseEntity<Note> getNoteById(@PathVariable String id) {
        Note note = noteService.findById(id);

        if (note != null) {
            return ResponseEntity.ok(note);
        } else {
            return ResponseEntity.notFound().build();
        }
    }



    @GetMapping("/tag/{tag}")
    public ResponseEntity<List<Note>> getNotesByTag(@PathVariable Tag tag) {
        List<Note> notes = noteService.findByTag(tag);

        if (notes != null && !notes.isEmpty()) {
            return ResponseEntity.ok(notes);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    //GET /api/notes/pages?cursor=1700913977&page=0&size=3
    @GetMapping("/q")
    public List<Note> getNotesByCursor(
            @RequestParam long cursor,
            Pageable pageRequest
    ) {
        return noteService.findNotesByCursor(new Date(cursor * 1000L), pageRequest);
    }




}
