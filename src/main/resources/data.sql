INSERT INTO Role (role) 
   VALUES
      ( 'ADMIN'),
      ( 'USER');

INSERT INTO Address (zip_code, state, city, district, street, number)
   VALUES
      ('18000-000', 'SP', 'Sorocaba',  'Jd. Santa Cruz', 'Rua no Campolim', '170'),
      ('19000-000', 'SP', 'Sao Paulo',  'Morumbi', 'Rua no Morumbi', '172'),
      ('22000-000', 'SP', 'Boituva',  'Novo mundo', 'Rua no Novo mundo', '173'),
      ('20000-000', 'SP', 'Ipero',  'Jd. Irene', 'Rua no Jd. Irene', '174');

INSERT INTO User (username, password, name, birth_date, rg, cpf, email, gender, address_id)
   VALUES
      ( 'Joao', '12345', 'Joao Silva',  '2001-01-30', '11.111.111-1', '123.123.123-12', 'joao@email.com', 'M', 1),
      ( 'Ibere', '12345', 'Ibere Thenorio',  '1988-01-30', '22.222.222-2', '123.123.123-12', 'ibere@email.com', 'M', 2),
      ( 'Michelli', '12345', 'Michelli Brito',  '1990-01-30', '33.333.333-3', '123.123.123-12', 'raul@email.com', 'F',4),
      ( 'Rafaela', '12345', 'Rafaela Silva',  '2000-01-30', '44.444.444-4', '123.123.123-12', 'raul@email.com', 'M',3);

INSERT INTO User_Roles(user_id, role_id)
   VALUES
      (1,1),
      (2,2),
      (3,2),
      (4,2);

INSERT INTO Event_main (title, description, is_public)
    VALUES
        ('Tomorrowland', 'Tomorrowland é um festival de música realizado anualmente.', true),
        ('Rock in Rio', 'O Rock in Rio é um festival de música idealizado pelo empresário brasileiro Roberto Medina pela primeira vez em 1985, sendo, desde sua criação, reconhecidamente, o maior festival musical do planeta.', true),
        ('Aniversário', 'Aniversário de uma pessoa', false);

INSERT INTO Event_sub (title, description, date_time, is_limited, capacity, event_id)
    VALUES
        ('Dia 1 Tomorrowland', 'Alok e CatDealers', '2021-08-27 19:00:00.000', true, 1500, 1),
        ('Dia 2 Tomorrowland', 'Steve Aoki e David Guetta', '2021-08-28 19:00:00.000', true, 1000, 1),
        ('Dia 1 Rock in Rio', 'Anitta', '2021-09-24 17:00:00.000', true, 2000, 2),
        ('Dia 2 Rock in Rio', 'Coldplay', '2021-09-25 19:00:00.000', true, 2000, 2),
        ('Aniversário', 'Maria', '2021-05-01 19:00:00.000', false, 0, 3);
