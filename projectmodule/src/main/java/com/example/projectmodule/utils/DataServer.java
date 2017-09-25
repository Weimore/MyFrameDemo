package com.example.projectmodule.utils;

import com.example.projectmodule.R;
import com.example.projectmodule.model.SectionData;
import com.example.projectmodule.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 吴城林 on 2017/8/28.
 */

public class DataServer {

    private static List<SectionData<User>> sections = new ArrayList<>();

    public static List<SectionData<User>> getSectionData(){
        sections.clear();

        for (int i = 0; i < 30; i++) {
            User user = new User("联合市场" + i, R.drawable.splash);
            if(i%6==0){
                SectionData<User> section = new SectionData<User>("header1");
                sections.add(section);
            }else {
                SectionData<User> section = new SectionData<User>(user);
                sections.add(section);
            }
        }
        return sections;
    }
}
