package com.mihajlo.notebook.model.persistence;

public interface ITextFile extends IFile {
    String read();
    void write(String text);
}
