package dulkirmod.overlays
import dulkirmod.features.chat.DungeonKeyDisplay


class KeyHud : CustomTextHud(false) {

    override fun getLines(lines: MutableList<String>?, example: Boolean) {
        if (example) {
            lines?.add(0, "Wither Key Display")
            return
        }
        if (DungeonKeyDisplay.hasKey) lines?.add(0, "Key Obtained")
    }
}