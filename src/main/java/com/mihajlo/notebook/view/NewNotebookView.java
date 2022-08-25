package com.mihajlo.notebook.view;

import com.mihajlo.notebook.controller.CreateNotebookControl;
import com.mihajlo.notebook.model.hierarchy.Subject;
import com.mihajlo.notebook.model.hierarchy.User;
import com.mihajlo.notebook.model.hierarchy.Workspace;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class NewNotebookView extends Stage {

    static NewNotebookView newNotebookView(ListView<User> userListView) {
        NewNotebookView newNotebookView = new NewNotebookView();
        if (userListView.getSelectionModel().getSelectedItem() != null)
            newNotebookView.cbUsers.setValue(userListView.getSelectionModel().getSelectedItem());
        return newNotebookView;
    }

    private final GridPane root = new GridPane();

    private final TextField tfNotebook = new TextField(),
                            tfSubject = new TextField(),
                            tfUser = new TextField();

    private final ComboBox<Subject> cbSubjects = new ComboBox<>(FXCollections.observableArrayList(Workspace.WORKSPACE.getSubjects()));

    private final ComboBox<User> cbUsers = new ComboBox<>(FXCollections.observableArrayList(Workspace.WORKSPACE.getUsers()));

    private final Button btCreate = new Button("Create");

    private NewNotebookView() {
        super.setTitle("Create a new notebook");
        this.btCreate.setOnAction(new CreateNotebookControl(
                this.tfNotebook, this.tfUser, this.tfSubject, this.cbUsers, this.cbSubjects) {
            @Override public void handle(ActionEvent event) {
                super.handle(event);
                NewNotebookView.super.close();
            }
        });
        this.root.add(new Label("Notebook name:"), 0, 0);
        this.root.add(this.tfNotebook, 1, 0, 2, 1);
        this.root.addRow(1, new Label("Subject:"), this.cbSubjects, this.tfSubject);
        this.root.addRow(2, new Label("User:"), this.cbUsers, this.tfUser);
        this.root.add(this.btCreate, 1, 3);
        this.root.setVgap(10);
        this.root.setHgap(10);
        this.root.setAlignment(Pos.CENTER);
        this.root.setPadding(new Insets(10));
        super.setScene(new Scene(this.root));
    }

}
