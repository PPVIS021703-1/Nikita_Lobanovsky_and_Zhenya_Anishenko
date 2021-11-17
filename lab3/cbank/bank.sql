CREATE TABLE card(
    card_id BIGSERIAL PRIMARY KEY,
    number VARCHAR(16),
    card_check BIGSERIAL,
    validation_date VARCHAR(5),
    is_valid BOOLEAN,
    pin VARCHAR(100)
    );

CREATE TABLE roles(
    role_id BIGSERIAL PRIMARY KEY,
    name VARCHAR(30)
);

INSERT INTO roles(role_id, name) VALUES(1, 'VERIFIED_CARD');
INSERT INTO roles(role_id, name) VALUES(2, 'ADMINISTRATOR');

CREATE TABLE bank_roles(
    card_id BIGSERIAL REFERENCES card(card_id),
    role_id BIGSERIAL REFERENCES roles(role_id)
);