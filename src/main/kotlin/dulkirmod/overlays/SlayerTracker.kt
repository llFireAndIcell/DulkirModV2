package dulkirmod.overlays

import dulkirmod.utils.SlayerTrackerUtil.averageBossesPerHour
import dulkirmod.utils.SlayerTrackerUtil.averageSpawnKillTime
import dulkirmod.utils.SlayerTrackerUtil.averageXPPerHour
import dulkirmod.utils.SlayerTrackerUtil.currentSlayerType
import dulkirmod.utils.SlayerTrackerUtil.sessionTime
import dulkirmod.utils.SlayerTrackerUtil.sessionXP
import dulkirmod.utils.Utils
import java.text.NumberFormat

class SlayerTracker : CustomTextHud(false) {

    override fun getLines(lines: MutableList<String>?, example: Boolean) {
        if (currentSlayerType != "" && !example) {
            val trimmedSlayer =
                if (currentSlayerType == "Endermen") "Enderman"
                else currentSlayerType.trimEnd('s')
            lines?.add("Slayer: $trimmedSlayer")
            lines?.add("Total XP: ${NumberFormat.getInstance().format(sessionXP)}")
            lines?.add("Spawn + Kill Time: ${"%.2f".format(averageSpawnKillTime)}s")
            lines?.add("Bosses/hr: ${"%.1f".format(averageBossesPerHour)}")
            lines?.add("XP/hr: ${NumberFormat.getInstance().format(averageXPPerHour)}")
            lines?.add("Total Time: ${Utils.formatTime(sessionTime)}")
        }
    }
}