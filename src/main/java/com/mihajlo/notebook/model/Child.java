package com.mihajlo.notebook.model;

public interface Child<H extends HierarchyElement<H> & Child<H, P>, P extends CompositeElement<P, H>> {
    P getParent();
}
