package utils

import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.example.fransoler.R
import common.Constant
import entity.Game

object ContextUtilities {

    fun shareGame(context: Context, game: Game) {
        val shareIntent = Intent().apply {
            action = Intent.ACTION_SEND
            type = "text/plain"
            putExtra(Intent.EXTRA_SUBJECT, Constant.APP_NAME)
            putExtra(Intent.EXTRA_TEXT, Constant.URL + game.slug)
        }

        try {
            context.startActivity(Intent.createChooser(shareIntent, null))
        } catch (e: Exception) {
            Toast.makeText(context, R.string.errorSharing, Toast.LENGTH_SHORT).show()
        }
    }

    fun showToast(context: Context, resId: Int, duration: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(context, context.getString(resId), duration).show()
    }

}