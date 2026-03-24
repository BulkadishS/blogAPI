Local branch for dev
TODO:
- Simplify SQL query in repository
- Fix some race conditions in schema.sql
- Add password encoding (without heavy dependencies like Spring security)

Note:
- Change DB_PORT in application.properties to ${DB_PORT:5433}, to build with docker
