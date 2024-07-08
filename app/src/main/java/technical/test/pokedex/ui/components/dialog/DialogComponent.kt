package technical.test.pokedex.ui.components.dialog

import android.content.Context
import androidx.appcompat.app.AlertDialog
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector

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

@Composable
fun AlertDialog(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    dialogTitle: String,
    dialogText: String,
    confirmButtonText: String = "Confirm",
    dismissButtonText: String = "Dismiss",
    icon: ImageVector? = null,
) {
    AlertDialog(
        icon = {
            icon?.let {
                Icon(icon, contentDescription = "Example Icon")
            }
        },
        title = {
            Text(text = dialogTitle)
        },
        text = {
            Text(text = dialogText)
        },
        onDismissRequest = {
            onDismissRequest()
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirmation()
                }
            ) {
                Text(confirmButtonText)
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    onDismissRequest()
                }
            ) {
                Text(dismissButtonText)
            }
        }
    )
}
