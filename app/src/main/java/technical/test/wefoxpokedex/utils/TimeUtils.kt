package technical.test.wefoxpokedex.utils

import technical.test.wefoxpokedex.utils.constans.Constants
import java.text.SimpleDateFormat
import java.util.*

class TimeUtils {

    companion object {

        fun getCurrentDate(): String {
            val sdf = SimpleDateFormat(Constants.FORMAT_TIME, Locale.ENGLISH)
            return sdf.format(Date())
        }
    }
}