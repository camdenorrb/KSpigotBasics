plugins {
	kotlin("jvm") version "2.1.20"
	id("com.github.johnrengelman.shadow") version "8.1.1"
}

group = "me.camdenorrb"
version = "1.1.0"

repositories {
	mavenCentral()
	maven("https://jitpack.io")
	maven("https://hub.spigotmc.org/nexus/content/repositories/public/") {
		name = "SpigotMC"
	}
}

dependencies {

	compileOnly("org.spigotmc:spigot-api:1.21.4-R0.1-SNAPSHOT")

	implementation(kotlin("reflect"))
	implementation(kotlin("stdlib-jdk8"))

	implementation("com.sxtanna.db:Kuery:1.6")
	implementation("com.sxtanna.database:Kedis:1.2")
	implementation("me.camdenorrb:KDI:1.1.0")
	implementation("me.camdenorrb:MiniBus:1.4.0")
	implementation("com.github.okkero:Skedule:v1.2.6")


	//https://github.com/Kotlin/kotlinx.coroutines/issues/659

	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.10.1") {
		exclude(group = "org.jetbrains.kotlinx", module = "atomicfu-common")
	}

	//compile "org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutineVersion"
}


/*
dokkaHtml {
	outputFormat = 'javadoc'
	outputDirectory = "docs"
}
*/

tasks {
	compileKotlin {
		kotlinOptions.freeCompilerArgs = listOf("-Xuse-experimental=kotlin.ExperimentalStdlibApi")
	}
	shadowJar {
		relocate("org.jetbrains", "me.camdenorrb.kspigotbasics.libs.org.jetbrains")
		relocate("org.intellij", "me.camdenorrb.kspigotbasics.libs.org.intellij")
		relocate("com.google", "me.camdenorrb.kspigotbasics.libs.com.google")
		relocate("kotlin", "me.camdenorrb.kspigotbasics.libs.kotlin")
	}
}

