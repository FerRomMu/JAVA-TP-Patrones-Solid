
# Trabajo Práctico Integrador

## Programación con Objetos II - 2020 - 1er Semestre

## Testing

Cobertura de tests 100% clase, 97% metodo, 94% lineas. Utilizando como parámetro el plug in de cobertura de Intellij.

# A la caza de las vinchucas

## Introducción

La enfermedad de Chagas, es una enfermedad potencialmente mortal causada por un parásito llamado Trypanosoma cruzi. Es una enfermedad endémica que se encuentra con gran presencia 
en América Latina, incluyendo Argentina. Esta enfermedad se transmite a los humanos principalmente a través de un insecto llamado Vinchuca. Los síntomas de esta enfermedad incluyen fiebre, dolor de cabeza, palidez, dolores musculares, y en fases agudas trastornos cardíacos severos. Con el paso del tiempo la infección puede causar la muerte súbita o una  insuficiencia cardíaca por la destrucción progresiva del músculo cardíaco.

El objetivo de este proyecto es poder realizar un mapeo de la presencia de vinchucas en el territorio argentino (al menos como comienzo) y así poder encontrar relaciones entre la  presencia de este insecto, los casos de chagas detectados, las organizaciones que se encuentran en la región, y la posibilidad de minimizar los casos de contagio de la enfermedad  de chagas. Para ello, lo que se convoca es a la participación de las personas que viven o se encuentran en argentina para que puedan enviar fotos y una serie de respuestas a un cuestionario para informar de la aparición de este insecto. A esto se lo llama envío de muestras. Como la participación es de cualquier persona, las muestras deben poder validarse por otras personas y así cada muestra podrá contar con un nivel de validación particular. El proyecto tiene dos aplicaciones conectadas, una móvil donde se realiza la toma de la información y una web donde se reciben los datos de las muestras y se hace el procesamiento. Este trabajo se centrará solamente en la lógica de negocio de la aplicación Web. La aplicación móvil no debe modelar ni tenerla en cuenta, solamente se describirá para que conozca la interacción.

En este caso, las muestras las toman las personas utilizando una aplicación móvil, la cual fotografia a las vinchucas, indica en qué posición geográfica se encuentra y envía una  serie de respuestas de un cuestionario. Todo esto es recibido por el sistema Web. Desde allí comienza el trabajo de este trabajo práctico.

## Las muestras

Las muestras son el elemento principal de este proyecto. A partir de ellas se realizan casi todas las evaluaciones y desafíos que requiere el proyecto. En principio, de cada  muestra se debe poder responder cuál es la especie de vinchuca que han fotografiado (Infestans, Sordida, Guasayana), la foto de la vinchuca, la ubicación y la identificación de la  persona que recolectó esa muestra.

### Verificación de las muestras

Como las muestras las puede recolectar cualquier persona que posee la aplicación, los usuarios básicos pueden opinar sobre la foto y los expertos también con el fin de verificarla. Los expertos son personas que han demostrado conocer sobre el tema, por ejemplo estudiosos en vinchucas.

La opinión puede ser: Vinchuca (y en tal caso la especie), Chince Foliada, Phtia-Chinche, Ninguna, Imagen poco clara.

En todo momento se puede pedir a una muestra su resultado actual, dado por la opinión que tenga mayoría de votos. La opinión de quien subió la foto cuenta. Por ejemplo si una foto subida como Vinchuca Infestans pero luego opinada por dos usuarios básicos como Imagen “poco clara”, el resultado actual es “Imagen poco clara”. Si luego 3 usuarios básicos nuevos opinan que es una “Chince Foliada”, el resultado actual será “Chince Foliada”.

Cuando opina un experto, los usuarios básicos ya no pueden opinar. Solo pueden opinar expertos. Para que la muestra quede verificada, deben coincidir dos expertos en su opinión.  Nadie puede opinar sobre muestras verificadas.

En caso de empate temporal el resultado actual es no definido. Es importante obtener el historial de votaciones realizado.

### Evolución de conocimiento de los participantes

Los participantes pueden realizar dos actividades básicas. Enviar una muestra y verificar muestras que envió otra persona. Nunca pueden verificar muestras que hayan enviados ellos mismos, como así tampoco verificar más de una vez una muestra.

El envío y la verificación de muestras les va dando diferentes niveles en el conocimiento.
El sistema tiene una regla de promociones que funciona de la siguiente manera:
1) Básico: para aquellas personas que recién comienzan a participar. Un participante nuevo posee nivel básico.
2) Experto: son personas que durante los últimos 30 días desde la fecha actual han realizado más de 10 envíos y más de 20 revisiones.

