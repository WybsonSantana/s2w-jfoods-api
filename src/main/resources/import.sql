INSERT INTO cozinha(id, nome) VALUES(1, 'Tailandesa');
INSERT INTO cozinha(id, nome) VALUES(2, 'Indiana');

INSERT INTO restaurante(id, nome, taxa_frete, cozinha_id) VALUES(1, 'Javai o Comilão!', 19, 1);
INSERT INTO restaurante(id, nome, taxa_frete, cozinha_id) VALUES(2, 'JVM Delivery', 17, 1);
INSERT INTO restaurante(id, nome, taxa_frete, cozinha_id) VALUES(3, 'Javeiro Lunch', 11, 2);

INSERT INTO estado(id, nome) VALUES(1, 'Goiás');
INSERT INTO estado(id, nome) VALUES(2, 'São Paulo');
INSERT INTO estado(id, nome) VALUES(3, 'Ceará');

INSERT INTO cidade(id, nome, estado_id) VALUES(1, 'Goiânia', 1);
INSERT INTO cidade(id, nome, estado_id) VALUES(2, 'Anápolis', 1);
INSERT INTO cidade(id, nome, estado_id) VALUES(3, 'São Paulo', 2);
INSERT INTO cidade(id, nome, estado_id) VALUES(4, 'Campinas', 2);
INSERT INTO cidade(id, nome, estado_id) VALUES(5, 'Fortaleza', 3);

INSERT INTO forma_pagamento(id, descricao) VALUES(1, 'Cartão de crédito');
INSERT INTO forma_pagamento(id, descricao) VALUES(2, 'Cartão de débito');
INSERT INTO forma_pagamento(id, descricao) VALUES(3, 'Dinheiro');
INSERT INTO forma_pagamento(id, descricao) VALUES(4, 'Pix');

INSERT INTO permissao(id, nome, descricao) VALUES(1, 'CONSULTAR_COZINHAS', 'Permite consultar cozinhas');
INSERT INTO permissao(id, nome, descricao) VALUES(2, 'EDITAR_COZINHAS', 'Permite editar cozinhas');