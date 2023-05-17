package dulkirmod.overlays

import cc.polyfrost.oneconfig.hud.TextHud
import cc.polyfrost.oneconfig.libs.universal.UMatrixStack
import dulkirmod.config.DulkirConfig
import dulkirmod.utils.TabListUtils
import dulkirmod.utils.Utils

class GardenInfoHud : TextHud(false) {

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
        if (TabListUtils.area != "Garden") return
        var i = 0
        if (DulkirConfig.gardenMilestoneDisplay) {
            lines?.add(i, TabListUtils.gardenMilestone)
            ++i
        }
        if (DulkirConfig.visitorInfo) {
            lines?.add(i, "Visitors: ${TabListUtils.numVisitors} - ${TabListUtils.timeTillNextVisitor}")
            ++i
        }
        if (DulkirConfig.composterAlert) {
            if (TabListUtils.emptyComposter) {
                lines?.add(i, "Empty Composter!")
                ++i
            }
        }
    }
}