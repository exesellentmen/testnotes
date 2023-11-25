package example.app.entities;

import jakarta.validation.constraints.NotBlank;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Document(collection = "notes")
public class Note {
    @Id
    private String id;

    @NotBlank(message = "Title is required")
    private String title;

    @Indexed
    private Date createdDate;

    @NotBlank(message = "Text is required")
    private String text;

    private List<Tag> tags;

    private Stats stats;

    public Note(String title, String text) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Title is required");
        }
        if (text == null || text.trim().isEmpty()) {
            throw new IllegalArgumentException("Text is required");
        }

        this.title = title;
        this.text = text;
        this.stats = new Stats();
        updateStats();
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Note setTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be empty");
        }
        this.title = title;
        return this;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public Note setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public String getText() {
        return text;
    }

    public Note setText(String text) {
        if (text == null || text.trim().isEmpty()) {
            throw new IllegalArgumentException("Text cannot be empty");
        }
        this.text = text;
        updateStats();
        return this;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public Note setTags(List<Tag> tags) {
        this.tags = tags.stream().distinct().collect(Collectors.toList());
        return this;
    }


    public void updateStats() {
        String[] words = this.text.replaceAll("[^A-Za-z0-9' -]", "").split("[\\s\\n]+");
        stats.wordCounts = Arrays.stream(words)
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        Collectors.counting()
                )).entrySet()
                .stream()
                .sorted((a,b)->b.getValue().compareTo(a.getValue()))
                .collect(Collectors.toMap(Map.Entry::getKey,Map.Entry::getValue,(a,b)->a,LinkedHashMap::new));
    }

    public Stats getStats() {
        return stats;
    }
}
