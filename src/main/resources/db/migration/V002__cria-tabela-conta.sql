CREATE TABLE conta (
	id BIGINT NOT NULL AUTO_INCREMENT,
    numero VARCHAR(6) NOT NULL,
    usuario_id BIGINT NOT NULL,
    saldo DECIMAL(10,2) NOT NULL,
    
    PRIMARY KEY (id)
);

ALTER TABLE conta ADD CONSTRAINT fk_conta_usuario 
FOREIGN KEY (usuario_id) REFERENCES usuario (id);