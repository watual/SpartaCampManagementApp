package campManage.model;

public class Score extends management {
    String[] score = {"A","B","C","D","F","N"};


    public String matchRank(int score){
        if(95<=score && score<=100){
            return "A";
        }else if(90<=score && score<=94){
            return "B";
        }else if(80<=score && score<=89){
            return "C";
        }else if(70<=score && score<=79){
            return "D";
        }else if(60<=score && score<=69){
            return "F";
        }else{
            return "N";
        }
    }
}
