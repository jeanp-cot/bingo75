# bingo75
Ejercicio #6 del #30DaysOfCodeByAEIS

Lenguaje: Java

Objetivos:
1.	🧑‍💻👩‍💻 Programa un bloque o función que nos ayude a generar las tablas de bingo, recuerda que puede haber n participantes.
2.	🧑‍💻👩‍💻 Programa un bloque o función que simule ser la persona que canta los números. Para esto tu bloque o función tiene que mostrar el orden de salida de los 75 números. Recuerda, esto es al azar.
3.	🧑‍💻👩‍💻 Simulemos que los n participantes juegan al mismo tiempo. Programa un bloque o función que nos muestre cual participante de los n ganó en el BINGO. Para esto tu bloque o función tiene que recibir 
los datos necesarios, más un parámetro que nos indique si se juega cartón completo, quina (Diagonal, línea) o 4 esquinas.

Extendí de la clase Thread a las TablasDeBingo para que se pudieran estar revisando y actualizando todas al mismo tiempo. Para crear las tablas hice una función que llenara los espacios, esta tenia que 
controlar que no se repitieran los numeros para lo cual usé otra función que revisara los demás números.

Para la función que simulaba ser la persona que decía los números usé la misma lógica que con las tablas.

Para que todos estuvieran jugando al mismo tiempo primero hice que todas las tablas supieran de qué forma se podía ganar y luego que comenzaran a revisar si habian ganado
y marcar los números que obtenían. Luego les iba pasando el número aleatorio. Al momento en que una ganaba se les avisaba a todas que una ya había ganado y de esa forma se 
detenían todas.
