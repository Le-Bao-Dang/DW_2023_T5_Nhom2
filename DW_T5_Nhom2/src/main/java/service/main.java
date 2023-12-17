package service;

public class main {
    public static void main(String[] args) {
        ScriptCrawData.crawlerDataformConfig();
        ScriptLoadDataToStaging.loadDataToStaging();
        LoadDataToFact.performETL();
        LoadDataToAggregate.performETL();
        LoadDataToMart.performETL();

    }
}
