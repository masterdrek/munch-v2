package munch;

import javafx.event.ActionEvent;

import java.io.IOException;

public class HomePageController
{
    public void profileAction(ActionEvent actionEvent)
    {
        //Switch to profileScene
    }

    public void logOutAction(ActionEvent actionEvent) throws IOException {
        //Switch back to loginScene
        System.out.println("logging out of userid: " + Integer.toString(MunchApp.currentUserID));
        MunchApp.currentUserID = -1;
        SceneController.switchToLogin(actionEvent);

    }
}
