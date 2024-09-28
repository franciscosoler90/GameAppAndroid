package entity.enums

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.automirrored.rounded.ArrowForward
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Search
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.fransoler.R

enum class BottomBarDestination(
    val state : BottomBarState,
    @StringRes val label: Int,
    val icon: ImageVector
) {
    Home(BottomBarState.HOME, R.string.home, Icons.Rounded.Home),
    Search(BottomBarState.SEARCH, R.string.search, Icons.Rounded.Search),
    Favorite(BottomBarState.FAVORITE, R.string.favorites, Icons.Rounded.Favorite),
}


enum class BottomGameBarDestination(
    @StringRes val label: Int,
    val icon: ImageVector
) {
    Back(R.string.back, Icons.AutoMirrored.Rounded.ArrowBack),
    Forward(R.string.forward, Icons.AutoMirrored.Rounded.ArrowForward),
}