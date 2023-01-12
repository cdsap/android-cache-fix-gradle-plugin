package org.gradle.android.workarounds.room.commandlineproviders

import org.gradle.api.file.Directory
import org.gradle.api.provider.Provider

class JavaCompilerRoomSchemaLocationArgumentProvider extends RoomSchemaLocationArgumentProvider {
    JavaCompilerRoomSchemaLocationArgumentProvider(Provider<Directory> configuredSchemaLocationDir, Provider<Directory> schemaLocationDir) {
        super(configuredSchemaLocationDir, schemaLocationDir)
    }
}
