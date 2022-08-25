package com.mihajlo.notebook;

import com.mihajlo.notebook.model.persistence.context.Pusher;
import com.mihajlo.notebook.view.MainView;
import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {
    @Override public void start(Stage primaryStage) throws Exception {
        MainView.MAIN_VIEW.show();
    }

    @Override public void stop() throws Exception {
        super.stop();
        Pusher.push();
    }
}
