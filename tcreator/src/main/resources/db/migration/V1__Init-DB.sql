create table answer_variant (question_id bigint not null, answer_text text, correct bit) engine=MyISAM;
create table exam (id bigint not null, test_types varchar(255), order_id bigint not null, primary key (id)) engine=MyISAM;
create table exam_order (id bigint not null, date date, ndt_method varchar(255), number varchar(255), primary key (id)) engine=MyISAM;
create table exam_ticket (id bigint not null, ticket_variant integer, exam_id bigint not null, primary key (id)) engine=MyISAM;
create table exam_ticket_questions (exam_ticketdb_id bigint not null, questions_id bigint not null, primary key (exam_ticketdb_id, questions_id)) engine=MyISAM;

create table jpwh_sequence (next_val bigint) engine=MyISAM;

 insert into jpwh_sequence values ( 1000 );
 insert into jpwh_sequence values ( 1000 );
 insert into jpwh_sequence values ( 1000 );
 insert into jpwh_sequence values ( 1000 );
 insert into jpwh_sequence values ( 1000 );


create table question (id bigint not null, enabled bit, ndt_method varchar(20) not null, question_number integer not null, question_text text, test_type varchar(20) not null, primary key (id)) engine=MyISAM;
create table question_statistic (id bigint not null, ndt_method varchar(255), order_id bigint not null, question_number integer, test_type varchar(255), total_count integer, wrong_answer_count integer, primary key (id)) engine=MyISAM;


 alter table exam_ticket_questions add constraint UK_EXAM_TICKET_QUESTIONS unique (questions_id);
 alter table question add constraint UK_QUEST_NUMBER_NDT_METHOD_TEST_TYPE unique (question_number, ndt_method, test_type);

 alter table answer_variant add constraint FK_ANSWER_VARIANT_QUESTIONS
    foreign key (question_id)
    references question (id);

 alter table exam add constraint FK_EXAM_ORDER
    foreign key (order_id)
    references exam_order (id);

 alter table exam_ticket add constraint FK_EXAM_TICKET_EXAM
    foreign key (exam_id)
    references exam (id);

 alter table exam_ticket_questions add constraint FK_EXAM_TICKET_QUESTIONS
    foreign key (questions_id)
    references question (id);

 alter table exam_ticket_questions add constraint FK_EXAM_TICKET_QUESTIONS_EXAM_TICKET
    foreign key (exam_ticketdb_id)
    references exam_ticket (id);