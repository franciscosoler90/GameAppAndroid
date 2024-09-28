package common

import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.example.fransoler.R
import entity.Game

object ContextUtilities {
    fun shareGame(context: Context, game: Game) {
        val shareIntent = Intent().apply {
            action = Intent.ACTION_SEND
            type = "text/plain"
            putExtra(Intent.EXTRA_SUBJECT, Constant.APP_NAME)
            putExtra(Intent.EXTRA_TEXT, Constant.URL + game.slug)
        }
        context.startActivity(Intent.createChooser(shareIntent, null))
    }


    fun onToggleFavorite(context: Context, favorite: Boolean) {
        if(favorite){
            Toast.makeText(context,R.string.addFavorite, Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(context, R.string.deleteFavorite, Toast.LENGTH_SHORT).show()
        }
    }
}