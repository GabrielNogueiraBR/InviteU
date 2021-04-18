INSERT INTO Role (role)
   VALUES
      ( 'ADMIN'),
      ( 'USER');

INSERT INTO Status (description)
   VALUES
      ( 'In progress'),
      ( 'Pending'),
      ( 'Expired'),
      ( 'Full');

INSERT INTO Address (zip_code, state, city, district, street, number)
   VALUES
      ('18000-000', 'SP', 'Sorocaba',  'Jd. Santa Cruz', 'Rua no Campolim', '170'),
      ('19000-000', 'SP', 'Sao Paulo',  'Morumbi', 'Rua no Morumbi', '172'),
      ('22000-000', 'SP', 'Boituva',  'Novo mundo', 'Rua no Novo mundo', '173'),
      ('20000-000', 'SP', 'Ipero',  'Jd. Irene', 'Rua no Jd. Irene', '174');

INSERT INTO User (username, password, name, birth_date, rg, cpf, email, gender, address_id)
   VALUES
      ( 'Joao', '$2a$10$ZcdPY.0/XOqoklEFbyULbOOIPdrArtC/xdpsHIyoWHkUOphovPWZK', 'Joao Silva',  '2001-01-30', '11.111.111-1', '123.123.123-12', 'joao@email.com', 'M', 1),
      ( 'Ibere', '$2a$10$ZcdPY.0/XOqoklEFbyULbOOIPdrArtC/xdpsHIyoWHkUOphovPWZK', 'Ibere Thenorio',  '1988-01-30', '22.222.222-2', '123.123.123-12', 'ibere@email.com', 'M', 2),
      ( 'Michelli', '$2a$10$ZcdPY.0/XOqoklEFbyULbOOIPdrArtC/xdpsHIyoWHkUOphovPWZK', 'Michelli Brito',  '1990-01-30', '33.333.333-3', '123.123.123-12', 'raul@email.com', 'F',4),
      ( 'Rafaela', '$2a$10$ZcdPY.0/XOqoklEFbyULbOOIPdrArtC/xdpsHIyoWHkUOphovPWZK', 'Rafaela Silva',  '2000-01-30', '44.444.444-4', '123.123.123-12', 'raul@email.com', 'M',3);

INSERT INTO User_Roles(user_id, role_id)
   VALUES
      (1,1),
      (2,2),
      (3,2),
      (4,2);

INSERT INTO Event_main (title, description, is_public, address_id, user_owner_id)
    VALUES
        ('Tomorrowland', 'Tomorrowland é um festival de música realizado anualmente.', true, 3, 2),
        ('Rock in Rio', 'O Rock in Rio é um festival de música idealizado pelo empresário brasileiro Roberto Medina pela primeira vez em 1985, sendo, desde sua criação, reconhecidamente, o maior festival musical do planeta.', true, 1, 2),
        ('Aniversário', 'Aniversário de uma pessoa', false, 2, 2);

INSERT INTO Event_sub (title, description, start_date_time, end_date_time, is_limited, capacity, event_id, status_id)
    VALUES
        ('Dia 1 Tomorrowland', 'Alok e CatDealers', '2021-08-27 19:00:00.000', '2021-08-28 19:00:00.000' , true, 1500, 1, 1),
        ('Dia 2 Tomorrowland', 'Steve Aoki e David Guetta', '2021-08-28 19:00:00.000', '2021-08-29 19:00:00.000' , true, 1000, 1, 2),
        ('Dia 1 Rock in Rio', 'Anitta', '2021-09-24 17:00:00.000', '2021-09-24 23:00:00.000' , true, 2000, 2, 3),
        ('Dia 2 Rock in Rio', 'Coldplay', '2021-09-25 19:00:00.000', '2021-09-26 03:00:00.000', true, 2000, 2, 4),
        ('Aniversário', 'Maria', '2021-05-01 19:00:00.000', '2021-05-02 00:00:00.000', false, 0, 3, 3);

INSERT INTO Rating (sub_Event_id, rating, user_id)
   VALUES
      (1, 2, 2),
      (2, 2, 2),
      (1, 5, 3),
      (1, 5, 4);

INSERT INTO User_Sub_Event (user_id, sub_event)
   VALUES
      (1,1),
      (1,2),
      (2,3),
      (2,4),
      (3,1),
      (3,2),
      (3,3),
      (3,4);
