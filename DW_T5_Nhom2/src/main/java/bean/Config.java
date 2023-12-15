package bean;

import java.io.Serializable;
import java.time.LocalDateTime;


public class Config implements Serializable {
    private int id;
    private String name;
    private String code;
    private String description;
    private String source_path;
    private String source_url;
    private String file_format;
    private String destination;
    private LocalDateTime create_at;
    private String create_by;
    private LocalDateTime update_at;
    private String update_by;

    public Config() {

    }

    public Config(int id, String name, String code, String description, String source_path, String source_url, String file_format, String destination, LocalDateTime create_at, String create_by, LocalDateTime update_at, String update_by) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.description = description;
        this.source_path = source_path;
        this.source_url = source_url;
        this.file_format = file_format;
        this.destination = destination;
        this.create_at = create_at;
        this.create_by = create_by;
        this.update_at = update_at;
        this.update_by = update_by;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSource_path() {
        return source_path;
    }

    public void setSource_path(String source_path) {
        this.source_path = source_path;
    }

    public String getSource_url() {
        return source_url;
    }

    public void setSource_url(String source_url) {
        this.source_url = source_url;
    }

    public String getFile_format() {
        return file_format;
    }

    public void setFile_format(String file_format) {
        this.file_format = file_format;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
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

    public LocalDateTime getUpdate_at() {
        return update_at;
    }

    public void setUpdate_at(LocalDateTime update_at) {
        this.update_at = update_at;
    }

    public String getUpdate_by() {
        return update_by;
    }

    public void setUpdate_by(String update_by) {
        this.update_by = update_by;
    }

    @Override
    public String toString() {
        return "Config{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", description='" + description + '\'' +
                ", source_path='" + source_path + '\'' +
                ", source_url='" + source_url + '\'' +
                ", file_format='" + file_format + '\'' +
                ", destination='" + destination + '\'' +
                ", create_at=" + create_at +
                ", create_by='" + create_by + '\'' +
                ", update_at=" + update_at +
                ", update_by='" + update_by + '\'' +
                '}';
    }
}
