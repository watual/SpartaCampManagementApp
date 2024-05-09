package campManage.studentManagement;

import campManage.src.DBConfig;
import org.json.simple.JSONObject;

import java.util.Iterator;
import java.util.Scanner;


public class StudentManager {
    Scanner sc;

    //생성자
    public StudentManager() {
        sc = new Scanner(System.in);
    }

    //조회
    public void serStu() throws Exception {
        System.out.println("================수강생 조회===================");
        System.out.println("1. 전체 조회 2. ID 조회 3. 상태별 조회");
        switch (sc.nextLine()) {
            case "1" -> allStudent();
            case "2" -> selectStudent();
            case "3" -> stateStudent();
            default -> System.out.println("잘못입력!");
        }
    }

    private void stateStudent() {
        String state = "";
        loop:
        while (true) {
            System.out.println("1. Green  2. Yellow  3. Red");
            switch (Integer.parseInt(sc.nextLine())) {
                case 1 -> {
                    state = "Green";
                    break loop;
                }
                case 2 -> {
                    state = "Yellow";
                    break loop;
                }
                case 3 -> {
                    state = "Red";
                    break loop;
                }
                default -> System.out.println("올바르게 입력해주세요");
            }
        }
        String finalState = state;
        DBConfig.students.keySet().stream().filter(key -> {
            return ((JSONObject) DBConfig.students.get(key)).get("상태").equals(finalState);
        }).forEach(key -> {
            JSONObject student = (JSONObject) DBConfig.students.get(key);

            System.out.print("ID: " + key + "\t/ ");
            System.out.print("상태: " + student.get("상태") + "\t/ ");
            System.out.println(
                    "이름: " + student.get("이름") + "\t/ " +
                            "필수과목: " + ((JSONObject) student.get("필수과목")).keySet() + "\t/ " +
                            "선택과목: " + ((JSONObject) student.get("선택과목")).keySet() + "\t/ "
            );
        });
        if(DBConfig.students.keySet().stream().filter(key -> {
            return ((JSONObject) DBConfig.students.get(key)).get("상태").equals(finalState);
        }).toList().isEmpty()){
            System.out.println("현재 상태에 등록된 ID가 없습니다");
        }
    }

    // 전체조회
    public void allStudent() throws Exception {
        DBConfig.students.keySet().forEach(key -> {
            System.out.print("ID: " + key + "\t/ ");
            JSONObject student = (JSONObject) DBConfig.students.get(key);
            System.out.println(
                    "이름: " + student.get("이름") + "\t/ " +
                            "필수과목: " + ((JSONObject) student.get("필수과목")).keySet() + "\t/ " +
                            "선택과목: " + ((JSONObject) student.get("선택과목")).keySet() + "\t/ " +
                            "상태: " + student.get("상태") + "\t/ " + "점수등록여부: " +student.get("점수초기등록자")
            );
        });
    }

    //아이디조회
    public void selectStudent() throws Exception {
        System.out.print("id 입력 : ");
        String id = sc.nextLine();
        JSONObject getId = (JSONObject) DBConfig.students.get(id);
        getId.keySet().stream().sorted().forEach(e -> {
            System.out.println(e + " : " + getId.get(e));
        });
    }

    //안내문
    public void info() {
        System.out.println("====필수과목====");
        JSONObject subMandatory = (JSONObject) DBConfig.config.get("필수과목");
        subMandatory.keySet().stream().sorted().forEach(e -> {
            System.out.println(e + ". " + subMandatory.get(e));
        });
        System.out.println("최소 3개 입력 (0 입력시 입력종료)");
    }

    public void info2() {
        System.out.println("=====선택과목=====");
        JSONObject subMandatory2 = (JSONObject) DBConfig.config.get("선택과목");
        subMandatory2.keySet().stream().sorted().forEach(e -> {
            System.out.println(e + ". " + subMandatory2.get(e));
        });
        System.out.println("최소 2개 입력 (0 입력시 입력종료)");
    }

