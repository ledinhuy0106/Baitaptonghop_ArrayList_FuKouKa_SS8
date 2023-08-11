package src;

import java.util.List;

import static src.StudentManagement.scanner;

public class Student implements IStudent<Student> {
    private String studentId;
    private String studentName;
    private int age;
    private float markHtml;
    private float markCss;
    private float markJavascript;
    private boolean isMale;
    private String studentRank;
    private String studentStatus;

    public Student() {
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentCode) {
        this.studentId = studentCode;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public float getMarkHtml() {
        return markHtml;
    }

    public void setMarkHtml(float markHtml) {
        this.markHtml = markHtml;
    }

    public float getMarkCss() {
        return markCss;
    }

    public void setMarkCss(float markCss) {
        this.markCss = markCss;
    }

    public float getMarkJavascript() {
        return markJavascript;
    }

    public void setMarkJavascript(float markJavascript) {
        this.markJavascript = markJavascript;
    }

    public boolean isMale() {
        return isMale;
    }

    public void setMale(boolean male) {
        isMale = male;
    }

    public void setStudentRank(String studentRank) {
        this.studentRank = studentRank;
    }

    public void setStudentStatus(String studentStatus) {
        this.studentStatus = studentStatus;
    }

    public Student(String studentId, String studentName, int age, float markHtml, float markCss, float markJavascript, boolean isMale) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.age = age;
        this.markHtml = markHtml;
        this.markCss = markCss;
        this.markJavascript = markJavascript;
        this.isMale = isMale;
        this.studentRank = "";
        this.studentStatus = "";
    }

    @Override
    public void inputData(List<Student> studentList) {
        System.out.print("Nhập mã sinh viên: ");
        studentId = scanner.nextLine();
        while (isExistIdStudent(studentId,studentList) && !studentId.startsWith("SV") && studentId.length() != 4) {
            System.out.println("Mã sinh viên phải gồm 4 ký tự và bắt đầu bằng SV hoặc mã sinh viên đã tồn tại. Vui lòng nhập lại.");
            System.out.print("Nhập mã sinh viên: ");
            studentId = scanner.nextLine();
        }
        System.out.print("Nhập tên sinh viên: ");
        studentName = scanner.nextLine();

        while (!checkLength(studentName)) {
            System.out.println("Tên sinh viên phải từ 6-50 ký tự");
            System.out.print("Nhập tên sinh viên: ");
            studentName = scanner.nextLine();
        }
        System.out.print("Nhập tuổi: ");
        age = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Nhập điểm HTML: ");
        markHtml = scanner.nextFloat();
        scanner.nextLine();

        System.out.print("Nhập điểm CSS: ");
        markCss = scanner.nextFloat();
        scanner.nextLine();

        System.out.print("Nhập điểm JavaScript: ");
        markJavascript = scanner.nextFloat();
        scanner.nextLine();

        System.out.print("Sinh viên là nam? (true/false): ");
        isMale = scanner.nextBoolean();
        scanner.nextLine();
    }

    @Override
    public void displayData() {
        System.out.println("Mã SV: " + studentId);
        System.out.println("Tên SV: " + studentName);
        System.out.println("Tuổi: " + age);
        System.out.println("Điểm HTML: " + markHtml);
        System.out.println("Điểm CSS: " + markCss);
        System.out.println("Điểm JavaScript: " + markJavascript);
        System.out.println("Giới tính: " + (isMale ? "Nam" : "Nữ"));
        System.out.println("Xếp loại: " + studentRank);
        System.out.println("Trạng thái: " + studentStatus);
    }

    @Override
    public float calAvgMark() {
        return (markHtml + markCss + markJavascript) / 3;
    }
    public void calculateStudentRank() {
        float avgMark = calAvgMark();
        if (avgMark >= 0 && avgMark < 5) {
            studentRank = "Yếu";
        } else if (avgMark < 7) {
            studentRank = "Trung bình";
        } else if (avgMark < 8) {
            studentRank = "Khá";
        } else if (avgMark < 9) {
            studentRank = "Giỏi";
        } else if (avgMark <= 10) {
            studentRank = "Xuất sắc";
        }
    }

    public void calculateStudentStatus() {
        float avgMark = calAvgMark();
        if (avgMark >= MARK_PASS) {
            studentStatus = "PASS";
        } else {
            studentStatus = "FAIL";
        }
    }

    public String getStudentRank() {
        return studentRank;
    }

    public String getStudentStatus() {
        return studentStatus;
    }

    public String getStudentName() {
        return studentName;
    }

    boolean isExistIdStudent(String studentId,List<Student> studentList) {
        for (Student studentLists : studentList) {
            if (studentLists.studentId.equals(studentId)) {
                return true;
            }
        }
        return false;
    }

    boolean checkLength(String studentName) {
        return studentName.length() >= 6 && studentName.length() <= 50;
    }
}
