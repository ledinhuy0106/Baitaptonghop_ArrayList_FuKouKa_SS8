package src;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class StudentManagement {
     static final List<Student> studentList = new ArrayList<>();
    public static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("***************************MENU*************************");
            System.out.println("1. Nhập thông tin n sinh viên (n nhập từ bàn phím)");
            System.out.println("2. Tính điểm trung bình tất cả sinh viên");
            System.out.println("3. Đánh giá xếp loại các sinh viên");
            System.out.println("4. Tính trạng thái của sinh viên");
            System.out.println("5. In thông tin các sinh viên");
            System.out.println("6. Sắp xếp sinh viên tăng dần theo điểm trung bình");
            System.out.println("7. Tìm kiếm sinh viên theo tên sinh viên");
            System.out.println("8. Thống kê sinh viên theo xếp loại");
            System.out.println("9. Thống kê sinh viên theo trạng thái");
            System.out.println("10. Thoát");
            System.out.print("Nhập lựa chọn của bạn: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Đọc dòng trống sau nextInt()

            switch (choice) {
                case 1 -> inputStudents();
                case 2 -> calculateAverageMarks();
                case 3 -> evaluateClassifications();
                case 4 -> calculateStatuses();
                case 5 -> displayAllStudents();
                case 6 -> sortStudentsByAvgMark();
                case 7 -> searchStudentsByName();
                case 8 -> groupStudentsByClassification();
                case 9 -> groupStudentsByStatus();
                case 10 -> System.out.println("Chương trình kết thúc.");
                default -> System.out.println("Lựa chọn không hợp lệ.");
            }
        } while (choice != 10);
    }

    private static void inputStudents() {
        System.out.print("Nhập số lượng sinh viên: ");
        int n = scanner.nextInt();
        scanner.nextLine(); // Đọc dòng trống sau nextInt()

        for (int i = 0; i < n; i++) {
            System.out.println("Nhập thông tin sinh viên thứ " + (i + 1));
            Student student = new Student();
            student.inputData(studentList);
            studentList.add(student);
            displayAllStudents();
        }
    }

    private static void calculateAverageMarks() {
        for (Student student : studentList) {
            System.out.println("Điểm trung bình của " + student.getStudentName() + ": " + student.calAvgMark());
        }
    }

    private static void evaluateClassifications() {
        for (Student student : studentList) {
            student.calculateStudentRank();
            System.out.println("Xếp loại của " + student.getStudentName() + ": " + student.getStudentRank());
        }
    }

    private static void calculateStatuses() {
        for (Student student : studentList) {
            student.calculateStudentStatus();
            System.out.println("Trạng thái của " + student.getStudentName() + ": " + student.getStudentStatus());
        }
    }

    private static void displayAllStudents() {
        for (Student student : studentList) {
            student.displayData();
            System.out.println("---------------------------------------");
        }
    }

    private static void sortStudentsByAvgMark() {
        studentList.sort(new Comparator<Student>() {
            @Override
            public int compare(Student student1, Student student2) {
                return Float.compare(student1.calAvgMark(), student2.calAvgMark());
            }
        });

        System.out.println("Danh sách sinh viên sắp xếp theo điểm trung bình:");
        for (Student student : studentList) {
            student.displayData();
            System.out.println("---------------------------------------");
        }
    }

    private static void searchStudentsByName() {
        System.out.print("Nhập tên sinh viên cần tìm: ");
        String searchName = scanner.nextLine();

        List<Student> matchedStudents = new ArrayList<>();
        for (Student student : studentList) {
            if (student.getStudentName().equalsIgnoreCase(searchName)) {
                matchedStudents.add(student);
            }
        }

        if (!matchedStudents.isEmpty()) {
            System.out.println("Danh sách sinh viên có tên '" + searchName + "':");
            for (Student student : matchedStudents) {
                student.displayData();
                System.out.println("---------------------------------------");
            }
        } else {
            System.out.println("Không tìm thấy sinh viên có tên '" + searchName + "'.");
        }
    }

    private static void groupStudentsByClassification() {
        List<String> classifications = new ArrayList<>();

        for (Student student : studentList) {
            if (!classifications.contains(student.getStudentRank())) {
                classifications.add(student.getStudentRank());
            }
        }

        System.out.println("Thống kê sinh viên theo xếp loại:");
        for (String classification : classifications) {
            int count = 0;
            for (Student student : studentList) {
                if (student.getStudentRank().equals(classification)) {
                    count++;
                }
            }
            System.out.println(classification + ": " + count + " sinh viên");
        }
    }

    private static void groupStudentsByStatus() {
        List<String> statuses = new ArrayList<>();

        for (Student student : studentList) {
            if (!statuses.contains(student.getStudentStatus())) {
                statuses.add(student.getStudentStatus());
            }
        }

        System.out.println("Thống kê sinh viên theo trạng thái:");
        for (String status : statuses) {
            int count = 0;
            for (Student student : studentList) {
                if (student.getStudentStatus().equals(status)) {
                    count++;
                }
            }
            System.out.println(status + ": " + count + " sinh viên");
        }
    }
}