    //생성
    public void makeStu() throws Exception {
        JSONObject margeStudent = new JSONObject();
        JSONObject addStudent = new JSONObject();
        JSONObject addMainSubject = new JSONObject();
        JSONObject addSubSubject = new JSONObject();
        JSONObject addScore = new JSONObject();
        JSONObject studentFeel = (JSONObject) DBConfig.config.get("상태");
        JSONObject subMandatory = (JSONObject) DBConfig.config.get("필수과목");
        JSONObject subMandatory2 = (JSONObject) DBConfig.config.get("선택과목");
        boolean flag = true;
        System.out.println("========수강생 생성=========");
        while (flag) {
            JSONObject stuObj = new JSONObject();
            String id = DBConfig.config.get("id").toString();
            System.out.print("이름 입력 : ");
            String name = sc.nextLine();
            info();
            Loopout:
            while (true) {
                String must;
                loopout3:
                while (true) {
                    //must : 필수과목 이름
                    must = sc.nextLine();
                    if ((must.equals("0") || addMainSubject.size() >= 5) && addMainSubject.size() >= 3) break Loopout;
                    switch (must) {
                        case "1" -> {
                            must = (String) subMandatory.get("1");
                            break loopout3;
                        }
                        case "2" -> {
                            must = (String) subMandatory.get("2");
                            break loopout3;
                        }
                        case "3" -> {
                            must = (String) subMandatory.get("3");
                            break loopout3;
                        }
                        case "4" -> {
                            must = (String) subMandatory.get("4");
                            break loopout3;
                        }
                        case "5" -> {
                            must = (String) subMandatory.get("5");
                            break loopout3;
                        }
                        default -> System.out.println("올바르게 입력해주세요");
                    }
                }
                addScore.put("1", "0");
                addMainSubject.put(must, addScore);

                //필수과목 key값 입력 -> value(java)에 emptySubject달아서 임시 json 생성
            }
            info2();
            System.out.print("선택 과목 입력 : ");
            Loopout2:
            while (true) {
                String may = "";
                loopout3:
                while (true) {
                    may = sc.nextLine();
                    if ((may.equals("0") || addSubSubject.size() >= 4) && addSubSubject.size() >= 2) {
                        break Loopout2;
                    }
                    switch (may) {
                        case "1" -> {
                            may = (String) subMandatory2.get("1");
                            break loopout3;
                        }
                        case "2" -> {
                            may = (String) subMandatory2.get("2");
                            break loopout3;
                        }
                        case "3" -> {
                            may = (String) subMandatory2.get("3");
                            break loopout3;
                        }
                        case "4" -> {
                            may = (String) subMandatory2.get("4");
                            break loopout3;
                        }
                        default -> System.out.println("올바르게 입력해주세요");
                    }
                }
                addScore.put("1", "0");
                addSubSubject.put(may, addScore);
            }
            String addStuFeel = null;
            System.out.println("현재 상태 1. Red 2. Yellow 3. Green");
            loop:
            switch (sc.nextLine()) {
                case "1" -> {
                    addStuFeel = (String) studentFeel.get("1");
                    break loop;
                }
                case "2" -> {
                    addStuFeel = (String) studentFeel.get("2");
                    break loop;
                }
                case "3" -> {
                    addStuFeel = (String) studentFeel.get("3");
                    break loop;
                }
                default -> System.out.println("잘못된 입력!");
            }

            stuObj.put("점수초기등록자","1");
            stuObj.put("상태", addStuFeel);
            stuObj.put("선택과목", addSubSubject);
            stuObj.put("필수과목", addMainSubject);
            stuObj.put("이름", name);
            stuObj.put("id", id);
            addStudent.put(id, stuObj);
            //config의 고유번호 상승
            int newConfigID = 0;
            while (true) {
                if (DBConfig.students.containsKey(Integer.toString(newConfigID)) || Integer.toString(newConfigID).equals(id)) {
                    newConfigID++;
                } else {
                    break;
                }
            }
            DBConfig.config.put("id", Integer.toString(newConfigID));

            margeStudent.put("students", addStudent);   //student{1, 2, 3, 4}, config

            JSONObject[] objs = new JSONObject[]{DBConfig.students, addStudent};
            for (JSONObject object : objs) {
                Iterator it = object.keySet().iterator();
                while (it.hasNext()) {
                    String key = (String) it.next();
                    DBConfig.students.put(key, object.get(key));
                }
            }

            inquiryJson(stuObj);
//            System.out.println("ID: " + stuObj.get("id") +
//                    "\t/ 이름: " + stuObj.get("이름") +
//                    "\t/ 필수과목: " + stuObj.get("필수과목") +
//                    "\t/ 선택과목: " + stuObj.get("선택과목") +
//                    "\t/ 상태: " + stuObj.get("상태")
//            );
            System.out.println("생성완료");
            System.out.println("더 추가 하시겠습니까? 1. 예 2. 아니요");
            if (sc.nextLine().equals("2")) {
                break;
            }
        }
        DBConfig.updateDatabase();  //test.json 파일을 변경 -> Reader로 가져오기 전에 최신화
    }

