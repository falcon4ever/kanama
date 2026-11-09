@tool
extends EditorSyntaxHighlighter

const KEYWORDS := {
    "as": true,
    "by": true,
    "catch": true,
    "class": true,
    "companion": true,
    "constructor": true,
    "data": true,
    "dynamic": true,
    "enum": true,
    "expect": true,
    "external": true,
    "false": true,
    "field": true,
    "file": true,
    "finally": true,
    "fun": true,
    "get": true,
    "import": true,
    "in": true,
    "init": true,
    "interface": true,
    "is": true,
    "null": true,
    "object": true,
    "package": true,
    "param": true,
    "property": true,
    "receiver": true,
    "set": true,
    "setparam": true,
    "super": true,
    "this": true,
    "true": true,
    "typealias": true,
    "typeof": true,
    "val": true,
    "value": true,
    "var": true,
    "where": true,
}
const CONTROL_FLOW_KEYWORDS := {
    "break": true,
    "continue": true,
    "do": true,
    "else": true,
    "for": true,
    "if": true,
    "return": true,
    "throw": true,
    "try": true,
    "when": true,
    "while": true,
}
const MODIFIERS := {
    "abstract": true,
    "actual": true,
    "annotation": true,
    "const": true,
    "crossinline": true,
    "delegate": true,
    "final": true,
    "infix": true,
    "inline": true,
    "inner": true,
    "internal": true,
    "lateinit": true,
    "noinline": true,
    "open": true,
    "operator": true,
    "out": true,
    "override": true,
    "private": true,
    "protected": true,
    "public": true,
    "reified": true,
    "sealed": true,
    "suspend": true,
    "tailrec": true,
    "vararg": true,
}
const BUILTIN_TYPES := {
    "Any": true,
    "Boolean": true,
    "Byte": true,
    "Char": true,
    "Double": true,
    "Float": true,
    "Int": true,
    "Long": true,
    "Nothing": true,
    "Short": true,
    "String": true,
    "Unit": true,
    "UByte": true,
    "UInt": true,
    "ULong": true,
    "UShort": true,
}
const SYMBOLS := "{}[]().,;:+-*/%!=<>?&|^~"

var _text_color := Color(0.86, 0.86, 0.86)
var _keyword_color := Color(1.0, 0.44, 0.52)
var _control_flow_keyword_color := Color(1.0, 0.44, 0.52)
var _type_color := Color(0.48, 0.76, 1.0)
var _function_color := Color(0.55, 0.85, 0.55)
var _comment_color := Color(0.45, 0.65, 0.45)
var _doc_comment_color := Color(0.55, 0.75, 0.55)
var _string_color := Color(1.0, 0.74, 0.42)
var _number_color := Color(0.9, 0.65, 1.0)
var _symbol_color := Color(0.75, 0.75, 0.75)
var _annotation_color := Color(0.95, 0.8, 0.45)


func _get_name() -> String:
    return "Kotlin"


func _get_supported_languages() -> PackedStringArray:
    return PackedStringArray(["Kotlin", "Kanama", "kt"])


func _create() -> EditorSyntaxHighlighter:
    return get_script().new()


func _update_cache() -> void:
    _load_theme_colors()


func _get_line_syntax_highlighting(line: int) -> Dictionary:
    var text_edit := get_text_edit()
    if text_edit == null or line < 0 or line >= text_edit.get_line_count():
        return {}

    _load_theme_colors()
    var text := text_edit.get_line(line)
    var result: Dictionary = {}
    var state := _state_before_line(text_edit, line)
    var i := 0

    while i < text.length():
        if state == "block_comment":
            _set_color(result, i, _comment_color)
            var block_end := text.find("*/", i)
            if block_end == -1:
                return result
            i = block_end + 2
            _set_color(result, i, _text_color)
            state = ""
            continue

        if state == "triple_string":
            _set_color(result, i, _string_color)
            var triple_end := text.find("\"\"\"", i)
            if triple_end == -1:
                return result
            i = triple_end + 3
            _set_color(result, i, _text_color)
            state = ""
            continue

        var ch := _char_at(text, i)
        var next := _char_at(text, i + 1)

        if ch == "/" and next == "/":
            _set_color(result, i, _comment_color)
            return result

        if ch == "/" and next == "*":
            var color := _doc_comment_color if _char_at(text, i + 2) == "*" else _comment_color
            _set_color(result, i, color)
            var end := text.find("*/", i + 2)
            if end == -1:
                return result
            i = end + 2
            _set_color(result, i, _text_color)
            continue

        if text.substr(i, 3) == "\"\"\"":
            _set_color(result, i, _string_color)
            var end := text.find("\"\"\"", i + 3)
            if end == -1:
                return result
            i = end + 3
            _set_color(result, i, _text_color)
            continue

        if ch == "\"" or ch == "'":
            i = _highlight_quoted_string(result, text, i, ch)
            continue

        if ch == "@":
            var annotation_end := _consume_identifier(text, i + 1)
            if annotation_end > i + 1:
                _set_color(result, i, _annotation_color)
                _set_color(result, annotation_end, _text_color)
                i = annotation_end
                continue

        if _is_digit(ch):
            var number_end := _consume_number(text, i)
            _set_color(result, i, _number_color)
            _set_color(result, number_end, _text_color)
            i = number_end
            continue

        if _is_identifier_start(ch):
            var end := _consume_identifier(text, i)
            var word := text.substr(i, end - i)
            var color: Variant = _color_for_identifier(word, text, end)
            if color != null:
                _set_color(result, i, color)
                _set_color(result, end, _text_color)
            i = end
            continue

        if SYMBOLS.contains(ch):
            _set_color(result, i, _symbol_color)
            _set_color(result, i + 1, _text_color)

        i += 1

    return result


