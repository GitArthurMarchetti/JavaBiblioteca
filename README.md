# JavaBiblioteca – Library Loans API

A full-stack library management system built as a team project at SENAI: a **Java / Spring Boot REST API** for students, books and loans, plus web front ends.

## Back end (`back-end/JavaBiblioteca`)

- Java 21 · Spring Boot 3.3 · Spring Web · Spring Data JPA (Hibernate) · MySQL
- Layered architecture: `Controller` → `Service` → `Repository` → `Entity`
- CORS configuration for the web clients

### Main endpoints (context path `/biblio`)

| Resource | Endpoints |
|---|---|
| Students | `POST /estudante`, `POST /estudante/login`, `GET /estudante`, `GET /estudante/id/{id}`, `PUT /estudante` |
| Books | `POST /livro`, `GET /livro/titulo/{title}`, `GET /livro/pag/{page}` (paginated), `PUT /livro`, `DELETE /livro/{id}` |
| Loans | `GET /emprestimo`, `POST /emprestimo/{studentId}/{bookId}`, `PUT /emprestimo`, `DELETE /emprestimo/{id}` |

### Run

```bash
cd back-end/JavaBiblioteca
# configure spring.datasource.* in src/main/resources/application.properties
./mvnw spring-boot:run
```

## Front ends (`front-end/`)

- `java-biblioteca-front`: Next.js 14 + TypeScript + Tailwind (shadcn/ui) client using Axios
- `loginHtml`: first version in plain HTML/CSS/JavaScript (login, books, students and loans pages)

## Team

Arthur Marchetti, Francisco Ribas and Luiza Fischer.
