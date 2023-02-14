package dulkirmod.command

import dulkirmod.DulkirMod
import net.minecraft.command.ICommandSender

class SettingsCommand : ClientCommandBase("dulkir", mutableListOf("dulkirmod", "dulk")) {
    override fun processCommand(sender: ICommandSender?, args: Array<out String>?) {
        DulkirMod.display = DulkirMod.config.gui()
    }
}