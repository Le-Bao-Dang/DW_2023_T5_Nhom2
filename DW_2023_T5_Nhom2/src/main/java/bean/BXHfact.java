package bean;

public class BXHfact {
    private int id;
    private Doibongdim id_doi_bong;
    private Giaidaudim id_giai_dau;
    private Thoigiandim id_thoi_gian;

    public BXHfact() {
    }

    public BXHfact(int id, Doibongdim id_doi_bong, Giaidaudim id_giai_dau, Thoigiandim id_thoi_gian) {
        this.id = id;
        this.id_doi_bong = id_doi_bong;
        this.id_giai_dau = id_giai_dau;
        this.id_thoi_gian = id_thoi_gian;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Doibongdim getId_doi_bong() {
        return id_doi_bong;
    }

    public void setId_doi_bong(Doibongdim id_doi_bong) {
        this.id_doi_bong = id_doi_bong;
    }

    public Giaidaudim getId_giai_dau() {
        return id_giai_dau;
    }

    public void setId_giai_dau(Giaidaudim id_giai_dau) {
        this.id_giai_dau = id_giai_dau;
    }

    public Thoigiandim getId_thoi_gian() {
        return id_thoi_gian;
    }

    public void setId_thoi_gian(Thoigiandim id_thoi_gian) {
        this.id_thoi_gian = id_thoi_gian;
    }

    @Override
    public String toString() {
        return "BXHfact{" +
                "id=" + id +
                ", id_doi_bong=" + id_doi_bong +
                ", id_giai_dau=" + id_giai_dau +
                ", id_thoi_gian=" + id_thoi_gian +
                '}';
    }
}
