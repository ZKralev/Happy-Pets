SET FOREIGN_KEY_CHECKS=0;

-- some test users
INSERT INTO users (id, username, password, email, full_name, user_role)
VALUES (1, 'zdravko', '4b148b365433c559fdc07a0742712e88b61d5e23a52bb10206c308908e2e67836ecb3ff5714006ea', 'zdravko@example.com' ,'Zdravko Kralev', 'ADMIN');

INSERT INTO users (id, username, password, email, full_name, user_role )
VALUES (2, 'stoyan', 'cf42fc3d2ccd6f7438778a61bffe940bfeee00cb6eea44bd510658c19c69a72b572a13ee09c51a8f', 'stoyan@example.com' ,'Stoyan Stoyanov', 'USER');

INSERT INTO users (id, username, password, email, full_name, user_role )
VALUES (3, 'petyr', '74dc9ebd4377bbce15a0614ecad422955ad0f6d95fee746c31d3d39d6cf959d53cc93a3992c06855', 'petyr@example.com' ,'Petyr Petrov', 'USER');

-- comments
INSERT INTO comments ( id,  created, message, author_id)
VALUES
    (1, STR_TO_DATE("10-17-2021 15:40:10", "%m-%d-%Y %H:%i:%s"),
     'Left my little puppy here Lola and she had a ball, I would definitely recommend these guys,
                        the staff are lovely and extremely friendly, Happy Pets are clean and spacious, if you want clean and reliable staff book with these guys you won’t be disappointed,
                        I have recommend Happy Pets to all my friends and family I will definitely be using again in the future.', 1);
INSERT INTO comments ( id,  created, message, author_id)
VALUES
    (2, STR_TO_DATE("11-20-2021 15:40:10", "%m-%d-%Y %H:%i:%s"),
     'We have taken our 2 dogs to Happy Pets for the last 2 years, and also our cat.
                        They are friendly staff, and clearly love the animals that they work with.
                        Our dog is always very excited when he realised where we are and seems to know all the staff. We have a very barky,
                        jumpy dog who they manage really well. We would highly recommend it to anyone. They are clearly well looked after there.', 2);
INSERT INTO comments ( id,  created, message, author_id)
VALUES
    (3, STR_TO_DATE("12-27-2021 15:40:10", "%m-%d-%Y %H:%i:%s"),
     'First time using Happy Pets for my not so little fur baby and they were absolutely amazing. I
                        know I have now found a kennels for life when traveling away... all the staff are so nice and lovely,
                        they saw when I dropped her off that I was nervous and they reassured me she was fine, and having them send me
                        a couple of pictures they to show me how well she was doing relaxed me even more... thank you so much. Arya loved her stay.', 3);
INSERT INTO comments ( id,  created, message, author_id)
VALUES
    (4, STR_TO_DATE("01-20-2022 15:40:10", "%m-%d-%Y %H:%i:%s"),
     'I honestly can''t recommend this place enough.
                        My very nervous reactive boy loves it here.
                        They genuinely  care about our pets and you can rest assured that they are  treated like they are at home.', 2);

-- pets
INSERT INTO pets (id, age, breed, kind, name, owner_id)
VALUES (1, 1, 'British', 'CAT' ,'luci', 1);
INSERT INTO pets (id, age, breed, kind, name, owner_id)
VALUES (2, 3, 'Corgi', 'DOG' ,'leo', 1);
INSERT INTO pets (id, age, breed, kind, name, owner_id)
VALUES (3, 3, 'Corgi', 'DOG' ,'sharo', 2);
INSERT INTO pets (id, age, breed, kind, name, owner_id)
VALUES (4, 5, 'Husky', 'DOG' ,'ivan', 3);

-- bookings
INSERT INTO booking (id, email, name, reservation_date_time, service)
VALUES (1, 'ivan@example.com', 'ivan', STR_TO_DATE("12-27-2022 15:41:10", "%m-%d-%Y %H:%i:%s") ,'BOARDING');
INSERT INTO booking (id, email, name, reservation_date_time, service)
VALUES (2, 'stoyan@example.com', 'stoyan', STR_TO_DATE("12-22-2022 15:40:10", "%m-%d-%Y %H:%i:%s") ,'FEEDING');
INSERT INTO booking (id, email, name, reservation_date_time, service)
VALUES (3, 'ivan@example.com', 'ivan', STR_TO_DATE("12-23-2022 15:42:10", "%m-%d-%Y %H:%i:%s") ,'TRAINING');