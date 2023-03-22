package dulkirmod.utils

import com.google.common.collect.ComparisonChain
import com.google.common.collect.Ordering
import dulkirmod.DulkirMod.Companion.mc
import net.minecraft.client.network.NetworkPlayerInfo
import net.minecraft.world.WorldSettings

val NetworkPlayerInfo.text: String
    get() = mc.ingameGUI.tabList.getPlayerName(this)

// STOLEN FROM SKYTILS mmm yes
object TabListUtils {
    var area: String = ""
    var explosivity: Boolean = false
    var isInDungeons: Boolean = false
    var maxVisitors: Boolean = false

    private val playerInfoOrdering = object : Ordering<NetworkPlayerInfo>() {
        override fun compare(o1: NetworkPlayerInfo?, o2: NetworkPlayerInfo?): Int {
            val scorePlayerTeam = o1?.playerTeam
            val scorePlayerTeam1 = o2?.playerTeam
            if (o1 != null) {
                if (o2 != null) {
                    return ComparisonChain.start().compareTrueFirst(
                        o1.gameType != WorldSettings.GameType.SPECTATOR,
                        o2.gameType != WorldSettings.GameType.SPECTATOR
                    ).compare(
                        if (scorePlayerTeam != null) scorePlayerTeam.registeredName else "",
                        if (scorePlayerTeam1 != null) scorePlayerTeam1.registeredName else ""
                    ).compare(o1.gameProfile.name, o2.gameProfile.name).result()
                }
                return 0
            }
            return -1
        }
    }
    var tabEntries: List<Pair<NetworkPlayerInfo, String>> = emptyList()
    fun fetchTabEntries(): List<NetworkPlayerInfo> =
        if (mc.thePlayer == null) emptyList() else playerInfoOrdering.sortedCopy(
            mc.thePlayer.sendQueue.playerInfoMap
        )

    /**
     * Sets a bunch of useful values based on the state of the scoreboard. Functionality is collected all into
     * this one method in order to avoid more transversal of the list than is necessary, as these checks need
     * to happen somewhat frequently.
     */
    fun parseTabEntries() {
        // exploFlag is just telling the loop that the next line is the relevant tab entry
        var exploFlag = false
        // dungeonFlag keeps track of whether we've found the in-dungeons state.
        var dungeonFlag = false
        val scoreboardList: List<String> = fetchTabEntries().mapNotNull {
            it.displayName?.unformattedText
        }

        for (line in scoreboardList) {
            if (line.startsWith("Area: "))
                area = line.substring(6)
            else if (line == "Volcano Explosivity:") {
                exploFlag = true
            }
            else if (exploFlag) {
                explosivity = line != " INACTIVE"
                exploFlag = false
            }
            else if (line == "       Dungeon Stats") {
                isInDungeons = true
                dungeonFlag = true
            }
            // Here is some scuffed code that basically makes sure maxVisitors is assigned appropriately.
            // It's awful and I do not care to fix it lol.
            else if (line == " Next Visitor: Queue Full!")
                maxVisitors = true
            else if (line.startsWith(" Next Visitor:"))
                maxVisitors = false
        }

        if (area != "Crimson Isle")
            explosivity = false
        if (area != "Garden")
            maxVisitors = false
        if (!dungeonFlag)
            isInDungeons = false
    }
}