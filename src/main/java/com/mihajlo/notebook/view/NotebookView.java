package com.mihajlo.notebook.view;

import com.mihajlo.notebook.model.hierarchy.Notebook;
import com.mihajlo.notebook.model.hierarchy.Topic;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class NotebookView extends Stage {

    private final Notebook notebook;

    private final VBox root = new VBox(10);

    private final ListView<Topic> lvTopics = new ListView<>();

    private final Button btNewTopic = new Button("Create new topic"),
                        btOpenTopic = new Button("Open topic");

    NotebookView(Notebook notebook) {
        this.notebook = notebook;
        super.setTitle(this.notebook.getTitle());
        this.lvTopics.setItems(FXCollections.observableArrayList(this.notebook.getChildren()));
        this.btNewTopic.setOnAction(e -> {
            TextInputDialog dialog = new TextInputDialog("Enter name of new topic");
            dialog.setHeaderText("New topic");
            dialog.showAndWait();
            String topicName = dialog.getEditor().getText();
            Topic topic = new Topic(topicName, this.notebook);
            try {
                Files.createFile(Paths.get(topic.path()));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            lvTopics.getItems().add(topic);
        });
        this.btOpenTopic.setOnAction(e -> {
            Topic topic = lvTopics.getSelectionModel().getSelectedItem();
            if (topic == null)
                return;
            TopicView topicView = new TopicView(topic);
            topicView.show();
        });
        this.root.getChildren().add(this.lvTopics);
        this.root.getChildren().add(this.down());
        super.setScene(new Scene(this.root));
    }

    private HBox down() {
        HBox hbox = new HBox(10, this.btNewTopic, this.btOpenTopic);
        hbox.setAlignment(Pos.CENTER);
        hbox.setPadding(new Insets(10));
        return hbox;
    }

}
