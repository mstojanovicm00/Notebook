package com.mihajlo.notebook.view.tables;

import com.mihajlo.notebook.model.hierarchy.Notebook;
import com.mihajlo.notebook.model.hierarchy.Subject;
import com.mihajlo.notebook.model.hierarchy.Workspace;
import com.mihajlo.notebook.observer.Subscriber;
import javafx.collections.FXCollections;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class NotebookTable extends TableView<Notebook> implements Subscriber {
    public NotebookTable() {
        super(FXCollections.observableArrayList(Workspace.WORKSPACE.getNotebooks()));
        Workspace.WORKSPACE.addSubscriber(this);

        TableColumn<Notebook, String> tcTitle = new TableColumn<>("Title");
        tcTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        super.getColumns().add(tcTitle);

        TableColumn<Notebook, Subject> tcSubject = new TableColumn<>("Subject");
        tcSubject.setCellValueFactory(new PropertyValueFactory<>("parent"));
        super.getColumns().add(tcSubject);
    }

    @Override public void update(Object notification) {
        if (notification instanceof Notebook) {
            Notebook notebook = (Notebook) notification;
            super.getItems().add(notebook);
        }
    }
}
