# Management System

Api Rest de gestion de provedores y productos

## Stack Tecnológico
- Java 21
- Spring Boot 3.4.3
- Spring Data JPA
- Flyway
- PostgreSQL 17-alpine
- Docker 29.2.1


## Requisitos
- Java >=21
- Docker >=29.2.0
- PostgreSQL >=17
- git


## Instalación de proyecto
Clonar el repositorio usando git

```bash
  git clone https://github.com/Juan-Gonzalez-Gonzalez/Management-System.git
```

## Ejecutar proyecto
> [!NOTE]
> El proyecto puede funcionar de dos formas usando docker compose o ejecutando el jar directamente pero este ultimo ocupa tener instalado java y postgres en el equipo.


### Pasos para ejecutar el proyecto localmente usando docker compose (RECOMENDADO)
> [!IMPORTANT]
> Por defecto el archivo de compose.yaml ya trae las variables por defecto para levantar el proyecto en modo desarrollo pero se puede modificar la variable *SPRING_PROFILES_ACTIVE=dev* a *SPRING_PROFILES_ACTIVE=prod* para levantar el proyecto en modo producción.

1. Ejecutar el siguiente comando para levantar el proyecto usando docker compose

```bash
    docker-compose up -d --build
```

2. Probar el proyecto en Postman con la documentación proporcionada o desde el navegador si se tiene el frontend corriendo.


### Pasos para ejecutar el proyecto localmente usando Java

por hacer

## Variables de entorno de spring boot

| Variable               | Valor                                              |
|------------------------|----------------------------------------------------|
| SPRING_PROFILES_ACTIVE | dev o prod                                         |
| DB_URL                 | string de coneccion a la base de datos de postgres |
| DB_USER                | usuario de la base de datos                        |
| DB_PASSWORD            | contraseña de la base de datos                     |
| ALLOWED_ORIGINS        | URL del frontend para habilitar CORS               |


## Configuración de la base de datos

La base de datos se crea en el archivo *src/main/resources/db/migration/V1__.sql* y al momento de levantar la aplicacion Flyway se encarga de crear la base de datos y migrar las tablas automáticamente la primera vez que se ejecute la aplicación o al detectar nuevos archivos. 

## Documentacion de la API 

El siguiente enlace es para la documentacion de los endpoints de la api en Postman

[Postman](https://www.postman.com/exampledevteam/workspace/management-system/collection/34630516-9e1b22b5-2db1-4f83-911a-3ed3233454b7?action=share&creator=34630516)
