plugins {
	kotlin("jvm")
}

sourceSets.main {
	java.srcDirs("src")
}

java {
	modularity.inferModulePath.set(true)
}

kotlin {
	jvmToolchain(23)
	compilerOptions {
		freeCompilerArgs.add("-Xjvm-default=all")
	}
	sourceSets.main {
		kotlin.srcDirs("src")
	}
}

tasks.named("compileJava", JavaCompile::class.java) {
	options.compilerArgumentProviders.add(CommandLineArgumentProvider {
		// Provide compiled Kotlin classes to javac – needed for Java/Kotlin mixed sources to work
		listOf("--patch-module", "com.sterndu.Util=${sourceSets["main"].output.asPath}") // , "--enable-preview"
	})
}