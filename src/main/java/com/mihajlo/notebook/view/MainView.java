package com.mihajlo.notebook.view;

import com.mihajlo.notebook.model.hierarchy.Notebook;
import com.mihajlo.notebook.model.hierarchy.User;
import com.mihajlo.notebook.view.tables.NotebookTable;
import com.mihajlo.notebook.view.tables.UserList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainView extends Stage {

    public static final MainView MAIN_VIEW = new MainView();

    private final HBox root = new HBox(10);

    private final ListView<User> lvUsers = new UserList();

    private final TableView<Notebook> tvNotebooks = new NotebookTable();

    private final Button btNewNotebook = new Button("Create a new notebook"),
                        btOpenNotebook = new Button("Open selected notebook");

    private MainView() {
        super.setTitle("Notebook");
        this.btNewNotebook.setOnAction(e -> NewNotebookView.newNotebookView(this.lvUsers).show());
        this.btOpenNotebook.setOnAction(event -> {
            if (tvNotebooks.getSelectionModel().getSelectedItem() == null)
                return;
            Notebook notebook = tvNotebooks.getSelectionModel().getSelectedItem();
            NotebookView notebookView = new NotebookView(notebook);
            notebookView.show();
        });
        this.root.getChildren().addAll(this.left(), this.right());
        this.root.setAlignment(Pos.CENTER);
        this.root.setPadding(new Insets(10));
        super.setScene(new Scene(this.root));
    }

    private HBox leftUp() {
        HBox hbox = new HBox(10, new Label("Filtriranje liste"));
        hbox.setPadding(new Insets(10));
        hbox.setAlignment(Pos.CENTER);
        return hbox;
    }

    private HBox rightUp() {
        HBox hbox = new HBox(10, new Label("Filtriranje tabele"));
        hbox.setPadding(new Insets(10));
        hbox.setAlignment(Pos.CENTER);
        return hbox;
    }

    private HBox rightDown() {
        HBox hbox = new HBox(10, this.btNewNotebook, this.btOpenNotebook);
        hbox.setPadding(new Insets(10));
        hbox.setAlignment(Pos.CENTER);
        return hbox;
    }

    private VBox left() {
        VBox vbox = new VBox(10, this.leftUp(), this.lvUsers);
        vbox.setPadding(new Insets(10));
        vbox.setAlignment(Pos.CENTER);
        return vbox;
    }

    private VBox right() {
        VBox vbox = new VBox(10, this.rightUp(), this.tvNotebooks, this.rightDown());
        vbox.setPadding(new Insets(10));
        vbox.setAlignment(Pos.CENTER);
        return vbox;
    }

}
