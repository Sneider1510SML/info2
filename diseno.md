# Diseño

## Diagrama de clases

```mermaid
classDiagram
    class Jugador {
        -int id
        -String nombre
        -String alias
        -String correo
        -int equipoId
        +getNombre() String
        +setNombre(String)
        +toString() String
    }
    class Equipo {
        -int id
        -String nombre
        -String juego
        +getNombre() String
        +getJuego() String
        +toString() String
    }
    class Partido {
        -int equipoAId
        -int equipoBId
        -String fecha
        -int marcadorA
        -int marcadorB
        -boolean finalizado
        +registrarResultado(int, int)
        +getEstado() String
        +getMarcador() String
    }
    class FilaTabla {
        -String equipo
        -int jugados
        -int puntos
        -int diferencia
        +sumarPartido(int, int)
    }
    Equipo "1" o-- "0..5" Jugador : integrantes
    Partido --> "2" Equipo : enfrenta
    FilaTabla ..> Partido : se calcula con
```

`-` = privado, `+` = público. Los atributos son privados (encapsulamiento) y `Partido` protege sus propias reglas con `throw`.

## Modelo de datos (SQLite · `arena.db`)

| Tabla | Columnas |
|---|---|
| `jugador` | `id` PK · `nombre` · `alias` · `correo` UNIQUE · `equipo_id` (0 = sin equipo) |
| `equipo` | `id` PK · `nombre` UNIQUE · `juego` |
| `partido` | `id` PK · `equipo_a` → equipo · `equipo_b` → equipo · `fecha` · `marcador_a` · `marcador_b` · `finalizado` (0 o 1) |

## Reglas del torneo

- Victoria = 3 puntos, empate = 1, derrota = 0.
- Orden de la tabla: más puntos arriba; si empatan, mejor diferencia.
- Máximo 5 integrantes por equipo (constante `MAXIMO` en `EquipoController`).
