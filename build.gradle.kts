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

	implementation("com.sxtanna.db:Kuery:1.6")
	implementation("com.sxtanna.database:Kedis:1.2")
	implementation("com.github.camdenorrb:KDI:V1.0.1")
	implementation("me.camdenorrb:MiniBus:1.4.0")
	implementation("com.github.okkero:Skedule:v1.2.6")
	implementation("org.jetbrains.kotlin:kotlin-reflect:1.6.21")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.6.21")

	//https://github.com/Kotlin/kotlinx.coroutines/issues/659

	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.1") {
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

