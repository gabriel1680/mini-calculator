# Mini Calculator

This project is a basic calculator with a GUI interface to exercise TDD, Object Oriented Programming and learning more
GUI programming with Java and AWT library.

## Features

* Infix basic mathematical expression parsing and evaluation (e.g.: `((5 - 2) / 3) * 1.5`)
* Supported operators: `+`, `-`, `*`, `/`, `%`, `√`
* Supported numbers set: `Real Numbers`
* Supported modes: `Basic` and `Scientific`

## Architectural Patterns

* [Modular Monolith](https://medium.com/design-microservices-architecture-with-patterns/microservices-killer-modular-monolithic-architecture-ac83814f6862)
  for the project overall structure
* [Model-View-Presenter](https://www.geeksforgeeks.org/android/mvp-model-view-presenter-architecture-pattern-in-android-with-example/)
  for the UI

### Diagram

```mermaid
flowchart TD
    View["AWTCalculatorView - View"] --> Presenter["Presenter / ViewController"]
    Presenter --> Model["Model - Calculator + InputState"]
    Model --> Presenter
    Presenter --> View
    View --> User["User sees display text"]
    Symbols["Symbol enum (Digit, Operator, Function)"] --> Model
    Symbols --> Presenter

    classDef view fill:#bbf,stroke:#333,stroke-width:1px;
    classDef presenter fill:#bfb,stroke:#333,stroke-width:1px;
    classDef model fill:#ffb,stroke:#333,stroke-width:1px;
    classDef user fill:#fbb,stroke:#333,stroke-width:1px;
    classDef symbols fill:#ffd,stroke:#333,stroke-width:1px;

    class View view;
    class Presenter presenter;
    class Model model;
    class User user;
    class Symbols symbols;
```

### WIP

* Calculator modules:
    * Scientific - trigonometry

## Images

![image](./assets/screen.png)

### Modes

![basic](./assets/modes.png)

### Basic

![basic](./assets/basic.png)

### Scientific

![basic](./assets/scientific.png)

## Integration Tests Using DSL

```mermaid
flowchart TD
    A["Integration Test DSL: calculator().type('2','+','2')"] --> B["AWTCalculatorView - View"]
    B --> C["Presenter - ViewController / CalculatorPresenter"]
    C --> D["Model - Calculator + InputState"]
    D --> C
    C --> B
    B --> E["User sees display text: 4.0"]

    classDef testDSL fill:#f9f,stroke:#333,stroke-width:1px;
    classDef view fill:#bbf,stroke:#333,stroke-width:1px;
    classDef presenter fill:#bfb,stroke:#333,stroke-width:1px;
    classDef model fill:#ffb,stroke:#333,stroke-width:1px;
    classDef user fill:#fbb,stroke:#333,stroke-width:1px;

    class A testDSL;
    class B view;
    class C presenter;
    class D model;
    class E user;
```