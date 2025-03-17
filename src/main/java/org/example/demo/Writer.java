package org.example.demo;
//
public class Writer {
    private final String name;
    private final org.example.demo.Library library;

    public Writer(String name, org.example.demo.Library library) {
        this.name = name;
        this.library = library;
    }

    public void writeBook() {
        library.write(name);
    }

    public String getName() {
        return name;
    }
}
