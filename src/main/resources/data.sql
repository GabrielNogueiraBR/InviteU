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