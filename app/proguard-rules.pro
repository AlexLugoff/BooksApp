# --- R8 / ProGuard Optimization Rules ---

# Preserve line number information for better stack traces in crash reports
-keepattributes SourceFile,LineNumberTable

# Preserve Annotations for reflection-based libraries (Hilt, Retrofit, Gson)
-keepattributes *Annotation*
-keepattributes Signature
-keepattributes InnerClasses,EnclosingMethod

# --- Retrofit & OkHttp ---
-dontwarn retrofit2.**
-keepattributes Signature, MethodParameters
-keep @interface retrofit2.http.** { *; }
-keepclassmembers interface * {
    @retrofit2.http.** <methods>;
}
-dontwarn okhttp3.**
-dontwarn okio.**
-dontwarn javax.annotation.**
-dontwarn org.conscrypt.**

# --- Gson ---
# Keep all classes in the model package to avoid issues with serialization
-keep class io.github.alexlugoff.booksapp.core.network.model.** { *; }
-keep class io.github.alexlugoff.booksapp.core.model.** { *; }

# Also keep fields annotated with @SerializedName
-keepclassmembers class * {
    @com.google.gson.annotations.SerializedName <fields>;
}

# --- Hilt / Dagger ---
-keep class dagger.hilt.android.internal.managers.** { *; }
-dontwarn private.com.google.dexmaker.**

# --- Kotlin Serialization ---
-keepclassmembernames class * {
    @kotlinx.serialization.SerialName <fields>;
}

# --- Timber ---
-dontwarn timber.log.**
-keep class timber.log.** { *; }

# --- Optimization ---
# Encourage more aggressive shrinking and optimization
-repackageclasses ''
-allowaccessmodification
-mergeinterfacesaggressively

# --- Compose ---
# Compose usually handles itself, but these attributes are helpful
-keepattributes RuntimeVisibleAnnotations,RuntimeVisibleParameterAnnotations
-keepclassmembers class * {
    @androidx.compose.runtime.Composable <methods>;
    @androidx.compose.runtime.ReadOnlyComposable <methods>;
}
