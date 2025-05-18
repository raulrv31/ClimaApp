**_APP CLIMA**_

Aplicacion movil desarrollada en Kotlin.
Permite que el usuario visualice el clima actual, el pronostico del clima por horas y gestione las 
ubicaciones de de donde desea obtener la informacion meterologica.

La informacion se obtiene desde la API de Open-Meteo (https://open-meteo.com/)

**App:**
-Consta de tres pantallas:
    -Clima actual: En caso de no haber ninguna ubicacion seleccionada se lo indicara al usuario.
        Si hay una ubicacion seleccionada, mostrara el nombre con el que se guardo esta, asi como
        la fecha y hora a la que se hizo la peticion del clima y si es de dia o de noche,
        la temeratura y que clima hay representado con un emoji, el viento en km/h 
        y por ultimo muestra en formato scroll horizontal, el pronostico del tiempo por horas,
        mostrando fecha y hora, el clima con emoji y la temperatura.
    -Pronostico: Muestra en formato scroll vertical la prevision de tiempo para la ubicacion
        seleccionada, indicando fecha y hora, clima previsto con un emoji, temepratura y viento.
    -Cambiar ubicacion: En esta pantalla el usuario puede insertar los datos de la ubicacion
        que desea guardar (nombre, latitud y longitud) en los campos de texto de la card agregar
        ubicacion y despues puede pulsar el boton guardar para que se almacene dicha ubicacion.
        Tambien consta de una lista de ubicaciones guardadas donde puede ver el nombre de cada una
        junto con sus coordenadas, tambien puede eliminar cada ubicacion pulsando el boton 
        representado con una papelera roja.

-Navegacion entre pantallas:
    El usuario puede moverse entre pantallas usuando la TopAppBar de la parte superior de la app.
    Consta de cuatro partes, la primera, un boton de flecha que permite navegar a la pantalla
    anterior. La segunda es el nombre de la pantalla en la que se encuentra el usuario. La tercera 
    es un boton con icono de Ubicacion que permite ir directamente a la pantalla encargada de 
    gestionar las ubicaciones almacenadas y añadir nuevas. Y la cuarta es un boton de menu, que 
    despliega un menu lateral desde la izquierda que da al usuario la opcion de navegar directamente
    a cualquiera de las tres pantallas de la app, asi como cerrarlo si pulsa en cualquier sitio
    que no sean las opciones de navegacion a las otras pantallas.


**Se utiliza:**
- Jetpack Compose, para las interfaces de usuario.
- SharedPreferences, para almacenar las ubicaciones de forma persistente.
- Retrofit, para la API REST.
- ViewModel, para almacenar y gestionar los datos que se emplean en la UI.

**GitHub:**
https://github.com/raulrv31

**Autor:**
_RAÚL RODRÍGUEZ VÁZQUEZ_