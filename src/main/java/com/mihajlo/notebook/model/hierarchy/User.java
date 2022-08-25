package com.mihajlo.notebook.model.hierarchy;

import com.mihajlo.notebook.model.Child;
import com.mihajlo.notebook.model.CompositeElement;
import com.mihajlo.notebook.model.persistence.IFolder;

public class User extends CompositeElement<User, Subject> implements Child<User, Workspace>, IFolder {

    private final Workspace workspace;

    public User(String title, Workspace workspace) {
        super(title);
        this.workspace = workspace;
        this.workspace.addChild(this);
    }

    @Override public Workspace getParent() {
        return this.workspace;
    }

    @Override public String path() {
        return this.getParent().path() + "/" + this.title;
    }
}
