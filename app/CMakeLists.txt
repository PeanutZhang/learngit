add_library( # Sets the name of the library.
             key

             # Sets the library as a shared library.
             SHARED

             # Provides a relative path to your source file(s).
             src/main/cpp/key.cpp)

target_link_libraries( # Specifies the target library.
                       key

                       # Links the target library to the log library
                       # included in the NDK.
                       ${log-lib} )