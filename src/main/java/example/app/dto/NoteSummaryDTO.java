package example.app.dto;

import java.util.Date;

public class NoteSummaryDTO {
    private String id;
    private String title;
    private Date createdDate;
    private String noteLink;

    public String getId() {
        return id;
    }

    public NoteSummaryDTO setId(String id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public NoteSummaryDTO setTitle(String title) {
        this.title = title;
        return this;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public NoteSummaryDTO setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public String getNoteLink() {
        return noteLink;
    }

    public NoteSummaryDTO setNoteLink(String noteLink) {
        this.noteLink = noteLink;
        return this;
    }
}
