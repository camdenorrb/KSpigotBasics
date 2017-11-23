@file:JvmName("SkullUtils")


package me.camdenorrb.kspigotbasics.utils

import me.camdenorrb.kspigotbasics.ext.skullMeta
import org.bukkit.Material.SKULL_ITEM
import org.bukkit.OfflinePlayer
import org.bukkit.inventory.ItemStack
import java.util.*


fun skull() = ItemStack(SKULL_ITEM, 1, 3)


fun skull(uuid: UUID) = ItemStack(SKULL_ITEM, 1, 3).skullMeta {
	owner = findOfflinePlayer(uuid)?.name
}

fun OfflinePlayer.skull() = ItemStack(SKULL_ITEM, 1, 3).skullMeta {
	owner = this@skull.name
}

fun skull(playerName: String) = ItemStack(SKULL_ITEM, 1, 3).skullMeta {
	owner = playerName
}



// Mobs

fun alexSkull() = skull("MHF_Alex")

fun blazeSkull() = skull("MHF_Blaze")

fun caveSpiderSkull() = skull("MHF_CaveSpider")

fun chickenSkull() = skull("MHF_Chicken")

fun cowSkull() = skull("MHF_Cow")

fun creeperSkull() = skull("MHF_Creeper")

fun endermanSkull() = skull("MHF_Enderman")

fun ghastSkull() = skull("MHF_Ghast")

fun golemSkull() = skull("MHF_Golem")

fun herobrineSkull() = skull("MHF_Herobrine")

fun lavaslimeSkull() = skull("MHF_LavaSlime")

fun mooshroomSkull() = skull("MHF_MushroomCow")

fun ocelotSkull() = skull("MHF_Ocelot")

fun pigSkull() = skull("MHF_Pig")

fun pigZombieSkull() = skull("MHF_PigZombie")

fun sheepSkull() = skull("MHF_Sheep")

fun skeletonSkull() = skull("MHF_Skeleton")

fun slimeSkull() = skull("MHF_Slime")

fun spiderSkull() = skull("MHF_Spider")

fun squidSkull() = skull("MHF_Squid")

fun steveSkull() = skull("MHF_Steve")

fun villagerSkull() = skull("MHF_Villager")

fun witherSkeletonSkull() = skull("MHF_WSkeleton")

fun zombieSkull() = skull("MHF_Zombie")



// Blocks

fun cactusSkull() = skull("MHF_Cactus")

fun cakeSkull() = skull("MHF_Cake")

fun chestSkull() = skull("MHF_Chest")

fun coconutBSkull() = skull("MHF_CoconutB")

fun coconutGSkull() = skull("MHF_CoconutG")

fun melonSkull() = skull("MHF_Melon")

fun oakLogSkull() = skull("MHF_OakLog")

fun present1Skull() = skull("MHF_Present1")

fun present2Skull() = skull("MHF_Present2")

fun pumpkinSkull() = skull("MHF_Pumpkin")

fun tntSkull() = skull("MHF_TNT")

fun tnt2Skull() = skull("MHF_TNT2")


// Bonus

fun arrowUpSkull() = skull("MHF_ArrowUp")

fun arrowDownSkull() = skull("MHF_ArrowDown")

fun arrowLeftSkull() = skull("MHF_ArrowLeft")

fun arrowRightSkull() = skull("MHF_ArrowRight")

fun exclamationSkull() = skull("MHF_Exclamation")

fun questionSkull() = skull("MHF_Question")