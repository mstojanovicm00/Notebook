package com.mihajlo.notebook.model.hierarchy;

import com.mihajlo.notebook.model.CompositeElement;
import com.mihajlo.notebook.model.persistence.IFolder;
import com.mihajlo.notebook.model.persistence.context.Puller;
import com.mihajlo.notebook.observer.Publisher;
import com.mihajlo.notebook.observer.Subscriber;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.TreeSet;
import java.util.function.Predicate;

public class Workspace extends CompositeElement<Workspace, User> implements IFolder, Publisher {

    public static final Workspace WORKSPACE = Puller.pull();

    private final List<Subscriber> subscribers = new ArrayList<>();

    public Workspace() {
        super("My Workspace");
    }

    public Collection<User> getUsers() {
        return super.children;
    }

    public Collection<Subject> getSubjects() {
        Collection<Subject> subjects = new TreeSet<>();
        for (User u: super.children)
            subjects.addAll(u.getChildren());
        return subjects;
    }

    public Collection<Notebook> getNotebooks() {
        Collection<Notebook> notebooks = new TreeSet<>();
        for (Subject s: this.getSubjects())
            notebooks.addAll(s.getChildren());
        return notebooks;
    }

    @Override public void addChild(User child) {
        super.addChild(child);
        this.notifySubscribers(child);
    }

    @Override public String path() {
        return "notebooks";
    }

    @Override public void addSubscriber(Subscriber sub) {
        if (sub != null)
            this.subscribers.add(sub);
    }

    @Override public void notifySubscribers(Object notification, Predicate<Subscriber> subscriberPredicate) {
        this.subscribers.stream().filter(subscriberPredicate).forEach(subscriber -> subscriber.update(notification));
    }
}
