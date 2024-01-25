# Challenge Java Backend - Eldar - Fiserv

## Requerimiento
Para este primer paso, tuve varias ideas sobre cómo encarar las consideraciones del mismo.
1. En una primera instancia pensé en crear un enum que represente la marca de las tarjetas existentes, junto a este enum dentro de su constructor incluir como parámetro una interface que contenga los métodos necesarios para realizar los cálculos de la tasa de servicios, por lo que, al implementar esta interfaz, cada marca tendría su propia manera de realizar dicho calculo.
  Luego de indagar un poco por internet me topé con que esta solución que plantee cumple de alguna manera con el patrón de diseño Strategy, cosa que previamente no sabía.
2. Como segunda idea pensé que podría hacer que estas marcas no sean constantes y sean dinámicas utilizando una base de datos o algo por el estilo, dentro de la cual almacenaría la marca de la tarjeta junto con un campo de texto que sea una expresión de JavaScript o similar que al realizar su respectivo parse devuelva la tasa calculada.
3. La última idea, y esta fue por la que opté, se basó en seguir con el diseño estándar de la mayoría de las aplicaciones creadas con el framework Spring, la cual se basa en tres capas (repositorio, servicio y controlador), por lo que cree las clases necesarias para el requerimiento y dejé sin lógica alguna a la constante de las marcas de tarjetas.

Me enfoqué en realizar un análisis de las excepciones necesarias para que quien fuese a desarrollar en un futuro sobre esta base esté obligado a realizar el correspondiente manejo de las checked exceptions que son lanzadas en los servicios creados, servicios que a su vez se encuentran compuestos por varias funciones con una lógica muy reducida y distribuida para que a futuro se pueda añadir, quitar o modificar comprobaciones sin problemas.

## Ejercicio 1
En este primer ejercicio hice la implementación de los correctos manejos de errores tal como mencioné previamente en el Requerimiento.
Hice que los mensajes fueran constantes y estáticos para evitar la inconsistencia de los mismos en toda la aplicación de consola. Hice uso del String.format para permitirme la implementación de placeholders que luego eran parseadas durante la ejecución del código.
Para realizar la búsqueda de una tarjeta de crédito las cuales en la aplicación de consola se encuentran almacenadas en un array me apoyé en el uso de la Stream API de java incluida a partir de Java 8.
Para verificar si una tarjeta era igual a otra hice un override del método equals(Object o) que traen las clases para implementarlo de la manera que se adapta a este caso.

## Ejercicio 2
Para este ejercicio no tengo mucho que comentar, simplemente cree un endpoint el cual recibe peticiones POST, cree dos objetos, uno de request y otro de response los cuales tienen en sus variables los datos a recibir y datos a enviar.
Por otro lado hice un manejo global de las exceptions para poder actuar de una manera más homogénea en cuanto al envío de errores.

## Ejercicio 3
Sin conocimientos previos en PL/SQL, a primera vista pareciera que esa consulta hará algo con un límite de 10, pero luego de investigar un poco, la cláusula BULK COLLECT es utilizada para la mejora en el rendimiento de peticiones a grandes porciones de datos, en este caso limitando estas porciones a 10 filas, por lo que al final lo hará con el total de los registros (107 en este caso), distribuidos en bloques de 10 registros, 11 porciones en total, y mejorando de esta manera la utilización de memoria.

## Ejercicio 4
Viendo esta función claramente el problema es que no tiene la cláusula de retorno, y luego de investigar, al presentarse esta situación el error será en tiempo de ejecución. Esto se solucionaría simplemente agregando luego del select …; return L_salary;

## Ejercicio 5
Para este último ejercicio decidí resolver la consigna con los dos polos opuestos, por un lado, haciéndolo lo más pegado al lenguaje, en este caso Java, utilizando todos métodos built-in del lenguaje.
Por otro lado, hice un método que cumple la misma función, pero lo traté de hacer lo más alejado del lenguaje, sabiendo previamente que solamente se utilizará caracteres ASCII de la a-z y A-Z, castee estos caracteres a un int, para luego comprobar si ese código corresponde a una letra mayúscula y en ese caso sumarle 32 al int, valor que sale de la diferencia numérica dentro de la tabla ASCII, obteniendo así el mismo carácter, pero en minúscula.
