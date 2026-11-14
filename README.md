# UPN Arena

Aplicación de escritorio en **Java + JavaFX + SQLite** para organizar un torneo de videojuegos en la Universidad Pedagógica Nacional: registra jugadores, arma equipos, programa partidos y calcula sola la tabla de posiciones.

Proyecto integrador · Informática 2 · Universidad Pedagógica Nacional

## Equipo

| Integrante | Usuario de GitHub |
|---|---|
| _Nombre 1_ | _@usuario1_ |
| _Nombre 2_ | _@usuario2_ |
| _Nombre 3_ | _@usuario3_ |

## Capturas

![Inicio](docs/capturas/00-inicio.png)

| Jugadores | Equipos |
|---|---|
| ![Jugadores](docs/capturas/01-jugadores.png) | ![Equipos](docs/capturas/02-equipos.png) |

| Partidos | Clasificación |
|---|---|
| ![Partidos](docs/capturas/03-partidos.png) | ![Clasificación](docs/capturas/04-clasificacion.png) |

## Funcionalidades

- **Inicio:** la app abre en un resumen con tarjetas (jugadores, equipos, partidos por jugar y jugados) y los pasos para empezar.
- **Jugadores:** registrar, editar y eliminar (con confirmación). Valida campos vacíos, correo con `@` y correo repetido.
- **Equipos:** crear equipos (nombre único) y agregar o quitar integrantes. Máximo 5 por equipo; un jugador no puede estar en dos equipos.
- **Partidos:** programar partidos con fecha y registrar el resultado. No deja que un equipo juegue contra sí mismo, no acepta marcadores negativos ni letras y no deja cambiar un partido ya finalizado.
- **Clasificación:** se calcula a partir de los partidos finalizados. Victoria 3 puntos, empate 1, derrota 0. Desempate por diferencia.
- **Persistencia:** todo se guarda en `arena.db` (SQLite) y sigue ahí al cerrar y abrir la app.

## Requisitos

- JDK 25 (Eclipse Temurin 25 LTS)
- Apache NetBeans 31 (o Maven 3.9+)
- Scene Builder (opcional, para editar las pantallas)

JavaFX 25 y el driver de SQLite se descargan solos con Maven.

## Cómo ejecutar

**En NetBeans:** File › Open Project › carpeta del proyecto › botón **▶ Run Project** (F6).
No uses *Run File* sobre `App.java`: sale el error *JavaFX runtime components are missing*.

**Desde la terminal:**

```bash
mvn clean javafx:run
```

La primera ejecución crea el archivo `arena.db` en la carpeta del proyecto. Para empezar con la base vacía, cierra la app y borra ese archivo.

## Estructura

```
arena/
├── pom.xml
└── src/main/
    ├── java/co/edu/upn/arena/
    │   ├── App.java                      Arranque de la app
    │   ├── Mensajes.java                 Ventanas de aviso
    │   ├── Conexion.java                 Conexión y creación de tablas
    │   ├── Jugador.java · Equipo.java · Partido.java · FilaTabla.java   Clases del torneo
    │   ├── JugadorDAO.java · EquipoDAO.java · PartidoDAO.java          Acceso a datos
    │   └── PrincipalController.java · InicioController.java · JugadorController.java ·
    │       EquipoController.java · PartidoController.java ·
    │       ClasificacionController.java                                Controladores
    └── resources/co/edu/upn/arena/
        ├── principal.fxml · inicio.fxml · jugadores.fxml · equipos.fxml ·
        │   partidos.fxml · clasificacion.fxml                          Pantallas
        └── estilos.css                                                 Colores y estilos
```

## Arquitectura

```
Pantalla (.fxml) → Controlador (…Controller.java) → Clases (Jugador.java…) → DAO (…DAO.java) → Base de datos (arena.db)
```

La pantalla es la cara, el controlador el cerebro, las clases las fichas con los datos, el DAO el mensajero y la base de datos el archivador. Todas las consultas usan `PreparedStatement` con `?`, nunca texto del usuario pegado en el SQL.

Más detalle en [`docs/`](docs): historias de usuario, diagrama de clases, modelo de datos y pruebas.
