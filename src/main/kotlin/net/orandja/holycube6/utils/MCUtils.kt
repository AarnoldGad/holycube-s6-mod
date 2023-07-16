package net.orandja.holycube6.utils

import net.minecraft.server.network.ServerPlayerEntity
import net.minecraft.text.LiteralTextContent
import net.minecraft.text.MutableText
import net.minecraft.text.Text

fun ServerPlayerEntity.sendHUD(message: Any) {
    sendMessage(message as? Text ?: MutableText.of(LiteralTextContent(message.toString())), true)
}
