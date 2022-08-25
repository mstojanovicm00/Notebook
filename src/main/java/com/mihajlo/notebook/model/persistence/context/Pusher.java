package com.mihajlo.notebook.model.persistence.context;

import com.mihajlo.notebook.model.hierarchy.*;

public class Pusher {

    public static void push() {
        push(Workspace.WORKSPACE);
    }

    private static void push(Workspace workspace) {
        for (User u: workspace.getChildren())
            push(u);
    }

    private static void push(User user) {
        for (Subject s: user.getChildren())
            push(s);
    }

    private static void push(Subject subject) {
        for (Notebook n: subject.getChildren())
            push(n);
    }

    private static void push(Notebook notebook) {
        for (Topic t: notebook.getChildren())
            push(t);
    }

    private static void push(Topic topic) {
        if (topic.isChanged())
            topic.write(topic.getContent());
    }

    private Pusher() {

    }
}
