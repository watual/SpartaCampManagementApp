package scoreManagement;

import java.util.*;

public class ScoreManagement_CYJ {
//현재 절차지향으로 러프하게 짜보고 있습니다.
    //그래서 당연하게 클래스랑 메서드는 안나눠놨고, 나중에 한번에 바꿀 생각입니다.

    //다른 파일에서 테스트로 작업중이라 subject, score, student 클래스는 따로 이 프로젝트 파일에서 변경하지 않았습니다.
    //그래서 빨간줄 떠서 주석처리 해놨습니다.

    //현재까지 학생관리쪽은 대충 끝났습니다. (subjects set 원소를 Subject 객체로 만들어서 바꾸면 학생관리쪽은 마무리 됩니다.)
    //주말까지 컬렉션 작업 버전은 완성해둘거고, 다음주 월요일부터 데이터베이스를 활용한 구현 들어가보려 합니다.

    //저는 데이터베이스 다뤄본적이 없어서 아직 많이 미흡합니다.
    //그래도 포기안하고 최대한 할 수 있는 만큼 노력해보겠습니다
    //많이 부족하더라도 넓은 아량으로 이해해주시면 감사하겠습니다 저도 많이 노력하겠습니다!

//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        Map<Integer, Student> studentsMap = new HashMap<>();
//        Set<String> subjects = new HashSet<>();
//
//        String SUBJECT_TYPE_MANDATORY = "MANDATORY";
//        String SUBJECT_TYPE_CHOICE = "CHOICE";
//
//        // index 관리 필드
//        int studentIndex;
//        String INDEX_TYPE_STUDENT = "ST";
//        int subjectIndex;
//        String INDEX_TYPE_SUBJECT = "SU";
//        int scoreIndex;
//        String INDEX_TYPE_SCORE = "SC";
//
//
//        boolean mainWhileFlag = true;
//        while (mainWhileFlag) {
//            System.out.println("\n==================================");
//            System.out.println("내일배움캠프 수강생 관리 프로그램 실행 중...");
//            System.out.println("1. 수강생 관리");
//            System.out.println("2. 점수 관리");
//            System.out.println("3. 프로그램 종료");
//            System.out.print("관리 항목을 선택하세요...");
//            int input = sc.nextInt();
//
//            switch (input) {
//                case 1 :
//                    boolean case1Flag = true;
//
//                    while (case1Flag) {
//                        System.out.println("==================================");
//                        System.out.println("수강생 관리 실행 중...");
//                        System.out.println("1. 수강생 등록");
//                        System.out.println("2. 수강생 목록 조회");
//
//                        int studentProgram = sc.nextInt();
//
//                        switch (studentProgram) {
//                            case 1:
//                                System.out.println("수강생 고유 번호를 입력하세요");
//                                int studentId = sc.nextInt();
//                                sc.nextLine();
//
//                                System.out.println("수강생 이름을 입력하세요");
//                                String studentName = sc.nextLine();
//
//                                System.out.println("수강 과목들을 입력하세요 (exit 입력시 종료)");
//
//                                boolean addingSubjects = true;
//
//                                while (addingSubjects) {
//                                    String inputTxt = sc.nextLine();
//                                    if (inputTxt.equals("exit")) {
//                                        addingSubjects = false;
//                                    } else {
//                                        subjects.add(inputTxt);
//                                    }
//                                }
//
//                                Student student = new Student(studentId, studentName, subjects);
//                                studentsMap.put(studentId, student);
//                                break;
//
//                            case 2:
//                                System.out.println("수강생 목록을 조회합니다.");
//                                for (Student s : studentsMap.values()) {
//                                    System.out.println("학생 ID: " + s.getStudentId() +
//                                            ", 이름: " + s.getStudentName() +
//                                            ", 수강 과목: " + s.getSubjects());
//                                }
//                                break;
//                        }
//                        case1Flag = false;
//                    }
//
//                case 2 :
//                    boolean case2flag = true;
//                    while (case2flag) {
//                        System.out.println("==================================");
//                        System.out.println("점수 관리 실행 중...");
//                        System.out.println("1. 수강생의 과목별 시험 회차 및 점수 등록");
//                        System.out.println("2. 수강생의 과목별 회차 점수 수정");
//                        System.out.println("3. 수강생의 특정 과목 회차별 등급 조회");
//                        System.out.println("4. 메인 화면 이동");
//                        System.out.print("관리 항목을 선택하세요...");
//                        int scoreProgram = sc.nextInt();
//
//                        switch (scoreProgram) {
//                            case 1:
//                                System.out.println("수강생 고유 번호를 입력하세요.");
//                                int studentId = sc.nextInt();
//                                Student foundStudent = studentsMap.get(studentId);
//                                if (foundStudent != null) {
//                                    System.out.println("수강생 이름: " + foundStudent.getStudentName());
//                                    System.out.println("해당 수강생이 수강하는 과목 목록: " + foundStudent.getSubjects());
//                                    System.out.println("점수를 등록할 과목을 선택하세요.");
//
//                                } else {
//                                    System.out.println("해당 고유 번호의 수강생을 찾을 수 없습니다.");
//                                }
//                                break;
//                            case 2 :
//                                System.out.println(""); // 수강생의 과목별 회차 점수 수정
//                                break;
//                            case 3 :
//                                System.out.println(""); // 수강생의 특정 과목 회차별 등급 조회
//                                break;
//                            case 4 :
//                                case2flag = false; // 메인 화면 이동
//                            default :
//                                System.out.println("잘못된 입력입니다.\n메인 화면 이동...");
//
//                        }
//                    }
//                case 3 :
//                    mainWhileFlag = false; // 프로그램 종료
//                default :
//                    System.out.println("잘못된 입력입니다.\n되돌아갑니다!");
//
//            }
//        }
//        System.out.println("프로그램을 종료합니다.");
//    }
//}
}