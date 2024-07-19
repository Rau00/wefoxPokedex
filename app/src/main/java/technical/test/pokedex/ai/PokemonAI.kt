package technical.test.pokedex.ai

import android.app.Activity
import org.tensorflow.lite.Interpreter
import technical.test.pokedex.Constants.MODEL_PATH
import technical.test.pokedex.getAIModel
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.FloatBuffer

class PokemonAI(activity: Activity) {

    private val tfLiteInterpreter: Interpreter = Interpreter(activity.getAIModel(MODEL_PATH))

    fun predict(input: FloatArray): Float {
        val output = Array(1) { FloatArray(1) }
        tfLiteInterpreter.run(input, output)
        return output[0][0]
    }
}