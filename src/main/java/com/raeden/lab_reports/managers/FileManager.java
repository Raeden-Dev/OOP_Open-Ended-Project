package com.raeden.lab_reports.managers;

import com.google.gson.JsonSyntaxException;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

import static com.raeden.lab_reports.Main.GSON;
import static com.raeden.lab_reports.ui.printStrings.*;

public class FileManager {
    public static void createDirectory(Path directoryPath, boolean showInfo) {
        if (Files.exists(directoryPath)) return;

        String fileName = "[" + directoryPath.getFileName().toString() + "]";
        try {
            Files.createDirectories(directoryPath);
            if (showInfo) {
                printDirCreate(fileName, directoryPath.toString());
            }
        } catch (IOException e) {
            printDirCreateFail(fileName, directoryPath.toString());
        }
    }

    public static <T> T loadJsonFile(Path filePath, Type typeOfT) {
        return loadJsonFile(filePath.getFileName().toString(), filePath, typeOfT, false);
    }

    public static <T> T loadJsonFile(String fileName, Path filePath, Type typeOfT) {
        return loadJsonFile(fileName, filePath, typeOfT, false);
    }

    public static <T> T loadJsonFile(Path filePath, Type typeOfT, boolean showInfo) {
        return loadJsonFile(filePath.getFileName().toString(), filePath, typeOfT, showInfo);
    }

    public static <T> T loadJsonFile(String fileName, Path filePath, Type typeOfT, boolean showInfo) {
        if (Files.exists(filePath)) {
            try (BufferedReader reader = Files.newBufferedReader(filePath, StandardCharsets.UTF_8)) {
                T myObj = GSON.fromJson(reader, typeOfT);

                if (myObj == null) {
                    printLoadFailure(fileName);
                    return null;
                } else {
                    if (showInfo) {
                        printLoadSuccess(fileName);
                    }
                    return myObj;
                }
            } catch (IOException | JsonSyntaxException e) {
                printLoadFailure(fileName + " " + e);
            }
        } else {
            printLoadFailure(fileName);
        }
        return null;
    }

    public static <T> void saveJsonFile(Path savePath, T dataObject) {
        saveJsonFile(savePath.getFileName().toString(), savePath, dataObject, false);
    }

    public static <T> void saveJsonFile(String fileName, Path savePath, T dataObject) {
        saveJsonFile(fileName, savePath, dataObject, false);
    }

    public static <T> void saveJsonFile(String fileName, Path savePath, T dataObject, boolean showInfo) {
        if (dataObject == null) {
            printSaveFailure(fileName);
            return;
        }
        try (BufferedWriter writer = Files.newBufferedWriter(savePath, StandardCharsets.UTF_8, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)) {
            GSON.toJson(dataObject, writer);
            if (showInfo) {
                printSaveSuccess(fileName);
            }
        } catch (IOException e) {
            printSaveFailure(fileName + " " + e);
        }
    }
}
