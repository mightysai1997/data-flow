#include <stdio.h>
#include <wchar.h>

void copy_wstring(wchar_t *dst, const wchar_t *src, size_t n) {
    wcsncpy(dst, src, n); // Vulnerable use of wcsncpy
    // Code that manipulates dst without proper null termination
}

int main() {
    wchar_t dst[20];
    const wchar_t *src = L"Hello, world!";

    copy_wstring(dst, src, sizeof(dst) / sizeof(dst[0]));

    wprintf(L"Copied wide string: %ls\n", dst);

    return 0;
}
