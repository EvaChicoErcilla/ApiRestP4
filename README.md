## Endpoints

| Método | Ruta                          | Cuerpo JSON                                     | Descripción                                      | Respuestas posibles                  |
|--------|-------------------------------|-------------------------------------------------|--------------------------------------------------|--------------------------------------|
| POST   | `/api/personas`               | `{ "nombre": "", "apellidos": "", "email": "" }`| Crea una nueva persona                           | 200 OK, 409 Conflict, 400 Bad Request |
| GET    | `/api/personas/{nombre}`      | —                                               | Devuelve una lista de personas con ese nombre    | 200 OK, [] si no hay coincidencias   |
| PUT    | `/api/personas/editar`        | `{ "nombre": "", "apellidos": "", "email": "" }`| Edita el email de una persona (por nombre+apellido) | 200 OK, 404 Not Found            |
| DELETE | `/api/personas/eliminar`      | `{ "nombre": "", "apellidos": "", "email": "" }`| Elimina una persona con coincidencia exacta      | 204 No Content, 404 Not Found        |
