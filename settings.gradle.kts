rootProject.name = "KSpigotBasics"

sourceControl {
	gitRepository(java.net.URI.create("https://github.com/MiniMineCraft/MiniBus.git")) {
		producesModule("me.camdenorrb:MiniBus")
	}
}

sourceControl {
	gitRepository(java.net.URI.create("https://github.com/camdenorrb/KDI.git")) {
		producesModule("me.camdenorrb:KDI")
	}
}