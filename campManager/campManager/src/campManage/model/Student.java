package campManage.model;

import java.util.Set;

public class Student {
    private int studentId;
    private String studentName;
    private Set<String> subjects;

    public Student(int studentId, String studentName, Set<String> subjects) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.subjects = subjects;
    }

    public int getStudentId() {
        return studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public Set<String> getSubjects() {
        return subjects;
    }
}
