package geekbang.designpattern;

import java.util.ArrayList;
import java.util.List;

public class UserFile {

    private String name;

    private int age;

    private String gender;

    public UserFile(String name, int age, String gender){
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    public static UserFile parseFrom(String userInfoText){
        //
        return null;
    }

    public String formatToText(){
        // 将类 UserFile 格式化文本
        return null;
    }
}

class UserFormatter{

    public void format(String userFile, String formattedUserFile){
        List users = new ArrayList();
        while (true){
            UserFile userFile1 = UserFile.parseFrom(userFile);
            users.add(userFile);
        }



    }




}
