INSERT INTO category (description) VALUES ('American');
INSERT INTO category (description) VALUES ('Italian');
INSERT INTO category (description) VALUES ('Mexican');
INSERT INTO category (description) VALUES ('Fast Food');

INSERT INTO unit_of_measure (description) VALUES('Teaspoon');
INSERT INTO unit_of_measure (description) VALUES('Tablespoon');
INSERT INTO unit_of_measure (description) VALUES('Cup');
INSERT INTO unit_of_measure (description) VALUES('Pinch');
INSERT INTO unit_of_measure (description) VALUES('Ounce');


INSERT INTO recipe (description, prep_Time, cook_Time, servings, source, url, directions)
    VALUES ('porrige', 60, 20, 4, 'Kenny', 'http://kenny.org/porrige', 'Start by cooking milk');
INSERT INTO notes (description, recipe_id) VALUES ('This should be done by all people', 1);
UPDATE recipe SET notes_id = 1 Where id = 1;
