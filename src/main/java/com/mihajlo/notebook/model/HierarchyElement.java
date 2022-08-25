package com.mihajlo.notebook.model;

public abstract class HierarchyElement<H extends HierarchyElement<H>> implements Comparable<H> {

    protected final String title;

    protected HierarchyElement(String title) {
        this.title = title;
    }

    @Override public int compareTo(H h) {
        return this.title.compareTo(h.title);
    }

    @Override public String toString() {
        return this.title;
    }

    public String getTitle() {
        return title;
    }
}
