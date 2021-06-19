package net.linkbutton.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import net.fabricmc.loader.api.FabricLoader;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Config {
    public static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    public static final File CONFIG_FILE = FabricLoader.getInstance().getConfigDir().resolve("linkbutton.json")
            .toFile();

    public static final String DEFAULT_LINK = "https://www.curseforge.com/minecraft/mc-mods/link-button";
    public static final String DEFAULT_TEXT = "Example";
    public static final Integer DEFAULT_WIDTH = 50;
    public static final Integer DEFAULT_HEIGHT = 20;

    public String text;
    public String link;
    public Integer width;
    public Integer height;

    public Config() {
        this.link = DEFAULT_LINK;
        this.text = DEFAULT_TEXT;
        this.width = DEFAULT_WIDTH;
        this.height = DEFAULT_HEIGHT;
    }

    public Config(Config config) {
        this.link = config.link;
        this.text = config.text;
        this.width = config.width;
        this.height = config.height;
    }

    public static Config loadOrCreate() {
        if (!CONFIG_FILE.exists()) {

            Config config = new Config();
            if (config.save()) {

            }
            return config;

           /* try {
                CONFIG_FILE.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            } */
        }
        try {
            FileReader reader = new FileReader(CONFIG_FILE);
            Config config = GSON.fromJson(reader, Config.class);
            return config != null ? config : new Config();
        } catch (IOException | JsonIOException | JsonSyntaxException e) {
            return new Config();
        }
    }

    // Save is not used, from early testing
    public boolean save() {
        try (FileWriter writer = new FileWriter(CONFIG_FILE)) {
            writer.write(GSON.toJson(this));
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