func _highlight_quoted_string(result: Dictionary, text: String, start: int, quote: String) -> int:
    _set_color(result, start, _string_color)
    var i := start + 1
    while i < text.length():
        var ch := _char_at(text, i)
        if ch == "\\":
            i += 2
            continue
        if ch == quote:
            i += 1
            _set_color(result, i, _text_color)
            return i
        i += 1
    return text.length()


func _state_before_line(text_edit: TextEdit, target_line: int) -> String:
    var state := ""
    for row in range(target_line):
        state = _scan_end_state(text_edit.get_line(row), state)
    return state


func _scan_end_state(text: String, state: String) -> String:
    var i := 0
    while i < text.length():
        if state == "block_comment":
            var block_end := text.find("*/", i)
            if block_end == -1:
                return state
            i = block_end + 2
            state = ""
            continue

        if state == "triple_string":
            var triple_end := text.find("\"\"\"", i)
            if triple_end == -1:
                return state
            i = triple_end + 3
            state = ""
            continue

        var ch := _char_at(text, i)
        var next := _char_at(text, i + 1)
        if ch == "/" and next == "/":
            return state
        if ch == "/" and next == "*":
            state = "block_comment"
            i += 2
            continue
        if text.substr(i, 3) == "\"\"\"":
            state = "triple_string"
            i += 3
            continue
        if ch == "\"" or ch == "'":
            i = _skip_quoted_string(text, i, ch)
            continue
        i += 1
    return state


func _skip_quoted_string(text: String, start: int, quote: String) -> int:
    var i := start + 1
    while i < text.length():
        var ch := _char_at(text, i)
        if ch == "\\":
            i += 2
            continue
        if ch == quote:
            return i + 1
        i += 1
    return text.length()


func _color_for_identifier(word: String, text: String, end: int) -> Variant:
    if CONTROL_FLOW_KEYWORDS.has(word):
        return _control_flow_keyword_color
    if KEYWORDS.has(word) or MODIFIERS.has(word):
        return _keyword_color
    if BUILTIN_TYPES.has(word) or _starts_with_uppercase(word):
        return _type_color
    if _next_non_space(text, end) == "(":
        return _function_color
    return null


func _consume_identifier(text: String, start: int) -> int:
    var i := start
    while i < text.length() and _is_identifier_part(_char_at(text, i)):
        i += 1
    return i


func _consume_number(text: String, start: int) -> int:
    var i := start
    while i < text.length():
        var ch := _char_at(text, i)
        if _is_identifier_part(ch) or ch == ".":
            i += 1
        else:
            break
    return i


func _next_non_space(text: String, start: int) -> String:
    var i := start
    while i < text.length():
        var ch := _char_at(text, i)
        if ch != " " and ch != "\t":
            return ch
        i += 1
    return ""


func _set_color(result: Dictionary, column: int, color: Color) -> void:
    if column >= 0:
        result[column] = {"color": color}


func _load_theme_colors() -> void:
    var settings := EditorInterface.get_editor_settings()
    if settings == null:
        return
    _text_color = _editor_color(settings, "text_editor/theme/highlighting/text_color", _text_color)
    _keyword_color = _editor_color(settings, "text_editor/theme/highlighting/keyword_color", _keyword_color)
    _control_flow_keyword_color = _editor_color(
        settings,
        "text_editor/theme/highlighting/control_flow_keyword_color",
        _control_flow_keyword_color
    )
    _type_color = _editor_color(settings, "text_editor/theme/highlighting/base_type_color", _type_color)
    _function_color = _editor_color(settings, "text_editor/theme/highlighting/function_color", _function_color)
    _comment_color = _editor_color(settings, "text_editor/theme/highlighting/comment_color", _comment_color)
    _doc_comment_color = _editor_color(settings, "text_editor/theme/highlighting/doc_comment_color", _doc_comment_color)
    _string_color = _editor_color(settings, "text_editor/theme/highlighting/string_color", _string_color)
    _number_color = _editor_color(settings, "text_editor/theme/highlighting/number_color", _number_color)
    _symbol_color = _editor_color(settings, "text_editor/theme/highlighting/symbol_color", _symbol_color)
    _annotation_color = _editor_color(settings, "text_editor/theme/highlighting/gdscript/annotation_color", _annotation_color)


func _editor_color(settings: EditorSettings, name: String, fallback: Color) -> Color:
    var value = settings.get_setting(name)
    if value is Color:
        return value
    return fallback


func _char_at(text: String, index: int) -> String:
    if index < 0 or index >= text.length():
        return ""
    return text.substr(index, 1)


func _is_identifier_start(ch: String) -> bool:
    if ch.is_empty():
        return false
    var code := ch.unicode_at(0)
    return ch == "_" or (code >= 65 and code <= 90) or (code >= 97 and code <= 122)


func _is_identifier_part(ch: String) -> bool:
    return _is_identifier_start(ch) or _is_digit(ch)


func _is_digit(ch: String) -> bool:
    if ch.is_empty():
        return false
    var code := ch.unicode_at(0)
    return code >= 48 and code <= 57


func _starts_with_uppercase(word: String) -> bool:
    if word.is_empty():
        return false
    var code := word.unicode_at(0)
    return code >= 65 and code <= 90
