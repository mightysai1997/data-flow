#include <stdio.h>
#include <string.h>

void copy_string(char *dst, const char *src, size_t n) {
    stpncpy(dst, src, n); // Vulnerable use of stpncpy
    // Code that manipulates dst without proper null termination
}

int main() {
    char dst[20];
    const char *src = "Hello, world!";

    copy_string(dst, src, sizeof(dst));

    printf("Copied string: %s\n", dst);

    return 0;
}
