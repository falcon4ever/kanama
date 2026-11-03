# Installs the built bootstrap SRC as DST (example_project/addons/kanama), run with
# `cmake -DSRC=... -DDST=... -P install_into_example.cmake` by the kanama_bootstrap_install target.
#
# A copy that already has the same bytes is left alone. A changed one replaces DST by a rename, never
# by writing into it: a Godot editor of the example project may have DST loaded, and on Linux
# rewriting a mapped library in place throws away the process's relocations of it (the editor then
# crashed on quit, task 143 item 4). The rename leaves the editor the file it mapped.
if(NOT SRC OR NOT DST)
    message(FATAL_ERROR "install_into_example.cmake needs -DSRC=<built library> -DDST=<installed copy>")
endif()
if(EXISTS "${DST}")
    execute_process(COMMAND "${CMAKE_COMMAND}" -E compare_files "${SRC}" "${DST}" RESULT_VARIABLE kanama_differs)
    if(kanama_differs EQUAL 0)
        return()
    endif()
endif()
set(kanama_incoming "${DST}.kanama-new")
execute_process(COMMAND "${CMAKE_COMMAND}" -E copy "${SRC}" "${kanama_incoming}" RESULT_VARIABLE kanama_copy_failed)
if(NOT kanama_copy_failed EQUAL 0)
    file(REMOVE "${kanama_incoming}")
    message(FATAL_ERROR "Could not copy ${SRC} to ${kanama_incoming}")
endif()
file(RENAME "${kanama_incoming}" "${DST}" RESULT kanama_rename_result)
if(NOT kanama_rename_result EQUAL 0)
    file(REMOVE "${kanama_incoming}")
    message(FATAL_ERROR "Could not replace ${DST} (${kanama_rename_result}): on Windows, close a Godot editor that has it loaded and build again.")
endif()
