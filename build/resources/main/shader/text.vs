#version 450
layout (location = 0) in vec3 aPos;

uniform vec4 textPos;
uniform vec2 textureXY;
uniform vec2 textureStride;
uniform vec2 screen;
uniform vec3 color;
uniform vec4 transform;

out vec3 fColor;
out vec2 texCoords;

void main()
{
    gl_Position = vec4((aPos.x * transform.z + textPos.x + transform.x - screen.x / 2) / (screen.x / 2),  (((1-aPos.y) * transform.w + textPos.y + transform.y - screen.y / 2) / (screen.y / 2)), aPos.z, 1.0);
    texCoords = textureXY + vec2(aPos.x * textureStride.x, -aPos.y * textureStride.y);
    fColor = color;
}