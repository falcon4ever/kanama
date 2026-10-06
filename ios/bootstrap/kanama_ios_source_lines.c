// Kotlin file:line for a Kotlin/Native stack frame on an iOS device (task 131 item 13).
//
// Kotlin/Native symbolicates a stack trace with source positions only through CoreSymbolication
// (macOS and the simulators) or libbacktrace, and both read DWARF -- which an iOS app never carries:
// Apple's linker leaves the debug info in the object files on the Mac (or a dSYM beside the app),
// so a device frame is just `kfun:<symbol> + <offset>`. The offset of a return address inside its
// function is fixed when the static library is built (linking moves a function, never its body),
// so the debug build maps (symbol, offset) to the game's Kotlin file and line ahead of time:
// `generateIosDeviceDebugSourceLines` (build.gradle.kts) reads the debug library's DWARF and
// writes kanama_ios_source_lines_table.inc for the game's own functions (its script dirs) and the
// self-test probe. Other builds compile this file without a table and every lookup answers 0.
// Static data only: nothing runs until a script error is reported.

#include <stdint.h>
#include <string.h>

// Declared for Kotlin in ios/include/kanama_ios.h.
int32_t kanama_ios_source_line(const char *symbol, int32_t offset, char *file_out, int32_t file_cap);

typedef struct {
    uint32_t name;      // offset of the symbol (`kfun:...`, no leading underscore) in k_strings
    uint32_t first_row; // index of the function's first row in k_rows
    uint32_t row_count;
} KanamaIosSourceSymbol;

typedef struct {
    uint32_t offset; // the row's first byte, from the function's start
    uint32_t file;   // offset of the Kotlin file's base name in k_strings
    uint32_t line;
} KanamaIosSourceRow;

#if KANAMA_IOS_SOURCE_LINES
// k_strings, k_symbols (sorted by name, byte order), k_symbol_count, k_rows.
#include "kanama_ios_source_lines_table.inc"
#else
static const char k_strings[] = "";
static const KanamaIosSourceSymbol k_symbols[1] = {{0, 0, 0}};
static const uint32_t k_symbol_count = 0;
static const KanamaIosSourceRow k_rows[1] = {{0, 0, 0}};
#endif

int32_t kanama_ios_source_line(const char *symbol, int32_t offset, char *file_out, int32_t file_cap) {
    if (symbol == NULL || offset <= 0 || file_out == NULL || file_cap <= 0) {
        return 0;
    }
    uint32_t low = 0;
    uint32_t high = k_symbol_count;
    while (low < high) {
        uint32_t mid = low + (high - low) / 2;
        int order = strcmp(k_strings + k_symbols[mid].name, symbol);
        if (order == 0) {
            // A frame's offset is a return address: the call is the instruction before it.
            uint32_t pc = (uint32_t)offset - 1;
            const KanamaIosSourceRow *rows = k_rows + k_symbols[mid].first_row;
            const KanamaIosSourceRow *found = NULL;
            for (uint32_t i = 0; i < k_symbols[mid].row_count && rows[i].offset <= pc; i++) {
                found = &rows[i];
            }
            if (found == NULL) {
                return 0;
            }
            strncpy(file_out, k_strings + found->file, (size_t)file_cap - 1);
            file_out[file_cap - 1] = '\0';
            return (int32_t)found->line;
        }
        if (order < 0) {
            low = mid + 1;
        } else {
            high = mid;
        }
    }
    return 0;
}
