package me.camdenorrb.kspigotbasics.types


interface Openable<in T : Any> {

	fun open(target: T)

}