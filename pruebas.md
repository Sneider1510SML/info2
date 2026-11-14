# Pruebas

Marquen cada casilla cuando lo prueben en la app (Sesión 3, Sesión 4 y pruebas cruzadas de la Sesión 6).

## Sesión 3 · Formulario de jugadores

| Haz esto… | …y debería pasar esto | ✔ |
|---|---|---|
| Llenar los tres campos y pulsar Guardar | Aparece en la tabla y sale «Jugador registrado» | ☐ |
| Pulsar Guardar con el alias vacío | Mensaje «Completa nombre, alias y correo» | ☐ |
| Escribir un correo sin @ | Mensaje «El correo debe tener @» | ☐ |
| Hacer clic en una fila | Sus datos pasan al formulario | ☐ |
| Cambiar el alias y pulsar Actualizar | La tabla muestra el alias nuevo | ☐ |
| Pulsar Eliminar y luego Cancelar | No se borra nada | ☐ |
| Cerrar la app y volver a abrirla | Los jugadores siguen ahí (desde la Sesión 4) | ☐ |

## Sesión 4 · Equipos

| Caso | Resultado esperado | ✔ |
|---|---|---|
| Crear equipo sin nombre | «Escribe el nombre del equipo y el videojuego.» | ☐ |
| Crear equipo con nombre repetido | «No se pudo crear. ¿Ya existe un equipo con ese nombre?» | ☐ |
| Agregar un sexto jugador | «Este equipo ya tiene 5 integrantes.» | ☐ |
| Quitar un integrante | Vuelve a la lista de jugadores sin equipo | ☐ |

## Sesión 6 · Lista de chequeo (pruebas cruzadas)

- [ ] La app abre sin errores.
- [ ] Se puede navegar entre las cuatro pantallas.
- [ ] No deja guardar un jugador con campos vacíos.
- [ ] No acepta un correo sin @ ni un correo repetido.
- [ ] Se puede editar y eliminar un jugador (con confirmación).
- [ ] Se puede crear un equipo y agregarle jugadores.
- [ ] Un jugador no puede quedar en dos equipos.
- [ ] No deja pasar del máximo de integrantes.
- [ ] No deja programar un equipo contra sí mismo.
- [ ] No acepta marcadores negativos ni letras.
- [ ] No deja cambiar el resultado de un partido finalizado.
- [ ] La tabla de posiciones suma bien los puntos.
- [ ] Todo sigue ahí después de cerrar y abrir la app.
- [ ] Los mensajes son claros y amables.

## Caso de referencia de la clasificación

| Partido | Resultado |
|---|---|
| Los Pedagogos vs Pixel Norte | 3 - 1 |
| Pixel Norte vs Team Calle 72 | 2 - 2 |
| Los Pedagogos vs Team Calle 72 | 1 - 0 |
| Pixel Norte vs Team Calle 72 | por jugar (no suma) |

| # | Equipo | PJ | PG | PE | PP | Dif | Pts |
|---|---|---|---|---|---|---|---|
| 1 | Los Pedagogos | 2 | 2 | 0 | 0 | +3 | 6 |
| 2 | Team Calle 72 | 2 | 0 | 1 | 1 | −1 | 1 |
| 3 | Pixel Norte | 2 | 0 | 1 | 1 | −2 | 1 |

Resultado obtenido con la app: idéntico (ver `capturas/04-clasificacion.png`).
