#version 450
layout (location = 0) in vec3 aPos;

uniform vec4 transform;
uniform vec2 screen;

out vec2 texCoords;

void main()
{
    gl_Position =  vec4((aPos.x * transform.z + transform.x - screen.x / 2) / (screen.x / 2), (aPos.y * transform.w + transform.y - screen.y / 2) / (screen.y / 2), aPos.z, 1.0);
    texCoords = vec2(aPos.x, aPos.y);
}