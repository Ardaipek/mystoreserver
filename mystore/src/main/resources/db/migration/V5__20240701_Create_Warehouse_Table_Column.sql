-- Create the warehouse table
CREATE TABLE warehouse
(
    id       SERIAL PRIMARY KEY,
    name     VARCHAR(100) NOT NULL,
    location VARCHAR(255) NOT NULL
);

-- Create the junction table to represent the many-to-many relationship
CREATE TABLE product_warehouse
(
    product_id   INTEGER NOT NULL,
    warehouse_id INTEGER NOT NULL,
    PRIMARY KEY (product_id, warehouse_id),
    FOREIGN KEY (product_id) REFERENCES product (id),
    FOREIGN KEY (warehouse_id) REFERENCES warehouse (id)
);
