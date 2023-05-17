package dulkirmod.overlays
import cc.polyfrost.oneconfig.hud.TextHud
import cc.polyfrost.oneconfig.libs.universal.UMatrixStack
import dulkirmod.features.chat.DungeonKeyDisplay


class KeyHud : TextHud(false) {

    override fun draw(matrices: UMatrixStack?, x: Float, y: Float, scale: Float, example: Boolean) {
        if (lines.isNullOrEmpty()) return

        var textY = y
        for (line in lines) {
            drawLine(line, x, textY, scale)
            textY += 9 * scale
        }
    }

    override fun getHeight(scale: Float, example: Boolean): Float {
        return if (lines == null) 0f else (lines.size * 9 - 1) * scale
    }

    override fun getLines(lines: MutableList<String>?, example: Boolean) {
        if (example) {
            lines?.add(0, "Wither Key Display")
            return
        }
        if (DungeonKeyDisplay.hasKey) lines?.add(0, "Key Obtained")
    }
}