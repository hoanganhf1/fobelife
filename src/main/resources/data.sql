INSERT INTO ROLES(ID, NAME, DESCRIPTION) SELECT 1, 'ADMIN', 'Admin page' WHERE NOT EXISTS (SELECT * FROM ROLES WHERE NAME='ADMIN');
INSERT INTO ROLES(ID, NAME, DESCRIPTION) SELECT 2, 'CUSTOMER', 'Customer' WHERE NOT EXISTS (SELECT * FROM ROLES WHERE NAME='CUSTOMER');