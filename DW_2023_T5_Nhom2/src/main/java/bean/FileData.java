package bean;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class FileData implements Serializable {
    private int id;
    private Config config;
    private String name;
    private LocalDate date_range_from;
    private LocalDate date_range_to;
    private LocalDateTime create_at;
    private String create_by;
    private String note;
    private int status;
    public FileData(){

    }

    public FileData(int id, Config config, String name, LocalDate date_range_from, LocalDate date_range_to, LocalDateTime create_at, String create_by, String note, int status) {
        this.id = id;
        this.config = config;
        this.name = name;
        this.date_range_from = date_range_from;
        this.date_range_to = date_range_to;
        this.create_at = create_at;
        this.create_by = create_by;
        this.note = note;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Config getConfig() {
        return config;
    }

    public void setConfig(Config config) {
        this.config = config;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDate_range_from() {
        return date_range_from;
    }

    public void setDate_range_from(LocalDate date_range_from) {
        this.date_range_from = date_range_from;
    }

    public LocalDate getDate_range_to() {
        return date_range_to;
    }

    public void setDate_range_to(LocalDate date_range_to) {
        this.date_range_to = date_range_to;
    }

    public LocalDateTime getCreate_at() {
        return create_at;
    }

    public void setCreate_at(LocalDateTime create_at) {
        this.create_at = create_at;
    }

    public String getCreate_by() {
        return create_by;
    }

    public void setCreate_by(String create_by) {
        this.create_by = create_by;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "FileData{" +
                "id=" + id +
                ", config=" + config +
                ", name='" + name + '\'' +
                ", date_range_from=" + date_range_from +
                ", date_range_to=" + date_range_to +
                ", create_at=" + create_at +
                ", create_by='" + create_by + '\'' +
                ", note='" + note + '\'' +
                ", status=" + status +
                '}';
    }
}
