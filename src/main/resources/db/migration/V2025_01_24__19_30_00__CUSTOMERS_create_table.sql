CREATE TABLE CUSTOMERS (
    customer_id SERIAL PRIMARY KEY,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL UNIQUE,
    name VARCHAR(255) NOT NULL,
    phone_nr VARCHAR(15),
    CONSTRAINT uq_customer_email UNIQUE (email),
    CONSTRAINT uq_customer_password UNIQUE (password)
);
