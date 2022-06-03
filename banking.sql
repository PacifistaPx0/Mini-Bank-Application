use bank;

/*	create table customer( 
    id int not null auto_increment,
    ac_no varchar(45) unique default null,
	ac_name varchar(45) unique default null,
    balance varchar(45) default null,
    passcode int default null,
    primary key(id)) */
    
alter table customer 
add unique(ac_name);

select * from customer;