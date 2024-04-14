package org.example.modules;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class TextFile implements Module{
    String Path = "D:\\Folder";
    @Override
    public boolean formatWorksSupported(String name) {
        return name.endsWith(".txt");
    }

    @Override
    public void getDescription() {
        System.out.println("Текстовый файл");
        System.out.println("1. Подсчет и вывод количества строк.");
        System.out.println("2. Вывод частоты вхождения каждого символа.");
        System.out.println("3. Вывод количества символов.");
    }

    @Override
    public void functionOne(String name) {
        String path = Path + "\\" + name;

        int count = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            while ((reader.readLine()) != null) {
                count++;
            }
            System.out.println("Количество строк в файле: " + count);
        } catch (IOException e) {
            System.out.println("Ошибка при чтении файла: " + e.getMessage());
        }
    }

    @Override
    public void functionTwo(String name) {
        String path = Path + "\\" + name;

        Map<Character, Integer> map = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = reader.readLine()) != null) {
                for (char c : line.toCharArray()){
                    map.put(c, map.getOrDefault(c, 0) + 1);
                }
            }
            for (Map.Entry<Character, Integer> pair : map.entrySet()) {
                System.out.println(pair.getKey() + ": " + pair.getValue());
            }
        } catch (IOException e) {
            System.err.println("Ошибка при чтении файла: " + e.getMessage());
        }
    }

    @Override
    public void functionThree(String name) {
        String path = Path + "\\" + name;

        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            long count = 0;
            while (reader.read() != -1) {
                count++;
            }
            System.out.printf("В файле - " + count + " символов");
        } catch (IOException e) {
            System.out.println("Ошибка при чтении файла: " + e.getMessage());
        }
    }
}
