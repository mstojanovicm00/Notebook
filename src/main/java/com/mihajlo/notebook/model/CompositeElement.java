package com.mihajlo.notebook.model;

import java.util.Set;
import java.util.TreeSet;

public abstract class CompositeElement<H extends CompositeElement<H, C>, C extends HierarchyElement<C> & Child<C, H>>
        extends HierarchyElement<H> {

    protected final Set<C> children = new TreeSet<>();

    protected CompositeElement(String title) {
        super(title);
    }

    public void addChild(C child) {
        if (child == null)
            return;
        this.children.add(child);
    }

    public void removeChild(C child) {
        if (this.children.contains(child))
            this.children.remove(child);
    }

    public C findChildByTitle(String title) {
        for (C c: this.children) {
            if (c.title.equals(title))
                return c;
        }
        return null;
    }

    public Set<C> getChildren() {
        return children;
    }
}
