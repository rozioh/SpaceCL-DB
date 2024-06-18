-- 2024-06-07

/* DB ≠ DBMS
 * DB : 중복 데이터를 최소화하여 목적에 맞게 데이터를 효율적으로 관리
 * DBMS : 데이터베이스 관리 시스템(입력, 수정, 삭제 등의 기능 제공)
 * 
 * SQL : DBMS의 데이터를 관리하는 프로그래밍 언어
 * SQL 문법의 종류
 * - DDL: 데이터 정의 언어 (CREATE, ALTER, DROP, TRUNCATE)
 * - DML: 데이터 조작 언어 (SELECT, INSERT, UPDATE, DELETE)
 * - DCL: 데이터 제어 언어 (GRANT, REVOKE, COMMIT, ROLLBACK)
 */

-- 데이터 베이스 생성
create database StudyDB1 default character set UTF8;

-- 데이터 베이스 drop
-- drop database stucydb1;

-- 유저 생성
create user spacecl@'localhost' identified by '비밀번호';

-- 유저 삭제
drop user 'spacecl'@'localhost';

-- 권한 주기
GRANT ALL PRIVILEGES ON StudyDB1.* TO 'spacecl'@'localhost';

-- 모든 명령어 반영
FLUSH PRIVILEGES;

-- doit_create_table 테이블에 데이터 입력
insert into doit_create_table values(6, 'col_3값 생략', '2023-12-31');
insert into doit_create_table values(300, null, now());
insert into doit_create_table values(null, 'null', now()); -- null이라는 문자와 진짜 [NULL]

-- 데이터 개수 조회
select count(*) from doit_create_table dct;

-- 전체 데이터 조회
select * from doit_create_table dct; 

-- 원하는 컬럼만 조회
select col_1, col_3 from doit_create_table dct ; 

-- DB sorting
select * from doit_create_table dct order by col_3 desc; 

-- 테이블의 전체 데이터 삭제
delete from doit_create_table ;

-- 데이터 수정
update doit_create_table set col_1 = 100; -- col_1의 모든 데이터를 100으로 바꾸는 것
update doit_create_table set col_2 = "데이터 수정" where col_1 = 1; -- where 조건에 맞게 set 데이터 수정
update doit_create_table set col_2 = "점심시간" where col_1 = 3; -- where 조건에 맞게 set 데이터 수정

select * from doit_create_table dct ;

/*
 * 여러 줄 주석
 */

-- 한 줄 주석

-- 안전모드 비활성화 (실행하진 않았음)
set SQL_SAFE_UPDATES = 0; 

-- where 조건에 맞는 데이터 삭제
delete from doit_create_table where col_2 = 'col_3값 생략'; 
