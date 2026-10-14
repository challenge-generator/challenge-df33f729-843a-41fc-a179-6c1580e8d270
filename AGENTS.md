# AGENTS.md

Instrucciones para el agente de IA que abra este repositorio (Claude Code, Cursor, Codex, Copilot, Gemini). Se cargan solas: no hay que pegar nada en ningun chat.

## Que es este repositorio

Es el codigo base de un reto de aprendizaje de Pragma: **Desarrollo de un sistema de gestión de pedidos avanzado utilizando POO**.

| | |
|---|---|
| Tema | implementación de programación orientada a objetos (POO) - avanzado |
| Nivel | senior-l2 |
| Chapter | Calidad de Software |
| Especialidad | Automatizador |
| Stack | Java / Spring Boot 3.4 |
| Patron arquitectonico | hexagonal/clean |
| Tiempo estimado | 10 horas |

## Receta del stack

Esqueleto obligatorio:

- `pom.xml en la raiz con el runner y el plugin de reportes`
- `src/test/resources/features con los .feature en Gherkin`
- `src/test/java/.../runners con el runner`
- `src/test/java/.../pages o /tasks con Page Objects o Screenplay`
- `src/test/java/.../steps con los step definitions`
- `serenity.conf o config del entorno`

Trampas conocidas:

- Sin parent POM que gestione versiones, TODA dependencia lleva su `<version>` completa de tres segmentos.
- El groupId de Serenity es `net.serenity-bdd`, NO `org.serenity-bdd`. Con el groupId equivocado el artefacto no existe y el build muere resolviendo dependencias.
- Coordenadas exactas de lo mas usado: Selenium `org.seleniumhq.selenium:selenium-java`, Rest Assured `io.rest-assured:rest-assured`, Karate `com.intuit.karate:karate-junit5`, Cucumber `io.cucumber:cucumber-java`.
- JUnit 5 se declara con `junit-jupiter` (agregador) y necesita `maven-surefire-plugin` reciente para ejecutarse.
- Serenity y Cucumber tienen que ser de lineas compatibles entre si; mezclarlas rompe el runner.

Dependencias:

- org.springframework.boot:spring-boot-starter-web 3.4.0
- org.springframework.boot:spring-boot-starter-data-jpa 3.4.0
- com.h2database:h2 2.3.232
- org.projectlombok:lombok 1.18.34
- org.springframework.boot:spring-boot-starter-test 3.4.0
- org.mockito:mockito-core 5.14.0
- org.junit.jupiter:junit-jupiter 5.11.0

## Tu tarea

Dejar este proyecto en estado **verificable**: que el comando de verificacion corra sin errores. Escribi los archivos en disco, en este repositorio. No generes ZIPs ni archivos adjuntos.

En orden:

1. Corre `mvn clean test-compile` y mira que falla.
2. Completa lo que falte de la lista de abajo: manifiesto de dependencias, punto de entrada, capa de interfaz y las capas del patron declarado.
3. Arregla SOLO los errores que impiden compilar o arrancar.
4. Volve a correr `mvn clean test-compile` hasta que pase.
5. Pará ahí.

## Regla dura: las fases son trabajo del humano

**PROHIBIDO implementar los entregables de las fases.** El valor del reto esta en que la persona los resuelva. Tu trabajo es que tenga un proyecto que arranca; el hueco pedagogico se queda como esta.

No resuelvas nada de esto:

- **Fase 1 — Modelado de clases y atributos**: Diagrama de clases y descripción de atributos.
- **Fase 2 — Implementación de métodos y manejo de excepciones**: Código fuente con métodos implementados y manejo de excepciones.
- **Fase 3 — Integración con base de datos**: Código fuente con integración de base de datos.
- **Fase 4 — Pruebas unitarias y optimización**: Código fuente con pruebas unitarias y optimizaciones aplicadas.

Distincion operativa:

- **Arreglar** (si): import faltante, tipo que no existe, dependencia sin declarar, error de sintaxis, archivo referenciado que no existe.
- **No tocar** (no): logica de negocio incompleta, validaciones ausentes, secretos hardcodeados, APIs deprecadas que funcionan, concurrencia insegura, patrones mejorables. Eso es lo que la persona tiene que encontrar.

