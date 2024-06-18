-- 2024-06-10

select * from car;

select * from customer;

select * from sale_invoice si ;
-- 조인 문장

select c2.Last_Name , c2.First_Name from sale_invoice si , car c , customer c2 
where si.Car_ID = c.Car_ID 
and si.Customer_ID = c2.Customer_ID ;

select * from city;
select * from country where name like '%Korea' or name = 'Aus%';

select * from country 
where SurfaceArea >= 100000 and Continent = 'asia' 
order by Code asc;

select sum(c2.Population) from country c , city c2
where c.Code = c2.CountryCode and c.Name like '%korea' 
and c2.District in ('seoul', 'inchon');

select sum(c2.Population) from country c , city c2
where c.Code = c2.CountryCode and c.Name like '%korea' 
and (c2.District = 'seoul' or c2.District = 'inchon');

show columns from city ;

select * from city where name is null ;

select * from customer where First_Name < '햐';

