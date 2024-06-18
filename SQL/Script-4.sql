-- 2024-06-12
-- sakila

-- inner 조인
select * from address a inner join customer c 
	on a.address_id = c.address_id ;
	
select c.last_name , a.phone from customer c , address a 
	where c.address_id = a.address_id and c.last_name like 'ROBERT%';

select c.*, a.address, c2.city from customer c 
	inner join address a on c.address_id = a.address_id 
	inner join city c2 on a.city_id = c2.city_id 
	where c.first_name = 'rosa';


-- left outer 조인
select * from address a left outer join store s 
	on a.address_id = s.address_id  
union -- full outer join	
-- right outer 조인
select * from address a right outer join store s 
	on a.address_id = s.address_id ;
	
-- union 은 사용하면 잡혀가~~~
select * from actor  
union
select * from store s ;


-- 셀프 조인
select * from customer c inner join customer c2 
	on c.customer_id = c2.customer_id ;

-- 셀프 조인문을 적용한 쿼리 2
-- 전일과 오늘의 매출을 비교하여 수익 유무 확인
select a.payment_id, a.amount, b.payment_id, b.amount, b.amount - a.amount as profit_amout 
	from payment a left outer join payment b 
	on a.payment_id = b.payment_id  - 1;
	
select * from payment;

-- sub query 괄호 안에 있는게 서브쿼리다
select * from customer c 
	where customer_id = (select customer_id from customer c2 where c2.first_name = 'rosa');

select * from customer c 
	where customer_id in (select customer_id from customer c2 where c2.first_name like 'r%');
	
select 
	c.first_name, 
	(select phone from address a where a.address_id = c.address_id) as phone
	from customer c 
	where customer_id = (select customer_id from customer c2 where first_name = 'rosa');
	
-- 서브쿼리는 단발성

-- p.203
select
	a.film_id, a.title
from film a
	inner join film_category b on a.film_id = b.film_id 
	inner join category c on b.category_id = c.category_id 
where c.name = 'Action';

-- 돌발퀴즈!
-- 필름 타이틀이 ALONE TRIP 영화의 카테고리와 출연자(actor)을 찾으시오
select * from film a , film_category b where a.film_id = b.film_id and a.title = 'ALONE TRIP'; 

select * from film;
select * from film_category fc ;
select * from category c ;
select * from actor;
select * from film_actor fa ;

select c.name, a.first_name, a.last_name 
from film f, film_category fc , category c , film_actor fa , actor a 
where f.title = 'alone trip'
	and f.film_id = fc.film_id 
	and fc.category_id = c.category_id 
	and f.film_id = fa.film_id 
	and fa.actor_id = a.actor_id ;

select f.title,
	(select c.name from category c where fc.category_id = c.category_id) as category_name,
	concat((select a.last_name from actor a where a.actor_id = fa.actor_id), 
		' ',
		(select a.first_name from actor a where a.actor_id = fa.actor_id)) as actor_name
from film f 
	inner join film_category fc on f.film_id = fc.film_id 
	inner join film_actor fa on f.film_id = fa.film_id 
where f.title = 'alone trip';

-- 임짱 코드
select 
   f.title,  
   (select c.name        from category c where c.category_id = fc.category_id) as category,
   (select a.first_name from actor a     where a.actor_id = fa.actor_id)       as actor_first_name, 
   (select a.last_name  from actor a     where a.actor_id = fa.actor_id)      as actor_last_name
from film f 
   inner join film_actor fa    on f.film_id = fa.film_id 
   inner join film_category fc   on f.film_id = fc.film_id 
where f.title = 'ALONE TRIP';

-- EXISTS 문
select * from customer c 
	where exists 
	(
		select customer_id from customer c2 where first_name in ('rosa', 'ana')
	);
	
-- ALL
select * from customer c 
where customer_id = all 
	(
		select customer_id from customer c2 where first_name in ('ROSA', 'ANA')
	);
select * from customer c 
where customer_id = 112 
	or customer_id = 181;
	
select 
	a.film_id , a.title , a.special_features , x.name
from film as a
	inner join(
	select
		b.film_id, c.name
	from film_category as b
		inner join category as c on b.category_id = c.category_id
	where b.film_id > 10 and b.film_id < 20) as x on a.film_id = x.film_id;


with cte_customer(customer_id, first_name, email)
as
(
	select customer_id, first_name, email from customer c where customer_id >= 10
	and customer_id < 100
)
select * from cte_customer;

select * from actor; -- actor_id 사용
select * from category c ; -- id
select * from film_category fc ;
select * from film;
select * from film_actor fa ;

-- p.219 되새김 문제 Q4.
select c.name as category_name,
	concat((select a.first_name from actor a where fa.actor_id = a.actor_id),
		' ',
		(select a.last_name from actor a where fa.actor_id = a.actor_id)) as actor_name,
	f.title as film_title,
	f.release_year
from category c 
	inner join film_category fc on c.category_id = fc.category_id 
	inner join film f on fc.film_id = f.film_id 
	inner join film_actor fa on f.film_id = fa.film_id 
where c.name = 'Action';


select c.country '나라',c2.city '도시' from country c ,city c2 
   where c.country_id = c2.country_id 
   and c.country like 'United States';

select * from rental;
select * from inventory; -- film_id를 영화랑 연결
select * from category; -- category_id, 장르=name
select * from film; -- flim_id

select 
from film f 
	inner join inventory i on f.film_id = i.film_id ;
	
	