## Superficie de practica (NO completes)

Estos archivos SON el ejercicio de la persona. No los implementes; deja stubs. No toques la logica que el reto pide completar.

- [ ] `RunCucumberTest.java` — El topic pide TDD/pruebas: este archivo es el ejercicio.
- [ ] `src/test/java/com/pragma/ordersystem/domain/service/OrderServiceTest.java` — El topic pide TDD/pruebas: este archivo es el ejercicio.
- [ ] `src/test/java/com/pragma/ordersystem/application/rest/OrderControllerTest.java` — El topic pide TDD/pruebas: este archivo es el ejercicio.
- [ ] `src/test/java/com/pragma/ordersystem/infrastructure/adapter/OrderJpaRepositoryTest.java` — El topic pide TDD/pruebas: este archivo es el ejercicio.

## Lo que falta y tenes que completar

### 1. Archivos que la arquitectura declara (2 de 20)

La propuesta arquitectonica del reto los lista y no llegaron al repo. Crealos con implementacion real, respetando la capa en la que viven:

- [ ] `RunCucumberTest.java`
- [ ] `XSteps.java`

### 2. Referencias colgando (41)

Salieron de un analisis estatico del codigo que SI esta en el repo. Cada una rompe la compilacion:

- [ ] `src/main/java/com/pragma/ordersystem/config/DatabaseConfig.java` — `com.zaxxer.hikari`
      El import com.zaxxer.hikari.HikariConfig pertenece a com.zaxxer.hikari, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/test/java/com/pragma/ordersystem/application/rest/OrderControllerTest.java` — `com.fasterxml.jackson`
      El import com.fasterxml.jackson.databind.ObjectMapper pertenece a com.fasterxml.jackson, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/test/java/com/pragma/ordersystem/steps/XSteps.java` — `io.cucumber.java`
      El import io.cucumber.java.en.Given pertenece a io.cucumber.java, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/pragma/ordersystem/domain/exception/PaymentFailedException.java` — `PaymentMethod.name`
      Se invoca `name` sobre `PaymentMethod`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/ordersystem/domain/exception/PaymentFailedException.java` — `PaymentFailureReason.name`
      Se invoca `name` sobre `PaymentFailureReason`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/ordersystem/domain/service/OrderService.java` — `OrderStatus.name`
      Se invoca `name` sobre `OrderStatus`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/ordersystem/infrastructure/adapter/OrderJpaRepository.java` — `OrderEntity.setCustomer`
      Se invoca `setCustomer` sobre `OrderEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/ordersystem/infrastructure/adapter/OrderJpaRepository.java` — `OrderItemEntity.setQuantity`
      Se invoca `setQuantity` sobre `OrderItemEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/ordersystem/infrastructure/adapter/OrderJpaRepository.java` — `OrderItem.getQuantity`
      Se invoca `getQuantity` sobre `OrderItem`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/ordersystem/infrastructure/adapter/OrderJpaRepository.java` — `OrderItemEntity.setUnitPrice`
      Se invoca `setUnitPrice` sobre `OrderItemEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/ordersystem/infrastructure/adapter/OrderJpaRepository.java` — `OrderItem.getUnitPrice`
      Se invoca `getUnitPrice` sobre `OrderItem`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/ordersystem/infrastructure/adapter/OrderJpaRepository.java` — `OrderItemEntity.setSubtotal`
      Se invoca `setSubtotal` sobre `OrderItemEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/ordersystem/infrastructure/adapter/OrderJpaRepository.java` — `OrderItem.getSubtotal`
      Se invoca `getSubtotal` sobre `OrderItem`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/ordersystem/infrastructure/adapter/OrderJpaRepository.java` — `OrderItem.getProduct`
      Se invoca `getProduct` sobre `OrderItem`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/ordersystem/infrastructure/adapter/OrderJpaRepository.java` — `OrderItemEntity.setProduct`
      Se invoca `setProduct` sobre `OrderItemEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/ordersystem/infrastructure/adapter/OrderJpaRepository.java` — `OrderEntity.getCustomer`
      Se invoca `getCustomer` sobre `OrderEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/ordersystem/infrastructure/adapter/OrderJpaRepository.java` — `OrderItemEntity.getProduct`
      Se invoca `getProduct` sobre `OrderItemEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/ordersystem/infrastructure/adapter/OrderJpaRepository.java` — `OrderItemEntity.getQuantity`
      Se invoca `getQuantity` sobre `OrderItemEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/ordersystem/infrastructure/adapter/OrderJpaRepository.java` — `OrderItemEntity.getUnitPrice`
      Se invoca `getUnitPrice` sobre `OrderItemEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/ordersystem/application/rest/OrderController.java` — `OrderService.getOrdersByCustomerId`
      Se invoca `getOrdersByCustomerId` sobre `OrderService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/ordersystem/application/rest/OrderController.java` — `OrderService.calculateOrderTotal`
      Se invoca `calculateOrderTotal` sobre `OrderService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/ordersystem/application/rest/OrderController.java` — `OrderService.deleteOrder`
      Se invoca `deleteOrder` sobre `OrderService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/ordersystem/infrastructure/entity/OrderEntity.java` — `OrderEntity.getCustomer`
      Se invoca `getCustomer` sobre `OrderEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/ordersystem/infrastructure/entity/OrderEntity.java` — `OrderItemEntity.setOrder`
      Se invoca `setOrder` sobre `OrderItemEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/ordersystem/infrastructure/entity/OrderEntity.java` — `OrderItem.getProduct`
      Se invoca `getProduct` sobre `OrderItem`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/ordersystem/infrastructure/entity/OrderEntity.java` — `OrderItemEntity.setProductId`
      Se invoca `setProductId` sobre `OrderItemEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/ordersystem/infrastructure/entity/OrderEntity.java` — `OrderItemEntity.setProductName`
      Se invoca `setProductName` sobre `OrderItemEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/ordersystem/infrastructure/entity/OrderEntity.java` — `OrderItemEntity.setQuantity`
      Se invoca `setQuantity` sobre `OrderItemEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/ordersystem/infrastructure/entity/OrderEntity.java` — `OrderItem.getQuantity`
      Se invoca `getQuantity` sobre `OrderItem`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/ordersystem/infrastructure/entity/OrderEntity.java` — `OrderItemEntity.setUnitPrice`
      Se invoca `setUnitPrice` sobre `OrderItemEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/ordersystem/infrastructure/entity/OrderEntity.java` — `OrderItem.getUnitPrice`
      Se invoca `getUnitPrice` sobre `OrderItem`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/ordersystem/infrastructure/entity/OrderEntity.java` — `OrderItemEntity.setSubtotal`
      Se invoca `setSubtotal` sobre `OrderItemEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/ordersystem/infrastructure/entity/OrderEntity.java` — `OrderItem.getSubtotal`
      Se invoca `getSubtotal` sobre `OrderItem`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/pragma/ordersystem/domain/service/OrderServiceTest.java` — `Order.isEmpty`
      Se invoca `isEmpty` sobre `Order`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/pragma/ordersystem/domain/service/OrderServiceTest.java` — `Order.size`
      Se invoca `size` sobre `Order`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/pragma/ordersystem/infrastructure/adapter/OrderJpaRepositoryTest.java` — `OrderEntity.isPresent`
      Se invoca `isPresent` sobre `OrderEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/pragma/ordersystem/infrastructure/adapter/OrderJpaRepositoryTest.java` — `OrderEntity.get`
      Se invoca `get` sobre `OrderEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/pragma/ordersystem/infrastructure/adapter/OrderJpaRepositoryTest.java` — `OrderEntity.isEmpty`
      Se invoca `isEmpty` sobre `OrderEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/pragma/ordersystem/infrastructure/adapter/OrderJpaRepositoryTest.java` — `OrderEntity.size`
      Se invoca `size` sobre `OrderEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/pragma/ordersystem/infrastructure/adapter/OrderJpaRepositoryTest.java` — `OrderJpaRepository.deleteById`
      Se invoca `deleteById` sobre `OrderJpaRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/pragma/ordersystem/infrastructure/adapter/OrderJpaRepositoryTest.java` — `OrderJpaRepository.countByStatus`
      Se invoca `countByStatus` sobre `OrderJpaRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.

### Presentes (23)

- `pom.xml`
- `src/main/java/com/pragma/ordersystem/OrdersystemApplication.java`
- `src/main/resources/application.yml`
- `src/main/java/com/pragma/ordersystem/domain/model/Order.java`
- `src/main/java/com/pragma/ordersystem/domain/model/Product.java`
- `src/main/java/com/pragma/ordersystem/domain/model/Customer.java`
- `src/main/java/com/pragma/ordersystem/domain/exception/OrderNotFoundException.java`
- `src/main/java/com/pragma/ordersystem/domain/exception/ProductOutOfStockException.java`
- `src/main/java/com/pragma/ordersystem/domain/exception/PaymentFailedException.java`
- `src/main/java/com/pragma/ordersystem/domain/port/OrderRepository.java`
- `src/main/java/com/pragma/ordersystem/domain/service/OrderService.java`
- `src/main/java/com/pragma/ordersystem/infrastructure/adapter/OrderJpaRepository.java`
- `src/main/java/com/pragma/ordersystem/config/DatabaseConfig.java`
- `src/main/java/com/pragma/ordersystem/application/rest/OrderController.java`
- `src/main/java/com/pragma/ordersystem/infrastructure/entity/OrderEntity.java`
- `src/main/java/com/pragma/ordersystem/infrastructure/entity/ProductEntity.java`
- `src/main/java/com/pragma/ordersystem/infrastructure/entity/CustomerEntity.java`
- `src/test/java/com/pragma/ordersystem/domain/service/OrderServiceTest.java`
- `src/test/java/com/pragma/ordersystem/application/rest/OrderControllerTest.java`
- `src/test/java/com/pragma/ordersystem/infrastructure/adapter/OrderJpaRepositoryTest.java`
- `src/test/java/com/pragma/ordersystem/runners/RunCucumberTest.java`
- `src/test/java/com/pragma/ordersystem/steps/XSteps.java`
- `README.md`

### Capas del patron declarado

Cada una tiene que existir como directorio real con al menos un archivo. Codigo plano en la raiz no satisface el patron.

- `src/main/java/com/pragma/ordersystem/application`
- `src/main/java/com/pragma/ordersystem/domain`
- `src/main/java/com/pragma/ordersystem/infrastructure`
- `src/main/java/com/pragma/ordersystem/config`
- `src/test/java/com/pragma/ordersystem`

## Verificacion

```bash
mvn clean test-compile
```

El comando tiene que pasar SIN implementar los archivos de la superficie de practica: solo andamiaje.

Ese comando pasando es la definicion de "terminado" para vos.

## Convenciones que tenes que respetar

- Un solo ecosistema: no declares librerias de otro lenguaje ni mezcles gestores de paquetes.
- Toda libreria que uses tiene que estar declarada en el manifiesto de dependencias.
- Todo import declarado tiene que usarse; todo tipo usado tiene que existir o venir de una dependencia declarada.
- El patron es **hexagonal/clean**: los contratos (interfaces, puertos) los define la capa interna y los implementa la externa, nunca al revés.
- Los archivos que crees llevan implementacion real, no stubs: sin `TODO`, sin cuerpos vacios, sin `// getters y setters`.

## Contexto del candidato

Sirve para calibrar el nivel del codigo, no para resolver las fases.

- Perfil: Chapter Calidad de Software, Especialidad Automatizador, Tecnología POO Avanzada, Senior
- Brecha que el reto ataca: Demuestra un dominio avanzado en al menos uno de los siguientes lenguajes de Programación Orientada a Objetos: Java, Python, dart, JavaScript, TypeScript, en temas clave como: conexiones a base de datos, manejo de excepciones, interfaces, clases abstractas, colecciones, uso de bibliotecas/frameworks, patrones de diseño orientados a objetos, herramientas de pruebas unitarias
- Mision: Candidato con experiencia en automatización de calidad de software a nivel Senior

---

*Generado por Challenge Generator — Pragma. `README.md` tiene el enunciado completo del reto para la persona. `PROMPT_MEJORA.md` es la variante para pegar en un chat, si se prefiere ese flujo.*
