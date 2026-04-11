--  (AppUser)
CREATE TABLE IF NOT EXISTS app_user (
                                        id BIGSERIAL PRIMARY KEY,
                                        name VARCHAR(255) NOT NULL UNIQUE,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
    );

--  (Category)
CREATE TABLE IF NOT EXISTS category (
                                        id BIGSERIAL PRIMARY KEY,
                                        name VARCHAR(255) NOT NULL,
    type VARCHAR(50) NOT NULL
    );

--  (Transaction)
CREATE TABLE IF NOT EXISTS transaction (
                                           id BIGSERIAL PRIMARY KEY,
                                           amount NUMERIC(19, 2) NOT NULL,
    description VARCHAR(255) NOT NULL,
    transaction_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    user_id BIGINT NOT NULL,
    category_id BIGINT NOT NULL,

    --  Foreign Keys
    CONSTRAINT fk_transaction_user
    FOREIGN KEY (user_id)
    REFERENCES app_user (id)
    ON DELETE CASCADE,

    CONSTRAINT fk_transaction_category
    FOREIGN KEY (category_id)
    REFERENCES category (id)
    );