package me.camdenorrb.kspigotbasics.gson

import java.io.File
import java.io.FilenameFilter


object JsonFileNameFilter : FilenameFilter {

	override fun accept(dir: File?, name: String) = name.contains(".json")

}