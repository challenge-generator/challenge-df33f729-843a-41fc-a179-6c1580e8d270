# Prompt para Mejorar el Codigo Base

Copia y pega el contenido del bloque de abajo en un asistente de IA (Claude, ChatGPT)
para obtener un ZIP con el proyecto completo y arrancable.

Si preferis trabajar en tu editor con un agente local (Claude Code, Cursor, Copilot), usa `AGENTS.md` en vez de este archivo: dice lo mismo pero para que escriba los archivos en disco.

## Las dos reglas que no se negocian

1. **Completa el boilerplate.** Todo lo que el proyecto necesita para compilar y arrancar: manifiesto de dependencias, punto de entrada, configuracion, capa de interfaz, y las capas del patron arquitectonico declarado. Eso es andamiaje y es tu trabajo.
2. **NO resuelvas el reto.** Los entregables de las fases son el trabajo de la persona. El hueco pedagogico se deja como esta: el proyecto arranca, pero lo que el reto pide implementar NO esta implementado.

Dicho de otra forma: si algo impide compilar, arreglalo. Si algo es logica de negocio incompleta, validaciones ausentes, un secreto hardcodeado o un patron mejorable, dejalo exactamente como esta — es lo que la persona tiene que encontrar.

## Superficie de practica — NO resuelvas

Estos archivos SON el ejercicio de la persona. No los implementes; deja stubs.

- `RunCucumberTest.java` — El topic pide TDD/pruebas: este archivo es el ejercicio.
- `src/test/java/com/pragma/ordersystem/domain/service/OrderServiceTest.java` — El topic pide TDD/pruebas: este archivo es el ejercicio.
- `src/test/java/com/pragma/ordersystem/application/rest/OrderControllerTest.java` — El topic pide TDD/pruebas: este archivo es el ejercicio.
- `src/test/java/com/pragma/ordersystem/infrastructure/adapter/OrderJpaRepositoryTest.java` — El topic pide TDD/pruebas: este archivo es el ejercicio.

## Lo que le falta a este proyecto

Esto NO lo tenes que adivinar: salio de comparar el proyecto contra la arquitectura declarada del reto y de un analisis estatico del codigo. Completalo TODO.

### Archivos que la arquitectura del reto declara y no estan

Creálos con implementacion real, en la capa que les corresponde:

- `RunCucumberTest.java`
- `XSteps.java`

### Referencias colgando en el codigo que si esta

Cada una rompe la compilacion:

- `src/main/java/com/pragma/ordersystem/config/DatabaseConfig.java` — `com.zaxxer.hikari`: El import com.zaxxer.hikari.HikariConfig pertenece a com.zaxxer.hikari, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/test/java/com/pragma/ordersystem/application/rest/OrderControllerTest.java` — `com.fasterxml.jackson`: El import com.fasterxml.jackson.databind.ObjectMapper pertenece a com.fasterxml.jackson, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/test/java/com/pragma/ordersystem/steps/XSteps.java` — `io.cucumber.java`: El import io.cucumber.java.en.Given pertenece a io.cucumber.java, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragma/ordersystem/domain/exception/PaymentFailedException.java` — `PaymentMethod.name`: Se invoca `name` sobre `PaymentMethod`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/ordersystem/domain/exception/PaymentFailedException.java` — `PaymentFailureReason.name`: Se invoca `name` sobre `PaymentFailureReason`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/ordersystem/domain/service/OrderService.java` — `OrderStatus.name`: Se invoca `name` sobre `OrderStatus`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/ordersystem/infrastructure/adapter/OrderJpaRepository.java` — `OrderEntity.setCustomer`: Se invoca `setCustomer` sobre `OrderEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/ordersystem/infrastructure/adapter/OrderJpaRepository.java` — `OrderItemEntity.setQuantity`: Se invoca `setQuantity` sobre `OrderItemEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/ordersystem/infrastructure/adapter/OrderJpaRepository.java` — `OrderItem.getQuantity`: Se invoca `getQuantity` sobre `OrderItem`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/ordersystem/infrastructure/adapter/OrderJpaRepository.java` — `OrderItemEntity.setUnitPrice`: Se invoca `setUnitPrice` sobre `OrderItemEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/ordersystem/infrastructure/adapter/OrderJpaRepository.java` — `OrderItem.getUnitPrice`: Se invoca `getUnitPrice` sobre `OrderItem`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/ordersystem/infrastructure/adapter/OrderJpaRepository.java` — `OrderItemEntity.setSubtotal`: Se invoca `setSubtotal` sobre `OrderItemEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/ordersystem/infrastructure/adapter/OrderJpaRepository.java` — `OrderItem.getSubtotal`: Se invoca `getSubtotal` sobre `OrderItem`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/ordersystem/infrastructure/adapter/OrderJpaRepository.java` — `OrderItem.getProduct`: Se invoca `getProduct` sobre `OrderItem`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/ordersystem/infrastructure/adapter/OrderJpaRepository.java` — `OrderItemEntity.setProduct`: Se invoca `setProduct` sobre `OrderItemEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/ordersystem/infrastructure/adapter/OrderJpaRepository.java` — `OrderEntity.getCustomer`: Se invoca `getCustomer` sobre `OrderEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/ordersystem/infrastructure/adapter/OrderJpaRepository.java` — `OrderItemEntity.getProduct`: Se invoca `getProduct` sobre `OrderItemEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/ordersystem/infrastructure/adapter/OrderJpaRepository.java` — `OrderItemEntity.getQuantity`: Se invoca `getQuantity` sobre `OrderItemEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/ordersystem/infrastructure/adapter/OrderJpaRepository.java` — `OrderItemEntity.getUnitPrice`: Se invoca `getUnitPrice` sobre `OrderItemEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/ordersystem/application/rest/OrderController.java` — `OrderService.getOrdersByCustomerId`: Se invoca `getOrdersByCustomerId` sobre `OrderService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/ordersystem/application/rest/OrderController.java` — `OrderService.calculateOrderTotal`: Se invoca `calculateOrderTotal` sobre `OrderService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/ordersystem/application/rest/OrderController.java` — `OrderService.deleteOrder`: Se invoca `deleteOrder` sobre `OrderService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/ordersystem/infrastructure/entity/OrderEntity.java` — `OrderEntity.getCustomer`: Se invoca `getCustomer` sobre `OrderEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/ordersystem/infrastructure/entity/OrderEntity.java` — `OrderItemEntity.setOrder`: Se invoca `setOrder` sobre `OrderItemEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/ordersystem/infrastructure/entity/OrderEntity.java` — `OrderItem.getProduct`: Se invoca `getProduct` sobre `OrderItem`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/ordersystem/infrastructure/entity/OrderEntity.java` — `OrderItemEntity.setProductId`: Se invoca `setProductId` sobre `OrderItemEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/ordersystem/infrastructure/entity/OrderEntity.java` — `OrderItemEntity.setProductName`: Se invoca `setProductName` sobre `OrderItemEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/ordersystem/infrastructure/entity/OrderEntity.java` — `OrderItemEntity.setQuantity`: Se invoca `setQuantity` sobre `OrderItemEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/ordersystem/infrastructure/entity/OrderEntity.java` — `OrderItem.getQuantity`: Se invoca `getQuantity` sobre `OrderItem`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/ordersystem/infrastructure/entity/OrderEntity.java` — `OrderItemEntity.setUnitPrice`: Se invoca `setUnitPrice` sobre `OrderItemEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/ordersystem/infrastructure/entity/OrderEntity.java` — `OrderItem.getUnitPrice`: Se invoca `getUnitPrice` sobre `OrderItem`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/ordersystem/infrastructure/entity/OrderEntity.java` — `OrderItemEntity.setSubtotal`: Se invoca `setSubtotal` sobre `OrderItemEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/ordersystem/infrastructure/entity/OrderEntity.java` — `OrderItem.getSubtotal`: Se invoca `getSubtotal` sobre `OrderItem`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/ordersystem/domain/service/OrderServiceTest.java` — `Order.isEmpty`: Se invoca `isEmpty` sobre `Order`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/ordersystem/domain/service/OrderServiceTest.java` — `Order.size`: Se invoca `size` sobre `Order`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/ordersystem/infrastructure/adapter/OrderJpaRepositoryTest.java` — `OrderEntity.isPresent`: Se invoca `isPresent` sobre `OrderEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/ordersystem/infrastructure/adapter/OrderJpaRepositoryTest.java` — `OrderEntity.get`: Se invoca `get` sobre `OrderEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/ordersystem/infrastructure/adapter/OrderJpaRepositoryTest.java` — `OrderEntity.isEmpty`: Se invoca `isEmpty` sobre `OrderEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/ordersystem/infrastructure/adapter/OrderJpaRepositoryTest.java` — `OrderEntity.size`: Se invoca `size` sobre `OrderEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/ordersystem/infrastructure/adapter/OrderJpaRepositoryTest.java` — `OrderJpaRepository.deleteById`: Se invoca `deleteById` sobre `OrderJpaRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/ordersystem/infrastructure/adapter/OrderJpaRepositoryTest.java` — `OrderJpaRepository.countByStatus`: Se invoca `countByStatus` sobre `OrderJpaRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.

## Como saber que terminaste

```bash
mvn clean test-compile
```

Ese comando corriendo sin errores es la definicion de "listo".

---

```
## Briefing del reto (autoridad)
Este bloque manda sobre los archivos adjuntos. El stack y el rol salen de AQUÍ, no de un topic genérico ni de markdown placeholder.

### Perfil
Chapter Calidad de Software, Especialidad Automatizador, Tecnología POO Avanzada, Senior

### Brecha de conocimiento
Demuestra un dominio avanzado en al menos uno de los siguientes lenguajes de Programación Orientada a Objetos: Java, Python, dart, JavaScript, TypeScript, en temas clave como: conexiones a base de datos, manejo de excepciones, interfaces, clases abstractas, colecciones, uso de bibliotecas/frameworks, patrones de diseño orientados a objetos, herramientas de pruebas unitarias

### Misión / candidato
Candidato con experiencia en automatización de calidad de software a nivel Senior

### Reto
- Tema: implementación de programación orientada a objetos (POO) - avanzado
- Seniority: senior-l2
- Tipo: practical
- Título: Desarrollo de un sistema de gestión de pedidos avanzado utilizando POO
- Tiempo estimado: 10 horas

### Fases (trabajo del HUMANO — PROHIBIDO completarlas)
No implementes estos entregables. Dejalos como hueco pedagógico. El asistente solo materializa el proyecto arrancable para que el participante pueda trabajar.
- Fase 1: Modelado de clases y atributos — objetivo: Definir las clases y atributos necesarios para representar los pedidos y sus estados. — entregable (NO resolver): Diagrama de clases y descripción de atributos.
- Fase 2: Implementación de métodos y manejo de excepciones — objetivo: Implementar los métodos necesarios para la creación, modificación y cancelación de pedidos, así como manejar las excepciones que puedan surgir. — entregable (NO resolver): Código fuente con métodos implementados y manejo de excepciones.
- Fase 3: Integración con base de datos — objetivo: Integrar el sistema con una base de datos para el almacenamiento y recuperación de los pedidos. — entregable (NO resolver): Código fuente con integración de base de datos.
- Fase 4: Pruebas unitarias y optimización — objetivo: Escribir pruebas unitarias para los métodos implementados y optimizar el rendimiento del sistema. — entregable (NO resolver): Código fuente con pruebas unitarias y optimizaciones aplicadas.

