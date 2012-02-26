psql --command='drop schema public cascade' pengurus postgres
psql --command='create schema public' pengurus postgres
psql --file create.sql pengurus postgres
