package com.mihajlo.notebook;

public class Launcher {

    private static final Launcher LAUNCHER = new Launcher();

    private Launcher() {

    }

    static Launcher getLauncher() {
        return LAUNCHER;
    }

    void launch(String... args) {
        App.launch(App.class, args);
    }
}
