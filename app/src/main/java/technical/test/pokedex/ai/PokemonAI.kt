package technical.test.pokedex.ai

import android.app.Activity
import org.tensorflow.lite.Interpreter
import technical.test.pokedex.Constants.MODEL_PATH
import technical.test.pokedex.getAIModel
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.FloatBuffer

class PokemonAI(private val activity: Activity) {

    private val tfLiteInterpreter: Interpreter = Interpreter(activity.getAIModel(MODEL_PATH))

    fun predict(input: FloatBuffer): Float {
        val output = Array(1) { FloatArray(1) }
        tfLiteInterpreter.run(input, output)
        return output[0][0]
    }

    fun floatArrayToBuffer(floatArray: FloatArray): FloatBuffer {
        val byteBuffer: ByteBuffer = ByteBuffer
            .allocateDirect(floatArray.size * 4)

        byteBuffer.order(ByteOrder.nativeOrder())

        val floatBuffer: FloatBuffer = byteBuffer.asFloatBuffer()

        floatBuffer.put(floatArray)
        floatBuffer.position(0)
        return floatBuffer
    }
}