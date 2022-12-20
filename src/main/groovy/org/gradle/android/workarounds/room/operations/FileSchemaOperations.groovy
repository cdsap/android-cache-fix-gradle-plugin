package org.gradle.android.workarounds.room.operations

import org.gradle.api.Project
import org.gradle.api.file.Directory
import org.gradle.api.internal.file.FileOperations
import org.gradle.api.provider.Provider

class FileSchemaOperations {
    FileOperations fileOperations

    FileSchemaOperations(Project project) {
        this.fileOperations = project.fileOperations
    }

    void sync(Provider<Directory> origin, Provider<Directory> destination) {
        if (origin.isPresent()) {
            fileOperations.sync {
                it.from origin
                it.into destination
            }
        }
    }
}
