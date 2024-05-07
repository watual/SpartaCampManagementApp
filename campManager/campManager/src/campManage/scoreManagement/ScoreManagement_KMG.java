package campManage.scoreManagement;

import java.util.HashMap;
import java.util.Map;

public class ScoreManagement_KMG {
    private Map<String, Map<Integer, Integer>> studentScores; // 수강생별 과목 및 회차별 점수를 저장하는 맵

    // 생성자
    public void ScoreManager() {
        studentScores = new HashMap<>();
    }

    // 과목별 시험 회차 및 점수 등록
    public void registerScore(String studentName, int round, int score) {
        // 해당 수강생이 맵에 없으면 새로운 맵을 생성하여 추가
        if (!studentScores.containsKey(studentName)) {
            studentScores.put(studentName, new HashMap<>());
        }

        Map<Integer, Integer> subjectScores = studentScores.get(studentName);
        // 해당 회차의 점수를 저장
        subjectScores.put(round, score);
        System.out.println("점수가 등록되었습니다.");
    }

    // 과목별 회차 점수 조회
    public int getScore(String studentName, int round) {
        if (studentScores.containsKey(studentName)) {
            Map<Integer, Integer> subjectScores = studentScores.get(studentName);
            // 해당 회차의 점수를 반환
            if (subjectScores.containsKey(round)) {
                return subjectScores.get(round);
            }
        }
        // 등록되지 않은 회차일 경우 -1 반환
        return -1;
    }

    public static <ScoreManager> void run() {
        ScoreManager manager = (ScoreManager) new ScoreManagement_KMG();

        // 과목별 회차 점수 조회
        System.out.println("누구1의 1회차 점수: " + manager.getClass());
        System.out.println("누구2의 2회차 점수: " + manager.getClass());
        System.out.println("누구3의 3회차 점수: " + manager.getClass()); // 등록되지 않은 회차
    }
}
