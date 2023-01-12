package org.gradle.android.workarounds.room.commandlineproviders

import org.gradle.api.file.Directory
import org.gradle.api.provider.Provider
import org.gradle.api.tasks.Internal
import org.gradle.api.tasks.Optional
import org.gradle.api.tasks.OutputDirectory
import org.gradle.process.CommandLineArgumentProvider

abstract class RoomSchemaLocationArgumentProvider implements CommandLineArgumentProvider {

    public static final String ROOM_SCHEMA_LOCATION = "room.schemaLocation"

    @Internal
    final Provider<Directory> configuredSchemaLocationDir

    @Internal
    final Provider<Directory> schemaLocationDir

    RoomSchemaLocationArgumentProvider(Provider<Directory> configuredSchemaLocationDir, Provider<Directory> schemaLocationDir) {
        this.configuredSchemaLocationDir = configuredSchemaLocationDir
        this.schemaLocationDir = schemaLocationDir
    }

    @Internal
    protected String getSchemaLocationPath() {
        return schemaLocationDir.get().asFile.absolutePath
    }

    @Override
    Iterable<String> asArguments() {
        if (configuredSchemaLocationDir.isPresent()) {
            if (this instanceof KspRoomSchemaLocationArgumentProvider) {
                return ["${ROOM_SCHEMA_LOCATION}=${schemaLocationPath}" as String]
            } else {
                return ["-A${ROOM_SCHEMA_LOCATION}=${schemaLocationPath}" as String]
            }

        } else {
            return []
        }
    }

    @OutputDirectory
    @Optional
    Provider<Directory> getEffectiveSchemaLocationDir() {
        return schemaLocationDir
    }
}