Los algoritmos para subir o bajar de categoría de experto se pueden ejecutar en cualquier momento y considerarán siempre los 30 días anteriores al día de la fecha en que se lancen.

Las opiniones que realiza un usuario consideran el estado del usuario (básico o experto) al momento del voto. Si por ejemplo un usuario experto opina hoy y mañana vuelve a básico, su opinión de ayer sigue siendo considerada de experto.

Finalmente, existen algunos usuarios que poseen conocimiento validado en forma externa. Por ejemplo son especialistas reconocidos en la detección de vinchucas. Estos usuarios siempre son expertos, sin importar el tiempo que llevan participando.

### Las organizaciones

También se registran organizaciones no gubernamentales... de las que se puede conocer donde están ubicadas, el tipo de organización (salud, educativa, cultural, asistencia), y cuántas personas trabajan allí.

### Ubicación

Las ubicaciones están definidas con dos elementos: latitud y longitud. Además de una ubicación se quiere saber:

1. La distancia entre dos ubicaciones
2. Conocer, a partir de una lista de ubicaciones, aquellas que se encuentran a menos
    de x metros, o kilómetros.
3. Dado una muestra, conocer todas las muestras obtenidas a menos de x metros o
    kilómetros.

## Zonas de cobertura

Las zonas de cobertura son áreas en las que se organizan regiones geográficas. Se definen a partir de un epicentro y de este se define un radio en kilómetros. En la imágen se muestran dos áreas de cobertura de diferente alcance, las cuales poseen un espacio donde se solapan. El radio está representado por la flecha de color.

De cada zona de cobertura se desea conocer el nombre que lo identifica, el epicentro y la distancia del mismo en kilómetros. También es importante que se conozcan en todo momento las muestras que se han reportado en cada zona de cobertura.

Otras de las funcionalidades pedidas para las zonas de cobertura es saber cuales son las zonas que la solapan.

## Aviso a las organizaciones

Las organizaciones trabajan de acuerdo a una serie de acontecimientos que suceden en las áreas de cobertura. No es necesario que una organización esté dentro de un área de cobertura para que trabaje con un área particular.

Lo que les interesa a las organizaciones es poder enterarse de la carga y validaciones de las muestras que pertenecen geográficamente a una zona de cobertura. Es por ello que las organizaciones deciden registrarse a estos eventos a diferentes zonas. Una organización puede registrarse a todas las zonas que les parezcan importantes, así como también dejar de estar registradas en las mismas.

Lo que determinan las organizaciones es una serie de actividades que quieren hacer y las dividen de acuerdo a si se registró una nueva muestra o si se validó una muestra existente.

Cuando se registra una nueva muestra o validación en la zona a las que están registradas pueden realizar alguna acción programada, llamada Funcionalidad Externa. Una organización se configura con una única funcionalidad externa para actuar cuando se carga una nueva muestra y una para cuando se valida alguna muestra, aunque en su vida útil la funcionalidad pueden cambiar o usar la misma funcionalidad para ambas cosas. Las funcionalidades externas son un especie de plugins que deben quedar definidos por una interfaz con la siguiente forma:

```java
public interface FuncionalidadExterna{
public void nuevoEvento(<Organización>, <zonaDeCobertura>, <Muestra>);
}
```

Donde <Organización>, <zonaDeCobertura> y <Muestra> representan a las clases de su
solución que modelen esos conceptos.

La idea es que se puedan definir diferentes funcionalidades externas y cada organización pueda configurar cuál de estas funcionalidades quiere usar. Recuerden que solo usa una para nuevas muestras y otra para nuevas validaciones.

Ud debe programar y documentar para que sea posible utilizar lo relacionado a los avisos a las organizaciones y también documentar como se implementa y usaría en su solución una funcionalidad externa. Tenga en cuenta que la funcionalidad externa puede ser cualquiera que utilice esa información.

## Búsquedas de Muestras

Como hay muchas muestras disponibles, se quiere realizar una serie de búsquedas
basadas en filtros. Por lo cual se quieren buscar muestras por los siguientes criterios:
● Fecha de creación de la muestra.
● Fecha de la última verificación.
● Tipo de insecto detectado en la muestra.
● Nivel de validación

Estos criterios pueden ser combinados de diversas formas con operadores lógicos OR y
AND, para formar expresiones complejas. Los siguientes son ejemplos de criterios de
búsqueda:
● _Fecha de la última verificación_ ​> ‘20/04/2019’
● Nivel de validación = alto ’ AND ​ Fecha de la última verificación ​> ‘20/04/2019’
● Tipo de insecto detectado = ‘Vinchuca’ AND (Nivel de validación = alto ’ OR ​ Fecha
de la última verificación ​> ‘20/04/2019’)
