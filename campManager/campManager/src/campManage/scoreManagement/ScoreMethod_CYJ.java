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

    Scanner scanner;
    JSONObject database;
    JSONObject students;

    public ScoreMethod_CYJ(){

        database = new JSONObject();
        students = new JSONObject();

    }
    //생성하기
    public static void crateScore() throws IOException, ParseException {
        while (true) {
            System.out.println("새로운 학생 정보를 입력하세요 (종료하려면 'exit' 입력)");

            // 고유 번호 입력 받기
            System.out.print("고유 번호를 입력하세요: ");
            String studentId = scanner.nextLine().trim();
            if (studentId.equalsIgnoreCase("exit")) {
                break; // 종료 조건
            }

            JSONObject studentInfo = new JSONObject();

            // 필수과목 입력 받기
            //requiredSubjects : 필수과목
            JSONObject requiredSubjects = new JSONObject();
            System.out.println("필수과목의 점수를 입력해주세요. (Java, 객체지향, Spring, JPA 순으로 입력):");
            requiredSubjects.put("Java", createSubjectJSONObject());
            requiredSubjects.put("객체지향", createSubjectJSONObject());
            requiredSubjects.put("Spring", createSubjectJSONObject());
            requiredSubjects.put("JPA", createSubjectJSONObject());

            studentInfo.put("필수과목", requiredSubjects);

            // 선택과목 입력 받기
            JSONObject electiveSubjects = new JSONObject();
            System.out.println("선택과목의 점수를 입력해주세요. (디자인 패턴, Spring Security, Redis, MongoDB 순으로 입력):");
            electiveSubjects.put("디자인 패턴", createSubjectJSONObject());
            electiveSubjects.put("Spring Security", createSubjectJSONObject());
            electiveSubjects.put("Redis", createSubjectJSONObject());
            electiveSubjects.put("MongoDB", createSubjectJSONObject());

            studentInfo.put("선택과목", electiveSubjects);

            // 이름 입력 받기
            System.out.print("이름을 입력하세요: ");
            String studentName = scanner.nextLine().trim();
            studentInfo.put("이름", studentName);

            // 상태 입력 받기
            System.out.print("상태를 입력하세요 (red, yellow, green 중 선택): ");
            String status = scanner.nextLine().trim();
            studentInfo.put("상태", status);

            // 학생 정보를 전체 데이터베이스에 추가
            database.put(studentId, studentInfo);
        }

        // 생성된 JSON 데이터를 파일로 저장
        saveJSONToFile(database);
    }

    private static JSONObject createSubjectJSONObject() {
        Scanner scanner = new Scanner(System.in);
        JSONObject subjectJSONObject = new JSONObject();
        for (int i = 1; i <= 10; i++) {
            System.out.println("종료하려면 'exit' 입력");
            System.out.print("회차 " + i + " 점수를 입력하세요: ");
            String input = scanner.nextLine().trim(); // 입력 값을 문자열로 받음

            if (input.equalsIgnoreCase("exit")) {
                break; // exit 입력 시 반복문 종료
            }

            try {
                int score = Integer.parseInt(input); // 입력된 문자열을 정수로 변환
                subjectJSONObject.put(String.valueOf(i), score); // JSONObject에 추가
            } catch (NumberFormatException e) {
                System.out.println("잘못된 입력입니다. 숫자를 입력하세요.");
                i--; // 잘못된 입력을 처리하기 위해 반복 횟수를 다시 조정
            }
        }
        return subjectJSONObject;
    }

    private static void saveJSONToFile(JSONObject json) {
        try (FileWriter fileWriter = new FileWriter("C:\\Users\\최유진\\Desktop\\SpartaCampManagementApp\\campManager\\campManager\\src\\campManage\\src\\test2.json")) {
            fileWriter.write(json.toString());
            System.out.println("JSON DB 파일이 성공적으로 생성되었습니다");
        } catch (IOException e) {
            System.err.println("JSON DB 파일 생성 중 오류가 발생하였습니다: " + e.getMessage());
        }
    }


    public static void updateRoundScoreBySubject() throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        String fileName = "C:\\Users\\최유진\\Desktop\\SpartaCampManagementApp\\campManager\\campManager\\src\\campManage\\src\\test2.json";

        try (Reader reader = new FileReader(fileName)) {
            Scanner scanner = new Scanner(System.in);
            JSONObject jsonObject = (JSONObject) parser.parse(reader);

            System.out.print("학생 고유번호(studentId)를 입력하세요: ");
            String studentId = scanner.nextLine();

            if (jsonObject.containsKey(studentId)) {
                JSONObject studentObj = (JSONObject) jsonObject.get(studentId);

                String name = (String) studentObj.get("이름");
                JSONObject requiredSubjects = (JSONObject) studentObj.get("필수과목");
                JSONObject optionalSubjects = (JSONObject) studentObj.get("선택과목");

                System.out.println("학생 이름: " + name);
                System.out.println("필수 과목:");
                printSubjects(requiredSubjects);
                System.out.println("선택 과목:");
                printSubjects(optionalSubjects);

                System.out.print("수정할 과목명을 입력하세요: ");
                String subjectName = scanner.nextLine();

                System.out.print("수정할 회차를 입력하세요: ");
                String round = scanner.nextLine();

                System.out.print("수정할 점수를 입력하세요: ");
                int newScore = scanner.nextInt();

                JSONObject subjectsToUpdate;
                if (requiredSubjects.containsKey(subjectName)) {
                    subjectsToUpdate = requiredSubjects;
                } else if (optionalSubjects.containsKey(subjectName)) {
                    subjectsToUpdate = optionalSubjects;
                } else {
                    System.out.println("해당 과목은 존재하지 않습니다.");
                    return;
                }

                JSONObject subjectScores = (JSONObject) subjectsToUpdate.get(subjectName);
                subjectScores.put(round, newScore);

                // JSON 파일 업데이트
                try (FileWriter fileWriter = new FileWriter(fileName)) {
                    fileWriter.write(jsonObject.toJSONString());
                    System.out.println("점수가 성공적으로 수정되었습니다.");
                } catch (IOException e) {
                    e.printStackTrace();
                }

            } else {
                System.out.println("해당 학생 고유번호가 존재하지 않습니다.");
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    private static void printSubjects(JSONObject subjects) {
        Iterator<String> subjectKeys = subjects.keySet().iterator();
        while (subjectKeys.hasNext()) {
            String subjectName = subjectKeys.next();
            JSONObject subjectScores = (JSONObject) subjects.get(subjectName);

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
        JSONParser parser = new JSONParser();
        String fileName = "C:\\Users\\최유진\\Desktop\\SpartaCampManagementApp\\campManager\\campManager\\src\\campManage\\src\\test2.json";

        try (Reader reader = new FileReader(fileName)) {
            Scanner scanner = new Scanner(System.in);
            JSONObject jsonObject = (JSONObject) parser.parse(reader);

            System.out.print("학생 고유번호(studentId)를 입력하세요: ");
            String studentId = scanner.nextLine();

            if (jsonObject.containsKey(studentId)) {
                JSONObject studentObj = (JSONObject) jsonObject.get(studentId);

                String name = (String) studentObj.get("이름");
                JSONObject requiredSubjects = (JSONObject) studentObj.get("필수과목");
                JSONObject optionalSubjects = (JSONObject) studentObj.get("선택과목");

                System.out.println("학생 이름: " + name);
                System.out.println("필수 과목:");
                printSubjects(requiredSubjects);
                System.out.println("선택 과목:");
                printSubjects(optionalSubjects);

                System.out.print("수정할 과목명을 입력하세요: ");
                String subjectName = scanner.nextLine();

                System.out.print("수정할 회차를 입력하세요: ");
                String round = scanner.nextLine();

                System.out.print("수정할 점수를 입력하세요: ");
                int newScore = scanner.nextInt();

                JSONObject subjectsToUpdate;
                if (requiredSubjects.containsKey(subjectName)) {
                    subjectsToUpdate = requiredSubjects;
                } else if (optionalSubjects.containsKey(subjectName)) {
                    subjectsToUpdate = optionalSubjects;
                } else {
                    System.out.println("해당 과목은 존재하지 않습니다.");
                    return;
                }

                JSONObject subjectScores = (JSONObject) subjectsToUpdate.get(subjectName);
                subjectScores.put(round, newScore);

                // JSON 파일 업데이트
                try (FileWriter fileWriter = new FileWriter(fileName)) {
                    fileWriter.write(jsonObject.toJSONString());
                    System.out.println("점수가 성공적으로 수정되었습니다.");
                } catch (IOException e) {
                    e.printStackTrace();
                }

            } else {
                System.out.println("해당 학생 고유번호가 존재하지 않습니다.");
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
//        JSONObject j;
//        Object o = new JSONParser().parse(new FileReader("C:\\Users\\최유진\\Desktop\\SpartaCampManagementApp\\campManager\\campManager\\src\\campManage\\src\\testCYJ.json"));
//        j = (JSONObject) o;
//
//        Long jsonRound = (Long) j.get("회차");
//        Long storedScore = (Long) j.get("점수");
//
//        Grade grade = calculateRequiredSubjectGrade(storedScore);
//        calculateOptionalSubjectGrade();
//        System.out.println(grade);

    }

    private static Grade calculateRequiredSubjectGrade(int score) {
        if (score >= 95) {
            return Grade.A;
        } else if (score >= 90) {
            return Grade.B;
        } else if (score >= 80) {
            return Grade.C;
        } else if (score >= 70) {
            return Grade.D;
        } else if (score >= 60) {
            return Grade.F;
        } else {
            return Grade.N;
        }
    }

    private Grade calculateOptionalSubjectGrade(int score) {
        if (score >= 90) {
            return Grade.A;
        } else if (score >= 80) {
            return Grade.B;
        } else if (score >= 70) {
            return Grade.C;
        } else if (score >= 60) {
            return Grade.D;
        } else if (score >= 50) {
            return Grade.F;
        } else {
            return Grade.N;
        }
    }

    public enum Grade {
        A, B, C, D, F, N
    }
}
