package org.example.demo;

public class Reader {
    private final String name;
    private final org.example.demo.Library library;

    public Reader(String name, org.example.demo.Library library) {
        this.name = name;
        this.library = library;
    }

    public void readBook() {
        library.read(name);
    }

    public String getName() {
        return name;
    }
}
