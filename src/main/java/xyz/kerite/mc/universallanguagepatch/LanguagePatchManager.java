package xyz.kerite.mc.universallanguagepatch;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;

public class LanguagePatchManager {
    public static final HashMap<String, String> TRANSLATED_MAP = new HashMap<>();
    private static final Logger LOGGER = LogManager.getLogger();
    public final Path directory;

    public LanguagePatchManager(Path path) {
        directory = path;
    }

    public void loadLanguagePatch() {
        TRANSLATED_MAP.clear();
        if (Files.notExists(directory)) {
            Utils.tryRun(() -> Files.createDirectory(directory));
        }
        Arrays.stream(Objects.requireNonNull(directory.toFile().listFiles(
                (dir, name) -> name.endsWith(".json")))
        ).forEach(file -> {
            try {
                String content = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
                JsonObject json = new JsonParser().parse(content).getAsJsonObject();
                json.entrySet().forEach((entry) -> TRANSLATED_MAP.put(entry.getKey(), entry.getValue().getAsString()));
            } catch (Exception e) {
                LOGGER.error("Parsing translation patch failed, parsing file " + file.getName());
            }
        });
    }
}
