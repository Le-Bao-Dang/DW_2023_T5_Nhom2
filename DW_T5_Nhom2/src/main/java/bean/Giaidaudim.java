package bean;

public class Giaidaudim {
    private int id;
    private String tenGiaiDau;

    public Giaidaudim() {
    }

    public Giaidaudim(int id, String tenGiaiDau) {
        this.id = id;
        this.tenGiaiDau = tenGiaiDau;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenGiaiDau() {
        return tenGiaiDau;
    }

    public void setTenGiaiDau(String tenGiaiDau) {
        this.tenGiaiDau = tenGiaiDau;
    }

    @Override
    public String toString() {
        return "Giaidaudim{" +
                "id=" + id +
                ", tenGiaiDau='" + tenGiaiDau + '\'' +
                '}';
    }
}
