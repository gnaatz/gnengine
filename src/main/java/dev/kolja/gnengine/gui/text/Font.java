package dev.kolja.gnengine.gui.text;

import dev.kolja.gnengine.core.Engine;
import dev.kolja.gnengine.io.FileReader;
import dev.kolja.gnengine.texture.Texture;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Font {

    private final String name;
    private final Texture fontMap;
    private final Map<Character, CharInfo> charMap;

    public Font(String name) {
        this.name = name;
        fontMap = new Texture("font/" + name, Texture.Type.R);
        charMap = new HashMap<>();
        readCharMap(name);
    }

    public void use() {
        fontMap.use();
    }

    public CharInfo getCharInfo(char id) {
        if (!charMap.containsKey(id)) {
            Engine.LOGGER.error("Tried to get CharInfo for " + id + "; not included in font " + name);
        }
        return charMap.get(id);
    }

    public int getTextureWidth() {
        return fontMap.getWidth();
    }

    public int getTextureHeight() {
        return fontMap.getHeight();
    }

    private void readCharMap(String name) {
        try {
            FileReader.readFileLineByLine("src/main/resources/font/" + name + ".fnt", this::perLine);
        } catch (IOException ioException) {
            Engine.LOGGER.error("Font file " + name + " could not be found");
        }
    }

    private void perLine(String line, int index) {
        if (index < 4) {
            //Header stuff
        } else {
            String pattern = "^char id=(?<id>.*?)\\s*?x=(?<x>.*?)\\s*?y=(?<y>.*?)\\s*?width=(?<width>.*?)\\s*?height=(?<height>.*?)\\s*?xoffset=(?<xoffset>.*?)\\s*?yoffset=(?<yoffset>.*?)\\s*?xadvance=(?<xadvance>.*?)\\s*?page=(?<page>.*?)\\s*?chnl=(?<chnl>.*?)$";
            Pattern regex = Pattern.compile(pattern);
            Matcher matcher = regex.matcher(line);
            if(matcher.find()) {
                charMap.put((char) Integer.parseInt(matcher.group("id")), new CharInfo(
                        Integer.parseInt(matcher.group("x")),
                        Integer.parseInt(matcher.group("y")),
                        Integer.parseInt(matcher.group("width")),
                        Integer.parseInt(matcher.group("height")),
                        Integer.parseInt(matcher.group("xoffset")),
                        Integer.parseInt(matcher.group("yoffset")), Integer.parseInt(matcher.group("xadvance")),
                        Integer.parseInt(matcher.group("page")),
                        Integer.parseInt(matcher.group("chnl"))));
            }
        }
    }
}
