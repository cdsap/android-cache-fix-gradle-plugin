package org.gradle.android.workarounds.room

import org.gradle.android.workarounds.room.androidvariants.ApplyAndroidVariants
import org.gradle.android.workarounds.room.operations.FileSchemaOperations
import org.gradle.android.workarounds.room.task.RoomSchemaLocationMergeTask
import org.gradle.api.Project
import org.gradle.api.Task
import org.gradle.api.file.Directory
import org.gradle.api.provider.Provider
import org.gradle.api.tasks.TaskProvider

abstract class AnnotationProcessorWorkaround<T> {
    Project project
    RoomExtension roomExtension
    TaskProvider<RoomSchemaLocationMergeTask> mergeTask
    FileSchemaOperations fileOperationsWorkaround
    ApplyAndroidVariants androidVariantProvider

    AnnotationProcessorWorkaround(Project project, RoomExtension roomExtension, TaskProvider<RoomSchemaLocationMergeTask> mergeTask) {
        this.project = project
        this.roomExtension = roomExtension
        this.mergeTask = mergeTask
        this.androidVariantProvider = new ApplyAndroidVariants()
        this.fileOperationsWorkaround = new  FileSchemaOperations(this.project)

        initWorkaround()
    }

    abstract void initWorkaround()

    abstract void configureWorkaroundTask(Task task)

    abstract void copyExistingSchemasToTaskSpecificTmpDir(Provider<Directory> existingSchemaDir, T provider)

    abstract void copyGeneratedSchemasToOutput(T provider)
}
