package technical.test.pokedex

import android.app.Activity
import java.nio.ByteBuffer
import java.nio.channels.FileChannel

fun Activity.getAIModel(modelFileName: String): ByteBuffer {
    val assetFileDescriptor = assets.openFd(modelFileName)
    val fileChannel: FileChannel = assetFileDescriptor.createInputStream().channel

    return fileChannel.map(
        FileChannel.MapMode.READ_ONLY,
        assetFileDescriptor.startOffset,
        assetFileDescriptor.declaredLength
    )
}