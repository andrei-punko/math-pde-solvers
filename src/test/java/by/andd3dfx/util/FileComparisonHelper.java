package by.andd3dfx.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class FileComparisonHelper {

    public static final String BUILD_PATH = "build/";
    public static final String RESOURCES_PATH = "src/test/resources/";

    public static void checkGeneratedFileContent(String fileName) throws IOException {
        var generatedFileName = BUILD_PATH + fileName;
        Path generatedFilePath = Path.of(generatedFileName);
        String[] generatedFileLines = Files.readString(generatedFilePath).split("\n");

        var expectedOutputFileName = RESOURCES_PATH + fileName;
        Path expectedFilePath = Path.of(expectedOutputFileName);
        String[] expectedFileLines = Files.readString(expectedFilePath).split("\n");

        assertThat("Unexpected amount of lines in file " + generatedFilePath,
                generatedFileLines.length, is(expectedFileLines.length));

        for (int i = 0; i < generatedFileLines.length; i++) {
            assertThat("Wrong file content for file " + generatedFilePath,
                    generatedFileLines[i], is(expectedFileLines[i]));
        }
    }
}