Eres un asistente experto en análisis, corrección y generación de archivos de cualquier tipo:
código fuente, documentación, hojas de cálculo, documentos Word, configuraciones, entre otros.
Voy a enviarte una cadena de texto que contiene uno o más archivos. Cada archivo está delimitado por un marcador con el siguiente formato:
// === ARCHIVO: ruta/del/archivo.extension ===
o también puede aparecer como:
## === ARCHIVO: ruta/del/archivo.extension ===
Lo que sigue al marcador puede ser:

El contenido real del archivo (código, texto, YAML, etc.)
Una descripción en lenguaje natural de lo que debe contener el archivo


TU TAREA
PASO 0 — ¿Esto es un proyecto o una carcasa?
Antes de extraer archivos, leé el Briefing (si está) y diagnosticá el adjunto.

Es CARCASA si ocurre CUALQUIERA de estas:
- No hay manifiesto de dependencias del stack del briefing (manifest.json de VTEX IO / package.json / pom.xml / build.gradle / requirements.txt / go.mod / *.tf / *.csproj, según corresponda)
- Hay un "binario" que en realidad es un comentario ("no puede ser mostrado como texto plano", placeholder .fig/.docx vacío)
- Los markdowns ya completan entregables de fases posteriores ("se implementó fade-in", lista de áreas ya resuelta)

Si es CARCASA:
- MATERIALIZÁ un proyecto que arranca en el stack del briefing (VTEX IO Store Framework, Angular, Terraform, pytest, Nest, etc.). Incluí manifiesto, punto de entrada y capa de interfaz reales.
- NO copies los markdowns de "solución" como si fueran el producto. Son ruido de generación.
- NO resuelvas las fases del briefing (están marcadas PROHIBIDO). Dejá el hueco pedagógico: el flujo existe, las microinteracciones/calidad/infra que el reto pide NO están hechas.
- Después seguí al PASO 5 (ZIP).

Si es un proyecto REAL (manifiesto + código que compila o arranca):
- Seguí PASO 1 en adelante. 🔴 compilación sí. 🟡 pedagógico no.

PASO 1 — Detección y extracción
Identifica todos los archivos presentes en la cadena. Para cada archivo extrae:

Su ruta completa (ej: src/main/java/com/pragma/Service.java)
Su contenido o descripción

PASO 2 — Clasificación por tipo
Clasifica cada archivo en una de estas categorías:
A) Código fuente (Java, Python, TypeScript, JavaScript, Kotlin, etc.)
B) Configuración / documentación (YAML, properties, Markdown, JSON, txt, etc.)
C) Excel (.xlsx, .xls, .csv)
D) Word (.docx, .doc)
E) Otro tipo de archivo binario o especial
PASO 3 — Clasificación de errores en código fuente

Objetivo prioritario: que el proyecto compile. No corrijas flujo de negocio ni lógica funcional.

Antes de modificar cualquier archivo de código fuente, clasifica cada problema encontrado en una de estas dos categorías:
🔴 ERROR DE COMPILACIÓN — corregir siempre
Son errores que impiden que el proyecto arranque, sin valor pedagógico:

Import faltante o incorrecto
Clase, método o variable referenciada que no existe en ningún archivo del proyecto
Error de sintaxis
Anotación con atributos inválidos
Dependencia ausente en pom.xml, package.json, etc.
Archivo referenciado que no existe y debe ser creado con implementación mínima

→ CORREGIR estos errores.
🟡 PROBLEMA FUNCIONAL O DE CALIDAD — preservar siempre
Son problemas que no impiden compilar. Pueden ser intencionales para el aprendizaje:

Clave secreta hardcodeada ("secret", "password123")
API deprecada que funciona pero tiene reemplazo moderno
Lógica de negocio incorrecta o incompleta
Código redundante o de baja legibilidad
Falta de validaciones en flujo de negocio
Patrones de diseño incorrectos pero funcionales
Concurrencia no segura
Configuración funcional pero no óptima

→ PRESERVAR tal cual. No corregir, no mejorar, no comentar.
PASO 4 — Procesamiento según tipo de archivo
Tipo A — Código fuente
Aplica únicamente las correcciones clasificadas como 🔴 ERROR DE COMPILACIÓN.
No alteres ningún elemento clasificado como 🟡 PROBLEMA FUNCIONAL O DE CALIDAD.
Si falta un archivo referenciado, créalo con la implementación mínima necesaria para compilar.
Tipo B — Configuración / documentación
Extrae el contenido tal cual, sin modificaciones salvo errores evidentes de sintaxis
(ej: YAML mal indentado).
Tipo C — Excel (.xlsx)
Si viene con contenido real, genera el archivo respetando ese contenido.
Si viene con descripción en lenguaje natural, genera un archivo Excel funcional con:

Fila de encabezados en negrita con color de fondo distintivo
Columnas con ancho ajustado al contenido
Tipos de dato correctos por columna
Validaciones si la descripción lo indica
Hojas nombradas descriptivamente si hay más de una
Filas de ejemplo si no hay datos reales

Tipo D — Word (.docx)
Si viene con contenido real, genera el archivo respetando ese contenido.
Si viene con descripción en lenguaje natural, genera un documento Word funcional con:

Estilos de título (Título 1, Título 2) para jerarquía de secciones
Fuente legible (Calibri o equivalente), tamaño 11-12pt para cuerpo
Márgenes estándar
Tabla de contenido si tiene múltiples secciones
Tablas con encabezados en negrita si aplica

Tipo E — Otro
Genera el archivo con el contenido o estructura más apropiada según la descripción.
PASO 5 — Exportación en ZIP
Empaqueta todos los archivos en un único archivo ZIP descargable respetando exactamente
la estructura de rutas indicada por los marcadores.
El ZIP debe incluir:

Archivos de código con únicamente los errores de compilación corregidos
Archivos de configuración y documentación sin cambios
Archivos nuevos creados para resolver dependencias de compilación faltantes
Archivos Excel y Word generados desde descripción

IMPORTANTE: El ZIP debe estar listo para descargar al finalizar. No preguntes si el usuario
quiere generarlo. Simplemente genera el archivo y proporciona el enlace de descarga; No debes desplegar en el chat el resumen de lo que arreglaste al Zip, solo entregalo.

REGLAS IMPORTANTES

No omitas ningún archivo aunque no tenga errores ni modificaciones
Respeta los nombres y rutas exactas indicadas por los marcadores
Si un archivo no tiene marcador claro, infiere el nombre desde su contenido
Si la cadena contiene solo documentación, placeholders o binarios fake, NO la reproduzcas:
aplicá PASO 0 (materializar el proyecto del briefing). Reproducir la carcasa es un fallo.
No agregues texto después del enlace de descarga del ZIP
No preguntes si el usuario quiere el ZIP: simplemente generalo siempre
Si detectas que falta un archivo de configuración necesario para compilar
(pom.xml, package.json, requirements.txt, build.gradle, etc.), créalo e inclúyelo
inferiendo su contenido desde los imports y frameworks detectados en el código
Nunca corrijas problemas 🟡 aunque parezcan obvios o fáciles de mejorar.
El participante que recibirá este proyecto los debe encontrar y resolver él mismo.


INPUT
Aquí está la cadena con los archivos:

// === ARCHIVO: pom.xml ===
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>3.4.0</version>
        <relativePath/>
    </parent>

    <groupId>com.pragma</groupId>
    <artifactId>ordersystem</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <name>ordersystem</name>
    <description>Sistema de gestión de pedidos para comercio electrónico</description>

    <properties>
        <java.version>21</java.version>
        <maven.compiler.source>21</maven.compiler.source>
        <maven.compiler.target>21</maven.compiler.target>
    </properties>

    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>
        <dependency>
            <groupId>com.h2database</groupId>
            <artifactId>h2</artifactId>
            <scope>runtime</scope>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <scope>provided</scope>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.mockito</groupId>
            <artifactId>mockito-core</artifactId>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-surefire-plugin</artifactId>
                <version>3.5.0</version>
            </plugin>
        </plugins>
    </build>
</project>

// === ARCHIVO: src/main/java/com/pragma/ordersystem/OrdersystemApplication.java ===
package com.pragma.ordersystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Clase principal de la aplicación Spring Boot para el sistema de gestión de pedidos.
 * <p>
 * Esta clase inicia la aplicación y configura el escaneo de componentes para las capas
 * dominio, aplicación e infraestructura según el patrón hexagonal.
 * </p>
 */
@SpringBootApplication
@ComponentScan(basePackages = {
    "com.pragma.ordersystem.domain",
    "com.pragma.ordersystem.application",
    "com.pragma.ordersystem.infrastructure",
    "com.pragma.ordersystem.config"
})
public class OrdersystemApplication {

    /**
     * Método principal que inicia la aplicación Spring Boot.
     *
     * @param args Argumentos de línea de comandos.
     */
    public static void main(String[] args) {
        SpringApplication.run(OrdersystemApplication.class, args);
    }
}

// === ARCHIVO: src/main/resources/application.yml ===
# Configuración de Spring Boot para el sistema de gestión de pedidos
spring:
  application:
    name: ordersystem
  datasource:
    url: jdbc:h2:mem:ordersystemdb;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE
    driver-class-name: org.h2.Driver
    username: sa
    password: ""
    hikari:
      maximum-pool-size: 20
      connection-timeout: 30000
  h2:
    console:
      enabled: true
      path: /h2-console
      settings:
        web-allow-others: true
  jpa:
    database-platform: org.hibernate.dialect.H2Dialect
    hibernate:
      ddl-auto: update
    show-sql: true
    properties:
      hibernate:
        format_sql: true
        use_sql_comments: true
        jdbc:
          time_zone: UTC
        batch_size: 50
        order_inserts: true
        order_updates: true

# Configuración de logging
logging:
  level:
    root: INFO
    com.pragma.ordersystem: DEBUG
    org.hibernate.SQL: DEBUG
    org.hibernate.type.descriptor.sql.BasicBinder: TRACE
    org.springframework.web: INFO
    org.springframework.transaction: INFO

# Configuración de servidor
server:
  port: 8080
  servlet:
    context-path: /api
  tomcat:
    threads:
      max: 200
    connection-timeout: 20000ms

# Configuración de métricas
management:
  endpoints:
    web:
      exposure:
        include: health,metrics,prometheus
  endpoint:
    health:
      show-details: always
  metrics:
    export:
      prometheus:
        enabled: true

