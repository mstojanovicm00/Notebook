package com.mihajlo.notebook.model.persistence.context;

import com.mihajlo.notebook.model.hierarchy.*;

import java.io.File;

public class Puller {

    public static Workspace pull() {
        Workspace workspace = new Workspace();
        File workspaceFolder = new File(workspace.path());
        for (File userFolder: workspaceFolder.listFiles(File::isDirectory)) {
            String userName = userFolder.getName();
            User user = new User(userName, workspace);
            for (File subjectFolder: userFolder.listFiles(File::isDirectory)) {
                String subjectName = subjectFolder.getName();
                Subject subject = new Subject(subjectName, user);
                for (File notebookFolder: subjectFolder.listFiles(File::isDirectory)) {
                    String notebookName = notebookFolder.getName();
                    Notebook notebook = new Notebook(notebookName, subject);
                    for (File topicFile: notebookFolder.listFiles(file -> !file.isDirectory())) {
                        String topicName = topicFile.getName();
                        String[] a = topicName.split("\\.");
                        StringBuilder sb = new StringBuilder().append(a[0]);
                        for (int i = 1; i < a.length - 1; ++i)
                            sb.append(".").append(a[i]);
                        topicName = sb.toString();
                        new Topic(topicName, notebook);
                    }
                }
            }
        }
        return workspace;
    }

    private Puller() {

    }
}
