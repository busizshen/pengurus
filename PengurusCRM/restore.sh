sudo -u postgres psql --command='drop schema public cascade' pengurus postgres
sudo -u postgres psql --command='create schema public' pengurus postgres
sudo -u postgres psql --file `dirname $0`/create.sql pengurus postgres
