package org.example.modules;

import java.io.File;
import org.springframework.stereotype.Component;

@Component
public class Folder implements Module{
    String Path = "D:\\Folder";

    @Override
    public boolean formatWorksSupported(String name) {
        File file = new File(Path + "\\" + name);
        return file.isDirectory();
    }

    @Override
    public void getDescription() {
        System.out.println("Каталог");
        System.out.println("1. Вывод списка файлов в каталоге.");
        System.out.println("2. Подсчет размера всех файлов в каталоге.");
        System.out.println("3. Подсчет количества папок в каталоге");
    }

    @Override
    public void functionOne(String name) {
        File directory = new File(Path + "\\" + name);
        File[] files = directory.listFiles();
        if (files != null) {
            System.out.println("Список файлов:");
            for (File file : files) {
                if(file.isFile()){
                    System.out.println(file.getName());
                }
            }
        }
    }

    @Override
    public void functionTwo(String name) {
        File directory = new File(Path + "\\" + name);
        File[] files = directory.listFiles();
        long size = 0;
        if (files != null) {
            for (File file : files) {
                if (file.isFile()){
                    size += file.length();
                }
            }
        }
        System.out.printf("Размер всех файлов в каталоге: " + size + " байт");
    }

    @Override
    public void functionThree(String name) {
        File directory = new File(Path + "\\" + name);
        File[] files = directory.listFiles();
        int dirCount = 0;
        if (files != null) {
            for (File file : files) {
                if(!file.isFile()){
                    dirCount++;
                }
            }
        }
        System.out.printf("Количество папок: " + dirCount);
    }
}
