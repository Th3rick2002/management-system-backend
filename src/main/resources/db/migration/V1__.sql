CREATE TYPE PROVIDER_STATUS_ENUM AS ENUM ('ACTIVE', 'INACTIVE');

CREATE TABLE providers
(
    id          UUID            NOT NULL,
    created_at  TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at  TIMESTAMP WITHOUT TIME ZONE,
    deleted_at  TIMESTAMP WITHOUT TIME ZONE,
    name        VARCHAR(50),
    address     VARCHAR(50),
    phone       VARCHAR(20),
    email       VARCHAR(255),
    status      PROVIDER_STATUS_ENUM NOT NULL,
    description VARCHAR(200),
    CONSTRAINT pk_providers PRIMARY KEY (id)
);

CREATE TABLE products
(
    id             UUID             NOT NULL,
    created_at     TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at     TIMESTAMP WITHOUT TIME ZONE,
    deleted_at     TIMESTAMP WITHOUT TIME ZONE,
    sku            VARCHAR(20),
    name           VARCHAR(25),
    stock_quantity INTEGER          NOT NULL,
    category       VARCHAR(255),
    image_url      VARCHAR(255),
    is_active      BOOLEAN,
    weight         DOUBLE PRECISION NOT NULL,
    dimensions     VARCHAR(255),
    price          DECIMAL          NOT NULL,
    description    VARCHAR(200),
    provider_id    UUID,
    CONSTRAINT pk_products PRIMARY KEY (id)
);

ALTER TABLE products
    ADD CONSTRAINT FK_PRODUCTS_ON_PROVIDER FOREIGN KEY (provider_id) REFERENCES providers (id);