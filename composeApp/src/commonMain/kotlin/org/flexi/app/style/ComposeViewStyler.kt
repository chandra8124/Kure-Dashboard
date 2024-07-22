package org.flexi.app.style

object ComposeViewStyler {

    fun colorFromStr(colorStr: String, default: String = KureColors.WHITE.value) =
        try {
          //  Color(colorStr.toColorInt())
        } catch (e: Exception) {
           // Color(default.toColorInt())
        }

}