use hospital;
create table patients(
    patient_id int primary key,
    patient_name varchar(30) not null,
    patient_age int check (patient_age > 0),
    patient_gender varchar(10) not NULL
);

create table doctors(
    doctor_id int primary key,
    doctor_name varchar(30) not null,
    doctor_specialty varchar(30) not null
);

create table doctor_appointments(
    appointment_id int primary key,
    patient_id int,
    doctor_id int,
    appointment_date date,
    consulting_cost DECIMAL(8,2),
    FOREIGN KEY (patient_id) REFERENCES patients(patient_id),
    FOREIGN KEY (doctor_id) REFERENCES doctors(doctor_id)
);

create table  treatments(
    treatment_id int primary key,
    patient_id int,
    diagnosis varchar(100),
    cost decimal(10, 2),
    FOREIGN KEY (patient_id) REFERENCES patients(patient_id)
);



select * from doctor_appointments;

ALTER TABLE treatments
ADD disease VARCHAR(100);

UPDATE treatments SET disease = 'Migraine' WHERE treatment_id = 2007;
UPDATE treatments SET disease = 'Fever' WHERE treatment_id = 2001;
UPDATE treatments SET disease = 'Hypertension' WHERE treatment_id = 2013;
UPDATE treatments SET disease = 'Dermatitis' WHERE treatment_id = 2004;
UPDATE treatments SET disease = 'Hormonal Disorder' WHERE treatment_id = 2010;
UPDATE treatments SET disease = 'Pregnancy' WHERE treatment_id = 2002;
UPDATE treatments SET disease = 'Cardiac Issue' WHERE treatment_id = 2016;
UPDATE treatments SET disease = 'Acne' WHERE treatment_id = 2009;
UPDATE treatments SET disease = 'Post-Fever Weakness' WHERE treatment_id = 2018;
UPDATE treatments SET disease = 'Arthritis' WHERE treatment_id = 2005;
UPDATE treatments SET disease = 'Chronic Cough' WHERE treatment_id = 2012;
UPDATE treatments SET disease = 'Hypertension' WHERE treatment_id = 2015;
UPDATE treatments SET disease = 'Common Cold' WHERE treatment_id = 2006;
UPDATE treatments SET disease = 'Pregnancy Follow-up' WHERE treatment_id = 2019;
UPDATE treatments SET disease = 'Skin Allergy' WHERE treatment_id = 2011;
UPDATE treatments SET disease = 'Joint Pain' WHERE treatment_id = 2021;
UPDATE treatments SET disease = 'Migraine' WHERE treatment_id = 2023;
UPDATE treatments SET disease = 'Recovery Phase' WHERE treatment_id = 2025;
UPDATE treatments SET disease = 'Skin Condition' WHERE treatment_id = 2020;
UPDATE treatments SET disease = 'Hormonal Imbalance' WHERE treatment_id = 2022;

select * from treatments;

