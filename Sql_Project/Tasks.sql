use  hospital;

--1)Find most consulted doctors

select doctor_name,doctor_specialty,count(*) as total_appointments
from doctors d
join doctor_appointments da on d.doctor_id = da.doctor_id
group by doctor_name,doctor_specialty
order by total_appointments desc;

--2)Calculate total revenue generated per month
select date_format(appointment_date, '%Y-%m') as month, sum(consulting_cost) as total_revenue
from doctor_appointments
group by month
order by month;

--3)Identify most common disesases
select disease, count(*) as total_cases
from treatments
group by disease
order by total_cases desc;

--4)Find patients with multiple appointments
select patient_name, count(*) as total_appointments
from patients p
join doctor_appointments da on p.patient_id = da.patient_id
group by patient_name
having total_appointments > 1;

--5)Analyse doctor performance based on patient feedback (assuming we have a feedback table)
