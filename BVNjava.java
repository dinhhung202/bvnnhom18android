import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class SinhVien {
    private String firstName;
    private String lastName;
    private String birthDate;
    private String address;
    private String className;
    private double diemLTHDT;
    private double diemQLDA;
    private double diemHM; 
    private double diemCSDL;
    private double diemLTUCD; 

    public SinhVien(String firstName, String lastName, String birthDate, String address, String className,
                    double diemLTHDT, double diemQLDA, double diemHM, double diemCSDL, double diemLTUCD) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.address = address;
        this.className = className;
        this.diemLTHDT = diemLTHDT;
        this.diemQLDA = diemQLDA;
        this.diemHM = diemHM;
        this.diemCSDL = diemCSDL;
        this.diemLTUCD = diemLTUCD;
    }

    public double tinhDTB() {
        return (diemLTHDT + diemQLDA + diemHM + diemCSDL + diemLTUCD) / 5;
    }

    public String getRank() {
        double dtb = tinhDTB();
        if (dtb >= 8.5) return "A";
        else if (dtb >= 7.0) return "B";
        else if (dtb >= 5.5) return "C";
        else if (dtb >= 4.0) return "D";
        else return "<D";
    }

    @Override
    public String toString() {
        return firstName + " " + lastName + " - DTB: " + String.format("%.2f", tinhDTB()) + " - Rank: " + getRank();
    }
}

class LopHoc {
    private String maLop;
    private String tenLop;
    private ArrayList<SinhVien> danhSachSinhVien;

    public LopHoc(String maLop, String tenLop) {
        this.maLop = maLop;
        this.tenLop = tenLop;
        danhSachSinhVien = new ArrayList<>();
    }

    public void themSinhVien(SinhVien sv) {
        danhSachSinhVien.add(sv);
    }

    public ArrayList<SinhVien> getDanhSachSinhVien() {
        return danhSachSinhVien;
    }

    public void xuatDanhSachSinhVien() {
        System.out.println("Danh sách sinh viên của lớp " + tenLop + ":");
        for (SinhVien sv : danhSachSinhVien) {
            System.out.println(sv);
        }
    }

    public Map<String, Integer> demSoLuongTheoRank() {
        Map<String, Integer> map = new HashMap<>();
        map.put("A", 0);
        map.put("B", 0);
        map.put("C", 0);
        map.put("D", 0);
        map.put("<D", 0);
        for (SinhVien sv : danhSachSinhVien) {
            String rank = sv.getRank();
            map.put(rank, map.get(rank) + 1);
        }
        return map;
    }

    public void hienThiTongKet() {
        Map<String, Integer> tongKet = demSoLuongTheoRank();
        System.out.println("Tổng kết xếp loại của lớp " + tenLop + ":");
        System.out.println("Số lượng sinh viên loại A: " + tongKet.get("A"));
        System.out.println("Số lượng sinh viên loại B: " + tongKet.get("B"));
        System.out.println("Số lượng sinh viên loại C: " + tongKet.get("C"));
        System.out.println("Số lượng sinh viên loại D: " + tongKet.get("D"));
        System.out.println("Số lượng sinh viên loại <D: " + tongKet.get("<D"));
    }

    public String getMaLop() {
        return maLop;
    }

    public String getTenLop() {
        return tenLop;
    }
}

public class QuanLyLopHocCNTT {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<LopHoc> danhSachLop = new ArrayList<>();

        LopHoc lop1 = new LopHoc("CNTT01", "Lớp CNTT 1");
        LopHoc lop2 = new LopHoc("CNTT02", "Lớp CNTT 2");

        lop1.themSinhVien(new SinhVien("Nguyen", "Van An", "12/07/2000", "Ha Noi", "CNTT01", 9, 8, 7, 8, 9));
        lop1.themSinhVien(new SinhVien("Tran", "Thi Bich", "04/02/2000", "Hai Phong", "CNTT01", 6, 7, 8, 5, 6));

        lop2.themSinhVien(new SinhVien("Le", "Van Chi", "07/09/2000", "Da Nang", "CNTT02", 8, 7, 7, 8, 8));
        lop2.themSinhVien(new SinhVien("Pham", "Thi hoai", "24/02/2000", "Ho Chi Minh", "CNTT02", 4, 5, 3, 4, 5));

        danhSachLop.add(lop1);
        danhSachLop.add(lop2);

        System.out.println("Danh sách các lớp:");
        for (LopHoc lop : danhSachLop) {
            System.out.println(lop.getMaLop() + " - " + lop.getTenLop());
        }

        System.out.print("Nhập mã lớp để xem chi tiết: ");
        String maLopChon = scanner.nextLine();

        boolean timThay = false;
        for (LopHoc lop : danhSachLop) {
            if (lop.getMaLop().equalsIgnoreCase(maLopChon)) {
                timThay = true;
                lop.xuatDanhSachSinhVien();
                lop.hienThiTongKet();
                break;
            }
        }
        if (!timThay) {
            System.out.println("Không tìm thấy lớp có mã: " + maLopChon);
        }
    }
}
