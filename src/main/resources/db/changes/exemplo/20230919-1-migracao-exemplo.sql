CREATE TABLE exemplo
(
    id               VARCHAR(36)                 NOT NULL,
    data_criacao     TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    data_atualizacao TIMESTAMP WITHOUT TIME ZONE,
    descricao        VARCHAR(100),
    ativo            BOOLEAN                     NOT NULL,
    CONSTRAINT pk_exemplo PRIMARY KEY (id)
);