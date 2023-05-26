package dulkirmod.overlays

import cc.polyfrost.oneconfig.hud.TextHud
import cc.polyfrost.oneconfig.libs.universal.UMatrixStack
import dulkirmod.DulkirMod.Companion.mc
import dulkirmod.config.DulkirConfig
import dulkirmod.utils.TabListUtils
import dulkirmod.utils.Utils

class YawDisplayHud : TextHud(false) {

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
        if (!Utils.isInSkyblock()) return
        if (!DulkirConfig.showYawEverywhere && TabListUtils.area != "Garden") return

        val pitch = mc.thePlayer.rotationPitch
        var yaw = mc.thePlayer.rotationYaw % 360f

        if (yaw < -180.0f) yaw += 360.0f
        else if (yaw > 180.0f) yaw -= 360.0f

        if (DulkirConfig.yaw3Decimals) lines?.add(0, String.format("Yaw: %.3f", yaw))
        else lines?.add(0, String.format("Yaw: %.2f", yaw))

        if (DulkirConfig.showPitch)
            lines?.add(1, String.format("Pitch: %.2f", pitch))
    }
}