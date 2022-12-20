package org.gradle.android.workarounds.room

import org.gradle.android.workarounds.room.task.RoomSchemaLocationMergeTask
import org.gradle.android.workarounds.room.variants.AndroidVariantProvider
import org.gradle.api.Project
import org.gradle.api.Task
import org.gradle.api.file.Directory
import org.gradle.api.provider.Provider
import org.gradle.api.tasks.TaskProvider

abstract class AnnotationProcessorWorkaround<T> {
    Project project
    RoomExtension roomExtension
    TaskProvider<RoomSchemaLocationMergeTask> mergeTask
    FileOperationsWorkaround fileOperationsWorkaround
    AndroidVariantProvider androidVariantProvider

    AnnotationProcessorWorkaround(Project project, RoomExtension roomExtension, TaskProvider<RoomSchemaLocationMergeTask> mergeTask) {
        this.project = project
        this.roomExtension = roomExtension
        this.mergeTask = mergeTask
        this.androidVariantProvider = new AndroidVariantProvider()
        this.fileOperationsWorkaround = new  FileOperationsWorkaround(this.project)

        initWorkaround()
    }

    abstract void initWorkaround()

    abstract void configureWorkaroundTask(Task task)

    abstract void copyExistingSchemasToTaskSpecificTmpDir(Provider<Directory> existingSchemaDir, T provider)

    abstract void copyGeneratedSchemasToOutput(T provider)
}
