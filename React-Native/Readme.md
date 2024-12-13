# App con React Native

## Descripción

Implmementación de una app sencilla con React Native, sin el uso de frameworks para inicializarla

## Pasos a seguir

### Inicialización del proyecto

1. Creamos el directorio en donde vamos a guardar el proyecto.
2. Creamos el archivo README.md.
3. Inicializamos el control de versiones con git.

```sh
$ git init
$ git add .
$ git commit -m "Commit inicial"
```

4. Creamos la rama "develop" para llevar el control de las ramas de git.

```sh
$ git checkout -b develop
```

### Creación del proyecto

1. Inicializamos el proyecto de React-Native con el nombre de la carpeta MyFirstApp.

```sh
$ npx react-native@latest init MyFirstApp
```

2. Se te preguntará la versión de React-Native para proceder.
3. Se te preguntará si quieres instalar CocoaPods para poder trabajar también con XCode.

> Nota: En caso de que no se haya instalado CocoaPods, se puede instalar con el siguiente comando.
>
> ```sh
> $ cd MyFirstApp/ios
> $ bundle install
> $ bundle exec pod install
> $ cd ..
> ```

4. Elimina la carpeta `.git` autogenerada en el proyecto.

```sh
$ cd MyFirstApp
$ rm -rf .git
$ cd ..
```

5. Agrega los archivos al control de versiones y guarda los cambios.

```sh
$ git add .
$ git commit -m "Creación del proyecto"
```

### Ejecución del proyecto

1. Acceder a la carpeta del proyecto.

```sh
$ cd MyFirstApp
```

2. Ejecutar alguna de las siguientes instrucciones.

```sh
# Para correr el entorno Metro y seleccionar la opción del menú
$ npm start

# Para ejecutar la aplicación en Android
$ npm run android

# Para ejecutar la aplicación en iOS
$ npm run ios
```

> Nota: Se requiere que se tenga ejecutando el emulador de Android o iOS corriendo antes de ejecutar el comando.

## Librerías utilizadas

### React Native Papers

