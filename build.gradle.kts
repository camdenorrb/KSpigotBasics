plugins {
	kotlin("jvm") version "1.6.21"
	id("com.github.johnrengelman.shadow") version "7.1.2"
}

group = "me.camdenorrb"
version = "1.0.3"

repositories {
	mavenCentral()
	maven("https://jitpack.io")
	maven("https://hub.spigotmc.org/nexus/content/repositories/public/") {
		name = "SpigotMC"
	}
}

dependencies {

	compileOnly("org.spigotmc:spigot-api:1.18.2-R0.1-SNAPSHOT")

	implementation("com.sxtanna.db:Kuery:+")
	implementation("com.sxtanna.database:Kedis:+")
	implementation("com.github.camdenorrb:KDI:V1.0.1")
	implementation("com.github.MiniMineCraft:MiniBus:V1.2.6")
	implementation("com.github.okkero:Skedule:v1.2.6")
	implementation("org.jetbrains.kotlin:kotlin-reflect:+")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:+")

	//https://github.com/Kotlin/kotlinx.coroutines/issues/659

	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:+") {
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
		kotlinOptions.jvmTarget = "1.8"
		kotlinOptions.freeCompilerArgs = listOf("-Xuse-experimental=kotlin.ExperimentalStdlibApi")
	}

	compileTestKotlin {
		kotlinOptions.jvmTarget = "1.8"
	}
}

