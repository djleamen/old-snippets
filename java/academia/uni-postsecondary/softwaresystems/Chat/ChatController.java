// Description: This is the controller class for the chat application.
// It handles user input and updates the model and view accordingly.

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChatController {
    private final ChatModel model;
    private final ChatView view;

    public ChatController(ChatModel model, ChatView view) {
        this.model = model;
        this.view = view;

        // Add action listener to the send button
        view.getSendButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMessage();
            }
        });

        // Add Enter key functionality
        view.getInputField().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMessage();
            }
        });
    }

    private void sendMessage() {
        String message = view.getInputField().getText();
        model.addMessage(message);
        view.getChatArea().append("You: " + message + "\n");
        view.getInputField().setText("");
    }

    public static void main(String[] args) {
        // Create MVC components
        ChatModel model = new ChatModel();
        ChatView view = new ChatView();

        ChatController controller = new ChatController(model, view);

        // Make the GUI visible
        view.setVisible(true);
    }
}
