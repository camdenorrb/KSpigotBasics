@file:JvmName("TextCompUtils")

package me.camdenorrb.kspigotbasics.chat.utils

import net.md_5.bungee.api.chat.BaseComponent
import net.md_5.bungee.api.chat.TextComponent
import org.bukkit.util.Consumer


inline fun textComp(text: String = "", block: TextComponent.() -> Unit = {}): TextComponent {
	return TextComponent(text).apply(block)
}

inline fun textComp(component: TextComponent, block: TextComponent.() -> Unit = {}): TextComponent {
	return TextComponent(component).apply(block)
}

inline fun textComp(vararg extras: BaseComponent, block: TextComponent.() -> Unit = {}): TextComponent {
	return TextComponent(*extras).apply(block)
}


// For Java users

@JvmOverloads
fun textComp(text: String = "", consumer: Consumer<TextComponent> = Consumer {  }): TextComponent {
	return TextComponent(text).apply { consumer.accept(this) }
}

@JvmOverloads
fun textComp(component: TextComponent, consumer: Consumer<TextComponent> = Consumer {  }): TextComponent {
	return TextComponent(component).apply { consumer.accept(this) }
}

@JvmOverloads
fun textComp(vararg extras: BaseComponent, consumer: Consumer<TextComponent> = Consumer {  }): TextComponent {
	return TextComponent(*extras).apply { consumer.accept(this) }
}
