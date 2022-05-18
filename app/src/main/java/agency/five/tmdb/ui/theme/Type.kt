package agency.five.tmdb.ui.theme

import agency.five.tmdb.R
import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Set of Material typography styles to start with
val Typography = Typography(
    h1 = TextStyle(
        fontFamily = FontFamily(
            Font(R.font.proxima_nova_regular, FontWeight.Normal),
            Font(R.font.proxima_nova_bold, FontWeight.Bold),
            Font(R.font.proxima_nova_extrabold, FontWeight.ExtraBold)
        ),
        fontWeight = FontWeight.ExtraBold,
        fontSize = 20.sp,
    ),
    h2 = TextStyle(
        fontFamily = FontFamily(
            Font(R.font.proxima_nova_regular, FontWeight.Normal),
            Font(R.font.proxima_nova_bold, FontWeight.Bold),
            Font(R.font.proxima_nova_extrabold, FontWeight.ExtraBold)
        ),
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
    ),
    h3 = TextStyle(
        fontFamily = FontFamily(
            Font(R.font.proxima_nova_regular, FontWeight.Normal),
            Font(R.font.proxima_nova_bold, FontWeight.Bold),
            Font(R.font.proxima_nova_extrabold, FontWeight.ExtraBold)
        ),
        fontWeight = FontWeight.ExtraBold,
        fontSize = 15.sp,
    ),
    h4 = TextStyle(
        fontFamily = FontFamily(
            Font(R.font.proxima_nova_regular, FontWeight.Normal),
            Font(R.font.proxima_nova_bold, FontWeight.Bold),
            Font(R.font.proxima_nova_extrabold, FontWeight.ExtraBold)
        ),
        fontWeight = FontWeight.Bold,
        fontSize = 17.sp,
    ),
    h5 = TextStyle(
        fontFamily = FontFamily(
            Font(R.font.proxima_nova_regular, FontWeight.Normal),
            Font(R.font.proxima_nova_bold, FontWeight.Bold),
            Font(R.font.proxima_nova_extrabold, FontWeight.ExtraBold)
        ),
        fontWeight = FontWeight.ExtraBold,
        fontSize = 24.sp,
    ),
    h6 = TextStyle(
        fontFamily = FontFamily(
            Font(R.font.proxima_nova_regular, FontWeight.Normal),
            Font(R.font.proxima_nova_bold, FontWeight.Bold),
            Font(R.font.proxima_nova_extrabold, FontWeight.ExtraBold)
        ),
        fontWeight = FontWeight.Normal,
        fontSize = 24.sp,
    ),

    body1 = TextStyle(
        fontFamily = FontFamily(
            Font(R.font.proxima_nova_regular, FontWeight.Normal),
            Font(R.font.proxima_nova_bold, FontWeight.Bold),
            Font(R.font.proxima_nova_extrabold, FontWeight.ExtraBold)
        ),
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp
    ),
    body2 = TextStyle(
        fontFamily = FontFamily(
            Font(R.font.proxima_nova_regular, FontWeight.Normal),
            Font(R.font.proxima_nova_bold, FontWeight.Bold),
            Font(R.font.proxima_nova_extrabold, FontWeight.ExtraBold)
        ),
        fontWeight = FontWeight.ExtraBold,
        fontSize = 16.sp
    ),
    button = TextStyle(
        fontFamily = FontFamily(
            Font(R.font.proxima_nova_regular, FontWeight.Normal),
            Font(R.font.proxima_nova_bold, FontWeight.Bold),
            Font(R.font.proxima_nova_extrabold, FontWeight.ExtraBold)
        ),
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp
    ),
    subtitle1 = TextStyle(
        fontFamily = FontFamily(
            Font(R.font.proxima_nova_regular, FontWeight.Normal),
            Font(R.font.proxima_nova_bold, FontWeight.Bold),
            Font(R.font.proxima_nova_extrabold, FontWeight.ExtraBold)
        ),
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
    ),
)