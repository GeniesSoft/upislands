# Docker postgres server command
docker run --name postgres-dev -p 5432:5432 -e POSTGRES_PASSWORD=postgres -d postgres