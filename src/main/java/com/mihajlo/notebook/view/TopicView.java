package com.mihajlo.notebook.view;

import com.mihajlo.notebook.model.hierarchy.Topic;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.web.HTMLEditor;
import javafx.stage.Stage;

public class TopicView extends Stage {

    private final Topic topic;

    private final VBox root = new VBox(10);

    private final HTMLEditor htmlTopicContent = new HTMLEditor();

    private final Button btSave = new Button("Save");

    public TopicView(Topic topic) {
        this.topic = topic;
        super.setTitle(this.topic.getTitle());
        this.htmlTopicContent.setHtmlText(this.topic.getContent());
        this.btSave.setOnAction(e -> {
            TopicView.this.topic.setContent(this.htmlTopicContent.getHtmlText());
        });
        this.root.getChildren().add(this.htmlTopicContent);
        this.root.getChildren().add(this.btSave);
        super.setScene(new Scene(this.root));
    }
}
