package org.example.modules;

import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.tag.FieldKey;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class Mp3 implements Module{
    String Path = "D:\\Folder";
    @Override
    public boolean formatWorksSupported(String name) {
        return name.endsWith(".mp3");
    }

    @Override
    public void getDescription() {
        System.out.println("Mp3");
        System.out.println("1. Вывод названия трека из тегов.");
        System.out.println("2. Вывод длительности в секундах.");
        System.out.println("3. Выводит исполнителя трека из тегов");
    }

    @Override
    public void functionOne(String name) {
        String path = Path + "\\" + name;

        try {
            AudioFile audio = AudioFileIO.read(new File(path));
            System.out.println("Название трека: " + audio.getTag().getFirst(FieldKey.TITLE));
        } catch (Exception e) {
            System.err.println("Ошибка при чтении mp3: " + e.getMessage());
        }
    }

    @Override
    public void functionTwo(String name) {
        String path = Path + "\\" + name;

        try {
            AudioFile audio = AudioFileIO.read(new File(path));
            System.out.println("Длительность трека: " + audio.getAudioHeader().getTrackLength());
        } catch (Exception e) {
            System.err.println("Ошибка при чтении mp3: " + e.getMessage());
        }
    }

    @Override
    public void functionThree(String name) {
        String path = Path + "\\" + name;

        try {
            AudioFile audio = AudioFileIO.read(new File(path));
            System.out.println("Исполнитель: " + audio.getTag().getFirst(FieldKey.ARTIST));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
