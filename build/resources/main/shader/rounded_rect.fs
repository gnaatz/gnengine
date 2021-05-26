#version 450
out vec4 FragColor;

uniform float rounding;

in vec3 oColor;
in vec3 fragPos;

void main()
{
    float alpha = 1.0;

    float dist_x = max(max(rounding - fragPos.x, fragPos.x - (1.0 * fragPos.z - rounding)), 0);
    float dist_y = max(max(rounding - fragPos.y, fragPos.y - (1.0 - rounding)), 0);
    alpha = ceil(rounding * rounding - (dist_x * dist_x + dist_y * dist_y));

    FragColor = vec4(oColor, alpha);
}