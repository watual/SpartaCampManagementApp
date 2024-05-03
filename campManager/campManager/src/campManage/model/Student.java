package campManage.model;

public class Student extends management{
   private String ID;
   private String name;

    //생성자
    public Student(String ID, String name) {
        this.ID = ID;
        this.name = name;
    }
}

