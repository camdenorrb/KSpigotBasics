@file:JvmName("FileExt")

package me.camdenorrb.kspigotbasics.ext

import java.io.File
import java.io.FileReader
import java.io.FileWriter


inline fun <R> File.read(reader: FileReader.() -> R): R = FileReader(this).use(reader)


inline fun File.write(write: FileWriter.() -> Unit) {
	this.parentFile?.mkdirs()
	FileWriter(this).use(write)
}

inline fun File.readOrCreate(read: FileReader.() -> Unit) {
	if (this.parentFile == null || this.parentFile.mkdirs()) return
	FileReader(this).use(read)
}


fun File.create() {
	check(this.exists().not(), { "The file you tried to create already exists" })
	this.parentFile?.mkdirs().also { createNewFile() }
}