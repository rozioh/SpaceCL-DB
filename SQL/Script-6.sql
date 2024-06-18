-- 2024-06-17
select * from doit2;

-- 2024-06-18
show variables like 'autocommit';

start transaction;

select * from DOIT2;

delete from doit2 where col1 = 104;

-- COMMIT -- 트랜젝션을 DB에 적용
select * from doit2; -- 적용된 결과 조회 

rollback;

insert into doit2 (col2) values ('홍길동');
select last_insert_id(); -- 마지막에 insert 한 id 를 확인할 수 있다. 