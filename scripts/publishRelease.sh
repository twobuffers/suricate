#!/usr/bin/env bash

SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]:-${0}}")" || exit; pwd -P)"
SCRIPTS_ROOT_DIR="${SCRIPT_DIR}"
REPO_ROOT_DIR=$(cd "${SCRIPTS_ROOT_DIR}/.." || exit; pwd -P)

# https://docs.gradle.org/current/userguide/signing_plugin.html
# https://github.com/vanniktech/gradle-maven-publish-plugin

export PASSWORD_STORE_DIR="${REPO_ROOT_DIR}/.pass-store"
GPG_KEY_ID="0xB1E96730"
GPG_SECRING_PATH="${SCRIPTS_ROOT_DIR}/secring.gpg"
GPG_PASSPHRASE="$(pass show proj/wire/circleci-gpg/passphrase)"
MAVEN_CENTRAL_USERNAME="$(pass show www/sonatype/username)"
MAVEN_CENTRAL_PASSWORD="$(pass show www/sonatype/password)"

# restore secring.gpg
pass show proj/wire/circleci-gpg/secring.gpg | base64 -d > "${GPG_SECRING_PATH}"
# shellcheck disable=SC2064
trap "rm -f \"${GPG_SECRING_PATH}\"" EXIT

# https://github.com/vanniktech/gradle-maven-publish-plugin/blob/master/.github/workflows/publish-release.yml

./gradlew --console=verbose --stacktrace \
          -P"signing.secretKeyRingFile=${GPG_SECRING_PATH}" -P"signing.password=${GPG_PASSPHRASE}" -P"signing.keyId=${GPG_KEY_ID}" \
          -P"mavenCentralUsername=${MAVEN_CENTRAL_USERNAME}"  -P"mavenCentralPassword=${MAVEN_CENTRAL_PASSWORD}" \
          clean assemble publishAllPublicationsToMavenCentralRepository --no-daemon --no-parallel

./gradlew --console=verbose --stacktrace \
          -P"mavenCentralUsername=${MAVEN_CENTRAL_USERNAME}"  -P"mavenCentralPassword=${MAVEN_CENTRAL_PASSWORD}" \
          closeAndReleaseRepository --no-daemon --no-parallel
