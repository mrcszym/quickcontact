CREATE TABLE CUSTOMERS (
    customer_id SERIAL PRIMARY KEY,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    name VARCHAR(255) NOT NULL,
    phone VARCHAR(15),
    CONSTRAINT uq_customer_email UNIQUE (email)
);
