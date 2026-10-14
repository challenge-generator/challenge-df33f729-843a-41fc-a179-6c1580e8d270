# Desarrollo de un sistema de gestión de pedidos avanzado utilizando POO

El objetivo es desarrollar un sistema de gestión de pedidos en un contexto de comercio electrónico. El sistema debe manejar la creación, modificación y cancelación de pedidos, así como la interacción con una base de datos. Los pedidos tienen atributos como ID, cliente, productos, estado y fecha de creación. El sistema debe asegurar la consistencia de los datos y manejar adecuadamente las excepciones. Los actores involucrados son el cliente, el sistema de gestión de inventario y el sistema de pago. El sistema debe ser capaz de procesar al menos 10 000 pedidos por hora con una latencia máxima de 200ms por operación.

## Informacion General

| Campo | Valor |
|-------|-------|
| **Tema** | implementación de programación orientada a objetos (POO) - avanzado |
| **Nivel** | senior-l2 |
| **Tipo** | practical |
| **Tiempo estimado** | 10 horas |

## Fases del Reto

### Fase 0: Configuración del Proyecto

**Objetivo:** Obtener el proyecto base funcional enviando el Código Base a un asistente de IA, que lo analizará, corregirá errores y generará un ZIP listo para usar.

**Tiempo estimado:** 15-30 minutos

**Instrucciones:**

- Asegúrate de tener instalado para ejecutar el proyecto: JDK 17+, Maven 3.9+, IDE con soporte Java.
- Copia todo el contenido del campo **Código Base** de este reto — incluyendo el texto de instrucciones que aparece al inicio.
- Abre un asistente de IA (Claude en claude.ai, ChatGPT o Gemini — se recomienda Claude), pega el contenido copiado en el chat y envíalo.
- El asistente analizará los archivos, corregirá errores y generará un archivo ZIP descargable. Descárgalo y extráelo en la carpeta donde quieras trabajar.
- Ejecuta `mvn compile` en la raíz. Si no hay errores, estás listo.

**Entregable:** El proyecto compila/arranca sin errores.

<details>
<summary>Pistas de conocimiento</summary>

- Copia el Código Base completo incluyendo el texto de instrucciones al inicio — esas instrucciones le indican al asistente exactamente qué hacer con los archivos.
- Si el asistente no genera el ZIP automáticamente al terminar el análisis, escríbele: "genera el ZIP ahora".
- Si el proyecto tiene errores al arrancar, comparte el mensaje de error con el mismo asistente para que lo corrija.

</details>

### Fase 1: Modelado de clases y atributos

**Objetivo:** Definir las clases y atributos necesarios para representar los pedidos y sus estados.

**Tiempo estimado:** 2 horas

**Instrucciones:**

- Identificar las clases necesarias para representar los pedidos y sus estados.
- Definir los atributos de cada clase, asegurando que capturen toda la información relevante.
- Establecer relaciones entre las clases utilizando herencia y composición.

**Entregable:** Diagrama de clases y descripción de atributos.

<details>
<summary>Pistas de conocimiento</summary>

- Considera la necesidad de clases abstractas y interfaces para asegurar la flexibilidad y reutilización del código.
- Piensa en cómo representar los estados de los pedidos de manera eficiente.

</details>

### Fase 2: Implementación de métodos y manejo de excepciones

**Objetivo:** Implementar los métodos necesarios para la creación, modificación y cancelación de pedidos, así como manejar las excepciones que puedan surgir.

**Tiempo estimado:** 3 horas

**Instrucciones:**

- Implementar los métodos para crear, modificar y cancelar pedidos.
- Asegurar que los métodos manejen adecuadamente las excepciones que puedan surgir durante la ejecución.
- Probar los métodos para verificar su correcto funcionamiento.

**Entregable:** Código fuente con métodos implementados y manejo de excepciones.

<details>
<summary>Pistas de conocimiento</summary>

- Considera el uso de patrones de diseño para mejorar la estructura y mantenibilidad del código.
- Piensa en cómo asegurar la consistencia de los datos durante las operaciones.

</details>

### Fase 3: Integración con base de datos

**Objetivo:** Integrar el sistema con una base de datos para el almacenamiento y recuperación de los pedidos.

**Tiempo estimado:** 3 horas

**Instrucciones:**

- Establecer una conexión con la base de datos.
- Implementar las operaciones CRUD para los pedidos.
- Asegurar la consistencia de los datos durante las operaciones de base de datos.

**Entregable:** Código fuente con integración de base de datos.

<details>
<summary>Pistas de conocimiento</summary>

- Considera el uso de ORM para simplificar la interacción con la base de datos.
- Piensa en cómo manejar las transacciones para asegurar la consistencia de los datos.

</details>

### Fase 4: Pruebas unitarias y optimización

**Objetivo:** Escribir pruebas unitarias para los métodos implementados y optimizar el rendimiento del sistema.

**Tiempo estimado:** 2 horas

**Instrucciones:**

- Escribir pruebas unitarias para verificar el correcto funcionamiento de los métodos.
- Identificar y corregir posibles cuellos de botella en el rendimiento.
- Asegurar que el sistema cumpla con los requisitos de rendimiento establecidos.

**Entregable:** Código fuente con pruebas unitarias y optimizaciones aplicadas.

<details>
<summary>Pistas de conocimiento</summary>

- Utiliza herramientas de pruebas unitarias para automatizar el proceso de verificación.
- Considera el uso de técnicas de optimización como la memoización y la paralelización.

</details>

## Dimensiones Evaluadas

- **queEs**: ¿Qué son las clases y atributos en el contexto de la POO?
- **paraQueSirve**: ¿Para qué sirven los métodos y el manejo de excepciones en un sistema de gestión de pedidos?
- **comoSeUsa**: ¿Cómo se usa la integración con una base de datos para almacenar y recuperar pedidos?
- **erroresComunes**: ¿Cuáles son los errores comunes que pueden surgir durante la implementación de un sistema de gestión de pedidos utilizando POO?
- **queDecisionesImplica**: ¿Qué decisiones implica la optimización del rendimiento de un sistema de gestión de pedidos?

## Criterios de Evaluacion

- Modelar correctamente las clases y atributos para representar los pedidos y sus estados.
- Implementar los métodos necesarios para la creación, modificación y cancelación de pedidos, así como manejar adecuadamente las excepciones.
- Integrar el sistema con una base de datos y asegurar la consistencia de los datos durante las operaciones.
- Escribir pruebas unitarias para verificar el correcto funcionamiento de los métodos y optimizar el rendimiento del sistema.

## Como trabajar con un asistente de IA

Hay dos caminos, elegi uno:

- **AGENTS.md** (recomendado) — instrucciones nativas del repo. Abri esta carpeta con tu agente local (Claude Code, Cursor, Codex, Copilot, Gemini) y las carga solo. Sabe que archivos faltan y con que comando se verifica, y completa el scaffold escribiendo en disco.
- **PROMPT_MEJORA.md** — para copiar y pegar en un chat (claude.ai, ChatGPT). Devuelve un ZIP con el proyecto. Sirve si no tenes un agente en el IDE.

Ninguno de los dos resuelve las fases del reto: eso es tu trabajo.

## Verificacion

El proyecto esta listo para trabajar cuando este comando corre sin errores:

```bash
mvn clean test-compile
```

---

*Reto generado automaticamente por Challenge Generator - Pragma*
