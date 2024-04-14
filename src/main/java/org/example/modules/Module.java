package org.example.modules;

public interface  Module {
    boolean formatWorksSupported(String path);
    void getDescription();
    void functionOne(String path);
    void functionTwo(String path);
    void functionThree(String path);
}
