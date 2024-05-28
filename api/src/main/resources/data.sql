INSERT INTO users(id, first_name, last_name, email, password, role, created_by, created_at, updated_at, deleted) VALUES
(477, 'User_1_First_Name', 'User_1_Last_Name', 'prepopulatedadmin@nurtelecom.kg', 'doesntwork', 'ADMINISTRATOR', null, '2024-01-26T10:00:00', null, false)
;

INSERT INTO actions(id, name, description, start_date, end_date, created_at, updated_at, deleted, activated, created_by, updated_by, type) VALUES
(1, 'Action_1', 'Description Action_1', '2024-01-27T10:00:00', '2024-04-27T10:00:00', '2024-01-26T10:00:00', null, false, false, 477, null, 'TICKET'),
(2, 'Action_2', 'Description Action_2', '2024-01-27T10:00:00', '2024-04-27T10:00:00', '2024-01-26T10:00:00', null, false, true, 477, null, 'TICKET'),
(3, 'Action_3', 'Description Action_3', '2024-01-27T10:00:00', '2024-04-27T10:00:00', '2024-01-26T10:00:00', null, false, false, 477, null, 'TICKET'),
(4, 'Action_4', 'Description Action_4', '2024-01-27T10:00:00', '2024-04-27T10:00:00', '2024-01-26T10:00:00', null, false, true, 477, null, 'PROMOCODE'),
(5, 'Action_5', 'Description Action_5', '2024-01-27T10:00:00', '2024-04-27T10:00:00', '2024-01-26T10:00:00', null, false, true, 477, null, 'PROMOCODE')
;

INSERT INTO stages(id, action_id, start_date, end_date, ticket_limit, promocode_limit, created_at, updated_at, activated, deleted, created_by, updated_by) VALUES
(1, 1, '2024-01-27T10:00:00', '2024-02-27T10:00:00', 100, 0, '2024-01-26T10:00:00', null, false, false, 477, null),
(2, 1, '2024-02-27T10:00:00', '2024-03-27T10:00:00', 100, 100, '2024-01-26T10:00:00', null, false, false, 477, null),
(3, 1, '2024-03-27T10:00:00', '2024-04-27T10:00:00', 100, 100, '2024-01-26T10:00:00', null, false, false, 477, null),
(4, 2, '2024-01-27T10:00:00', '2024-02-27T10:00:00', 10, 0, '2024-01-26T10:00:00', null, false, false, 477, null),
(5, 2, '2024-02-27T10:00:01', '2024-03-27T10:00:00', 20, 0, '2024-01-26T10:00:00', null, false, false, 477, null),
(6, 2, '2024-03-27T10:00:01', '2024-04-27T10:00:00', 30, 0, '2024-01-26T10:00:00', null, false, false, 477, null),
(7, 3, '2024-01-27T10:00:00', '2024-02-27T10:00:00', 300, 0, '2024-01-26T10:00:00', null, false, false, 477, null),
(8, 3, '2024-02-27T10:00:00', '2024-03-27T10:00:00', 300, 0, '2024-01-26T10:00:00', null, false, false, 477, null),
(9, 3, '2024-03-27T10:00:00', '2024-04-27T10:00:00', 300, 0, '2024-01-26T10:00:00', null, false, false, 477, null),
(10, 4, '2024-01-27T10:00:00', '2024-02-27T10:00:00', 100, 100, '2024-01-26T10:00:00', null, false, false, 477, null),
(11, 4, '2024-02-27T10:00:00', '2024-03-27T10:00:00', 100, 100, '2024-01-26T10:00:00', null, false, false, 477, null),
(12, 4, '2024-03-27T10:00:00', '2024-04-27T10:00:00', 100, 100, '2024-01-26T10:00:00', null, false, false, 477, null),
(13, 5, '2024-01-27T10:00:00', '2024-02-27T10:00:00', 20, 0, '2024-01-26T10:00:00', null, false, false, 477, null),
(14, 5, '2024-02-27T10:00:00', '2024-03-27T10:00:00', 20, 0, '2024-01-26T10:00:00', null, false, false, 477, null),
(15, 5, '2024-03-27T10:00:00', '2024-04-27T10:00:00', 20, 0, '2024-01-26T10:00:00', null, false, false, 477, null)
;

