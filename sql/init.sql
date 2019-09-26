INSERT INTO user (id, username, password, firstname, lastname, email, enabled, lastpasswordresetdate) VALUES (10, 'admin', '$2a$08$lDnHPz7eUkSi6ao14Twuau08mzhWrL4kyZGGU5xfiGALO/Vxd5DOi', 'admin', 'admin', 'admin@admin.com', 1, PARSEDATETIME('01-01-2016', 'dd-MM-yyyy'));
INSERT INTO user (id, username, password, firstname, lastname, email, enabled, lastpasswordresetdate) VALUES (20, 'user', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'user', 'user', 'enabled@user.com', 1, PARSEDATETIME('01-01-2016','dd-MM-yyyy'));
INSERT INTO user (id, username, password, firstname, lastname, email, enabled, lastpasswordresetdate) VALUES (30, 'disabled', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'user', 'user', 'disabled@user.com', 0, PARSEDATETIME('01-01-2016','dd-MM-yyyy'));
INSERT INTO AUTHORITY (ID, NAME) VALUES (1, 'ROLE_USER');
INSERT INTO AUTHORITY (ID, NAME) VALUES (2, 'ROLE_ADMIN');
INSERT INTO user_authority (USER_ID, AUTHORITY_ID) VALUES (1, 1);
INSERT INTO user_authority (USER_ID, AUTHORITY_ID) VALUES (1, 2);
INSERT INTO user_authority (USER_ID, AUTHORITY_ID) VALUES (2, 1);
INSERT INTO user_authority (USER_ID, AUTHORITY_ID) VALUES (3, 1);