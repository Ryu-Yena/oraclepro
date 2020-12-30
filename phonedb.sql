--계정 생성
create user phonedb identified by phonedb;

--계정 권한
grant resource, connect to phonedb;

--테이블 삭제
drop table person;

--시퀀스 삭제
drop sequence seq_person_id;

--테이블 생성
create table person(
    person_id number(5),
    name varchar2(30) not null,
    hp varchar2(20),
    company varchar2(20)
    primary key(person_id)
);

--시퀀스 생성
create sequence seq_person_id
increment by 1
start with 1;

--전화번호 데이터 INSERT
insert in to person values(seq_person_id.nextval, '이효리', '010-1111-1111', '02-1111-1111');
insert in to person values(seq_person_id.nextval, '정우성', '010-2222-2222', '02-2222-2222');
insert in to person values(seq_person_id.nextval, '유재석', '010-3333-3333', '02-3333-3333');
insert in to person values(seq_person_id.nextval, '이정재', '010-4444-4444', '02-4444-4444');
insert in to person values(seq_person_id.nextval, '서장훈', '010-5555-5555', '02-5555-5555');

--전화번호부 SELECT
select person_id,
       name,
       hp,
       company
from person;

--전화번호부 UPDATE
update person
set name = '유정재',
    hp = '010-1234-5678',
    company = '02-9876-5432'
where person person_id = 4;

--전화번호부 DELETE
delete from person
where person_id = 2;
