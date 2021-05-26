#version 450
layout (location = 0) in vec3 aPos;

uniform vec2 screen;
uniform vec4 transform;
uniform vec3 color;

out vec3 oColor;
out vec3 fragPos;

void main()
{
    gl_Position =  vec4((aPos.x * transform.z + transform.x - screen.x / 2) / (screen.x / 2), (aPos.y * transform.w + transform.y - screen.y / 2) / (screen.y / 2), aPos.z, 1.0);
    float aspect = transform.z / transform.w;
    fragPos = vec3(aPos.x * aspect, aPos.y, aspect);
    oColor = color;
}