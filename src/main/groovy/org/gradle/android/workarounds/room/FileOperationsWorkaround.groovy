package org.gradle.android.workarounds.room

import org.gradle.api.Project
import org.gradle.api.file.Directory
import org.gradle.api.internal.file.FileOperations
import org.gradle.api.provider.Provider

class FileOperationsWorkaround {
    FileOperations fileOperations

    FileOperationsWorkaround(Project project) {
        fileOperations = project.fileOperations
    }

    protected void sync(Provider<Directory> origin, Provider<Directory> destination) {
        if (origin.isPresent()) {
            fileOperations.sync {
                it.from origin
                it.into destination
            }
        }
    }
}
