CREATE TABLE tb_client
(
    client_id      UUID           NOT NULL,
    first_name     VARCHAR(20)    NOT NULL,
    last_name      VARCHAR(20)    NOT NULL,
    client_email   VARCHAR(50)    NOT NULL,
    client_cpf     VARCHAR(255)   NOT NULL,
    client_balance DECIMAL(10, 2) NOT NULL,
    CONSTRAINT pk_tb_client PRIMARY KEY (client_id)
);

CREATE TABLE tb_enterprise
(
    enterprise_id      UUID           NOT NULL,
    enterprise_name    VARCHAR(150)   NOT NULL,
    enterprise_cnpj    VARCHAR(255)   NOT NULL,
    enterprise_balance DECIMAL(10, 2) NOT NULL,
    CONSTRAINT pk_tb_enterprise PRIMARY KEY (enterprise_id)
);

CREATE TABLE tb_transaction
(
    id                         UUID           NOT NULL,
    client_client_id           UUID           NOT NULL,
    enterprise_enterprise_id   UUID           NOT NULL,
    transaction_value          DECIMAL(10, 2) NOT NULL,
    transaction_fee_percentage DECIMAL(3, 2)  NOT NULL,
    transaction_type           VARCHAR(255)   NOT NULL,
    transaction_data_time      TIMESTAMP      NOT NULL,
    CONSTRAINT pk_tb_transaction PRIMARY KEY (id)
);

ALTER TABLE tb_client
    ADD CONSTRAINT uc_tb_client_client_cpf UNIQUE (client_cpf);

ALTER TABLE tb_enterprise
    ADD CONSTRAINT uc_tb_enterprise_enterprise_cnpj UNIQUE (enterprise_cnpj);

ALTER TABLE tb_transaction
    ADD CONSTRAINT FK_TB_TRANSACTION_ON_CLIENT_CLIENT FOREIGN KEY (client_client_id) REFERENCES tb_client (client_id);

ALTER TABLE tb_transaction
    ADD CONSTRAINT FK_TB_TRANSACTION_ON_ENTERPRISE_ENTERPRISE FOREIGN KEY (enterprise_enterprise_id) REFERENCES tb_enterprise (enterprise_id);