INSERT INTO rules(id, stage_id, min_sum, max_sum, amount, created_at, updated_at, deleted, created_by, updated_by) VALUES
(1, 1, 0, 100, 1000000, '2024-01-28T10:00:00', null, false, 477, null),
(2, 10, 0, 100, 1000000, '2024-01-28T10:00:00', null, false, 477, null),
(3, 11, 0, 100, 1000000, '2024-01-28T10:00:00', null, false, 477, null),
(4, 12, 0, 100, 1000000, '2024-01-28T10:00:00', null, false, 477, null)
;

INSERT INTO promocodes(id, rule_id, expired_date, created_at, updated_at, deleted, created_by, updated_by) VALUES
(1, 2, '2025-01-27T10:00:00', '2024-01-28T10:00:00', null, false, 477, null),
(2, 3, '2025-01-27T10:00:00', '2024-01-28T10:00:00', null, false, 477, null),
(3, 4, '2025-01-27T10:00:00', '2024-01-28T10:00:00', null, false, 477, null)
;

INSERT INTO tickets(id, rule_id, start_date, end_date, created_at, updated_at, deleted, created_by, updated_by) VALUES
(1, 1, '2025-01-27T10:00:00', '2025-01-29T10:00:00', '2024-01-28T10:00:00', null, false, 477, null)
;

INSERT INTO abonent_promocodes(id, promocode_id, abonent_phone_number, code, used_at, sum, cashback, status, created_at, created_by, deleted) VALUES
(1, 1, '12345', 'a', '2024-01-27T00:00:00', 100, 0, 'NOT_USED', '2024-01-27T00:00:00', null, false),
(2, 1, '12345', 'b', '2024-01-27T00:00:00', 100, 0, 'USED', '2024-01-27T00:00:00', null, false),
(3, 1, '12345', 'c', '2024-01-27T00:00:00', 100, 0, 'USED', '2024-01-27T00:00:00', null, false),
(4, 1, '12345', 'd', '2024-01-27T00:00:00', 100, 0, 'USED', '2024-01-27T00:00:00', null, false),
(5, 1, '12345', 'e', '2024-01-27T00:00:00', 100, 0, 'NOT_USED', '2024-01-27T00:00:00', null, false),
(6, 1, '12345', 'f', '2024-01-27T00:00:00', 100, 0, 'USED', '2024-01-27T00:00:00', null, false),
(7, 2, '12345', 'g', '2024-01-27T00:00:00', 100, 0, 'NOT_USED', '2024-01-27T00:00:00', null, false),
(8, 2, '12345', 'h', '2024-01-27T00:00:00', 100, 0, 'USED', '2024-01-27T00:00:00', null, false),
(9, 2, '12345', 'i', '2024-01-27T00:00:00', 100, 0, 'USED', '2024-01-27T00:00:00', null, false),
(10, 2, '12345', 'j', '2024-01-27T00:00:00', 100, 0, 'USED', '2024-01-27T00:00:00', null, false),
(11, 3, '12345', 'k', '2024-01-27T00:00:00', 100, 0, 'NOT_USED', '2024-01-27T00:00:00', null, false),
(12, 3, '12345', 'l', '2024-01-27T00:00:00', 100, 0, 'USED', '2024-01-27T00:00:00', null, false)
;

INSERT INTO abonent_tickets(id, ticket_id, abonent_phone_number, sum, cashback, status, created_at, deleted) VALUES
(1, 1, '12345', 100, 0, 'NOT_USED', '2024-01-27T00:00:00', false),
(2, 1, '12345', 100, 0, 'USED', '2024-01-27T00:00:00', false),
(3, 1, '12345', 100, 0, 'USED', '2024-01-27T00:00:00', false),
(4, 1, '12345', 100, 0, 'USED', '2024-01-27T00:00:00', false),
(5, 1, '12345', 100, 0, 'USED', '2024-01-27T00:00:00', false),
(6, 1, '12345', 100, 0, 'NOT_USED', '2024-01-27T00:00:00', false),
(7, 1, '12345', 100, 0, 'USED', '2024-01-27T00:00:00', false)
;
