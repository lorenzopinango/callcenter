# Ejercicio Java - Callcenter

La solución de los requerimientos es realiza creando un proyecto de Java con Maver y Spring Boot.
Se debe tener en cuenta el archivo application.properties incluido en el paquete resources ya que contiene las
propiedades de configuración de la aplicación tales como: número de llamadas concurrentes recibidas, capacidad de la
cola, número de operadores, supervisores y directores, bandera para espera de llamada para asignación de operador
disponible, tiempo de espera para revisión de operadores disponibles, tiempo máximo y mínimo de una llamada.

### Extras/Plus

Se realizan los extras de la prueba de la siguiente manera:

Para el punto 1, en el archivo application.properties mencionado anteriormente se crea la propiedad
waitOperatorAvailable de manera que cuando su valor sea TRUE, al momento de que en una llamada no se encuentren
operadores disponibles la aplicación deja la llamada en espera para asignarle un operador cuando quede disponible, de
igual forma se crea la propiedad timeAnswerOperatorAvailable el cual incluye el tiempo en segundos para configurar cada
cuanto tiempo se va a revisar si hay operadores disponibles para asignar la llamada.

Para el punto 2, en la aplicación se configura un ThreadPoolTaskExecutor el cual permite ir encolando las llamadas que
van llegando concurrentemente. La configuración para ThreadPoolTaskExecutor se encuentra en el archivo
application.properties mencionado anteriormente, numberConcurrentCalls y queueCapacity. Adicionalmente, como si se
activa la espera de llamada para asignación de operadores, las llamadas que no puedan ser atendidas quedarán en espera
como se explicó en el punto anterior.

Para el punto 3, se crea una prueba unitaria dentro de la aplicación en el paquete test.

Para el punto 4, el código de la aplicación se encuentra documentado.