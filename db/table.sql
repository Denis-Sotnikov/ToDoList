create table item(
    id SERIAL PRIMARY KEY,
    description text,
    created timestamp,
    done boolean
)