// === ARCHIVO: src/main/java/com/pragma/ordersystem/domain/model/Order.java ===
package com.pragma.ordersystem.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class Order {
    private final UUID id;
    private final Customer customer;
    private final List<OrderItem> items;
    private OrderStatus status;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private BigDecimal totalAmount;

    public Order(UUID id, Customer customer, List<OrderItem> items, OrderStatus status) {
        this.id = id != null ? id : UUID.randomUUID();
        this.customer = validateCustomer(customer);
        this.items = validateItems(items);
        this.status = status != null ? status : OrderStatus.CREATED;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        this.totalAmount = calculateTotalAmount();
    }

    private Customer validateCustomer(Customer customer) {
        if (customer == null) {
            throw new IllegalArgumentException("El cliente no puede ser nulo");
        }
        return customer;
    }

    private List<OrderItem> validateItems(List<OrderItem> items) {
        if (items == null || items.isEmpty()) {
            throw new IllegalArgumentException("La lista de items no puede ser nula o vacía");
        }
        return items.stream()
                .peek(item -> {
                    if (item == null) {
                        throw new IllegalArgumentException("Ningún item puede ser nulo");
                    }
                })
                .collect(Collectors.toUnmodifiableList());
    }

    private BigDecimal calculateTotalAmount() {
        return items.stream()
                .map(OrderItem::getTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public UUID getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void updateStatus(OrderStatus newStatus) {
        if (newStatus == null) {
            throw new IllegalArgumentException("El nuevo estado no puede ser nulo");
        }
        this.status = newStatus;
        this.updatedAt = LocalDateTime.now();
    }

    public void cancel() {
        if (this.status == OrderStatus.CANCELLED || this.status == OrderStatus.DELIVERED) {
            throw new IllegalStateException("No se puede cancelar un pedido en estado " + this.status);
        }
        this.status = OrderStatus.CANCELLED;
        this.updatedAt = LocalDateTime.now();
    }

    public static class OrderItem {
        private final UUID productId;
        private final String productName;
        private final int quantity;
        private final BigDecimal unitPrice;

        public OrderItem(UUID productId, String productName, int quantity, BigDecimal unitPrice) {
            this.productId = validateProductId(productId);
            this.productName = validateProductName(productName);
            this.quantity = validateQuantity(quantity);
            this.unitPrice = validateUnitPrice(unitPrice);
        }

        private UUID validateProductId(UUID productId) {
            if (productId == null) {
                throw new IllegalArgumentException("El ID del producto no puede ser nulo");
            }
            return productId;
        }

        private String validateProductName(String productName) {
            if (productName == null || productName.trim().isEmpty()) {
                throw new IllegalArgumentException("El nombre del producto no puede ser nulo o vacío");
            }
            return productName;
        }

        private int validateQuantity(int quantity) {
            if (quantity <= 0) {
                throw new IllegalArgumentException("La cantidad debe ser mayor que cero");
            }
            return quantity;
        }

        private BigDecimal validateUnitPrice(BigDecimal unitPrice) {
            if (unitPrice == null || unitPrice.compareTo(BigDecimal.ZERO) <= 0) {
                throw new IllegalArgumentException("El precio unitario debe ser mayor que cero");
            }
            return unitPrice;
        }

        public UUID getProductId() {
            return productId;
        }

        public String getProductName() {
            return productName;
        }

        public int getQuantity() {
            return quantity;
        }

        public BigDecimal getUnitPrice() {
            return unitPrice;
        }

        public BigDecimal getTotalPrice() {
            return unitPrice.multiply(BigDecimal.valueOf(quantity));
        }
    }

    public enum OrderStatus {
        CREATED,
        PROCESSING,
        SHIPPED,
        DELIVERED,
        CANCELLED
    }
}

// === ARCHIVO: src/main/java/com/pragma/ordersystem/domain/model/Product.java ===
package com.pragma.ordersystem.domain.model;

import java.math.BigDecimal;
import java.util.UUID;

public class Product {
    private final UUID id;
    private final String name;
    private final String description;
    private final BigDecimal price;
    private int stock;
    private final String category;
    private final boolean active;

    public Product(UUID id, String name, String description, BigDecimal price, int stock, String category, boolean active) {
        this.id = id != null ? id : UUID.randomUUID();
        this.name = validateName(name);
        this.description = description != null ? description : "";
        this.price = validatePrice(price);
        this.stock = validateStock(stock);
        this.category = validateCategory(category);
        this.active = active;
    }

    private String validateName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del producto no puede ser nulo o vacío");
        }
        return name;
    }

    private BigDecimal validatePrice(BigDecimal price) {
        if (price == null || price.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El precio debe ser mayor que cero");
        }
        return price;
    }

    private int validateStock(int stock) {
        if (stock < 0) {
            throw new IllegalArgumentException("El stock no puede ser negativo");
        }
        return stock;
    }

    private String validateCategory(String category) {
        if (category == null || category.trim().isEmpty()) {
            throw new IllegalArgumentException("La categoría no puede ser nula o vacía");
        }
        return category;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public String getCategory() {
        return category;
    }

    public boolean isActive() {
        return active;
    }

    public void updateStock(int newStock) {
        if (newStock < 0) {
            throw new IllegalArgumentException("El stock no puede ser negativo");
        }
        this.stock = newStock;
    }

    public void reserveStock(int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("La cantidad a reservar debe ser mayor que cero");
        }
        if (this.stock < quantity) {
            throw new IllegalStateException("Stock insuficiente para el producto " + this.name);
        }
        this.stock -= quantity;
    }

    public void releaseStock(int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("La cantidad a liberar debe ser mayor que cero");
        }
        this.stock += quantity;
    }
}

// === ARCHIVO: src/main/java/com/pragma/ordersystem/domain/model/Customer.java ===
package com.pragma.ordersystem.domain.model;

import java.util.UUID;
import java.util.regex.Pattern;

public class Customer {
    private final UUID id;
    private final String name;
    private final String email;
    private final String phone;
    private final String address;
    private final CustomerStatus status;

    public Customer(UUID id, String name, String email, String phone, String address, CustomerStatus status) {
        this.id = id != null ? id : UUID.randomUUID();
        this.name = validateName(name);
        this.email = validateEmail(email);
        this.phone = validatePhone(phone);
        this.address = validateAddress(address);
        this.status = status != null ? status : CustomerStatus.ACTIVE;
    }

    private String validateName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede ser nulo o vacío");
        }
        return name;
    }

    private String validateEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("El email no puede ser nulo o vacío");
        }
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
        if (!Pattern.matches(regex, email)) {
            throw new IllegalArgumentException("El email no tiene un formato válido");
        }
        return email;
    }

    private String validatePhone(String phone) {
        if (phone == null || phone.trim().isEmpty()) {
            throw new IllegalArgumentException("El teléfono no puede ser nulo o vacío");
        }
        String regex = "^[0-9+() -]+$";
        if (!Pattern.matches(regex, phone)) {
            throw new IllegalArgumentException("El teléfono no tiene un formato válido");
        }
        return phone;
    }

    private String validateAddress(String address) {
        if (address == null || address.trim().isEmpty()) {
            throw new IllegalArgumentException("La dirección no puede ser nula o vacía");
        }
        return address;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public CustomerStatus getStatus() {
        return status;
    }

    public enum CustomerStatus {
        ACTIVE,
        INACTIVE,
        SUSPENDED
    }
}

// === ARCHIVO: src/main/java/com/pragma/ordersystem/domain/exception/OrderNotFoundException.java ===
package com.pragma.ordersystem.domain.exception;


import com.pragma.ordersystem.domain.model.Order;
import java.util.UUID;

public class OrderNotFoundException extends RuntimeException {
    private final UUID orderId;
    private final String searchCriteria;
    private final long timestamp;

    public OrderNotFoundException(UUID orderId) {
        super(buildMessage(orderId, null));
        this.orderId = orderId;
        this.searchCriteria = orderId.toString();
        this.timestamp = System.currentTimeMillis();
    }

    public OrderNotFoundException(String message) {
        super(message);
        this.orderId = null;
        this.searchCriteria = message;
        this.timestamp = System.currentTimeMillis();
    }

    public OrderNotFoundException(UUID orderId, String searchCriteria) {
        super(buildMessage(orderId, searchCriteria));
        this.orderId = orderId;
        this.searchCriteria = searchCriteria;
        this.timestamp = System.currentTimeMillis();
    }

    private static String buildMessage(UUID orderId, String criteria) {
        if (criteria != null && !criteria.equals(orderId != null ? orderId.toString() : "")) {
            return "Order not found with criteria: " + criteria;
        }
        return "Order not found with ID: " + (orderId != null ? orderId.toString() : "unknown");
    }

    public UUID getOrderId() {
        return orderId;
    }

    public String getSearchCriteria() {
        return searchCriteria;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getErrorCode() {
        return "ORDER_NOT_FOUND";
    }

    public String getDetailedMessage() {
        return String.format("ErrorCode: %s, OrderId: %s, Criteria: %s, Timestamp: %d",
                getErrorCode(),
                orderId != null ? orderId.toString() : "null",
                searchCriteria,
                timestamp);
    }
}

// === ARCHIVO: src/main/java/com/pragma/ordersystem/domain/exception/ProductOutOfStockException.java ===
package com.pragma.ordersystem.domain.exception;


import com.pragma.ordersystem.domain.model.Product;
import java.math.BigDecimal;
import java.util.UUID;

public class ProductOutOfStockException extends RuntimeException {
    private final UUID productId;
    private final String productName;
    private final int requestedQuantity;
    private final int availableStock;
    private final BigDecimal productPrice;
    private final String category;
    private final long timestamp;

    public ProductOutOfStockException(UUID productId, String productName, int requestedQuantity, int availableStock) {
        super(buildMessage(productId, productName, requestedQuantity, availableStock));
        this.productId = productId;
        this.productName = productName;
        this.requestedQuantity = requestedQuantity;
        this.availableStock = availableStock;
        this.productPrice = null;
        this.category = null;
        this.timestamp = System.currentTimeMillis();
    }

    public ProductOutOfStockException(UUID productId, String productName, int requestedQuantity, 
                                       int availableStock, BigDecimal productPrice, String category) {
        super(buildDetailedMessage(productId, productName, requestedQuantity, availableStock, productPrice, category));
        this.productId = productId;
        this.productName = productName;
        this.requestedQuantity = requestedQuantity;
        this.availableStock = availableStock;
        this.productPrice = productPrice;
        this.category = category;
        this.timestamp = System.currentTimeMillis();
    }

    private static String buildMessage(UUID productId, String productName, int requested, int available) {
        return String.format("Product out of stock: ID=%s, Name=%s, Requested=%d, Available=%d",
                productId != null ? productId.toString() : "unknown",
                productName != null ? productName : "unknown",
                requested,
                available);
    }

    private static String buildDetailedMessage(UUID productId, String productName, int requested, 
                                                int available, BigDecimal price, String category) {
        StringBuilder sb = new StringBuilder();
        sb.append("Product out of stock: ");
        sb.append("ID=").append(productId != null ? productId.toString() : "unknown");
        sb.append(", Name=").append(productName != null ? productName : "unknown");
        sb.append(", Requested=").append(requested);
        sb.append(", Available=").append(available);
        if (price != null) {
            sb.append(", Price=").append(price.toString());
        }
        if (category != null) {
            sb.append(", Category=").append(category);
        }
        return sb.toString();
    }

