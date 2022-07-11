.PHONY: depsUpdates
depsUpdates:
	-./gradlew dependencyUpdates

.PHONY: publishToMavenLocal
publishToMavenLocal:
	-./scripts/publishToMavenLocal.sh

.PHONY: publishSnapshot
publishSnapshot:
	-./scripts/publishSnapshot.sh

.PHONY: publishRelease
publishRelease:
	-./scripts/publishRelease.sh
