@file:JvmName("TextCompExt")

package me.camdenorrb.kspigotbasics.chat.ext

import net.md_5.bungee.api.chat.ClickEvent
import net.md_5.bungee.api.chat.ComponentBuilder
import net.md_5.bungee.api.chat.HoverEvent
import net.md_5.bungee.api.chat.TextComponent


@JvmOverloads
fun TextComponent.setClick(action: ClickEvent.Action = ClickEvent.Action.OPEN_URL, value: String) {
	this.clickEvent = ClickEvent(action, value)
}

@JvmOverloads
fun TextComponent.setHover(action: HoverEvent.Action = HoverEvent.Action.SHOW_TEXT, value: ComponentBuilder) {
	this.hoverEvent = HoverEvent(action, value.create())
}