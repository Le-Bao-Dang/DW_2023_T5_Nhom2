package bean;

import java.io.Serializable;
import java.time.LocalDate;

public class BangxepHangAggregate implements Serializable {
    int id;
    int hang;
    String logo;
    String ten_doi_bong;
    int so_tran;
    int tran_thang;
    int tran_hoa;
    int tran_thua;
    int he_so;
    int diem;
    String nam_tran_gan_nhat;
    String ten_giai_dau;
    String ngay;


    public BangxepHangAggregate() {

    }

    public BangxepHangAggregate(int id, int hang, String logo, String ten_doi_bong, int so_tran, int tran_thang, int tran_hoa, int tran_thua, int he_so, int diem, String nam_tran_gan_nhat, String ten_giai_dau, String ngay) {
        this.id = id;
        this.hang = hang;
        this.logo = logo;
        this.ten_doi_bong = ten_doi_bong;
        this.so_tran = so_tran;
        this.tran_thang = tran_thang;
        this.tran_hoa = tran_hoa;
        this.tran_thua = tran_thua;
        this.he_so = he_so;
        this.diem = diem;
        this.nam_tran_gan_nhat = nam_tran_gan_nhat;
        this.ten_giai_dau = ten_giai_dau;
        this.ngay = ngay;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHang() {
        return hang;
    }

    public void setHang(int hang) {
        this.hang = hang;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getTen_doi_bong() {
        return ten_doi_bong;
    }

    public void setTen_doi_bong(String ten_doi_bong) {
        this.ten_doi_bong = ten_doi_bong;
    }

    public int getSo_tran() {
        return so_tran;
    }

    public void setSo_tran(int so_tran) {
        this.so_tran = so_tran;
    }

    public int getTran_thang() {
        return tran_thang;
    }

    public void setTran_thang(int tran_thang) {
        this.tran_thang = tran_thang;
    }

    public int getTran_hoa() {
        return tran_hoa;
    }

    public void setTran_hoa(int tran_hoa) {
        this.tran_hoa = tran_hoa;
    }

    public int getTran_thua() {
        return tran_thua;
    }

    public void setTran_thua(int tran_thua) {
        this.tran_thua = tran_thua;
    }

    public int getHe_so() {
        return he_so;
    }

    public void setHe_so(int he_so) {
        this.he_so = he_so;
    }

    public int getDiem() {
        return diem;
    }

    public void setDiem(int diem) {
        this.diem = diem;
    }

    public String getNam_tran_gan_nhat() {
        return nam_tran_gan_nhat;
    }

    public void setNam_tran_gan_nhat(String nam_tran_gan_nhat) {
        this.nam_tran_gan_nhat = nam_tran_gan_nhat;
    }

    public String getTen_giai_dau() {
        return ten_giai_dau;
    }

    public void setTen_giai_dau(String ten_giai_dau) {
        this.ten_giai_dau = ten_giai_dau;
    }
    public String getNgay() {
        return ngay;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }


    @Override
    public String toString() {
        return "BangxepHangAggregate{" +
                "id=" + id +
                ", hang=" + hang +
                ", logo='" + logo + '\'' +
                ", ten_doi_bong='" + ten_doi_bong + '\'' +
                ", so_tran=" + so_tran +
                ", tran_thang=" + tran_thang +
                ", tran_hoa=" + tran_hoa +
                ", tran_thua=" + tran_thua +
                ", he_so=" + he_so +
                ", diem=" + diem +
                ", nam_tran_gan_nhat='" + nam_tran_gan_nhat + '\'' +
                ", ten_giai_dau='" + ten_giai_dau + '\'' +
                ", ngay=" + ngay +
                '}';
    }
}
