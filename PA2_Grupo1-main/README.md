# Programación avanzada  II - Grupo 1

## Información útil:

- Guardar datos en una base de datos online MySQL desde una aplicacion Android: https://www.youtube.com/watch?v=_YjNXTybueY

- Como asignar la Llave foranea en phpMyAdmin: https://www.youtube.com/watch?v=JWTpaxkNLW8

- Mostrar datos de MySQL en tabla html con php: https://www.youtube.com/watch?v=nPAp-gT5gPI

- Realizar Push a un repositorio GitHub desde Android Studio:https://www.youtube.com/watch?v=-dAr6VnmomM

  Si al dar Push, te dice "Push Rejected", probá lo siguiente:
  Proyecto > Git > Repository > Branches > En Remote Branches, elegí el que corresponde al repositorio nuestro > Rebase Current onto Selected

  Hecho eso, volvé a probar el Push y ahí debería funcionar.

  Luego para obtener el proyecto por primera vez,
  recomiendo que lo descarguen, hagan sus cambios y sigan los pasos del video anterior.
  De esta manera ya tienen configurado el github en su android studio.

  Luego para volver a obtener el proyecto, ya no es necesario seguir los pasos del video.
  Basta con ir en las opciones de arriba, VCS > Git > Pull.

## Entregas:

- Entrega 1: Documentación | **Hecha.**

- Entrega 2: Pantallas. | **Actual.**

Se deberá subir al aula virtual:

A.   Un archivo que contenga: las impresiones de pantallas de cada layout del TP integrador junto con la explicación de la funcionalidad que se realiza en dicha pantalla.

B.   El proyecto realizado en Android Studio, por favor aclarar para que dispositivo realizaron la aplicación.

Fecha de entrega: lunes 26/10/2020 23:55 hs.

- Entrega 3: Final.

Se deberá entregar el proyecto completo y funcionando. La entrega se realizará vía zoom, a cada grupo se le asignará un horario. Se deberá presentar el grupo completo, durante la presentación los integrantes del grupo serán evaluados de manera individual. En caso de que decidan presentarse en el recuperatorio por favor avisar, así no se les asigna horario. Tener en cuenta que si se presentar en la última fecha de recuperatorio, ya no habrá otra instancia para poder evaluarlos en caso de que desaprueben por ende desaprobarán la materia.

Fecha límite de entrega: Lunes 16/11/2020.

## Tablas:

- Usuarios:
  - IdUsuario.
  - Usuario.
  - Contraseña.
  - Nombre.
  - Apellido.
  - Email.
  - Ubicación.
  - Estado.
  
- Señas:
  - IdSeña.
  - Seña.
  - Descripción.
  
- Niveles:
  - IdNivel.
  - Nivel.
  
- Consignas:
  - IdConsigna.
  - IdSeña.
  - Descripción.
  
- Niveles x Usuario:
  - IdUsuario.
  - IdNivel.
  
- Consignas x Usuario:
  - IdUsuario.
  - IdConsigna.
  
- Señas x Nivel:
  - IdSeña.
  - IdNivel.
  
- Consignas x Niveles:
  - IdNivel.
  - IdConsigna.
  
- Respuestas x Consignas:
  - IdConsigna.
  - IdRespuesta.
  - Descripción.
  - Resultado.


