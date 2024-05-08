package campManage.scoreManagement;

import campManage.src.DBConfig;

import java.io.FileReader;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;
import org.w3c.dom.ls.LSOutput;


import java.io.*;
import java.util.Iterator;
import java.util.Scanner;

public class ScoreMethod_CYJ {

    static Scanner scanner;

    public ScoreMethod_CYJ() {
        scanner = new Scanner(System.in);
    }

    //생성하기
    public static void crateScore() throws IOException, ParseException {
        while (true) {
            System.out.println("========== test ==========");
            System.out.println("새로운 학생 정보를 입력하세요 [ 종료하려면 'exit' 입력 ]");

            // 고유 번호 입력 받기
            System.out.print("고유 번호를 입력하세요: ");
            String studentId = scanner.nextLine().trim();   //학생 고유번호
            if (studentId.equalsIgnoreCase("exit") || studentId.equalsIgnoreCase("e")) {
                break; // 종료 조건
            }

            //학생이 등록한 과목 목록을 출력해주고 번호를 입력받음
            JSONObject studentInfo = (JSONObject) DBConfig.students.get(studentId);

            // 필수과목 입력 받기
            //requiredSubjects : 필수과목
            JSONObject requiredSubjects = (JSONObject) studentInfo.get("필수과목");
            System.out.println(requiredSubjects.keySet()); //필수과목 key값들 : 과목들

            //학생이 수강하는 과목만 점수 입력 받도록 하기
            //안듣는 회차는 ""처리됨
            //비어있는데이터 만들어줌 -> 아예 데이터 비워져 1회차 점수만 다 받구요 (메서드)
            //선택
            System.out.println("필수과목의 점수를 입력해주세요.");
            requiredSubjects.keySet().stream().forEach(e -> {
                System.out.print("========== " + e + " 점수 입력 ==========");
                requiredSubjects.put(e, createSubjectJSONObject());
            });
//            requiredSubjects.put("Java", createSubjectJSONObject());
//            requiredSubjects.put("객체지향", createSubjectJSONObject());
//            requiredSubjects.put("Spring", createSubjectJSONObject());
//            requiredSubjects.put("JPA", createSubjectJSONObject());

            studentInfo.put("필수과목", requiredSubjects);

            // 선택과목 입력 받기
            JSONObject electiveSubjects = (JSONObject) studentInfo.get("선택과목");

            System.out.println(electiveSubjects.keySet()); //필수과목 key값들 : 과목들

            System.out.println("선택과목의 점수를 입력해주세요.");

            electiveSubjects.keySet().stream().forEach(e -> {
                System.out.print("========== " + e + " 점수 입력 ==========");
                electiveSubjects.put(e, createSubjectJSONObject());
            });
//            electiveSubjects.put("디자인 패턴", createSubjectJSONObject());
//            electiveSubjects.put("Spring Security", createSubjectJSONObject());
//            electiveSubjects.put("Redis", createSubjectJSONObject());
//            electiveSubjects.put("MongoDB", createSubjectJSONObject());

            studentInfo.put("선택과목", electiveSubjects);

            //등록
            DBConfig.students.put(studentId, studentInfo);
            DBConfig.updateDatabase();

//            // 이름 입력 받기
//            System.out.print("이름을 입력하세요: ");
//            String studentName = scanner.nextLine().trim();
//            studentInfo.put("이름", studentName);
//
//            // 상태 입력 받기
//            System.out.print("상태를 입력하세요 (red, yellow, green 중 선택): ");
//            String status = scanner.nextLine().trim();
//            studentInfo.put("상태", status);

            // 학생 정보를 전체 데이터베이스에 추가
//            database.put(studentId, studentInfo);
        }

        // 생성된 JSON 데이터를 파일로 저장
//        saveJSONToFile(database);
    }