    public UUID getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public int getRequestedQuantity() {
        return requestedQuantity;
    }

    public int getAvailableStock() {
        return availableStock;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public String getCategory() {
        return category;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getErrorCode() {
        return "PRODUCT_OUT_OF_STOCK";
    }

    public boolean hasEnoughStockFor(int quantity) {
        return availableStock >= quantity;
    }

    public int getShortageAmount() {
        return Math.max(0, requestedQuantity - availableStock);
    }

    public String getDetailedMessage() {
        return String.format("ErrorCode: %s, ProductId: %s, ProductName: %s, Requested: %d, Available: %d, Timestamp: %d",
                getErrorCode(),
                productId != null ? productId.toString() : "null",
                productName,
                requestedQuantity,
                availableStock,
                timestamp);
    }
}

// === ARCHIVO: src/main/java/com/pragma/ordersystem/domain/exception/PaymentFailedException.java ===
package com.pragma.ordersystem.domain.exception;

import java.math.BigDecimal;
import java.util.UUID;

public class PaymentFailedException extends RuntimeException {
    private final UUID orderId;
    private final UUID customerId;
    private final BigDecimal amount;
    private final PaymentMethod paymentMethod;
    private final PaymentFailureReason reason;
    private final String transactionId;
    private final long timestamp;

    public enum PaymentMethod {
        CREDIT_CARD,
        DEBIT_CARD,
        PAYPAL,
        BANK_TRANSFER,
        CRYPTO
    }

    public enum PaymentFailureReason {
        INSUFFICIENT_FUNDS,
        CARD_EXPIRED,
        INVALID_CARD,
        PAYMENT_GATEWAY_ERROR,
        NETWORK_ERROR,
        FRAUD_DETECTED,
        LIMIT_EXCEEDED,
        CURRENCY_NOT_SUPPORTED,
        TIMEOUT,
        UNKNOWN
    }

    public PaymentFailedException(UUID orderId, UUID customerId, BigDecimal amount, PaymentMethod method, PaymentFailureReason reason) {
        super(buildMessage(orderId, amount, method, reason));
        this.orderId = orderId;
        this.customerId = customerId;
        this.amount = amount;
        this.paymentMethod = method;
        this.reason = reason;
        this.transactionId = null;
        this.timestamp = System.currentTimeMillis();
    }

    public PaymentFailedException(UUID orderId, UUID customerId, BigDecimal amount, 
                                  PaymentMethod method, PaymentFailureReason reason, String transactionId) {
        super(buildMessageWithTransaction(orderId, amount, method, reason, transactionId));
        this.orderId = orderId;
        this.customerId = customerId;
        this.amount = amount;
        this.paymentMethod = method;
        this.reason = reason;
        this.transactionId = transactionId;
        this.timestamp = System.currentTimeMillis();
    }

    public PaymentFailedException(String message) {
        super(message);
        this.orderId = null;
        this.customerId = null;
        this.amount = null;
        this.paymentMethod = null;
        this.reason = PaymentFailureReason.UNKNOWN;
        this.transactionId = null;
        this.timestamp = System.currentTimeMillis();
    }

    private static String buildMessage(UUID orderId, BigDecimal amount, PaymentMethod method, PaymentFailureReason reason) {
        return String.format("Payment failed for order %s: Amount=%s, Method=%s, Reason=%s",
                orderId != null ? orderId.toString() : "unknown",
                amount != null ? amount.toString() : "0.00",
                method != null ? method.name() : "UNKNOWN",
                reason != null ? reason.name() : "UNKNOWN");
    }

    private static String buildMessageWithTransaction(UUID orderId, BigDecimal amount, 
                                                       PaymentMethod method, PaymentFailureReason reason, String transactionId) {
        return String.format("Payment failed for order %s: Amount=%s, Method=%s, Reason=%s, TransactionId=%s",
                orderId != null ? orderId.toString() : "unknown",
                amount != null ? amount.toString() : "0.00",
                method != null ? method.name() : "UNKNOWN",
                reason != null ? reason.name() : "UNKNOWN",
                transactionId != null ? transactionId : "N/A");
    }

    public UUID getOrderId() {
        return orderId;
    }

    public UUID getCustomerId() {
        return customerId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public PaymentFailureReason getReason() {
        return reason;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getErrorCode() {
        return "PAYMENT_FAILED";
    }

    public boolean isRetryable() {
        return reason == PaymentFailureReason.NETWORK_ERROR || 
               reason == PaymentFailureReason.TIMEOUT || 
               reason == PaymentFailureReason.PAYMENT_GATEWAY_ERROR;
    }

    public boolean isFraudRelated() {
        return reason == PaymentFailureReason.FRAUD_DETECTED;
    }

    public String getDetailedMessage() {
        return String.format("ErrorCode: %s, OrderId: %s, CustomerId: %s, Amount: %s, Method: %s, Reason: %s, TransactionId: %s, Timestamp: %d",
                getErrorCode(),
                orderId != null ? orderId.toString() : "null",
                customerId != null ? customerId.toString() : "null",
                amount != null ? amount.toString() : "null",
                paymentMethod != null ? paymentMethod.name() : "null",
                reason != null ? reason.name() : "null",
                transactionId,
                timestamp);
    }
}

// === ARCHIVO: src/main/java/com/pragma/ordersystem/domain/port/OrderRepository.java ===
package com.pragma.ordersystem.domain.port;

import com.pragma.ordersystem.domain.model.Order;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OrderRepository {
    
    Order save(Order order);
    
    Optional<Order> findById(UUID id);
    
    List<Order> findAll();
    
    List<Order> findByCustomerId(UUID customerId);
    
    List<Order> findByStatus(String status);
    
    void delete(UUID id);
    
    boolean existsById(UUID id);
    
    long count();
    
    List<Order> findByCustomerIdAndStatus(UUID customerId, String status);
    
    List<Order> findOrdersCreatedAfter(java.time.LocalDateTime dateTime);
}

// === ARCHIVO: src/main/java/com/pragma/ordersystem/domain/service/OrderService.java ===
package com.pragma.ordersystem.domain.service;

import com.pragma.ordersystem.domain.exception.OrderNotFoundException;
import com.pragma.ordersystem.domain.exception.PaymentFailedException;
import com.pragma.ordersystem.domain.exception.ProductOutOfStockException;
import com.pragma.ordersystem.domain.model.Customer;
import com.pragma.ordersystem.domain.model.Order;
import com.pragma.ordersystem.domain.model.Order.OrderStatus;
import com.pragma.ordersystem.domain.model.Order.OrderItem;
import com.pragma.ordersystem.domain.model.Product;
import com.pragma.ordersystem.domain.port.OrderRepository;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class OrderService {
    
    private final OrderRepository orderRepository;
    
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }
    
    public Order createOrder(Customer customer, List<OrderItem> items) {
        validateItemsNotEmpty(items);
        validateItemsStock(items);
        
        Order order = new Order(
            UUID.randomUUID(),
            customer,
            items,
            OrderStatus.PENDING,
            LocalDateTime.now(),
            null,
            BigDecimal.ZERO
        );
        
        Order savedOrder = orderRepository.save(order);
        reserveStockForItems(items);
        
        return savedOrder;
    }
    
    public Order getOrderById(UUID orderId) {
        return orderRepository.findById(orderId)
            .orElseThrow(() -> new OrderNotFoundException(
                "Pedido no encontrado con ID: " + orderId));
    }
    
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
    
    public List<Order> getOrdersByCustomer(UUID customerId) {
        return orderRepository.findByCustomerId(customerId);
    }
    
    public List<Order> getOrdersByStatus(OrderStatus status) {
        return orderRepository.findByStatus(status.name());
    }
    
    public Order updateOrderStatus(UUID orderId, OrderStatus newStatus) {
        Order order = getOrderById(orderId);
        validateStatusTransition(order.getStatus(), newStatus);
        order.updateStatus(newStatus);
        return orderRepository.save(order);
    }
    
    public Order cancelOrder(UUID orderId) {
        Order order = getOrderById(orderId);
        
        if (order.getStatus() == OrderStatus.DELIVERED || 
            order.getStatus() == OrderStatus.CANCELLED) {
            throw new IllegalStateException(
                "No se puede cancelar un pedido en estado: " + order.getStatus());
        }
        
        order.cancel();
        releaseStockForItems(order.getItems());
        return orderRepository.save(order);
    }
    
    public Order confirmPayment(UUID orderId) {
        Order order = getOrderById(orderId);
        
        if (order.getStatus() != OrderStatus.PENDING) {
            throw new PaymentFailedException(
                "El pedido no está en estado pendiente de pago");
        }
        
        order.updateStatus(OrderStatus.CONFIRMED);
        return orderRepository.save(order);
    }
    
    public Order markAsDelivered(UUID orderId) {
        Order order = getOrderById(orderId);
        
        if (order.getStatus() != OrderStatus.SHIPPED) {
            throw new IllegalStateException(
                "El pedido debe estar en estado enviado para marcar como entregado");
        }
        
        order.updateStatus(OrderStatus.DELIVERED);
        return orderRepository.save(order);
    }
    
    public Order markAsShipped(UUID orderId) {
        Order order = getOrderById(orderId);
        
        if (order.getStatus() != OrderStatus.CONFIRMED) {
            throw new IllegalStateException(
                "El pedido debe estar confirmado para ser enviado");
        }
        
        order.updateStatus(OrderStatus.SHIPPED);
        return orderRepository.save(order);
    }
    
    public List<Order> getOrdersByCustomerAndStatus(UUID customerId, OrderStatus status) {
        return orderRepository.findByCustomerIdAndStatus(customerId, status.name());
    }
    
    public List<Order> getRecentOrders(LocalDateTime since) {
        return orderRepository.findOrdersCreatedAfter(since);
    }
    
    public long getTotalOrdersCount() {
        return orderRepository.count();
    }
    
    private void validateItemsNotEmpty(List<OrderItem> items) {
        if (items == null || items.isEmpty()) {
            throw new IllegalArgumentException(
                "El pedido debe contener al menos un artículo");
        }
    }
    
    private void validateItemsStock(List<OrderItem> items) {
        for (OrderItem item : items) {
            Product product = item.getProduct();
            if (product == null) {
                throw new IllegalArgumentException(
                    "El artículo no tiene un producto asociado");
            }
            if (product.getStock() < item.getQuantity()) {
                throw new ProductOutOfStockException(
                    "Stock insuficiente para el producto: " + product.getName() +
                    ". Stock disponible: " + product.getStock() +
                    ", cantidad solicitada: " + item.getQuantity());
            }
        }
    }
    
    private void reserveStockForItems(List<OrderItem> items) {
        for (OrderItem item : items) {
            Product product = item.getProduct();
            product.reserveStock(item.getQuantity());
        }
    }
    
    private void releaseStockForItems(List<OrderItem> items) {
        for (OrderItem item : items) {
            Product product = item.getProduct();
            product.releaseStock(item.getQuantity());
        }
    }
    
    private void validateStatusTransition(OrderStatus currentStatus, OrderStatus newStatus) {
        boolean validTransition = switch (currentStatus) {
            case PENDING -> newStatus == OrderStatus.CONFIRMED || 
                            newStatus == OrderStatus.CANCELLED;
            case CONFIRMED -> newStatus == OrderStatus.SHIPPED || 
                              newStatus == OrderStatus.CANCELLED;
            case SHIPPED -> newStatus == OrderStatus.DELIVERED;
            case DELIVERED, CANCELLED -> false;
        };
        
        if (!validTransition) {
            throw new IllegalStateException(
                "Transición de estado inválida: " + currentStatus + " -> " + newStatus);
        }
    }
}

// === ARCHIVO: src/main/java/com/pragma/ordersystem/infrastructure/adapter/OrderJpaRepository.java ===
package com.pragma.ordersystem.infrastructure.adapter;


import com.pragma.ordersystem.infrastructure.entity.OrderItemEntity;
import com.pragma.ordersystem.domain.model.Customer;
import com.pragma.ordersystem.domain.model.Customer.CustomerStatus;
import com.pragma.ordersystem.domain.model.Order;
import com.pragma.ordersystem.domain.model.Order.OrderItem;
import com.pragma.ordersystem.domain.model.Order.OrderStatus;
import com.pragma.ordersystem.domain.model.Product;
import com.pragma.ordersystem.domain.port.OrderRepository;
import com.pragma.ordersystem.infrastructure.entity.OrderEntity;
import com.pragma.ordersystem.infrastructure.entity.CustomerEntity;
import com.pragma.ordersystem.infrastructure.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class OrderJpaRepository extends JpaRepository<OrderEntity, UUID> implements OrderRepository {
    
    @Override
    public Order save(Order order) {
        OrderEntity entity = toEntity(order);
        OrderEntity savedEntity = save(entity);
        return toDomain(savedEntity);
    }
    
    @Override
    public Optional<Order> findById(UUID id) {
        return findById(id).map(this::toDomain);
    }
    
    @Override
    public List<Order> findAll() {
        return findAll().stream()
            .map(this::toDomain)
            .collect(Collectors.toList());
    }
    
    @Override
    public List<Order> findByCustomerId(UUID customerId) {
        return findAll().stream()
            .filter(order -> order.getCustomer() != null && 
                           order.getCustomer().getId().equals(customerId))
            .map(this::toDomain)
            .collect(Collectors.toList());
    }
    
    @Override
    public List<Order> findByStatus(String status) {
        return findAll().stream()
            .filter(order -> order.getStatus().name().equals(status))
            .map(this::toDomain)
            .collect(Collectors.toList());
    }
    
    @Override
    public void delete(UUID id) {
        deleteById(id);
    }
    
    @Override
    public boolean existsById(UUID id) {
        return existsById(id);
    }
    
    @Override
    public long count() {
        return count();
    }
    
    @Override
    public List<Order> findByCustomerIdAndStatus(UUID customerId, String status) {
        return findAll().stream()
            .filter(order -> order.getCustomer() != null && 
                           order.getCustomer().getId().equals(customerId) &&
                           order.getStatus().name().equals(status))
            .map(this::toDomain)
            .collect(Collectors.toList());
    }
    
    @Override
    public List<Order> findOrdersCreatedAfter(LocalDateTime dateTime) {
        return findAll().stream()
            .filter(order -> order.getCreatedAt().isAfter(dateTime))
            .map(this::toDomain)
            .collect(Collectors.toList());
    }
    
    private OrderEntity toEntity(Order order) {
        OrderEntity entity = new OrderEntity();
        entity.setId(order.getId());
        entity.setStatus(order.getStatus().name());
        entity.setCreatedAt(order.getCreatedAt());
        entity.setUpdatedAt(order.getUpdatedAt());
        entity.setTotalAmount(order.getTotalAmount());
        
        if (order.getCustomer() != null) {
            CustomerEntity customerEntity = new CustomerEntity();
            customerEntity.setId(order.getCustomer().getId());
            customerEntity.setName(order.getCustomer().getName());
            customerEntity.setEmail(order.getCustomer().getEmail());
            customerEntity.setPhone(order.getCustomer().getPhone());
            customerEntity.setAddress(order.getCustomer().getAddress());
            customerEntity.setStatus(order.getCustomer().getStatus().name());
            entity.setCustomer(customerEntity);
        }
        
        List<OrderEntity.OrderItemEntity> itemEntities = new ArrayList<>();
        if (order.getItems() != null) {
            for (OrderItem item : order.getItems()) {
                OrderEntity.OrderItemEntity itemEntity = new OrderEntity.OrderItemEntity();
                itemEntity.setQuantity(item.getQuantity());
                itemEntity.setUnitPrice(item.getUnitPrice());
                itemEntity.setSubtotal(item.getSubtotal());
                
                if (item.getProduct() != null) {
                    ProductEntity productEntity = new ProductEntity();
                    productEntity.setId(item.getProduct().getId());
                    productEntity.setName(item.getProduct().getName());
                    productEntity.setDescription(item.getProduct().getDescription());
                    productEntity.setPrice(item.getProduct().getPrice());
                    productEntity.setStock(item.getProduct().getStock());
                    productEntity.setCategory(item.getProduct().getCategory());
                    productEntity.setActive(item.getProduct().isActive());
                    itemEntity.setProduct(productEntity);
                }
                
                itemEntities.add(itemEntity);
            }
        }
        entity.setItems(itemEntities);
        
        return entity;
    }
    
    private Order toDomain(OrderEntity entity) {
        Customer customer = null;
        if (entity.getCustomer() != null) {
            CustomerEntity ce = entity.getCustomer();
            customer = new Customer(
                ce.getId(),
                ce.getName(),
                ce.getEmail(),
                ce.getPhone(),
                ce.getAddress(),
                CustomerStatus.valueOf(ce.getStatus())
            );
        }
        
        List<OrderItem> items = new ArrayList<>();
        if (entity.getItems() != null) {
            for (OrderEntity.OrderItemEntity itemEntity : entity.getItems()) {
                Product product = null;
                if (itemEntity.getProduct() != null) {
                    ProductEntity pe = itemEntity.getProduct();
                    product = new Product(
                        pe.getId(),
                        pe.getName(),
                        pe.getDescription(),
                        pe.getPrice(),
                        pe.getStock(),
                        pe.getCategory(),
                        pe.isActive()
                    );
                }
                
                OrderItem item = new OrderItem(
                    itemEntity.getQuantity(),
                    product,
                    itemEntity.getUnitPrice()
                );
                items.add(item);
            }
        }
        
        Order order = new Order(
            entity.getId(),
            customer,
            items,
            OrderStatus.valueOf(entity.getStatus()),
            entity.getCreatedAt(),
            entity.getUpdatedAt(),
            entity.getTotalAmount()
        );
        
        return order;
    }
}

// === ARCHIVO: src/main/java/com/pragma/ordersystem/config/DatabaseConfig.java ===
package com.pragma.ordersystem.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.pragma.ordersystem.infrastructure.adapter")
public class DatabaseConfig {

    @Value("${spring.datasource.url:jdbc:h2:mem:ordersystemdb}")
    private String jdbcUrl;

    @Value("${spring.datasource.username:sa}")
    private String username;

    @Value("${spring.datasource.password:}")
    private String password;

    @Value("${spring.datasource.driver-class-name:org.h2.Driver}")
    private String driverClassName;

    @Value("${spring.jpa.hibernate.ddl-auto:update}")
    private String hibernateDdlAuto;

    @Value("${spring.jpa.show-sql:false}")
    private boolean showSql;

    @Value("${spring.jpa.properties.hibernate.dialect:org.hibernate.dialect.H2Dialect}")
    private String hibernateDialect;

    @Bean
    public DataSource dataSource() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(jdbcUrl);
        config.setUsername(username);
        config.setPassword(password);
        config.setDriverClassName(driverClassName);
        config.setMaximumPoolSize(10);
        config.setMinimumIdle(2);
        config.setConnectionTimeout(30000);
        config.setIdleTimeout(600000);
        config.setMaxLifetime(1800000);
        config.setConnectionTestQuery("SELECT 1");
        config.setPoolName("OrderSystemHikariCP");
        return new HikariDataSource(config);
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource);
        em.setPackagesToScan("com.pragma.ordersystem.infrastructure.entity");

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(true);
        vendorAdapter.setShowSql(showSql);
        em.setJpaVendorAdapter(vendorAdapter);

        Properties hibernateProperties = new Properties();
        hibernateProperties.setProperty("hibernate.hbm2ddl.auto", hibernateDdlAuto);
        hibernateProperties.setProperty("hibernate.dialect", hibernateDialect);
        hibernateProperties.setProperty("hibernate.format_sql", "true");
        hibernateProperties.setProperty("hibernate.use_sql_comments", "true");
        hibernateProperties.setProperty("hibernate.jdbc.batch_size", "20");
        hibernateProperties.setProperty("hibernate.order_inserts", "true");
        hibernateProperties.setProperty("hibernate.order_updates", "true");
        hibernateProperties.setProperty("hibernate.jdbc.batch_versioned_data", "true");
        em.setJpaProperties(hibernateProperties);

        return em;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }
}

// === ARCHIVO: src/main/java/com/pragma/ordersystem/application/rest/OrderController.java ===
package com.pragma.ordersystem.application.rest;

import com.pragma.ordersystem.domain.exception.OrderNotFoundException;
import com.pragma.ordersystem.domain.model.Order;
import com.pragma.ordersystem.domain.model.Order.OrderStatus;
import com.pragma.ordersystem.domain.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        Order createdOrder = orderService.createOrder(order);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdOrder);
    }

    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> orders = orderService.getAllOrders();
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable UUID id) {
        try {
            Order order = orderService.getOrderById(id);
            return ResponseEntity.ok(order);
        } catch (OrderNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<Order>> getOrdersByStatus(@PathVariable OrderStatus status) {
        List<Order> orders = orderService.getOrdersByStatus(status);
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<Order>> getOrdersByCustomer(@PathVariable UUID customerId) {
        List<Order> orders = orderService.getOrdersByCustomerId(customerId);
        return ResponseEntity.ok(orders);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Order> updateOrderStatus(
            @PathVariable UUID id,
            @RequestParam OrderStatus newStatus) {
        try {
            Order updatedOrder = orderService.updateOrderStatus(id, newStatus);
            return ResponseEntity.ok(updatedOrder);
        } catch (OrderNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}/cancel")
    public ResponseEntity<Order> cancelOrder(@PathVariable UUID id) {
        try {
            Order cancelledOrder = orderService.cancelOrder(id);
            return ResponseEntity.ok(cancelledOrder);
        } catch (OrderNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}/total")
    public ResponseEntity<BigDecimal> getOrderTotal(@PathVariable UUID id) {
        try {
            BigDecimal total = orderService.calculateOrderTotal(id);
            return ResponseEntity.ok(total);
        } catch (OrderNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable UUID id) {
        try {
            orderService.deleteOrder(id);
            return ResponseEntity.noContent().build();
        } catch (OrderNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/health")
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok("Order Service is running");
    }
}

// === ARCHIVO: src/main/java/com/pragma/ordersystem/infrastructure/entity/OrderEntity.java ===
package com.pragma.ordersystem.infrastructure.entity;

import com.pragma.ordersystem.domain.model.Customer;
import com.pragma.ordersystem.domain.model.Order;
import com.pragma.ordersystem.domain.model.Order.OrderItem;
import com.pragma.ordersystem.domain.model.Order.OrderStatus;
import com.pragma.ordersystem.domain.model.Product;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "orders", indexes = {
    @Index(name = "idx_order_customer_id", columnList = "customer_id"),
    @Index(name = "idx_order_status", columnList = "status"),
    @Index(name = "idx_order_created_at", columnList = "created_at")
})
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "customer_id", nullable = false)
    private UUID customerId;

    @Column(name = "customer_name", nullable = false)
    private String customerName;

    @Column(name = "customer_email", nullable = false)
    private String customerEmail;

    @Column(name = "customer_phone")
    private String customerPhone;

    @Column(name = "customer_address")
    private String customerAddress;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private OrderStatus status;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "total_amount", nullable = false, precision = 10, scale = 2)
    private BigDecimal totalAmount;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<OrderItemEntity> items = new ArrayList<>();

    public OrderEntity() {
    }

    public OrderEntity(UUID id, UUID customerId, String customerName, String customerEmail,
                       String customerPhone, String customerAddress, OrderStatus status,
                       LocalDateTime createdAt, LocalDateTime updatedAt, BigDecimal totalAmount) {
        this.id = id;
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.customerPhone = customerPhone;
        this.customerAddress = customerAddress;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.totalAmount = totalAmount;
    }

    public static OrderEntity fromDomain(Order order) {
        OrderEntity entity = new OrderEntity();
        entity.setId(order.getId());
        entity.setStatus(order.getStatus());
        entity.setCreatedAt(order.getCreatedAt());
        entity.setUpdatedAt(order.getUpdatedAt());
        entity.setTotalAmount(order.getTotalAmount());

        if (order.getCustomer() != null) {
            Customer customer = order.getCustomer();
            entity.setCustomerId(customer.getId());
            entity.setCustomerName(customer.getName());
            entity.setCustomerEmail(customer.getEmail());
            entity.setCustomerPhone(customer.getPhone());
            entity.setCustomerAddress(customer.getAddress());
        }

        if (order.getItems() != null) {
            for (OrderItem item : order.getItems()) {
                OrderItemEntity itemEntity = OrderItemEntity.fromDomain(item);
                itemEntity.setOrder(entity);
                entity.getItems().add(itemEntity);
            }
        }

        return entity;
    }

    public Order toDomain() {
        Customer customer = new Customer(
            customerId,
            customerName,
            customerEmail,
            customerPhone,
            customerAddress,
            null
        );

        List<OrderItem> orderItems = new ArrayList<>();
        for (OrderItemEntity itemEntity : items) {
            orderItems.add(itemEntity.toDomain());
        }

        Order order = new Order(
            id,
            customer,
            orderItems,
            status
        );

        return order;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getCustomerId() {
        return customerId;
    }

    public void setCustomerId(UUID customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public List<OrderItemEntity> getItems() {
        return items;
    }

    public void setItems(List<OrderItemEntity> items) {
        this.items = items;
    }

    @Entity
    @Table(name = "order_items", indexes = {
        @Index(name = "idx_order_item_order_id", columnList = "order_id"),
        @Index(name = "idx_order_item_product_id", columnList = "product_id")
    })
    public static class OrderItemEntity {

        @Id
        @GeneratedValue(strategy = GenerationType.UUID)
        private UUID id;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "order_id", nullable = false)
        private OrderEntity order;

        @Column(name = "product_id", nullable = false)
        private UUID productId;

        @Column(name = "product_name", nullable = false)
        private String productName;

        @Column(nullable = false)
        private Integer quantity;

        @Column(name = "unit_price", nullable = false, precision = 10, scale = 2)
        private BigDecimal unitPrice;

        @Column(name = "subtotal", nullable = false, precision = 10, scale = 2)
        private BigDecimal subtotal;

        public OrderItemEntity() {
        }

        public static OrderItemEntity fromDomain(OrderItem item) {
            OrderItemEntity entity = new OrderItemEntity();
            if (item.getProduct() != null) {
                Product product = item.getProduct();
                entity.setProductId(product.getId());
                entity.setProductName(product.getName());
            }
            entity.setQuantity(item.getQuantity());
            entity.setUnitPrice(item.getUnitPrice());
            entity.setSubtotal(item.getSubtotal());
            return entity;
        }

        public OrderItem toDomain() {
            Product product = new Product(
                productId,
                productName,
                null,
                unitPrice,
                0,
                null,
                true
            );
            return new OrderItem(product, quantity, unitPrice);
        }

        public UUID getId() {
            return id;
        }

        public void setId(UUID id) {
            this.id = id;
        }

        public OrderEntity getOrder() {
            return order;
        }

        public void setOrder(OrderEntity order) {
            this.order = order;
        }

        public UUID getProductId() {
            return productId;
        }

        public void setProductId(UUID productId) {
            this.productId = productId;
        }

        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }

        public Integer getQuantity() {
            return quantity;
        }

        public void setQuantity(Integer quantity) {
            this.quantity = quantity;
        }

        public BigDecimal getUnitPrice() {
            return unitPrice;
        }

        public void setUnitPrice(BigDecimal unitPrice) {
            this.unitPrice = unitPrice;
        }

        public BigDecimal getSubtotal() {
            return subtotal;
        }

        public void setSubtotal(BigDecimal subtotal) {
            this.subtotal = subtotal;
        }
    }
}

// === ARCHIVO: src/main/java/com/pragma/ordersystem/infrastructure/entity/ProductEntity.java ===
package com.pragma.ordersystem.infrastructure.entity;

import com.pragma.ordersystem.domain.model.Product;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "products")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(length = 500)
    private String description;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @Column(nullable = false)
    private int stock;

    @Column(nullable = false, length = 50)
    private String category;

    @Column(nullable = false)
    private boolean active;

    public ProductEntity() {
    }

    public ProductEntity(UUID id, String name, String description, BigDecimal price, 
                         int stock, String category, boolean active) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.category = category;
        this.active = active;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Product toDomain() {
        return new Product(
            this.id,
            this.name,
            this.description,
            this.price,
            this.stock,
            this.category,
            this.active
        );
    }

    public static ProductEntity fromDomain(Product product) {
        if (product == null) {
            return null;
        }
        return new ProductEntity(
            product.getId(),
            product.getName(),
            product.getDescription(),
            product.getPrice(),
            product.getStock(),
            product.getCategory(),
            product.isActive()
        );
    }
}

// === ARCHIVO: src/main/java/com/pragma/ordersystem/infrastructure/entity/CustomerEntity.java ===
package com.pragma.ordersystem.infrastructure.entity;

import com.pragma.ordersystem.domain.model.Customer;
import com.pragma.ordersystem.domain.model.Customer.CustomerStatus;
import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "customers")
public class CustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, unique = true, length = 150)
    private String email;

    @Column(length = 20)
    private String phone;

    @Column(length = 300)
    private String address;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private CustomerStatus status;

    public CustomerEntity() {
    }

    public CustomerEntity(UUID id, String name, String email, String phone, 
                          String address, CustomerStatus status) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.status = status;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public CustomerStatus getStatus() {
        return status;
    }

    public void setStatus(CustomerStatus status) {
        this.status = status;
    }

    public Customer toDomain() {
        return new Customer(
            this.id,
            this.name,
            this.email,
            this.phone,
            this.address,
            this.status
        );
    }

    public static CustomerEntity fromDomain(Customer customer) {
        if (customer == null) {
            return null;
        }
        return new CustomerEntity(
            customer.getId(),
            customer.getName(),
            customer.getEmail(),
            customer.getPhone(),
            customer.getAddress(),
            customer.getStatus()
        );
    }
}

