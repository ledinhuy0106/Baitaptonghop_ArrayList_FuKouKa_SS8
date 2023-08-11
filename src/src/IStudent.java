package src;

import java.util.List;

public interface IStudent<U> {
    int MARK_PASS = 5;

    void inputData(List<U> studentList);
    void displayData();
    float calAvgMark();
}
