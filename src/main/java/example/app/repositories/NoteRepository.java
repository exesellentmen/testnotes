package example.app.repositories;


import example.app.dto.NoteStatsDTO;
import example.app.dto.NoteSummaryDTO;
import example.app.entities.Note;
import example.app.entities.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Date;
import java.util.List;

public interface NoteRepository extends MongoRepository<Note, String> {
    List<Note> findAllByOrderByCreatedDateDesc();
    List<Note> findByTagsContaining(Tag tag);

    @Query("{ 'createdDate' : { $lt: ?0 } }")
    List<Note> findNotesByCursor(Date cursor, Pageable pageable);

    @Query(value = "{_id: ?0}", fields = "{title: 1, text: 1}")
    List<Note> findTitleAndTextById(String id);

    @Query(value = "{}", fields = "{title: 1, createdDate: 1}")
    List<NoteSummaryDTO> findAllNoteSummaries(Sort sort);


    @Query(value = "{}", fields = "{_id: 1, title: 1, stats: 1}")
    List<NoteStatsDTO> findAllNoteStats();

    @Query("{ 'tags' : ?0 }")
    List<Note> findByTag(Tag tag);

    @Query("{ 'createdDate' : { $lt: ?0 } }")
    List<Note> findNotesByCursorSorted(Date cursor, Pageable pageable);
}
