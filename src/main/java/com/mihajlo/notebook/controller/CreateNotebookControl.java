package com.mihajlo.notebook.controller;

import com.mihajlo.notebook.model.hierarchy.Notebook;
import com.mihajlo.notebook.model.hierarchy.Subject;
import com.mihajlo.notebook.model.hierarchy.User;
import com.mihajlo.notebook.model.hierarchy.Workspace;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class CreateNotebookControl implements EventHandler<ActionEvent> {

    private final TextField notebookNameTextField, usernameTextField, subjectNameTextField;

    private final ComboBox<User> userComboBox;

    private final ComboBox<Subject> subjectComboBox;

    public CreateNotebookControl(TextField notebookNameTextField, TextField usernameTextField, TextField subjectNameTextField,
                                 ComboBox<User> userComboBox, ComboBox<Subject> subjectComboBox) {
        this.notebookNameTextField = notebookNameTextField;
        this.usernameTextField = usernameTextField;
        this.subjectNameTextField = subjectNameTextField;
        this.userComboBox = userComboBox;
        this.subjectComboBox = subjectComboBox;
    }

    @Override public void handle(ActionEvent event) {
        String notebookName = this.notebookNameTextField.getText();
        User user = this.userComboBox.getValue();
        Subject subject = this.subjectComboBox.getValue();
        if (user == null) {
            String username = this.usernameTextField.getText();
            if (username == null || username.trim().equals(""))
                return;
            user = Workspace.WORKSPACE.findChildByTitle(username);
            if (user == null) {
                user = new User(username, Workspace.WORKSPACE);
                try {
                    Files.createDirectory(Path.of(user.path()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        if (subject == null) {
            String subjectName = this.subjectNameTextField.getText();
            if (subjectName == null || subjectName.trim().equals(""))
                return;
            subject = user.findChildByTitle(subjectName);
            if (subject == null) {
                subject = new Subject(subjectName, user);
                try {
                    Files.createDirectory(Path.of(subject.path()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else if (!user.getChildren().contains(subject))
            return;
        if (notebookName == null || notebookName.trim().equals(""))
            return;
        Notebook notebook = subject.findChildByTitle(notebookName) == null ? new Notebook(notebookName, subject) : null;
        if (notebook == null)
            return;
        try {
            Files.createDirectory(Path.of(notebook.path()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
