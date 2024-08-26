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

1. Abrir el proyecto de iOS con XCode (MyFirstApp.xcodeproj).
2. Crear un grupo llamado `Fonts` en la raíz del proyecto.
3. Desde la carpeta `node_modules/react-native-vector-icons/Fonts`, arrastrar los archivos de fuente requeridas .ttf a la carpeta Fonts en XCode.
4. En el diálogo desplegado, seleccionar la opción "Copy items if needed" y "Create folder references" y dar click en "Finish".
5. Cerrar XCode.
6. Abrir el archivo `Info.plist` de la carpeta ios/MyFirstApp y agregar las siguientes líneas en el archivo, dentro de la etiqueta `<dict>`.

```xml
<key>UIAppFonts</key>
<array>
    <!-- Agregar las fuentes requeridas -->
    <string>MaterialIcons.ttf</string>
</array>
```

7. Ejecutar el siguiente comando para sincronizar los iconos con la aplicación.

```sh
$ npx pod-install
```
