pluginManagement {
	repositories {
		maven("https://repository.hanbings.io/proxy") {
			name = "Fabric"
		}
		maven("https://repo.legacyfabric.net/repository/legacyfabric/") {
			name = "legacy-fabric"
		}
		mavenCentral()
		gradlePluginPortal()
	}
}

include("1_7_10f")
include("1_12_2f")
include("1_13_2f")
include("pre26f")
include("26xf")
include("1_7_10m")
include("1_12_2m")
include("1_16_5m")
include("1_20_4m")
include("26xm")
include("spigot")
