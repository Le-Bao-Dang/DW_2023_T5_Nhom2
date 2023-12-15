package service;

import bean.BangxepHangAggregate;
import db.JDBIConnector;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

//1. Kết nối DB Mart
public class AggregateService {

    public static List<BangxepHangAggregate> getData(){
        return JDBIConnector.getMartJdbi().withHandle(handle ->{
            //2. Thực hiện truy vấn để lấy dữ liệu từ bảng aggregate cho Ngoại hạng Anh và ngày hiện tại
            return handle.createQuery("SELECT * FROM aggregate where ten_giai_dau= '"+"BXH Ngoại hạng Anh (Mùa 2023/2024)"+"' and ngay= '"+ LocalDate
                            .now()+"'")
                    .mapToBean(BangxepHangAggregate.class).stream().collect(Collectors.toList());
        } );
    }
    public static List<BangxepHangAggregate> getDataV(){
        return JDBIConnector.getMartJdbi().withHandle(handle ->{
            // 3.Thực hiện truy vấn để lấy dữ liệu từ bảng aggregate cho V-League và ngày hiện tại
            return handle.createQuery("SELECT * FROM aggregate where ten_giai_dau= '"+"BXH V-League (Mùa 2023/2024)"+"' and ngay= '"+ LocalDate
                            .now()+"'")
                    .mapToBean(BangxepHangAggregate.class).stream().collect(Collectors.toList());
        } );
    }
    public static List<BangxepHangAggregate> getDataL(){
        return JDBIConnector.getMartJdbi().withHandle(handle ->{
            // 4.Thực hiện truy vấn để lấy dữ liệu từ bảng aggregate cho La Liga và ngày hiện tại
            return handle.createQuery("SELECT * FROM aggregate where ten_giai_dau= '"+"BXH La Liga (Mùa 2023/2024)"+"' and ngay= '"+ LocalDate
                            .now()+"'")
                    .mapToBean(BangxepHangAggregate.class).stream().collect(Collectors.toList());
        } );
    }



    public  static  LocalDate getDate(){
        // Lấy ngày từ dữ liệu mart
        return LocalDate.parse(getData().get(0).getNgay());
    }

    public static void main(String[] args) {

        System.out.println(getData());
        System.out.println(getDate());
    }
}
