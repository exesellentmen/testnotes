package example.app;

import example.app.entities.Note;
import example.app.entities.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest
public class NoteTest {

    // The only allowed tags are “BUSINESS”, “PERSONAL” and “IMPORTANT”
    @Test
    public void ewe(){
        assertThrows(IllegalArgumentException.class, () -> {
            Note note = new Note("1","1");
            note.setTags(List.of(Tag.valueOf("ew")));
        });
    }

    // The app should not allow to create notes without title or text
    @Test
    public void createNoteWithBlankTextOrTitle(){
        assertThrows(IllegalArgumentException.class, () -> {
            Note note = new Note("","");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            Note note = new Note("1","");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            Note note = new Note("","1");
        });
    }

    @Test
    public void setBlankTextOrTitleToNote(){
        assertThrows(IllegalArgumentException.class, () -> {
            Note note = new Note("1","1");
            note.setText("");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            Note note = new Note("1","1");
            note.setTitle("");
        });
    }








}
