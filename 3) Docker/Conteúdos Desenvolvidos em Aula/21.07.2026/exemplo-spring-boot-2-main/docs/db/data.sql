INSERT INTO departamento (nm_departamento) VALUES ('Recursos Humanos');
INSERT INTO departamento (nm_departamento) VALUES ('Tecnologia da Informação');

INSERT INTO funcionario (nome, cpf, email, senha, data_nascimento, salario, gerente, estado, cidade, bairro, logradouro, numero, cep, id_departamento, criado_por)
VALUES ('Henrique', '11111111111', 'henrique@email.com', 'senha', '2001-11-10', 1000, TRUE, 'SC', 'Blumenau', 'Viktor Konder', 'Rua São Paulo', '11147', '89012001', 1, NULL);

INSERT INTO funcionario (nome, cpf, email, senha, data_nascimento, salario, gerente, estado, cidade, bairro, logradouro, numero, cep, id_departamento, criado_por)
VALUES ('Ana', '22222222222', 'ana@email.com', 'senha', '1995-05-20', 2000, FALSE, 'SC', 'Florianópolis', 'Centro', 'Rua das Flores', '2020', '88015000', 2, 1);
