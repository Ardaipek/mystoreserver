CREATE TABLE product
(
    id          SERIAL PRIMARY KEY,
    name        VARCHAR(100) NOT NULL,
    quantity    INTEGER      NOT NULL,
    category_id INTEGER,
    FOREIGN KEY (category_id) REFERENCES category (id)
);
