-- 2024-06-11

-- 돌발 퀴즈!
select * from country 
where SurfaceArea between 80000 and 100000 
and not SurfaceArea = 83600;

select * from country 
where name in ('south korea', 'north korea', 'japan');


select * from actor order by first_name desc , last_name desc;

select * from actor order by actor_id asc limit 100, 10; -- 100 이후부터 10개 가져와

select * from actor order by actor_id limit  10 offset 100;

select * from actor where first_name like '_A_';

-- p. 142 정규 표현식 사용
select * from actor where first_name regexp '^K|N$'; -- K로 시작하거나 N으로 끝나는 데이터 조회

select * from actor where first_name regexp '[A-C]'; -- A에서 C까지 들어가는 데이터 조회
select * from actor where first_name regexp '[^A-C]'; -- A에서 C까지 들어가지 않는 데이터 조회

-- P.146 돌발퀴즈!
select Region 'R', count(Region) as 'RC' from country group by Region having Region like 'South%' and rc <= 11;


select continent, count(continent) as cc, region, count(Region) as rc from country 
group by Continent , Region; 


select distinct region from country c where Region = 'caribbean';\ 

-- DDL
create table doit2 (
	col1 int not null primary key auto_increment,
	col2 varchar(100) not null,
	col3 datetime not null default CURRENT_TIMESTAMP
);

alter table doit2 auto_increment = 100;

insert into doit2 (col2) values ('홍길동');
select * from doit2;
select last_insert_id();

delete from doit_create_table ;