    private static JSONObject createSubjectJSONObject() {
        JSONObject subjectJSONObject = new JSONObject();
        int i = 1;
        while (i <= 10) {

            System.out.println(" [ 종료하려면 'exit' 입력 ]");
            System.out.print("회차 " + i + " 점수를 입력하세요: ");
            String input = scanner.nextLine().trim(); // 입력 값을 문자열로 받음

            if (input.equalsIgnoreCase("exit") || input.equalsIgnoreCase("e")) {
                if (i == 1) {
                    System.out.println("그댄 자격이 없다.. 1회차는 무조건 입력하십쇼");
                    continue;
                } else {
                    break; // exit 입력 시 반복문 종료
                }
            }
            if (0 > Integer.parseInt(input) || Integer.parseInt(input) > 100) {
                System.out.println("그댄 자격이 없다.. 0 ~ 100의 값 입력하십쇼");
                continue;
            }


            try {
                int score = Integer.parseInt(input); // 입력된 문자열을 정수로 변환
                subjectJSONObject.put(String.valueOf(i), Integer.toString(score)); // JSONObject에 추가
            } catch (NumberFormatException e) {
                System.out.println("잘못된 입력입니다. 숫자를 입력하세요.");
                i--; // 잘못된 입력을 처리하기 위해 반복 횟수를 다시 조정
            }
            i++;
        }
        return subjectJSONObject;
    }

//    private static void saveJSONToFile(JSONObject json) {
//        try (FileWriter fileWriter = new FileWriter("C:\\Users\\최유진\\Desktop\\SpartaCampManagementApp\\campManager\\campManager\\src\\campManage\\src\\test2.json")) {
//            fileWriter.write(json.toString());
//            System.out.println("JSON DB 파일이 성공적으로 생성되었습니다");
//        } catch (IOException e) {
//            System.err.println("JSON DB 파일 생성 중 오류가 발생하였습니다: " + e.getMessage());
//        }
//    }