// === ARCHIVO: src/test/java/com/pragma/ordersystem/domain/service/OrderServiceTest.java ===
package com.pragma.ordersystem.domain.service;




import com.pragma.ordersystem.domain.model.OrderStatus;
import com.pragma.ordersystem.domain.model.OrderItem;
import com.pragma.ordersystem.domain.model.CustomerStatus;
import com.pragma.ordersystem.domain.model.Order;
import com.pragma.ordersystem.domain.model.Customer;
import com.pragma.ordersystem.domain.model.Product;
import com.pragma.ordersystem.domain.port.OrderRepository;
import com.pragma.ordersystem.domain.exception.OrderNotFoundException;
import com.pragma.ordersystem.domain.exception.ProductOutOfStockException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Tests para OrderService")
class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderService orderService;

    private Order testOrder;
    private Customer testCustomer;
    private Product testProduct;

    @BeforeEach
    void setUp() {
        testCustomer = new Customer(
                UUID.randomUUID(),
                "Juan Pérez",
                "juan@email.com",
                "+1234567890",
                "Calle 123",
                Customer.CustomerStatus.ACTIVE
        );

        testProduct = new Product(
                UUID.randomUUID(),
                "Producto Test",
                "Descripción del producto",
                new BigDecimal("99.99"),
                10,
                "Electrónica",
                true
        );

        List<Order.OrderItem> items = new ArrayList<>();
        items.add(new Order.OrderItem(testProduct, 2));

        testOrder = new Order(
                UUID.randomUUID(),
                testCustomer,
                items,
                Order.OrderStatus.PENDING,
                LocalDateTime.now()
        );
    }

    @Test
    @DisplayName("Crear un pedido exitosamente")
    void testCreateOrder_Success() {
        when(orderRepository.save(any(Order.class))).thenReturn(testOrder);

        Order result = orderService.createOrder(testOrder);

        assertNotNull(result);
        assertEquals(Order.OrderStatus.PENDING, result.getStatus());
        verify(orderRepository, times(1)).save(any(Order.class));
    }

    @Test
    @DisplayName("Obtener un pedido por ID exitosamente")
    void testGetOrderById_Success() {
        UUID orderId = testOrder.getId();
        when(orderRepository.findById(orderId)).thenReturn(Optional.of(testOrder));

        Order result = orderService.getOrderById(orderId);

        assertNotNull(result);
        assertEquals(orderId, result.getId());
    }

    @Test
    @DisplayName("Lanzar excepción cuando el pedido no existe")
    void testGetOrderById_NotFound() {
        UUID orderId = UUID.randomUUID();
        when(orderRepository.findById(orderId)).thenReturn(Optional.empty());

        assertThrows(OrderNotFoundException.class, () -> orderService.getOrderById(orderId));
    }

    @Test
    @DisplayName("Cancelar un pedido exitosamente")
    void testCancelOrder_Success() {
        when(orderRepository.findById(testOrder.getId())).thenReturn(Optional.of(testOrder));
        when(orderRepository.save(any(Order.class))).thenReturn(testOrder);

        orderService.cancelOrder(testOrder.getId());

        verify(orderRepository, times(1)).save(any(Order.class));
    }

    @Test
    @DisplayName("Actualizar estado de un pedido")
    void testUpdateOrderStatus() {
        when(orderRepository.findById(testOrder.getId())).thenReturn(Optional.of(testOrder));
        when(orderRepository.save(any(Order.class))).thenReturn(testOrder);

        orderService.updateOrderStatus(testOrder.getId(), Order.OrderStatus.COMPLETED);

        verify(orderRepository, times(1)).save(any(Order.class));
    }

    @Test
    @DisplayName("Obtener todos los pedidos")
    void testGetAllOrders() {
        List<Order> orders = List.of(testOrder);
        when(orderRepository.findAll()).thenReturn(orders);

        List<Order> result = orderService.getAllOrders();

        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
    }

    @Test
    @DisplayName("Verificar manejo de stock insuficiente")
    void testCreateOrder_InsufficientStock() {
        Product outOfStockProduct = new Product(
                UUID.randomUUID(),
                "Producto sin stock",
                "Descripción",
                new BigDecimal("50.00"),
                0,
                "Categoría",
                true
        );

        when(orderRepository.save(any(Order.class)))
                .thenThrow(new ProductOutOfStockException("Stock insuficiente"));

        assertThrows(ProductOutOfStockException.class, 
                () -> orderService.createOrder(testOrder));
    }
}

