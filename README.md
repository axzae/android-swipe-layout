# Android Swipe Layout

<p>

[![build](https://img.shields.io/github/actions/workflow/status/axzae/android-swipe-layout/pre-merge.yaml?branch=master)][actions]
[![github tag](https://img.shields.io/github/v/tag/axzae/android-swipe-layout?label=github)][releases]
[![maven central](https://img.shields.io/maven-central/v/com.axzae/swipelayout)][mavencentral]

</p>

This is an **AndroidX implementation** for [daimajia's AndroidSwipeLayout](https://github.com/daimajia/AndroidSwipeLayout).

By using this version, you can safety turn off [Jetifier][jetifier] in your project.

## Usage

#### Gradle

```properties
# gradle.properties

android.useAndroidX=true
android.enableJetifier=false
```

```kotlin
// build.gradle.kts (app module)

dependencies {
    implementation("com.axzae:swipelayout:1.3.0")
}
```

#### Layout file
```xml
<com.daimajia.swipe.SwipeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/layout_swipe"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    app:drag_edge="right"
    app:show_mode="pull_out">

    <!-- <LinearLayout /> -->

</com.daimajia.swipe.SwipeLayout>
```


## See Also

- [Usage](https://github.com/daimajia/AndroidSwipeLayout/wiki/Usage)
- [SwipeAdapter](https://github.com/daimajia/AndroidSwipeLayout/wiki/SwipeAdapter)

[mavencentral]: https://central.sonatype.com/artifact/com.axzae/swipelayout
[actions]: https://github.com/axzae/android-swipe-layout/actions
[releases]: https://github.com/axzae/android-swipe-layout/releases
[jetifier]: https://developer.android.com/tools/jetifier
