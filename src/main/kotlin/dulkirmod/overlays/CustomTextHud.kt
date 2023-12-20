package dulkirmod.overlays

import cc.polyfrost.oneconfig.hud.TextHud
import cc.polyfrost.oneconfig.libs.universal.UMatrixStack

abstract class CustomTextHud(enabled: Boolean) : TextHud(enabled) {

    final override fun draw(
        matrices: UMatrixStack?,
        x: Float,
        y: Float,
        scale: Float,
        example: Boolean
    ) {
        if (lines.isNullOrEmpty()) return

        var textY = y
        for (line in lines) {
            drawLine(line, x, textY, scale)
            textY += 9 * scale
        }
    }

    final override fun getHeight(scale: Float, example: Boolean): Float =
        if (lines == null) 0f else (lines.size * 9 - 1) * scale
}