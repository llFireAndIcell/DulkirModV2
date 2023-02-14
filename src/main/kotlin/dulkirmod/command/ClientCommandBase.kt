package dulkirmod.command

import net.minecraft.command.CommandBase
import net.minecraft.command.ICommandSender

abstract class ClientCommandBase protected constructor(
    private val name: String,
    private val aliases: MutableList<String> = mutableListOf()
) : CommandBase() {
    override fun getCommandName(): String = name
    override fun getCommandAliases(): MutableList<String> = aliases
    override fun getCommandUsage(sender: ICommandSender): String = "/$name"
    override fun canCommandSenderUseCommand(sender: ICommandSender): Boolean = true
}