// === ARCHIVO: src/test/java/com/pragma/ordersystem/application/rest/OrderControllerTest.java ===
package com.pragma.ordersystem.application.rest;




import com.pragma.ordersystem.domain.model.OrderStatus;
import com.pragma.ordersystem.domain.model.OrderItem;
import com.pragma.ordersystem.domain.model.CustomerStatus;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pragma.ordersystem.domain.model.Order;
import com.pragma.ordersystem.domain.model.Customer;
import com.pragma.ordersystem.domain.model.Product;
import com.pragma.ordersystem.domain.service.OrderService;
import com.pragma.ordersystem.domain.exception.OrderNotFoundException;
import com.pragma.ordersystem.domain.exception.ProductOutOfStockException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(OrderController.class)
@DisplayName("Tests para OrderController")
class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private OrderService orderService;

    private Order testOrder;
    private Customer testCustomer;

    @BeforeEach
    void setUp() {
        testCustomer = new Customer(
                UUID.randomUUID(),
                "María García",
                "maria@email.com",
                "+0987654321",
                "Avenida Principal 456",
                Customer.CustomerStatus.ACTIVE
        );

        Product testProduct = new Product(
                UUID.randomUUID(),
                "Laptop",
                "Laptop profesional",
                new BigDecimal("1500.00"),
                5,
                "Tecnología",
                true
        );

        List<Order.OrderItem> items = new ArrayList<>();
        items.add(new Order.OrderItem(testProduct, 1));

        testOrder = new Order(
                UUID.randomUUID(),
                testCustomer,
                items,
                Order.OrderStatus.PENDING,
                LocalDateTime.now()
        );
    }

    @Test
    @DisplayName("Crear un pedido - HTTP 201 Created")
    void testCreateOrder_Returns201() throws Exception {
        when(orderService.createOrder(any(Order.class))).thenReturn(testOrder);

        mockMvc.perform(post("/api/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testOrder)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(testOrder.getId().toString()));

        verify(orderService, times(1)).createOrder(any(Order.class));
    }

    @Test
    @DisplayName("Obtener pedido por ID - HTTP 200 OK")
    void testGetOrderById_Returns200() throws Exception {
        when(orderService.getOrderById(testOrder.getId())).thenReturn(testOrder);

        mockMvc.perform(get("/api/orders/{id}", testOrder.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(testOrder.getId().toString()))
                .andExpect(jsonPath("$.status").value("PENDING"));
    }

    @Test
    @DisplayName("Obtener pedido inexistente - HTTP 404 Not Found")
    void testGetOrderById_Returns404() throws Exception {
        UUID nonExistentId = UUID.randomUUID();
        when(orderService.getOrderById(nonExistentId))
                .thenThrow(new OrderNotFoundException("Pedido no encontrado"));

        mockMvc.perform(get("/api/orders/{id}", nonExistentId))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Obtener todos los pedidos - HTTP 200 OK")
    void testGetAllOrders_Returns200() throws Exception {
        when(orderService.getAllOrders()).thenReturn(List.of(testOrder));

        mockMvc.perform(get("/api/orders"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1));
    }

    @Test
    @DisplayName("Cancelar un pedido - HTTP 204 No Content")
    void testCancelOrder_Returns204() throws Exception {
        doNothing().when(orderService).cancelOrder(testOrder.getId());

        mockMvc.perform(delete("/api/orders/{id}", testOrder.getId()))
                .andExpect(status().isNoContent());

        verify(orderService, times(1)).cancelOrder(testOrder.getId());
    }

    @Test
    @DisplayName("Actualizar estado de un pedido - HTTP 200 OK")
    void testUpdateOrderStatus_Returns200() throws Exception {
        when(orderService.updateOrderStatus(eq(testOrder.getId()), any()))
                .thenReturn(testOrder);

        mockMvc.perform(patch("/api/orders/{id}/status", testOrder.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("\"COMPLETED\""))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Manejo de error de stock - HTTP 400 Bad Request")
    void testCreateOrder_StockError_Returns400() throws Exception {
        when(orderService.createOrder(any(Order.class)))
                .thenThrow(new ProductOutOfStockException("Stock insuficiente"));

        mockMvc.perform(post("/api/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testOrder)))
                .andExpect(status().isBadRequest());
    }
}

// === ARCHIVO: src/test/java/com/pragma/ordersystem/infrastructure/adapter/OrderJpaRepositoryTest.java ===
package com.pragma.ordersystem.infrastructure.adapter;





import com.pragma.ordersystem.infrastructure.entity.OrderItemEntity;
import com.pragma.ordersystem.domain.model.OrderStatus;
import com.pragma.ordersystem.domain.model.OrderItem;
import com.pragma.ordersystem.domain.model.CustomerStatus;
import com.pragma.ordersystem.domain.model.Order;
import com.pragma.ordersystem.domain.model.Customer;
import com.pragma.ordersystem.domain.model.Product;
import com.pragma.ordersystem.infrastructure.entity.OrderEntity;
import com.pragma.ordersystem.infrastructure.entity.CustomerEntity;
import com.pragma.ordersystem.infrastructure.entity.ProductEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Tests para OrderJpaRepository")
class OrderJpaRepositoryTest {

    @Mock
    private OrderJpaRepository orderJpaRepository;

    private Order testOrder;
    private OrderEntity testOrderEntity;
    private Customer testCustomer;
    private Product testProduct;

    @BeforeEach
    void setUp() {
        testCustomer = new Customer(
                UUID.randomUUID(),
                "Carlos López",
                "carlos@email.com",
                "+1122334455",
                "Calle Verde 789",
                Customer.CustomerStatus.ACTIVE
        );

        testProduct = new Product(
                UUID.randomUUID(),
                "Mouse Gamer",
                "Mouse inalámbrico de alta precisión",
                new BigDecimal("45.99"),
                20,
                "Accesorios",
                true
        );

        List<Order.OrderItem> items = new ArrayList<>();
        items.add(new Order.OrderItem(testProduct, 3));

        testOrder = new Order(
                UUID.randomUUID(),
                testCustomer,
                items,
                Order.OrderStatus.PROCESSING,
                LocalDateTime.now()
        );

        CustomerEntity customerEntity = new CustomerEntity(
                testCustomer.getId(),
                testCustomer.getName(),
                testCustomer.getEmail(),
                testCustomer.getPhone(),
                testCustomer.getAddress(),
                testCustomer.getStatus().name()
        );

        ProductEntity productEntity = new ProductEntity(
                testProduct.getId(),
                testProduct.getName(),
                testProduct.getDescription(),
                testProduct.getPrice(),
                testProduct.getStock(),
                testProduct.getCategory(),
                testProduct.isActive()
        );

        List<OrderEntity.OrderItemEntity> itemEntities = new ArrayList<>();
        itemEntities.add(new OrderEntity.OrderItemEntity(productEntity, 3));

        testOrderEntity = new OrderEntity(
                testOrder.getId(),
                customerEntity,
                itemEntities,
                testOrder.getStatus().name(),
                testOrder.getCreatedAt(),
                testOrder.getTotalAmount()
        );
    }

    @Test
    @DisplayName("Guardar un pedido exitosamente")
    void testSave_Success() {
        when(orderJpaRepository.save(any(OrderEntity.class))).thenReturn(testOrderEntity);

        OrderEntity result = orderJpaRepository.save(testOrderEntity);

        assertNotNull(result);
        assertNotNull(result.getId());
        verify(orderJpaRepository, times(1)).save(any(OrderEntity.class));
    }

    @Test
    @DisplayName("Buscar pedido por ID")
    void testFindById_Success() {
        UUID orderId = testOrder.getId();
        when(orderJpaRepository.findById(orderId)).thenReturn(Optional.of(testOrderEntity));

        Optional<OrderEntity> result = orderJpaRepository.findById(orderId);

        assertTrue(result.isPresent());
        assertEquals(orderId, result.get().getId());
    }

    @Test
    @DisplayName("Buscar pedido inexistente retorna vacío")
    void testFindById_NotFound() {
        UUID nonExistentId = UUID.randomUUID();
        when(orderJpaRepository.findById(nonExistentId)).thenReturn(Optional.empty());

        Optional<OrderEntity> result = orderJpaRepository.findById(nonExistentId);

        assertFalse(result.isPresent());
    }

    @Test
    @DisplayName("Buscar todos los pedidos")
    void testFindAll_Success() {
        when(orderJpaRepository.findAll()).thenReturn(List.of(testOrderEntity));

        List<OrderEntity> result = orderJpaRepository.findAll();

        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
    }

    @Test
    @DisplayName("Buscar pedidos por estado")
    void testFindByStatus_Success() {
        when(orderJpaRepository.findByStatus(Order.OrderStatus.PROCESSING.name()))
                .thenReturn(List.of(testOrderEntity));

        List<OrderEntity> result = orderJpaRepository
                .findByStatus(Order.OrderStatus.PROCESSING.name());

        assertFalse(result.isEmpty());
        assertEquals(Order.OrderStatus.PROCESSING.name(), result.get(0).getStatus());
    }

    @Test
    @DisplayName("Buscar pedidos por cliente")
    void testFindByCustomerId_Success() {
        UUID customerId = testCustomer.getId();
        when(orderJpaRepository.findByCustomerId(customerId))
                .thenReturn(List.of(testOrderEntity));

        List<OrderEntity> result = orderJpaRepository.findByCustomerId(customerId);

        assertFalse(result.isEmpty());
    }

    @Test
    @DisplayName("Eliminar un pedido")
    void testDelete_Success() {
        UUID orderId = testOrder.getId();
        doNothing().when(orderJpaRepository).deleteById(orderId);

        orderJpaRepository.deleteById(orderId);

        verify(orderJpaRepository, times(1)).deleteById(orderId);
    }

    @Test
    @DisplayName("Contar pedidos por estado")
    void testCountByStatus_Success() {
        when(orderJpaRepository.countByStatus(Order.OrderStatus.PENDING.name()))
                .thenReturn(5L);

        Long count = orderJpaRepository.countByStatus(Order.OrderStatus.PENDING.name());

        assertEquals(5L, count);
    }
}

// === ARCHIVO: src/test/java/com/pragma/ordersystem/runners/RunCucumberTest.java ===
package com.pragma.ordersystem.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/resources/features",
    glue = {"com.pragma.ordersystem.steps"},
    plugin = {"pretty", "html:target/cucumber-reports.html"},
    tags = "@ordenes",
    dryRun = false
)
public class RunCucumberTest {
}

// === ARCHIVO: src/test/java/com/pragma/ordersystem/steps/XSteps.java ===
package com.pragma.ordersystem.steps;


import com.pragma.ordersystem.domain.model.OrderItem;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.pragma.ordersystem.domain.model.Order;
import com.pragma.ordersystem.domain.model.Order.OrderStatus;
import com.pragma.ordersystem.domain.model.Customer;
import com.pragma.ordersystem.domain.model.Customer.CustomerStatus;
import com.pragma.ordersystem.domain.model.Product;
import com.pragma.ordersystem.domain.service.OrderService;
import com.pragma.ordersystem.domain.exception.OrderNotFoundException;
import com.pragma.ordersystem.domain.exception.ProductOutOfStockException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@SpringBootTest
public class XSteps {

    @Autowired
    private OrderService orderService;

    private Order ordenCreada;
    private Customer clientePrueba;
    private Product productoPrueba;
    private List<Order.OrderItem> itemsPedido;
    private Exception exceptionCapturada;

    @Given("un cliente con datos válidos")
    public void un_cliente_con_datos_validos() {
        clientePrueba = new Customer(
            UUID.randomUUID(),
            "Juan Pérez",
            "juan.perez@email.com",
            "+573001234567",
            "Carrera 10 #20-30, Bogotá",
            CustomerStatus.ACTIVE
        );
    }

    @Given("un producto disponible en inventario")
    public void un_producto_disponible_en_inventario() {
        productoPrueba = new Product(
            UUID.randomUUID(),
            "Laptop ThinkPad",
            "Computador portátil de alta gama",
            new BigDecimal("2500000"),
            50,
            "Electrónica",
            true
        );
    }

    @When("el cliente crea un pedido con el producto")
    public void el_cliente_crea_un_pedido_con_el_producto() {
        itemsPedido = new ArrayList<>();
        Order.OrderItem item = new Order.OrderItem(
            productoPrueba.getId(),
            productoPrueba.getName(),
            productoPrueba.getPrice(),
            2
        );
        itemsPedido.add(item);

        try {
            ordenCreada = orderService.createOrder(clientePrueba, itemsPedido);
        } catch (Exception e) {
            exceptionCapturada = e;
        }
    }

    @Then("el pedido debe ser creado exitosamente")
    public void el_pedido_debe_ser_creado_exitosamente() {
        Assertions.assertNotNull(ordenCreada);
        Assertions.assertNotNull(ordenCreada.getId());
        Assertions.assertEquals(OrderStatus.PENDING, ordenCreada.getStatus());
        Assertions.assertEquals(clientePrueba.getId(), ordenCreada.getCustomer().getId());
    }

    @And("el monto total debe ser correcto")
    public void el_monto_total_debe_ser_correcto() {
        BigDecimal expectedTotal = productoPrueba.getPrice().multiply(new BigDecimal(2));
        Assertions.assertEquals(0, expectedTotal.compareTo(ordenCreada.getTotalAmount()));
    }

    @When("el cliente intenta crear un pedido con cantidad mayor al stock")
    public void el_cliente_intenta_crear_un_pedido_con_cantidad_mayor_al_stock() {
        itemsPedido = new ArrayList<>();
        Order.OrderItem item = new Order.OrderItem(
            productoPrueba.getId(),
            productoPrueba.getName(),
            productoPrueba.getPrice(),
            100
        );
        itemsPedido.add(item);

        try {
            ordenCreada = orderService.createOrder(clientePrueba, itemsPedido);
        } catch (ProductOutOfStockException e) {
            exceptionCapturada = e;
        } catch (Exception e) {
            exceptionCapturada = e;
        }
    }

    @Then("el sistema debe lanzar una excepción de stock insuficiente")
    public void el_sistema_debe_lanzar_una_excepcion_de_stock_insuficiente() {
        Assertions.assertNotNull(exceptionCapturada);
        Assertions.assertTrue(exceptionCapturada instanceof ProductOutOfStockException);
    }

    @When("el cliente modifica el estado del pedido a CONFIRMADO")
    public void el_cliente_modifica_el_estado_del_pedido_a_confirmado() {
        try {
            orderService.updateOrderStatus(ordenCreada.getId(), OrderStatus.CONFIRMED);
            ordenCreada = orderService.getOrderById(ordenCreada.getId());
        } catch (Exception e) {
            exceptionCapturada = e;
        }
    }

    @Then("el pedido debe mostrar estado CONFIRMADO")
    public void el_pedido_debe_mostrar_estado_confirmado() {
        Assertions.assertEquals(OrderStatus.CONFIRMED, ordenCreada.getStatus());
    }

    @When("el cliente cancela el pedido")
    public void el_cliente_cancela_el_pedido() {
        try {
            orderService.cancelOrder(ordenCreada.getId());
            ordenCreada = orderService.getOrderById(ordenCreada.getId());
        } catch (Exception e) {
            exceptionCapturada = e;
        }
    }

    @Then("el pedido debe mostrar estado CANCELLED")
    public void el_pedido_debe_mostrar_estado_cancelled() {
        Assertions.assertEquals(OrderStatus.CANCELLED, ordenCreada.getStatus());
    }

    @When("se busca un pedido con ID inexistente")
    public void se_busca_un_pedido_con_id_inexistente() {
        UUID idInexistente = UUID.randomUUID();
        try {
            orderService.getOrderById(idInexistente);
        } catch (OrderNotFoundException e) {
            exceptionCapturada = e;
        } catch (Exception e) {
            exceptionCapturada = e;
        }
    }

    @Then("el sistema debe lanzar excepción de pedido no encontrado")
    public void el_sistema_debe_lanzar_excepcion_de_pedido_no_encontrado() {
        Assertions.assertNotNull(exceptionCapturada);
        Assertions.assertTrue(exceptionCapturada instanceof OrderNotFoundException);
    }
}

// === ARCHIVO: README.md ===
# OrderSystem - Sistema de Gestión de Pedidos

## Descripción

Sistema de gestión de pedidos para comercio electrónico desarrollado con Spring Boot 3.4 y Java 21. Implementa arquitectura hexagonal (clean architecture) con separación estricta de capas: dominio, aplicación e infraestructura.

## Requisitos Previos

- Java Development Kit (JDK) 21
- Maven 3.8+
- IDE compatible con Java (IntelliJ IDEA, Eclipse, VS Code)

## Configuración del Proyecto

### Estructura de Proyecto

```
src/
├── main/
│   ├── java/com/pragma/ordersystem/
│   │   ├── domain/           # Capa de dominio (modelos, puertos, excepciones)
│   │   ├── application/      # Capa de aplicación (casos de uso, DTOs)
│   │   ├── infrastructure/   # Capa de infraestructura (adaptadores, entidades)
│   │   └── config/           # Configuración de Spring
│   └── resources/
│       └── application.yml   # Configuración de la aplicación
└── test/
    ├── java/                 # Pruebas unitarias y de integración
    └── resources/
        └── features/         # Escenarios Gherkin para BDD
```

### Compilación

```bash
mvn clean compile
```

### Ejecución de Pruebas

```bash
# Todas las pruebas
mvn test

# Solo pruebas unitarias
mvn test -Dtest=*Test

# Compilación de pruebas
mvn test-compile
```

### Ejecución de la Aplicación

```bash
mvn spring-boot:run
```

La aplicación arrancará en `http://localhost:8080`

## API REST - Endpoints

### Crear Pedido
```http
POST /api/orders
Content-Type: application/json

{
  "customerId": "uuid-del-cliente",
  "items": [
    {
      "productId": "uuid-del-producto",
      "quantity": 2
    }
  ]
}
```

### Obtener Pedido por ID
```http
GET /api/orders/{id}
```

### Listar Pedidos
```http
GET /api/orders?page=0&size=10
```

### Actualizar Estado del Pedido
```http
PATCH /api/orders/{id}/status
Content-Type: application/json

{
  "status": "CONFIRMED"
}
```

### Cancelar Pedido
```http
DELETE /api/orders/{id}
```

## Tecnologías Utilizadas

- **Framework:** Spring Boot 3.4
- **Lenguaje:** Java 21
- **Persistencia:** JPA con Hibernate
- **Base de datos:** H2 (en memoria para desarrollo)
- **Build:** Maven
- **Testing:** JUnit 5, Mockito
- **BDD:** Cucumber con Gherkin
- **Lombok:** Reducción de boilerplate

## Excepciones del Sistema

- `OrderNotFoundException` - Pedido no encontrado
- `ProductOutOfStockException` - Stock insuficiente
- `PaymentFailedException` - Error en pago

## Modelo de Datos

### Order (Pedido)
- id: UUID
- customer: Customer
- items: List<OrderItem>
- status: OrderStatus (PENDING, CONFIRMED, CANCELLED, COMPLETED)
- createdAt: LocalDateTime
- updatedAt: LocalDateTime
- totalAmount: BigDecimal

### Customer (Cliente)
- id: UUID
- name: String
- email: String
- phone: String
- address: String
- status: CustomerStatus (ACTIVE, INACTIVE)

### Product (Producto)
- id: UUID
- name: String
- description: String
- price: BigDecimal
- stock: Integer
- category: String
- active: Boolean

## Конфигурация

La configuración se encuentra en `src/main/resources/application.yml`:

```yaml
spring:
  datasource:
    url: jdbc:h2:mem:ordersystem
    driver-class-name: org.h2.Driver
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
```

## Patrón Arquitectónico

El proyecto sigue el patrón hexagonal (clean architecture):

- **Capa de Dominio:** Modelos de negocio, puertos (interfaces), excepciones del dominio.
- **Capa de Aplicación:** Casos de uso, DTOs, controladores REST.
- **Capa de Infraestructura:** Implementaciones de repositorios, entidades JPA, adaptadores externos.

## Licencia

Proyecto desarrollado para Pragma.
```
