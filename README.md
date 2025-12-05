# MotoParts – system do zarządzania oponami i częściami dla warsztatów

Projekt zaliczeniowy z przedmiotów **Java** oraz **GIT**.

Aplikacja udostępnia REST API do zarządzania:
- produktami (opony, felgi, dętki, akcesoria),
- warsztatami,
- użytkownikami systemu (admin + warsztaty),
- stanem magazynowym i zamówieniami (rozszerzenie).

Model biznesowy inspirowany serwisami typu **Oponeo / InterCars**:
- **ADMIN** (właściciel hurtowni) zarządza asortymentem i warsztatami,
- **WORKSHOP** (warsztat) przegląda ofertę i może składać zamówienia.

---

## 1. Architektura

- **Backend**: Spring Boot
- **Baza danych**: H2
- **Dokumentacja API**: springdoc-openapi + Swagger UI
- **Bezpieczeństwo**: Spring Security
- **Serializacja danych**: JSON + XML (export/import)
- **Testy**:
    - testy jednostkowe (JUnit 5, Mockito),
    - testy integracyjne (Spring Boot Test + MockMvc)

Komunikacja w architekturze **klient–serwer**:
- serwer: Spring Boot REST,
- klient: Swagger UI / Postman.

---

## 2. Wymagania systemowe

- Java **17** 
- Maven
- IntelliJ IDEA 
- Wolny port `8081` 

---

## 3. Uruchomienie backendu

W katalogu:

```bash
cd motoparts-server
