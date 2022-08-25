package com.mihajlo.notebook.model.hierarchy;

import com.mihajlo.notebook.model.Child;
import com.mihajlo.notebook.model.CompositeElement;
import com.mihajlo.notebook.model.persistence.IFolder;

public class Notebook extends CompositeElement<Notebook, Topic> implements Child<Notebook, Subject>, IFolder {

    private final Subject subject;

    public Notebook(String title, Subject subject) {
        super(title);
        this.subject = subject;
        this.subject.addChild(this);
    }

    @Override public Subject getParent() {
        return this.subject;
    }

    @Override public String path() {
        return this.getParent().path() + "/" + this.title;
    }
}
