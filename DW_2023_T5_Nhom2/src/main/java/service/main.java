package service;

public class main {
    public static void main(String[] args) {
        CrawData.crawlerDataformConfig();
        ExtractDataToStaging.loadDataToStaging();
        LoadDataToFact.performETL();
        LoadDataToAggregate.performETL();
        LoadDataToMart.performETL();
    }
}
