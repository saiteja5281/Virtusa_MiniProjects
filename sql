create database LibraryManagement;
use LibraryManagement;
create table books(
bookId int primary key,
bookSubject varchar(20),
bookAuthor varchar(20),
bookAssigned int
);

create table students(
studentId int primary key,
studentName varchar(20),
studentYear int,
branch varchar(20)
);

create table issueBooks(
issueId int primary key auto_increment,
bookId int,
studentId int,
issueDate Date,
returnDate Date
);

INSERT INTO students VALUES
(101,'Sai',1,'CSE'),
(102,'Teja',2,'ECE'),
(103,'Revanth',3,'EEE'),
(104,'Poorna',4,'MECH'),
(105,'Shashank',2,'CSE'),
(106,'Vamsi',3,'ECE'),
(107,'Phani',1,'EEE'),
(108,'Kiran',4,'CIVIL'),
(109,'Shiva',2,'MECH'),
(110,'Saketh',3,'CSE');

INSERT INTO books VALUES
(1,'English','Author1',0),
(2,'Economy','Author2',0),
(3,'Science','Author3',0),
(4,'Maths','Author4',0),
(5,'Physics','Author5',0),
(6,'Chemistry','Author6',0),
(7,'Biology','Author7',0),
(8,'History','Author8',0),
(9,'Geography','Author9',0),
(10,'Computer','Author10',0);

INSERT INTO issueBooks (bookId,studentId,issueDate,returnDate) VALUES
(1,101,'2026-03-10',NULL),
(2,102,'2026-03-25',NULL),
(1,103,'2026-03-01',NULL),
(4,104,'2026-03-20','2026-03-28'),
(5,105,'2026-03-15',NULL),
(2,106,'2026-04-30',NULL),
(2,107,'2026-03-05',NULL),
(8,108,'2026-03-18','2026-03-25'),
(9,109,'2026-03-12',NULL),
(10,110,'2026-03-28',NULL);

insert into issueBooks(bookId,studentId,issueDate,returnDate) values
(2,106,'2026-04-30',NULL),
(2,107,'2026-03-05',NULL);

select s.studentName,s.studentId,s.branch,s.studentYear,b.bookId,i.issueDate
from issueBooks as i
join students as s 
on i.studentId=s.studentId
join books as b on b.bookId=i.bookId
where i.returnDate is null 
and datediff(curdate(),i.issueDate)>14;

select b.bookId,b.bookSubject,count(i.bookId) as no_of_sales
from issuebooks as i
join books as b
on i.bookId=b.bookId
group by b.bookId
order by no_of_sales desc
limit 1;

DELETE FROM students
WHERE studentId NOT IN (
    SELECT DISTINCT studentId
    FROM issueBooks
    WHERE issueDate >= datediff(CURDATE(),3*367)
);

