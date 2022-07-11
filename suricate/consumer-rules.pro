# Rules to Okhttp based on warning:

#    WARNING:Missing classes detected while running R8. Please add the missing classes or apply additional keep rules that are generated in /Users/greg/Sources/opensource/twobuffers/suricate/sample/build/outputs/mapping/release/missing_rules.txt.
#
#    WARNING:R8:
#    Missing class org.bouncycastle.jsse.BCSSLParameters (referenced from: void okhttp3.internal.platform.BouncyCastlePlatform.configureTlsExtensions(javax.net.ssl.SSLSocket, java.lang.String, java.util.List) and 1 other context)
#    Missing class org.bouncycastle.jsse.BCSSLSocket (referenced from: void okhttp3.internal.platform.BouncyCastlePlatform.configureTlsExtensions(javax.net.ssl.SSLSocket, java.lang.String, java.util.List) and 5 other contexts)
#    Missing class org.bouncycastle.jsse.provider.BouncyCastleJsseProvider (referenced from: void okhttp3.internal.platform.BouncyCastlePlatform.<init>())
#    Missing class org.conscrypt.Conscrypt$Version (referenced from: boolean okhttp3.internal.platform.ConscryptPlatform$Companion.atLeastVersion(int, int, int))
#    Missing class org.conscrypt.Conscrypt (referenced from: boolean okhttp3.internal.platform.ConscryptPlatform$Companion.atLeastVersion(int, int, int) and 4 other contexts)
#    Missing class org.conscrypt.ConscryptHostnameVerifier (referenced from: okhttp3.internal.platform.ConscryptPlatform$DisabledHostnameVerifier)
#    Missing class org.openjsse.javax.net.ssl.SSLParameters (referenced from: void okhttp3.internal.platform.OpenJSSEPlatform.configureTlsExtensions(javax.net.ssl.SSLSocket, java.lang.String, java.util.List))
#    Missing class org.openjsse.javax.net.ssl.SSLSocket (referenced from: void okhttp3.internal.platform.OpenJSSEPlatform.configureTlsExtensions(javax.net.ssl.SSLSocket, java.lang.String, java.util.List) and 1 other context)
#    Missing class org.openjsse.net.ssl.OpenJSSE (referenced from: void okhttp3.internal.platform.OpenJSSEPlatform.<init>())

# This is generated automatically by the Android Gradle plugin.
-dontwarn org.bouncycastle.jsse.BCSSLParameters
-dontwarn org.bouncycastle.jsse.BCSSLSocket
-dontwarn org.bouncycastle.jsse.provider.BouncyCastleJsseProvider
-dontwarn org.conscrypt.Conscrypt$Version
-dontwarn org.conscrypt.Conscrypt
-dontwarn org.conscrypt.ConscryptHostnameVerifier
-dontwarn org.openjsse.javax.net.ssl.SSLParameters
-dontwarn org.openjsse.javax.net.ssl.SSLSocket
-dontwarn org.openjsse.net.ssl.OpenJSSE


# Rule to keep Status enum:

-keepclassmembers enum com.twobuffers.suricate.Status {
    <fields>;
}
