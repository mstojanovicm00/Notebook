package com.mihajlo.notebook.model.hierarchy;

import com.mihajlo.notebook.model.Child;
import com.mihajlo.notebook.model.HierarchyElement;
import com.mihajlo.notebook.model.persistence.ITextFile;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;

public class Topic extends HierarchyElement<Topic> implements Child<Topic, Notebook>, ITextFile {

    private final Notebook notebook;

    private final int index;

    private String content;

    private boolean changed = false;

    public Topic(String title, Notebook notebook) {
        super(title);
        this.notebook = notebook;
        this.index = this.notebook.getChildren().size();
        this.notebook.addChild(this);
        this.content = this.read();
    }

    @Override public Notebook getParent() {
        return this.notebook;
    }

    @Override public String path() {
        return this.getParent().path() + "/" + this.title + ".html";
    }

    @Override public int compareTo(Topic topic) {
        return Integer.compare(this.index, topic.index);
    }

    @Override public String read() {
        if (!Files.exists(Paths.get(this.path())))
            return "";
        InputStreamReader inputStreamReader = null;
        FileInputStream fileInputStream = null;
        BufferedReader bufferedReader = null;
        Collection<String> lines = new ArrayList<>();
        try {
            fileInputStream = new FileInputStream(this.path());
            inputStreamReader = new InputStreamReader(fileInputStream, StandardCharsets.UTF_8);
            bufferedReader = new BufferedReader(inputStreamReader);
            for (String line = bufferedReader.readLine(); line != null && !(line = line.trim()).isEmpty();
                        line = bufferedReader.readLine())
                lines.add(line);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    return String.join("\n", lines);
                }
            }
            if (inputStreamReader != null) {
                try {
                    inputStreamReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    return String.join("\n", lines);
                }
            }
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    return String.join("\n", lines);
                }
            }
            return String.join("\n", lines);
        }
    }

    @Override public void write(String text) {
        FileOutputStream fileOutputStream = null;
        OutputStreamWriter outputStreamWriter = null;
        BufferedWriter bufferedWriter = null;
        try {
            fileOutputStream = new FileOutputStream(this.path());
            outputStreamWriter = new OutputStreamWriter(fileOutputStream, StandardCharsets.UTF_8);
            bufferedWriter = new BufferedWriter(outputStreamWriter);
            bufferedWriter.write(text);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (bufferedWriter != null) {
                try {
                    bufferedWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
            }
            if (outputStreamWriter != null) {
                try {
                    outputStreamWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
            }
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
            }
            return;
        }
    }

    public boolean isChanged() {
        return changed;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
        this.changed = true;
    }
}
