package com.mihajlo.notebook.model.hierarchy;

import com.mihajlo.notebook.model.Child;
import com.mihajlo.notebook.model.CompositeElement;
import com.mihajlo.notebook.model.persistence.IFolder;

public class Subject extends CompositeElement<Subject, Notebook> implements Child<Subject, User>, IFolder {

    private final User user;

    public Subject(String title, User user) {
        super(title);
        this.user = user;
        this.user.addChild(this);
    }

    @Override public void addChild(Notebook child) {
        super.addChild(child);
        if (Workspace.WORKSPACE != null)
            Workspace.WORKSPACE.notifySubscribers(child);
    }

    @Override public User getParent() {
        return this.user;
    }

    @Override public String path() {
        return this.getParent().path() + "/" + this.title;
    }

    @Override public String toString() {
        return this.getParent() + " -> " + super.toString();
    }
}
