package dev.kolja.gnengine.render;

import dev.kolja.gnengine.core.Engine;
import org.joml.Vector2f;
import org.joml.Vector3f;
import org.joml.Vector4f;

import static dev.kolja.gnengine.io.FileReader.readFile;
import static org.lwjgl.opengl.GL20.*;

public class Shader {

    private static final String SHADER_DIR = "src/main/resources/shader/";
    private static final String VERTEX_FILE_ENDING = ".vs";
    private static final String FRAGMENT_FILE_ENDING = ".fs";

    private final int id;

    public Shader(String name, String vertexShaderSource, String fragmentShaderSource) {
        int vertexShader = glCreateShader(GL_VERTEX_SHADER);
        glShaderSource(vertexShader, vertexShaderSource);
        glCompileShader(vertexShader);

        printShaderError(name, "vertex", vertexShader);

        int fragmentShader = glCreateShader(GL_FRAGMENT_SHADER);
        glShaderSource(fragmentShader, fragmentShaderSource);
        glCompileShader(fragmentShader);

        printShaderError(name, "fragment", fragmentShader);

        id = glCreateProgram();
        glAttachShader(id, vertexShader);
        glAttachShader(id, fragmentShader);
        glLinkProgram(id);

        if (glGetProgrami(id, GL_LINK_STATUS) == GL_FALSE) {
            Engine.LOGGER.error("Failed to link program");
            Engine.LOGGER.error(glGetProgramInfoLog(id, glGetProgrami(id, GL_INFO_LOG_LENGTH)));
        }

        glDeleteShader(vertexShader);
        glDeleteShader(fragmentShader);
    }

    static Shader fromFile(String name) {
        String vertexShaderSource = readFile(SHADER_DIR + name + VERTEX_FILE_ENDING);
        String fragmentShaderSource = readFile(SHADER_DIR + name + FRAGMENT_FILE_ENDING);

        return new Shader(name, vertexShaderSource, fragmentShaderSource);
    }

    public void use() {
        glUseProgram(id);
    }

    public void setBool(String name, boolean value) {
        glUniform1i(glGetUniformLocation(id, name), value ? 1 : 0);
    }

    public void setInt(String name, int value) {
        glUniform1i(glGetUniformLocation(id, name), value);
    }

    public void setFloat(String name, float value) {
        glUniform1f(glGetUniformLocation(id, name), value);
    }

    public void setVec3f(String name, Vector3f value) {
        glUniform3f(glGetUniformLocation(id, name), value.x, value.y, value.z);
    }

    public void setVec4f(String name, Vector4f value) {
        glUniform4f(glGetUniformLocation(id, name), value.x, value.y, value.z, value.w);
    }

    public void setVec2f(String name, Vector2f value) {
        glUniform2f(glGetUniformLocation(id, name), value.x, value.y);
    }

    public void setVec1fv(String name, float[] value) {
        glUniform1fv(glGetUniformLocation(id, name), value);
    }

    private void printShaderError(String name, String type, int id) {
        if(glGetShaderi(id, GL_COMPILE_STATUS) == GL_FALSE)
        {
            Engine.LOGGER.error("Shader [" + type + "]" + name + " failed to compile");
            Engine.LOGGER.error(glGetShaderInfoLog(id, 2048));
        }
    }

}
