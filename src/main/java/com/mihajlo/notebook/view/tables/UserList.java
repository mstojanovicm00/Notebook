package com.mihajlo.notebook.view.tables;

import com.mihajlo.notebook.model.hierarchy.User;
import com.mihajlo.notebook.model.hierarchy.Workspace;
import com.mihajlo.notebook.observer.Subscriber;
import javafx.collections.FXCollections;
import javafx.scene.control.ListView;

public class UserList extends ListView<User> implements Subscriber {
    public UserList() {
        super(FXCollections.observableArrayList(Workspace.WORKSPACE.getUsers()));
        Workspace.WORKSPACE.addSubscriber(this);
    }

    @Override public void update(Object notification) {
        if (notification instanceof User) {
            User user = (User) notification;
            super.getItems().add(user);
        }
    }
}
