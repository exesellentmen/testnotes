package example.app.dto;

import example.app.entities.Stats;

public class NoteStatsDTO {
    private String id;
    private String title;
    private Stats stats;

    public String getId() {
        return id;
    }

    public NoteStatsDTO setId(String id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public NoteStatsDTO setTitle(String title) {
        this.title = title;
        return this;
    }

    public Stats getStats() {
        return stats;
    }

    public NoteStatsDTO setStats(Stats stats) {
        this.stats = stats;
        return this;
    }
}
