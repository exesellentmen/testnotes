package example.app;

import example.app.entities.Note;
import example.app.entities.Tag;
import example.app.repositories.NoteRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.Query;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class NoteRepositoryTest {

    @Autowired
    NoteRepository noteRepository;

    @Test
    public void generateContent(){


        Note note = new Note("Title","If your text can contain both spaces and line breaks (newlines), and you want to split the text into words while handling both spaces and line breaks as word separators, you can modify the code to split the input string accordingly. You can use regular expressions to split the text into words by considering both spaces and line breaks as delimiters. Here's an updated version of the calculateWordCount method to handle this:");
        note.setCreatedDate(new Date());
        note.setTags(Arrays.asList(Tag.BUSINESS, Tag.IMPORTANT, Tag.PERSONAL,Tag.PERSONAL));
        noteRepository.save(note);

        Note note1 = new Note("Title1","If your text can contain both spaces and line breaks (newlines), and you want to split the text into words while handling both spaces and line breaks as word separators, you can modify the code to split the input string accordingly. You can use regular expressions to split the text into words by considering both spaces and line breaks as delimiters. Here's an updated version of the calculateWordCount method to handle this:");
        note1.setCreatedDate(new Date());
        note1.setTags(Arrays.asList(Tag.BUSINESS, Tag.PERSONAL));
        noteRepository.save(note1);

        Note note2 = new Note("Title2","If your text can contain both spaces and line breaks (newlines), and you want to split the text into words while handling both spaces and line breaks as word separators, you can modify the code to split the input string accordingly. You can use regular expressions to split the text into words by considering both spaces and line breaks as delimiters. Here's an updated version of the calculateWordCount method to handle this:");
        note2.setCreatedDate(new Date());
        note2.setTags(Arrays.asList(Tag.PERSONAL,Tag.PERSONAL));
        noteRepository.save(note2);

        Note note3 = new Note("Title3","If your text can contain both spaces and line breaks (newlines), and you want to split the text into words while handling both spaces and line breaks as word separators, you can modify the code to split the input string accordingly. You can use regular expressions to split the text into words by considering both spaces and line breaks as delimiters. Here's an updated version of the calculateWordCount method to handle this:");
        note3.setCreatedDate(new Date());
        note3.setTags(Arrays.asList(Tag.BUSINESS, Tag.IMPORTANT));
        noteRepository.save(note3);

        Note note4 = new Note("Title4","If your text can contain both spaces and line breaks (newlines), and you want to split the text into words while handling both spaces and line breaks as word separators, you can modify the code to split the input string accordingly. You can use regular expressions to split the text into words by considering both spaces and line breaks as delimiters. Here's an updated version of the calculateWordCount method to handle this:");
        note4.setCreatedDate(new Date());
        note4.setTags(Arrays.asList(Tag.PERSONAL,Tag.PERSONAL));
        noteRepository.save(note4);


        Note note5 = new Note("Title5","If your text can contain both spaces and line breaks (newlines), and you want to split the text into words while handling both spaces and line breaks as word separators, you can modify the code to split the input string accordingly. You can use regular expressions to split the text into words by considering both spaces and line breaks as delimiters. Here's an updated version of the calculateWordCount method to handle this:");
        note5.setCreatedDate(new Date());
        note5.setTags(Arrays.asList(Tag.BUSINESS));
        noteRepository.save(note5);
    }


    @Test
    public void getFiltered(){
        List<Note> byTagsContaining = noteRepository.findByTagsContaining(Tag.PERSONAL);
        long count = byTagsContaining.stream().filter(e -> !e.getTags().contains(Tag.PERSONAL)).count();
        assertTrue(count == 0);
    }

    @Test
    public void getSorted(){
        List<Note> allByOrderByCreatedDateDesc = noteRepository.findAllByOrderByCreatedDateDesc();
    }

    @Test
    public void getByPages(){
        PageRequest pageRequest = PageRequest.of(0, 2);
        List<Note> notesByCursor = noteRepository.findNotesByCursor(new Date(), pageRequest);
    }

    @Test
    public void getByPages1(){

        PageRequest pageRequest = PageRequest.of(0, 2);
        List<Note> notesByCursor = noteRepository.findNotesByCursor(new Date(), pageRequest);
    }


    @Test
    public void deleteElement(){
        Note note = noteRepository.findAll().get(0);
        String id = note.getId();
        noteRepository.deleteById(note.getId());
        Optional<Note> byId = noteRepository.findById(id);
        Note note1 = byId.orElse(null);
    }

    @Test
    public void updateElement(){
        Note note = noteRepository.findAll().get(0);
        note.setTitle("New Title");
        noteRepository.save(note);
    }



    @Test
    public void testText(){
        Note note = new Note("Title1","If your text can contain both spaces and line breaks (newlines), and you want to split the text into words while handling both spaces and line breaks as word separators, you can modify the code to split the input string accordingly. You can use regular expressions to split the text into words by considering both spaces and line breaks as delimiters. Here's an updated version of the calculateWordCount method to handle this:");
        noteRepository.save(note);


    }


    @Test
    public void testSortedNotesWithPagination(){
        PageRequest pageRequest = PageRequest.of(0, 2, Sort.by(Sort.Order.desc("createdDate")));
        List<Note> notes = noteRepository.findNotesByCursorSorted(new Date(), pageRequest);
    }






}
