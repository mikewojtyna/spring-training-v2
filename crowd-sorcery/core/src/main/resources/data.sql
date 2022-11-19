INSERT INTO investors(id, name, is_default)
VALUES ('0f3ba620-533a-4047-a1d7-e4cc77c263c6', 'Default User', TRUE);

INSERT INTO investor_profiles(id, isVip, score, ref_link, investor_id,
                              is_default)
VALUES ('83f43dec-8138-4fa7-9a6a-7aa46d1d37c0', TRUE, 50,
        'wojtyna.pl?refLink=123',
        '0f3ba620-533a-4047-a1d7-e4cc77c263c6', TRUE);

INSERT INTO borrowers(id, name, is_default)
VALUES ('123', 'George Borrower', TRUE);

INSERT INTO borrowers(id, name, is_default)
VALUES ('456', 'Henry Borrower', TRUE);

INSERT INTO borrowers(id, name, is_default)
VALUES ('789', 'Martin Borrower', TRUE);