

drop table if exists intervieww;
drop table if exists candidate;
create table candidate
(
    id              int auto_increment
        primary key,
    name            varchar(50)                                    not null,
    type            varchar(50)                                    not null,
    role            varchar(50)                                    not null,
    department      varchar(50)                                    null,
    status          varchar(30) default 'In the Interview Process' null,
    universty       varchar(50)                                    null,
    current_company varchar(50)                                    null,
    created_at      datetime                                       null,
    updated_at      datetime                                       null,
    status_code     varchar(20) default 'IN_PROCESS'               not null
);

create table intervieww
(
    id               int auto_increment
        primary key,
    interview_date   datetime                          not null,
    interviewer_name varchar(50)                       not null,
    result           enum ('pass', 'fail', 'pending')  not null,
    cmnt             text                              null,
    onboard_date     datetime                          null,
    type             enum ('hr', 'technical', 'final') not null,
    candidate_id     int                               null,
    created_at       datetime                          null,
    updated_at       datetime                          null,
    constraint FKqsuh5v4w1gqwcfabkqb20tuja
        foreign key (candidate_id) references recruitments.candidate (id),
    constraint FK74x4i4lcw4exv3k129r8yuagr
        foreign key (candidate_id) references recruitments.candidate (id)
);

