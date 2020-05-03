delete from cities where cities.name = 'Өзгөрүш';
alter table cities alter column geo_latitude set not null;
alter table cities alter column geo_longitude set not null;