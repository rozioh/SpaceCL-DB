-- 2024-06-14

-- concat 문자열 결합
-- sakila db 조회
select concat_ws(' ===> ', first_name, last_name, email) as customer_name 
from customer c;

SELECT CONCAT('I ', 'LOVE ', 'MySQL' AS col_1;

-- cast, convert 데이터 형변환
select 4/'2', 4/2, 4 / CAST('2' as unsigned); -- CAST: 부호 없는 정수형으로 변경

select now();
-- studydb1 조회
insert into doit2 (col2, col3) values ('2213', now()); -- 데이터를 넣을 때도 쓴다.

select cast(now() as char);
select cast('20230819' as date);

select convert(now(), signed);
select convert(20230819, date);

select cast(9223372036854775807 as unsigned) + 1;

-- ifnull, coalesce
select col2, ifnull(col4, '데이터가 없습니다.') col4, ifnull(col5, '') col5 from doit2;

select * from doit2;
update doit2 set col4 = 1.0 where col1 = 103;
select col2, coalesce(col4, col5, col6, col7) from doit2; -- 

-- lower, upper
select 'Do it! SQL', LOWER('Do it! SQL'), UPPER('Do it! SQL');

-- trim
select trim('   do    it!    ');

-- length
SELECT LENGTH('Do it! MySQL'), LENGTH('두잇 마이에스큐엘');
select length('한'), length ('a');
select char_length('한'), char_length('a한글');

-- position
select position("!" in "do!o it!! Mysql");

-- left, right
select left('Do it! Mysql', length('Do it! Mysql'));

select right('Do it! Mysql', 2);

-- substring
select 'Do it', substring('Do It! MySQL', 4, 2);

select substring('abc@email.com', 1, position('@' in 'abc@email.com') - 1);

-- replace
select first_name, replace (first_name, 'A', 'C')
from customer c 
where first_name like '%A%';

-- repeat
select repeat('0', 10);
-- sakila db 조회
select first_name, replace(First_Name, 'A', repeat('C', 10)) 
from customer c 
where First_Name like '%A%';

-- space
select concat(first_name, space(10), last_name)
from customer c ;

-- reverse
select 'Do it! MySQL', reverse('Do it! MySQL'); 

-- strcmp
select strcmp('Do it! MySQL', 'Do it! MySQL'); 

-- 날짜 함수
select current_date(), current_time(), current_timestamp(), now();

select now(), date_add( now(), interval 1 second );

select NOW(), 

-- datediff, timestampdiff
select datediff(date_add(now(), interval 1 year), now());

select timestampdiff( hour , now(), date_add(now(), interval 1 month) );

-- dayname
select dayname(now()); 

select year(now()), month (now());

-- date_format, get_format
select get_format(date, 'JIS'), get_format(date, 'USA'), get_format(date, 'INTERNAL') ;

-- 집계함수
-- count
select count(*) from customer;
select count(distinct continent) from customer;

-- rollup
select customer_id , staff_id , sum(amount)
from payment p 
group by customer_id , staff_id ;

select customer_id , staff_id , sum(amount)
from payment p 
group by customer_id , staff_id with rollup limit 1797, 1;


-- p.313 뷰 만들기!
create view v_customer
as
	select first_name, last_name, email from customer c ;
	
select * from actor_info ai ;
select * from v_customer;

-- 순위함수 p.270
select row_number() over(order by amount desc) as num, customer_id, amount
from (select customer_id, sum(amount) as amount
		from payment group by customer_id) as x;

	
select staff_id, row_number() over(partition by staff_id order by amount desc, customer_id asc) as num, customer_id, amount
from (
	select customer_id, staff_id, sum(amount) as amount
	from payment group by customer_id, staff_id) as x;
	

-- ntile
select ntile(100) over(order by amount desc) as num, customer_id, amount
from (
	select customer_id, sum(amount) as amount 
	from payment p group by customer_id
) as x;

-- percent_rank
select x.customer_id, x.amount, percent_rank() over (order by x.amount desc)
from (
	select customer_id, sum(amount) as amount
	from payment p group by customer_id
) as x
order by x.amount desc;

-- p.258 abs

-- ceiling & round
select ceiling(2.01), round(99.9994, 3);

-- power
select power(2, 3);

-- rand
select rand(100), abs(floor(rand() * 100));
 