package org.korobko.model;

/**
 * Created by Вова on 15.04.2016.
 */
public class Command {

    protected String nickname, fullPath;

    public Command() {

    }

    public void setNickname(String s) {
        nickname = s;

    }

    public void setFullPath(String s) {
        fullPath = s;
    }

    public String getNickname() {
        return nickname;
    }

    public String getFullPath() {
        return fullPath;
    }

}

