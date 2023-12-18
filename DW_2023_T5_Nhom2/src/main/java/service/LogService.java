package service;

import bean.Log;
import db.JDBIConnector;

import java.util.List;
import java.util.stream.Collectors;

public class LogService {
    private static LogService instance;


    public LogService() {

    }

    public static LogService getInstance() {
        if (instance == null) {
            instance = new LogService();
        }
        return instance;
    }

    public List<Log> getListLog() {
        return JDBIConnector.getControlJdbi().withHandle(handle -> {
            return handle.createQuery("SELECT id, log_date, message, status from log").mapToBean(Log.class).stream().collect(Collectors.toList());

        });
    }
    public static void main(String[] args) {

    }
    public void addLog(Log log) {
        JDBIConnector.getControlJdbi().withHandle(handle -> {
            return handle.createUpdate("INSERT INTO log values(:id,:log_date,:message,:status) ")
                    .bind("id", getListLog().size() + 1)
                    .bind("log_date", log.getLog_date())
                    .bind("message", log.getLog_message())
                    .bind("status", log.getStatus())
                    .execute();
        });
    }
}