    public static void updateRoundScoreBySubject() throws IOException, ParseException {
//        JSONParser parser = new JSONParser();
//        String fileName = "C:\\Users\\최유진\\Desktop\\SpartaCampManagementApp\\campManager\\campManager\\src\\campManage\\src\\test2.json";

//        try (Reader reader = new FileReader(fileName)) {
//            Scanner scanner = new Scanner(System.in);
//            JSONObject jsonObject = (JSONObject) parser.parse(reader);

        System.out.print("학생 고유번호(studentId)를 입력하세요: ");
        String studentId = scanner.nextLine();

        if (DBConfig.students.containsKey(studentId)) {
            JSONObject studentObj = (JSONObject) DBConfig.students.get(studentId);

            String name = (String) studentObj.get("이름");
            JSONObject requiredSubjects = (JSONObject) studentObj.get("필수과목");
            JSONObject optionalSubjects = (JSONObject) studentObj.get("선택과목");

            System.out.println("학생 이름: " + name);
            System.out.print("필수 과목:");
            printSubjects(requiredSubjects);
            System.out.print("선택 과목:");
            printSubjects(optionalSubjects);

            System.out.print("수정할 과목명을 입력하세요: ");
            String subjectName = scanner.nextLine();
            String round;
            while (true) {
                System.out.print("수정할 회차를 입력하세요: ");
                round = scanner.nextLine();
                if (Integer.parseInt(round) < 0 || Integer.parseInt(round) > 10) {
                    System.out.println("그댄 자격이 없다.. 0 ~ 10의 회차 입력하십쇼");
                } else {
                    break;
                }
            }

            int newScore;
            while (true) {
                System.out.print("수정할 점수를 입력하세요: ");
                newScore = scanner.nextInt();
                if (0 > newScore || newScore > 100) {
                    System.out.println("그댄 자격이 없다.. 0 ~ 100의 값 입력하십쇼");
                } else {
                    break;
                }
            }


            if (requiredSubjects.containsKey(subjectName)) {
//                    subjectsToUpdate = requiredSubjects;
//                    JSONObject subjectScores = (JSONObject) subjectsToUpdate.get(subjectName);
//                    subjectScores.put(round, newScore);
//                    System.out.println(subjectsToUpdate);
                JSONObject elementSubject = (JSONObject) requiredSubjects.get(subjectName);
                elementSubject.put(round, newScore);
                requiredSubjects.put(subjectName, elementSubject);
                studentObj.put("필수과목", requiredSubjects);
                DBConfig.students.put(studentId, studentObj);
                System.out.println(DBConfig.students.get(studentId));
                DBConfig.updateDatabase();
            } else if (optionalSubjects.containsKey(subjectName)) {
                JSONObject elementSubject = (JSONObject) optionalSubjects.get(subjectName);
                elementSubject.put(round, newScore);
                optionalSubjects.put(subjectName, elementSubject);
                studentObj.put("필수과목", optionalSubjects);
                DBConfig.students.put(studentId, studentObj);
                DBConfig.updateDatabase();
            } else {
                System.out.println("해당 과목은 존재하지 않습니다.");
                return;
            }
            System.out.println("~ ~ 수정 완료! ~ ~");

//                JSONObject subjectScores = (JSONObject) subjectsToUpdate.get(subjectName);
//                subjectScores.put(round, newScore);

            // JSON 파일 업데이트
//                try (FileWriter fileWriter = new FileWriter(fileName)) {
//                    fileWriter.write(jsonObject.toJSONString());
//                    System.out.println("점수가 성공적으로 수정되었습니다.");
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }

        } else {
            System.out.println("해당 학생 고유번호가 존재하지 않습니다.");
        }
//        } catch (IOException | ParseException e) {
//            e.printStackTrace();
//        }
    }

    private static void printSubjects(JSONObject subjects) {    // subjects : 필수과목 or 선택과목
        Iterator<String> subjectKeys = subjects.keySet().iterator();
        while (subjectKeys.hasNext()) {
            String subjectName = subjectKeys.next();
            JSONObject subjectScores = (JSONObject) subjects.get(subjectName);
            //subjectName : 과목 이름, subjectScores : 회차와 점수
            // roundKeys : 회차정보
            System.out.println("과목: " + subjectName);
            Iterator<String> roundKeys = subjectScores.keySet().iterator();
            while (roundKeys.hasNext()) {
                String round = roundKeys.next();
                int score = Integer.parseInt(subjectScores.get(round).toString());
                System.out.println("회차 " + round + ": " + score);
            }
        }
    }

    public static void inquireRoundGradeBySubject() throws IOException, ParseException {
        System.out.print("학생 고유번호(studentId)를 입력하세요: ");
        String studentId = scanner.nextLine();

        if (DBConfig.students.containsKey(studentId)) {
            JSONObject studentObj = (JSONObject) DBConfig.students.get(studentId);

            String name = (String) studentObj.get("이름");
            JSONObject requiredSubjects = (JSONObject) studentObj.get("필수과목");
            JSONObject optionalSubjects = (JSONObject) studentObj.get("선택과목");

            System.out.println("학생 이름: " + name);
            System.out.print("필수 과목:");
            printSubjects(requiredSubjects);
            System.out.print("선택 과목:");
            printSubjects(optionalSubjects);

            System.out.println("등급을 확인할 과목을 입력하세요.");
            String subjectName = scanner.nextLine();

            if (requiredSubjects.containsKey(subjectName)) {
                JSONObject elementSubject = (JSONObject) requiredSubjects.get(subjectName);
                Iterator<String> roundKeys = elementSubject.keySet().iterator();
                    while (roundKeys.hasNext()) {
                        String round = roundKeys.next();
                        int score = Integer.parseInt(elementSubject.get(round).toString());
                        Grade grade = calculateRequiredSubjectGrade(score);
                        System.out.println("회차" + round + "의 점수는 " + score + "이고 등급은" + grade + "입니다.");
                    }
            } else if (optionalSubjects.containsKey(subjectName)) {
                JSONObject elementSubject = (JSONObject) optionalSubjects.get(subjectName);
                Iterator<String> roundKeys = elementSubject.keySet().iterator();
                while (roundKeys.hasNext()) {
                    String round = roundKeys.next();
                    int score = Integer.parseInt(elementSubject.get(round).toString());
                    Grade grade = calculateOptionalSubjectGrade(score);
                    System.out.println("회차" + round + "의 점수는 " + score + "이고 등급은" + grade.name() + "입니다.");
                }
            } else {
                System.out.println("해당 과목은 존재하지 않습니다.");
                return;
            }
        }
    }

    private static Grade calculateRequiredSubjectGrade ( int score){
        if (100>= score && score >= 95) {
            return Grade.A;
        } else if (94>= score && score >= 90) {
            return Grade.B;
        } else if (89>= score && score >= 80) {
            return Grade.C;
        } else if (79>= score && score >= 70) {
            return Grade.D;
        } else if (69>= score && score >= 60) {
            return Grade.F;
        } else {
            return Grade.N;
        }
    }

    private static Grade calculateOptionalSubjectGrade ( int score){
        if (100>= score && score >= 90) {
            return Grade.A;
        } else if (89>= score && score >= 80) {
            return Grade.B;
        } else if (79>= score && score >= 70) {
            return Grade.C;
        } else if (69>= score && score >= 60) {
            return Grade.D;
        } else if (59>= score && score >= 50) {
            return Grade.F;
        } else {
            return Grade.N;
        }
    }

    public enum Grade {
        A, B, C, D, F, N
    }

    public static void avgGradeBySubject() {
        // 수강생의 과목별 평균 등급을 조회
        System.out.print("학생 고유번호(studentId)를 입력하세요: ");
        String studentId = scanner.nextLine();

        if (DBConfig.students.containsKey(studentId)) {
            JSONObject studentObj = (JSONObject) DBConfig.students.get(studentId);

            String name = (String) studentObj.get("이름");
            JSONObject requiredSubjects = (JSONObject) studentObj.get("필수과목");
            JSONObject optionalSubjects = (JSONObject) studentObj.get("선택과목");

            System.out.println("학생 이름: " + name);
            System.out.print("필수 과목:");
            printSubjects(requiredSubjects);
            System.out.print("선택 과목:");
            printSubjects(optionalSubjects);

            System.out.println("평균 등급을 확인할 과목을 입력하세요.");
            String subjectName = scanner.nextLine();

            int sumScore = 0;
            int cnt = 0;

            if (requiredSubjects.containsKey(subjectName)) {
                JSONObject elementSubject = (JSONObject) requiredSubjects.get(subjectName);
                Iterator<String> roundKeys = elementSubject.keySet().iterator();
                while (roundKeys.hasNext()) {
                    cnt++;
                    String round = roundKeys.next();
                    int score = Integer.parseInt(elementSubject.get(round).toString());
                    sumScore += score;
                }
                int avgScore = sumScore/cnt;
                System.out.println("평균점수: " + avgScore);
                Grade grade = calculateRequiredSubjectGrade(avgScore);
                System.out.println("선택한 과목의 평균 등급은" + grade + "입니다.");

            } else if (optionalSubjects.containsKey(subjectName)) {
                JSONObject elementSubject = (JSONObject) optionalSubjects.get(subjectName);
                Iterator<String> roundKeys = elementSubject.keySet().iterator();
                while (roundKeys.hasNext()) {
                    cnt++;
                    String round = roundKeys.next();
                    int score = Integer.parseInt(elementSubject.get(round).toString());
                    sumScore += score;
                }
                int avgScore = sumScore/cnt;
                System.out.println("평균점수: " + avgScore);
                Grade grade = calculateOptionalSubjectGrade(avgScore);
                System.out.println("선택한 과목의 평균 등급은" + grade + "입니다.");
            } else {
                System.out.println("해당 과목은 존재하지 않습니다.");
                return;
            }
        }

    }

    public static void avgGradeForRequiredSubjectByState() {
        System.out.println("아직 안했음다");
    }

}