[Documentación](https://reactnativepaper.com/): Librería de componentes para React Native.

```sh
$ npm install react-native-paper
$ npm install react-native-safe-area-context
$ npx pod-install
$ npm i --save-dev @types/react-native-vector-icons
```

### React Navive Vector Icons

- [Documentación](https://github.com/oblador/react-native-vector-icons#installation): Librería de iconos para React Native.
- [Conjuntos de iconos disponibles](https://github.com/oblador/react-native-vector-icons?tab=readme-ov-file#bundled-icon-sets)

```sh
$ npm install react-native-vector-icons
```

#### Configuración de la librería en Android

1. Abrir el archivo build.gradle de la carpeta android/app.
2. Agregar la siguiente línea en la sección de dependencias.

```gradle
apply from: "../../node_modules/react-native-vector-icons/fonts.gradle"
```

> Nota. En caso de que se requiera agregar solo un conjunto de iconos, se puede agregar la siguiente configuración antes de la línea anterior.
>
> ```gradle
> project.ext.vectoricons = [
>     iconFontNames: [ 'MaterialIcons.ttf' ]
> ]
> ```

#### Configuración de la librería en iOS

1. Ejecutar el siguiente comando para sincronizar los iconos con la aplicación.

```sh
$ npx pod-install
```

2. Abrir el archivo `Info.plist` de la carpeta ios/MyFirstApp y agregar las siguientes líneas en el archivo, dentro de la etiqueta `<dict>`.

```xml
<key>UIAppFonts</key>
<array>
    <!-- Agregar las fuentes requeridas -->
    <string>MaterialIcons.ttf</string>
</array>
```

### React navigation

- [Documentación](https://reactnavigation.org/docs/getting-started): Librería de navegación para React Native.

1. Ejecutar las siguientes líneas de comando para instalar la librería.

```sh
$ npm install @react-navigation/native
$ npm install react-native-screens react-native-safe-area-context
```

2. Realizar la configuración de la librería en Android e iOS.
3. Envolver la aplicación con el componente `NavigationContainer` en el archivo `App.js`.

```jsx
import "react-native-gesture-handler";
import * as React from "react";
import { NavigationContainer } from "@react-navigation/native";

export default function App() {
  return (
    <NavigationContainer>{/* Resto de la aplicación */}</NavigationContainer>
  );
}
```

#### Configuración de la librería en Android

1. Abrir el archivo `MainActivity.kt` del proyecto.
2. Agregar las siguientes líneas en el archivo.

```kotlin
import android.os.Bundle

class MainActivity: ReactActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(null)
    }
}
```

#### Configuración de la librería en iOS

1. Ejecutar el siguiente comando para sincronizar los iconos con la aplicación.

```sh
$ npx pod-install
```

#### Sublibrería: Stack Navigator

- [Documentación](https://reactnavigation.org/docs/stack-navigator): Librería de navegación en pila para React Native.

1. Ejecutar las siguientes líneas de comando para instalar la librería.

```sh
$ npm install @react-navigation/stack
$ npm install react-native-gesture-handler
```

2. Crear los siguientes archivos para importar la librería de forma condicional.

```js
// gesture-handler.native.js
import "react-native-gesture-handler";
```

```js
// gesture-handler.js
// Don't import react-native-gesture-handler on web
```

3. Agregar el componente `createStackNavigator` en el archivo `App.js`.

```jsx
import "./gesture-handler";
```

4. Opcionalmente, se puede instalar la siguiente librería en caso de que se requiere usar las animaciones con estílo UIKit para el encabezado.

```sh
$ npm install @react-native-masked-view/masked-view
```

#### Sublibrería: Navigation Drawer

- [Documentación](https://reactnavigation.org/docs/drawer-navigator): Librería de navegación con cajón para React Native.
- [Documentación React Native Reanimated](https://docs.swmansion.com/react-native-reanimated/docs/fundamentals/getting-started/): Librería de animaciones para React Native.

1. Ejecutar las siguientes líneas de comando para instalar la librería.

```sh
$ npm install @react-navigation/drawer
$ npm install react-native-gesture-handler react-native-reanimated
```

2. Crear los siguientes archivos para importar la librería de forma condicional.

```js
// gesture-handler.native.js
import "react-native-gesture-handler";
```

```js
// gesture-handler.js
// Don't import react-native-gesture-handler on web
```

3. Agregar el componente `createStackNavigator` en el archivo `App.js`.

```jsx
import "./gesture-handler";
```

4. En el archivo babel.config.js, agregar la siguiente configuración.

```js
module.exports = {
  presets: ["module:metro-react-native-babel-preset"],
  plugins: ["react-native-reanimated/plugin"],
};
```

Nota: Si da problemas al ejecutar la aplicación, ejecuta el siguiente comando.

```sh
$ npx pod-install
$ npx react-native start --reset-cache
```

#### Sublibrería: Bottom Tab Navigator

- [Documentación](https://reactnavigation.org/docs/bottom-tab-navigator): Librería de navegación con pestañas inferiores para React Native.

1. Ejecutar las siguientes líneas de comando para instalar la librería.

```sh
$ npm install @react-navigation/bottom-tabs
```

#### Sublibrería: Material Top Tab Navigator

- [Documentación](https://reactnavigation.org/docs/material-top-tab-navigator): Librería de navegación con pestañas superiores para React Native.

1. Ejecutar las siguientes líneas de comando para instalar la librería.

```sh
$ npm install @react-navigation/material-top-tabs react-native-tab-view
$ npm install react-native-pager-view
```

2. En caso de que se esté ejecutando en iOS, ejecutar el siguiente comando.

```sh
$ npx pod-install
```

### Zustand

- [Documentación](https://zustand.surge.sh/): Librería de manejo de estados para React.

```sh
$ npm install zustand
```

### Axios

- [Documentación](https://axios-http.com/docs/intro): Librería de peticiones HTTP para JavaScript.

```sh
$ npm install axios
```

### Variables de entorno en React Native

- [Documentación](https://www.npmjs.com/package/react-native-dotenv): Librería para manejar variables de entorno en React Native.

```sh
$ npm install -D react-native-dotenv
```

### React Native Prompt Android

- [Documentación](https://www.npmjs.com/package/react-native-prompt-android): Librería para mostrar un cuadro de diálogo con promt en Android.

```sh
$ npm i react-native-prompt-android
```

### Axios (Solicitudes HTTP)

- [Documentación](https://axios-http.com/docs/intro): Librería para realizar solicitudes HTTP.

```sh
$ npm install axios
```

### TanStack query

- [Documentación](https://tanstack.com/query/latest): Gestor de estados asíncronos para React.

```sh
$ npm install @tanstack/react-query

```

### React Native Image Colors

- [Documentación](https://www.npmjs.com/package/react-native-image-colors): Librería para obtener los colores predominantes de una imagen.

```sh
$ npm install react-native-image-colors@1.5.2
```

### React Native Permissions

- [Documentación](https://www.npmjs.com/package/react-native-permissions): Librería para manejar los permisos de la aplicación.

```sh
$ npm install react-native-permissions
```

#### Configuración de la librería en Android

1. Abrir el archivo `AndroidManifest.xml` de la carpeta android/app/src/main.
2. Agregar los permisos requeridos en el archivo.

```xml
<manifest xmlns:android="http://schemas.android.com/apk/res/android">

  <!-- 🚨 Keep only the permissions used in your app 🚨 -->

  <uses-permission android:name="android.permission.ACCEPT_HANDOVER" />
  <uses-permission android:name="android.permission.ACCESS_BACKGROUND_LOCATION" />
  <uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />
  <uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
  <uses-permission android:name="android.permission.ACCESS_MEDIA_LOCATION" />
  <uses-permission android:name="android.permission.ACTIVITY_RECOGNITION" />
  <uses-permission android:name="com.android.voicemail.permission.ADD_VOICEMAIL" />
  <uses-permission android:name="android.permission.ANSWER_PHONE_CALLS" />
  <uses-permission android:name="android.permission.BLUETOOTH_ADVERTISE" />
  <uses-permission android:name="android.permission.BLUETOOTH_CONNECT" />
  <uses-permission android:name="android.permission.BLUETOOTH_SCAN" />
  <uses-permission android:name="android.permission.BODY_SENSORS" />
  <uses-permission android:name="android.permission.BODY_SENSORS_BACKGROUND" />
  <uses-permission android:name="android.permission.CALL_PHONE" />
  <uses-permission android:name="android.permission.CAMERA" />
  <uses-permission android:name="android.permission.GET_ACCOUNTS" />
  <uses-permission android:name="android.permission.NEARBY_WIFI_DEVICES" />
  <uses-permission android:name="android.permission.POST_NOTIFICATIONS" />
  <uses-permission android:name="android.permission.PROCESS_OUTGOING_CALLS" />
  <uses-permission android:name="android.permission.READ_CALENDAR" />
  <uses-permission android:name="android.permission.READ_CALL_LOG" />
  <uses-permission android:name="android.permission.READ_CONTACTS" />
  <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
  <uses-permission android:name="android.permission.READ_MEDIA_AUDIO" />
  <uses-permission android:name="android.permission.READ_MEDIA_IMAGES" />
  <uses-permission android:name="android.permission.READ_MEDIA_VIDEO" />
  <uses-permission android:name="android.permission.READ_MEDIA_VISUAL_USER_SELECTED" />
  <uses-permission android:name="android.permission.READ_PHONE_NUMBERS" />
  <uses-permission android:name="android.permission.READ_PHONE_STATE" />
  <uses-permission android:name="android.permission.READ_SMS" />
  <uses-permission android:name="android.permission.RECEIVE_MMS" />
  <uses-permission android:name="android.permission.RECEIVE_SMS" />
  <uses-permission android:name="android.permission.RECEIVE_WAP_PUSH" />
  <uses-permission android:name="android.permission.RECORD_AUDIO" />
  <uses-permission android:name="android.permission.SCHEDULE_EXACT_ALARM" />
  <uses-permission android:name="android.permission.SEND_SMS" />
  <uses-permission android:name="android.permission.USE_SIP" />
  <uses-permission android:name="android.permission.UWB_RANGING" />
  <uses-permission android:name="android.permission.WRITE_CALENDAR" />
  <uses-permission android:name="android.permission.WRITE_CALL_LOG" />
  <uses-permission android:name="android.permission.WRITE_CONTACTS" />
  <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />

  <!-- … -->

</manifest>
```

#### Configuración de la librería en iOS

1. Abrir el Archivo PodFile.
2. Sustituir el contenido del archivo por el siguiente.

```ruby
# Transform this into a `node_require` generic function:
- # Resolve react_native_pods.rb with node to allow for hoisting
- require Pod::Executable.execute_command('node', ['-p',
-   'require.resolve(
-     "react-native/scripts/react_native_pods.rb",
-     {paths: [process.argv[1]]},
-   )', __dir__]).strip

+ def node_require(script)
+   # Resolve script with node to allow for hoisting
+   require Pod::Executable.execute_command('node', ['-p',
+     "require.resolve(
+       '#{script}',
+       {paths: [process.argv[1]]},
+     )", __dir__]).strip
+ end

# Use it to require both react-native's and this package's scripts:
+ node_require('react-native/scripts/react_native_pods.rb')
+ node_require('react-native-permissions/scripts/setup.rb')
```

3. En el mismo archivo, agregar los permisos requeridos:

```ruby
# …

platform :ios, min_ios_version_supported
prepare_react_native_project!

# ⬇️ uncomment the permissions you need
setup_permissions([
  # 'AppTrackingTransparency',
  # 'Bluetooth',
  # 'Calendars',
  # 'CalendarsWriteOnly',
  # 'Camera',
  # 'Contacts',
  # 'FaceID',
  # 'LocationAccuracy',
  # 'LocationAlways',
  # 'LocationWhenInUse',
  # 'MediaLibrary',
  # 'Microphone',
  # 'Motion',
  # 'Notifications',
  # 'PhotoLibrary',
  # 'PhotoLibraryAddOnly',
  # 'Reminders',
  # 'Siri',
  # 'SpeechRecognition',
  # 'StoreKit',
])

# …
```

4. Ejecutar el siguiente comando para sincronizar los permisos con la aplicación.

```sh
$ npx pod-install
```

5. Por último, agregar los permisos correspondientes en el archivo `Info.plist` de la carpeta ios/MyFirstApp.

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE plist PUBLIC "-//Apple//DTD PLIST 1.0//EN" "http://www.apple.com/DTDs/PropertyList-1.0.dtd">
<plist version="1.0">
<dict>

  <!-- 🚨 Keep only the permissions specified in `setup_permissions` 🚨 -->

  <key>NSAppleMusicUsageDescription</key>
  <string>[REASON]</string>
  <key>NSBluetoothAlwaysUsageDescription</key>
  <string>[REASON]</string>
  <key>NSBluetoothPeripheralUsageDescription</key>
  <string>[REASON]</string>
  <key>NSCalendarsFullAccessUsageDescription</key>
  <string>[REASON]</string>
  <key>NSCalendarsWriteOnlyAccessUsageDescription</key>
  <string>[REASON]</string>
  <key>NSCameraUsageDescription</key>
  <string>[REASON]</string>
  <key>NSContactsUsageDescription</key>
  <string>[REASON]</string>
  <key>NSFaceIDUsageDescription</key>
  <string>[REASON]</string>
  <key>NSLocationAlwaysAndWhenInUseUsageDescription</key>
  <string>[REASON]</string>
  <key>NSLocationTemporaryUsageDescriptionDictionary</key>
  <dict>
    <key>YOUR-PURPOSE-KEY</key>
    <string>[REASON]</string>
  </dict>
  <key>NSLocationWhenInUseUsageDescription</key>
  <string>[REASON]</string>
  <key>NSMicrophoneUsageDescription</key>
  <string>[REASON]</string>
  <key>NSMotionUsageDescription</key>
  <string>[REASON]</string>
  <key>NSPhotoLibraryUsageDescription</key>
  <string>[REASON]</string>
  <key>NSPhotoLibraryAddUsageDescription</key>
  <string>[REASON]</string>
  <key>NSRemindersFullAccessUsageDescription</key>
  <string>[REASON]</string>
  <key>NSSpeechRecognitionUsageDescription</key>
    <string>[REASON]</string>
  <key>NSSiriUsageDescription</key>
  <string>[REASON]</string>
  <key>NSUserTrackingUsageDescription</key>
  <string>[REASON]</string>

  <!-- … -->

</dict>
</plist>
```