    //삭제
    public void delStu() throws Exception {

        System.out.println("================수강생 삭제===================");
        String id;
        inquiryAll();
        while (true) {
            System.out.print("삭제할 수강생 ID 입력 : ");
            id = sc.nextLine();
            if (DBConfig.students.containsKey(id)) {
                DBConfig.students.remove(id);
                break;
            } else {
                System.out.println("잘못입력하셨습니다!");
            }
        }

        System.out.println("삭제 완료");
        DBConfig.config.put("id", id);
        DBConfig.updateDatabase();
    }

    //수정
    public void reStu() throws Exception {
        JSONObject resetSubject;
        JSONObject subject1 = new JSONObject();
        JSONObject originSubject;
        JSONObject studentFeel = (JSONObject) DBConfig.config.get("상태");
        JSONObject studentMainSub = (JSONObject) DBConfig.config.get("필수과목");
        JSONObject studentSubSub = (JSONObject) DBConfig.config.get("선택과목");
        System.out.println("================수강생 수정===================");
        inquiryAll();
        System.out.println("수정할 수강생 ID 입력");
        String id = sc.nextLine();
        resetSubject = (JSONObject) DBConfig.students.get(id);
        System.out.println("1. 필수과목 수정 2. 선택과목 수정 3. 상태수정 4. 이름수정");
        switch (sc.nextLine()) {

            case "1":
                String mainRemove;
                String mainNew;
                JSONObject newSubjectNum = new JSONObject();
                System.out.println("변경할 보유과목 선택 1. Java 2. 객체지향 3. Spring, 4. JPA 5. MySQL");
                loop:
                while (true) {
                    switch (sc.nextLine()) {
                        case "1" -> {
                            mainRemove = (String) studentMainSub.get("1");
                            break loop;
                        }
                        case "2" -> {
                            mainRemove = (String) studentMainSub.get("2");
                            break loop;
                        }
                        case "3" -> {
                            mainRemove = (String) studentMainSub.get("3");
                            break loop;
                        }
                        case "4" -> {
                            mainRemove = (String) studentMainSub.get("4");
                            break loop;
                        }
                        case "5" -> {
                            mainRemove = (String) studentMainSub.get("5");
                            break loop;
                        }
                        default -> System.out.println("올바른 입력을 해주세요");
                    }
                }
                originSubject = (JSONObject) resetSubject.get("필수과목");
                originSubject.remove(mainRemove);
                System.out.println("변경할 목표과목 선택 1. Java 2. 객체지향 3. Spring, 4. JPA 5. MySQL");
                loop:
                while (true) {
                    switch (sc.nextLine()) {
                        case "1" -> {
                            mainNew = (String) studentMainSub.get("1");
                            break loop;
                        }
                        case "2" -> {
                            mainNew = (String) studentMainSub.get("2");
                            break loop;
                        }
                        case "3" -> {
                            mainNew = (String) studentMainSub.get("3");
                            break loop;
                        }
                        case "4" -> {
                            mainNew = (String) studentMainSub.get("4");
                            break loop;
                        }
                        case "5" -> {
                            mainNew = (String) studentMainSub.get("5");
                            break loop;
                        }
                        default -> System.out.println("올바른 입력을 해주세요");
                    }
                }
                newSubjectNum.put("1", "0");
                subject1.put(mainNew, newSubjectNum);

                JSONObject subjectMerge = new JSONObject();
                JSONObject[] objs = new JSONObject[]{subject1, originSubject};
                for (JSONObject object : objs) {
                    Iterator it = object.keySet().iterator();
                    while (it.hasNext()) {
                        String key = (String) it.next();
                        subjectMerge.put(key, object.get(key));
                    }
                }
                resetSubject.put("필수과목", subjectMerge);

                break;
            case "2":
                String subRemove = null;
                String subNew;
                JSONObject newSubjectNum2 = new JSONObject();
                System.out.println("변경할 보유과목 선택 1.디자인 패턴 2. Spring Security 3. Redis, 4. MongoDB");
                loop:
                while (true) {
                    switch (sc.nextLine()) {
                        case "1" -> {
                            subRemove = (String) studentSubSub.get("1");
                            break loop;
                        }
                        case "2" -> {
                            subRemove = (String) studentSubSub.get("2");
                            break loop;
                        }
                        case "3" -> {
                            subRemove = (String) studentSubSub.get("3");
                            break loop;
                        }
                        case "4" -> {
                            subRemove = (String) studentSubSub.get("4");
                            break loop;
                        }
                        default -> System.out.println("올바른 번호를 입력해주세요");
                    }
                }
                originSubject = (JSONObject) resetSubject.get("선택과목");
                originSubject.remove(subRemove);
                System.out.println("변경할 목표과목 선택 1.디자인 패턴 2. Spring Security 3. Redis, 4. MongoDB");
                loop:
                while (true) {
                    switch (sc.nextLine()) {
                        case "1" -> {
                            subNew = (String) studentSubSub.get("1");
                            break loop;
                        }
                        case "2" -> {
                            subNew = (String) studentSubSub.get("2");
                            break loop;
                        }
                        case "3" -> {
                            subNew = (String) studentSubSub.get("3");
                            break loop;
                        }
                        case "4" -> {
                            subNew = (String) studentSubSub.get("4");
                            break loop;
                        }
                        default -> System.out.println("올바른 번호를 입력해주세요");
                    }
                }
                newSubjectNum2.put("1", "0");
                subject1.put(subNew, newSubjectNum2);
                JSONObject subjectMerge2 = new JSONObject();
                JSONObject[] objs2 = new JSONObject[]{subject1, originSubject};
                for (JSONObject object : objs2) {
                    Iterator it = object.keySet().iterator();
                    while (it.hasNext()) {
                        String key = (String) it.next();
                        subjectMerge2.put(key, object.get(key));
                    }
                }
                resetSubject.put("선택과목", subjectMerge2);

                break;
            case "3":
                String newFeel = "";
                resetSubject.remove("상태");
                System.out.println("변경할 상태 입력 1. Red, 2. Yellow, 3. Green");
//                String newFeel = sc.nextLine();
                loop:
                switch (sc.nextLine()) {
                    case "1" -> {
                        newFeel = (String) studentFeel.get("1");
                        break loop;
                    }
                    case "2" -> {
                        newFeel = (String) studentFeel.get("2");
                        break loop;
                    }
                    case "3" -> {
                        newFeel = (String) studentFeel.get("3");
                        break loop;
                    }
                    default -> System.out.println("잘못된 입력입니다");
                }
                resetSubject.put("상태", newFeel);
                break;
            case "4":
                String newName;
                resetSubject.remove("이름");
                System.out.println("변경할 이름 입력");
                newName = sc.nextLine();
                resetSubject.put("이름", newName);
                break;
        }
        System.out.println(resetSubject);
        DBConfig.students.put(id, resetSubject);
        System.out.println(DBConfig.students);
        System.out.println("수정 완료!");
        DBConfig.updateDatabase();

    }

    public void inquiryAll() {
        DBConfig.students.keySet().forEach(key -> {
            System.out.print("ID: " + key + "\t/ ");
            JSONObject student = (JSONObject) DBConfig.students.get(key);
            System.out.println(
                    "이름: " + student.get("이름") + "\t/ " +
                            "필수과목: " + ((JSONObject) student.get("필수과목")).keySet() + "\t/ " +
                            "선택과목: " + ((JSONObject) student.get("선택과목")).keySet() + "\t/ " +
                            "상태: " + student.get("상태") + "\t/ "
            );
        });
    }

    public void inquiryJson(JSONObject jsonObject) {
        //JSON 값 출력
        System.out.println(
                "이름: " + jsonObject.get("이름") + "\t/ " +
                        "필수과목: " + ((JSONObject) jsonObject.get("필수과목")).keySet() + "\t/ " +
                        "선택과목: " + ((JSONObject) jsonObject.get("선택과목")).keySet() + "\t/ " +
                        "상태: " + jsonObject.get("상태")
        );
    }
}