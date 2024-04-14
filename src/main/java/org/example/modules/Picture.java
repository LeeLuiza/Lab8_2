package org.example.modules;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Metadata;
import com.drew.metadata.exif.ExifSubIFDDirectory;
import org.springframework.stereotype.Component;
import com.drew.metadata.Tag;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

@Component
public class Picture implements Module {
    String Path = "D:\\Folder";
    @Override
    public boolean formatWorksSupported(String name) {
        return name.endsWith(".jpg");
    }

    @Override
    public void getDescription() {
        System.out.println("Изображение");
        System.out.println("1. Вывод размера изображения.");
        System.out.println("2. Вывод информации exif.");
        System.out.println("3. Вывод версии exif.");
    }

    @Override
    public void functionOne(String name) {
        String path = Path + "\\" + name;

        try {
            BufferedImage img = ImageIO.read(new File(path));
            System.out.println("Ширина изображения: " + img.getWidth());
            System.out.println("Высота изображения: " + img.getHeight());
        }
        catch (IOException e) {
            System.err.println("Ошибка при чтении изображения: " + e.getMessage());
        }
    }

    @Override
    public void functionTwo(String name) {
        String path = Path + "\\" + name;

        try {
            Metadata metadata = ImageMetadataReader.readMetadata(new File(path));
            ExifSubIFDDirectory exif = metadata.getFirstDirectoryOfType(ExifSubIFDDirectory.class);
            if (exif != null) {
                for (Tag tag : exif.getTags()) {
                    System.out.println(tag.getTagName() + ", " + tag.getDescription());
                }
            }
            else {
                System.out.println("exif информации нет");
            }
        }
        catch (IOException | ImageProcessingException e) {
            System.err.println("Ошибка при чтении изображения: " + e.getMessage());
        }
    }

    @Override
    public void functionThree(String name) {
        String path = Path + "\\" + name;

        try {
            Metadata metadata = ImageMetadataReader.readMetadata(new File(path));
            ExifSubIFDDirectory exif = metadata.getFirstDirectoryOfType(ExifSubIFDDirectory.class);
            if (exif != null) {
                System.out.println("Версия exif: " + exif.getDescription(ExifSubIFDDirectory.TAG_EXIF_VERSION));
            }
            else {
                System.out.println("exif информации нет");
            }
        }
        catch (IOException | ImageProcessingException e) {
            System.err.println("Ошибка при чтении изображения: " + e.getMessage());
        }
    }
}
