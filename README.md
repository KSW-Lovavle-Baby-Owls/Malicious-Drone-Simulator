# Malicious Drone Simulator(MDS)

## What is this?

  The Malicious drone simulator application takes over the detection aspect of the anti-drone system. By utilizing the DJI Android API, the malicious Drone Simulator application establishes a connection with a DJI drone, which assumes the role of a malicious drone. It retrieves the GPS data of the connected drone and transmits it to the server.
<br>
<br>

### What are functions?

1. Display TSPI data.
2. Transfer data to a database(Firebase)
3. Save the Log file(.csv).

<br>
<br>

### Test Environment

- Device: Motorola g7 (Android 10)
- laptop: Macbook Air 2(M2)

<br>
<br>

### 🖥️ Application User Interface

- Register Page

![Untitled](https://github.com/Uk-jake/ModifyReadme/blob/main/MDS/Malicious%20Drone%20Simulator(MDS)%20175031bb2dde4afc8c811734529cca52/Untitled.png?raw=true)

- Main Page

![Untitled](https://github.com/Uk-jake/ModifyReadme/blob/main/MDS/Malicious%20Drone%20Simulator(MDS)%20175031bb2dde4afc8c811734529cca52/Untitled%201.png?raw=true)

<br>
<br>

### 📁 Directory

```html
.
├── main
│   ├── AndroidManifest.xml
│   ├── java
│   │   └── com
│   │       └── dji
│   │           └── sdk
│   │               └── maldronesim
│   │                   ├── MApplication.java
│   │                   ├── MainActivity.java
│   │                   ├── RegisterActivity.java
│   │                   └── TSPI.java
│   └── res
│       ├── drawable
│       ├── drawable-v24
│       ├── layout
│       │   ├── activity_connection.xml
│       │   └── activity_main.xml
│       ├── mipmap
│       ├── values
│       │   ├── colors.xml
│       │   ├── strings.xml
│       │   └── themes.xml
│       ├── values-night
│       └── xml
```


<br>
<br>

### ⚠️Precautions

- You need to connect your DJI drone to go to the main page of the app. You cannot go to the main page of the app without the drone.
- When using the application for the first time, Wi-Fi must be connected because the DJI SDK must be installed.
- You must restart the application after compiling it to run the application.

---

<br>
<br>


## 🗒️ Environment settings

### Requirements

- Android Studio 3.2+
- Android System 4.1+
- DJI Android SDK 4.16.4

### Required API key

- DJI Developer Key
- Firebase API Key

### Integration

Declare dependency via 

**Gradle:app**

```java
dependencies {
    implementation 'androidx.appcompat:appcompat:1.6.1'
    implementation 'androidx.constraintlayout:constraintlayout:2.1.4'
    implementation 'com.google.android.material:material:1.4.0-alpha02'
    implementation 'com.google.firebase:firebase-firestore:24.1.1'
    implementation ('com.google.firebase:firebase-bom:32.0.0')
    testImplementation 'junit:junit:4.13.2'
    androidTestImplementation 'androidx.test.ext:junit:1.1.5'
    androidTestImplementation 'androidx.test.espresso:espresso-core:3.5.1'
    implementation 'com.google.android.gms:play-services-maps:18.1.0'
    implementation 'com.google.android.gms:play-services-location:20.0.0'
    implementation 'com.google.android.material:material:1.0.0'
    implementation 'com.squareup:otto:1.3.8'
    implementation('com.dji:dji-sdk:4.16.4', {
        exclude module: 'fly-safe-database'
        exclude module: 'library-anti-distortion'
    })
    compileOnly 'com.dji:dji-sdk-provided:4.16.4'
}
```

**Gradle: Project**

```java
dependencies {
        classpath 'com.google.gms:google-services:4.3.15'
    }
```

For further detail on how to integrate the DJI Android SDK into your Android Studio project, please check the [Integrate SDK into Application](http://developer.dji.com/mobile-sdk/documentation/application-development-workflow/workflow-integrate.html#import-maven-dependency) tutorial.

### Android Gradle Plugin Version + Gradle Version

- Android Gradle Plugin Version : 7.4.0
- Gradle Version : 7.5

![Untitled](https://github.com/Uk-jake/ModifyReadme/blob/main/MDS/Malicious%20Drone%20Simulator(MDS)%20175031bb2dde4afc8c811734529cca52/Untitled%202.png?raw=true)

### Modules

- Recommend 33(API : Android 13.0) or lower

![Untitled](https://github.com/Uk-jake/ModifyReadme/blob/main/MDS/Malicious%20Drone%20Simulator(MDS)%20175031bb2dde4afc8c811734529cca52/Untitled%203.png?raw=true)

### JDK version

- Recommend JDK 17 or JDK 8

![Untitled](https://github.com/Uk-jake/ModifyReadme/blob/main/MDS/Malicious%20Drone%20Simulator(MDS)%20175031bb2dde4afc8c811734529cca52/Untitled%204.png?raw=true)

### Modules

| Dependency | Configuration |
| --- | --- |
| androidx.appcompat:appcompat:1.6.1 | implementation |
| androidx.constraintlayout:constraintlayout:2.1.4 | implementation |
| com.dji:dji-sdk-provided:4.16.4 | compileOnly |
| com.dji:dji-sdk:4.16.4 | implementation |
| androidx.test.espresso:espresso-core:3.5.1 | androidTestImplementation |
| com.google.firebase:firebase-bom:32.0.0 | implementation |
| com.google.firebase:firebase-firestore:24.1.1 | implementation |
| androidx.test.ext:junit:1.1.5 | androidTestImplementation |
| junit:junit:4.13.2 | testImplementation |
| com.google.android.material:material:1.0.0 | implementation |
| com.google.android.material:material:1.4.0-alpha02 | implementation |
| com.squareup:otto:1.3.8 | implementation |
| com.google.android.gms:play-services-location:20.0.0 | implementation |

---

<br>
<br>

## 📚 Reference

The following reference were used to develop the application.

- https://github.com/dji-sdk/Mobile-SDK-Android
- [Add Firebase Android project](https://firebase.google.com/docs/android/setup)
- [DJI Developer Document](https://developer.dji.com/document/544659e8-9dab-4ad8-9414-a31e1c9b89b1)
- [https://github.com/DJI-Mobile-SDK-Tutorials](https://github.com/DJI-Mobile-SDK-Tutorials)

---

<br>
<br>


## 📞 Contact

- Uk Jang
    - E-Mail: mag0225@naver.com
    
    [Uk-jake - Overview](https://github.com/Uk-jake)
