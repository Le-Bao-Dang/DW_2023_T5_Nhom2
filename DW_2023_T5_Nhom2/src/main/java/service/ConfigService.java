package service;

import bean.Config;
import bean.FileData;
import db.JDBIConnector;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class ConfigService {
    private static ConfigService instance;
    public static final int PREPARE = 0;
    public static final int CRAWLING = 1;
    public static final int FAILED = -1;
    public static final int SUCCESS = 2;
    public static final int EXTRACTED = 3;
    public static final int EXTRACTING = -3;
    public static final int LOADED = 4;
    public static final int LOADING = -4;
    public static final int TRANSFORMING = -5;
    public static final int TRANSFORMED = 5;

    private ConfigService() {

    }

    public static ConfigService getInstance() {
        if (instance == null) {
            instance = new ConfigService();
        }
        return instance;
    }

    public List<Config> getListConfig() {
        return JDBIConnector.getControlJdbi().withHandle(handle -> {
            return handle.createQuery("select * from config").mapToBean(Config.class).stream().toList();
        });
    }

    public List<FileData> getListFileData() {
        return JDBIConnector.getControlJdbi().withHandle(handle -> {
            List<FileData> fileData = handle.createQuery("select * from file_data").mapToBean(FileData.class).stream().toList();
            for (FileData f : fileData) {
                f.setConfig(getConfigByFileData(f.getId()));
            }
            return fileData;
        });
    }

    public FileData getFileDataToDay(int configId) {
        return JDBIConnector.getControlJdbi().withHandle(handle -> {
            List<FileData> list = handle.createQuery("SELECT f.id,f.name, f.date_range_from, f.date_range_to,f.create_at,f.create_by,f.note, f.status from file_data f join config c on c.id = f.config_id where f.date_range_to = "+"'" + LocalDate.now() +"'" +"  and c.id= " + configId).mapToBean(FileData.class).stream().toList();
            if (list.size() != 1) {
                return null;
            }
            list.get(0).setConfig(getConfigByFileData(list.get(0).getId()));
            return list.get(0);
        });
    }

    public Config getConfigByFileData(int idFileData) {
        return JDBIConnector.getControlJdbi().withHandle(handle -> {
            return handle.createQuery("SELECT c.id,c.name,c.code,c.description,c.source_path,c.source_url,c.file_format,c.destination,c.create_at,c.create_by,c.update_at,c.update_by from config c join file_data f on c.id = f.config_id where f.id= " + idFileData).mapToBean(Config.class).one();
        });
    }


    public Integer insertFileData(FileData fileData) {
        return JDBIConnector.getControlJdbi().withHandle(handle -> {
            return handle.createUpdate("INSERT INTO file_data(config_id,`name`,date_range_from,date_range_to,create_at,create_by,note,status) values (?,?,?,?,?,?,?,?)")
                    .bind(0, fileData.getConfig().getId())
                    .bind(1, fileData.getName())
                    .bind(2, fileData.getDate_range_from())
                    .bind(3, fileData.getDate_range_to())
                    .bind(4, fileData.getCreate_at())
                    .bind(5, fileData.getCreate_by())
                    .bind(6, fileData.getNote())
                    .bind(7, fileData.getStatus())
                    .execute();
        });
    }

    public void setstatusFileData(int status, int id) {
        JDBIConnector.getControlJdbi().withHandle(handle -> {
            return handle.createUpdate("UPDATE file_data set status=? where id=?").bind(0, status).bind(1, id).execute();
        });
    }

    public static void main(String[] args) {
//        System.out.println(getInstance().getFileDataToDay(1));
//        System.out.println(LocalDate.now());
        getInstance().setstatusFileData(ConfigService.SUCCESS,16);


    }
}
