package technical.test.wefoxpokedex.ui.components.dialog

import android.content.Context
import androidx.appcompat.app.AlertDialog

class DialogComponent(val context: Context) {


    private lateinit var alertDialogBuilder: AlertDialog.Builder
    private lateinit var alertDialog: AlertDialog

    fun showDialog() {
        alertDialog.show()
    }

    fun setupActions(
        textPositive: String,
        actionPositive: () -> Unit,
        textNegative: String,
        actionNegative: () -> Unit = {},
        textNeutral: String? = null,
        actionNeutral: () -> Unit = {}) {

        alertDialogBuilder
            .setPositiveButton(textPositive) { dialog, _ ->
                dialog.dismiss()
                actionPositive()
            }
            .setNegativeButton(textNegative) { dialog, _ ->
                dialog.dismiss()
                actionNegative()
            }

        if (textNeutral.isNullOrEmpty().not() && actionNeutral != null) {
            alertDialogBuilder .setNeutralButton(textNeutral) { dialog, _ ->
                dialog.dismiss()
                actionNeutral()
            }
        }

        alertDialog = alertDialogBuilder.create()
    }

    fun createDialog(title: String, message: String) {
        alertDialogBuilder = AlertDialog.Builder(context)
            .setTitle(title)
            .setMessage(message)
            .setCancelable(false)
    }
}