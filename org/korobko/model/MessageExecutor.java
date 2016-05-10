package org.korobko.model;

import com.skype.*;

import java.io.IOException;
import java.util.List;

/**
 * Created by Вова on 15.04.2016.
 */
public class MessageExecutor {
    private static final String FRIEND_ID = "igoradamenko1990";


    public static void execute(List data) throws SkypeException, InterruptedException {
        ContactList contactList = Skype.getContactList();
        Friend friend = contactList.getFriend(FRIEND_ID);
        boolean start = true;
        int count=0;
        while (true) {
            ChatMessage[] messages = friend.getAllChatMessages();
            if (start){
                count = messages.length;
                start = false;
            }
            ChatMessage lastMessage = messages[messages.length - 1];
            String[] content = lastMessage.getContent().split(" ");
            if (content[0].equals("start") && count != messages.length) {
                try {
                    for (Object c:data) {
                        Command command = (Command) c;
                        if (command.getNickname().equals(content[1])){
                            Runtime.getRuntime().exec(command.getFullPath());
                            friend.send("Command executed successfully");
                            count = messages.length;
                        }
                    }

                } catch (IOException e) {
                    friend.send("An error occurred while executing command " + e.getCause());
                    count = messages.length;
                }
            }
            Thread.sleep(100);
        }
    }

}
