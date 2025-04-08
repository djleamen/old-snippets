// Description: This class represents the model for a chat application. 
// It stores messages in a list and provides methods to add and retrieve messages.

import java.util.ArrayList;
import java.util.List;

public class ChatModel {
    private final List<String> messages = new ArrayList<>();

    public void addMessage(String message) {
       messages.add(message);
    }

    public List<String> getMessages() {
        return messages;
    }
}
