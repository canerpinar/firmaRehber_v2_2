/*
DROP TABLE IF EXISTS kategoriler;
DROP TABLE IF EXISTS altkategori;
DROP TABLE IF EXISTS subaltkategori;

DROP TABLE IF EXISTS firma;
DROP TABLE IF EXISTS referanslar;
DROP TABLE IF EXISTS sube;
DROP TABLE IF EXISTS users;
*/
create table if not exists users(
	id integer not null auto_increment,
	username varchar(45) not null unique,
	password varchar(60) not null,
	primary key(id)
);

create table if not exists user_role(
	id integer not null auto_increment,
	role_username varchar(45) not null,
	rolename varchar(45),
	foreign key(role_username) references users(username) ON UPDATE CASCADE,
	primary key(id)
);

create table if not exists kategoriler(
	id integer not null auto_increment,
	kategoriad varchar(45) not null unique,
	seo_avaliable boolean default false,
	primary key(id)
);

create table if not exists altkategori(
	id integer not null auto_increment,
	kategori_id integer not null,
	altkategoriad varchar(45) not null,
	seo_avaliable boolean default false,
	foreign key (kategori_id) references kategoriler(id),
	primary key(id)
);


create table if not exists subaltkategori(
	id integer not null auto_increment,
	kategori_id integer not null,
	altkategori_id integer not null,
	subaltkategoriad varchar(45) not null,
	seo_avaliable boolean default false,
	foreign key (kategori_id) references kategoriler(id),
	foreign key (altkategori_id) references altkategori(id),
	primary key(id)
);


create table if not exists slider(
	id integer not null auto_increment,
	resim_Name varchar(45),
	resim_AltText varchar(45),
	resim_UstText varchar(150),
	resim_Link varchar(45),
	primary key(id)
);

create table if not exists reklam(
	id integer not null auto_increment,
	reklamAd varchar(45),
	reklamImage varchar(45),
	reklamLink varchar(45),
	reklamPosition varchar(45),
	primary key(id)
);

create table if not exists seo(
	id integer not null auto_increment,
	page_name varchar(45),
	primary key(id)
);


create table if not exists seo_Content(
	id integer not null auto_increment,
	seo_id integer not null,
	meta_name varchar(45),
	meta_content text,
	foreign key (seo_id) references seo(id) ON UPDATE CASCADE,
	primary key(id)
);

create table if not exists kategori_Seo(
	id integer not null auto_increment,
	kategori_id integer not null,
	kategori_ad varchar(45) not null,
	meta_name varchar(45),
	meta_content text,
	seo_content text,
	page_title text,
	primary key(id)
);

create table if not exists altkategori_Seo(
	id integer not null auto_increment,
	kategori_id integer not null,
	altkategori_id integer not null,
	kategori_ad varchar(45) not null,
	altkategori_ad varchar(45) not null,
	meta_name varchar(45),
	meta_content text,
	seo_content text,
	page_title text,
	primary key(id)
);

create table if not exists subaltkategori_Seo(
	id integer not null auto_increment,
	kategori_id integer not null,
	altkategori_id integer not null,
	subaltkategori_id integer not null,
	kategori_ad varchar(45) not null,
	altkategori_ad varchar(45) not null,
	subaltkategori_ad varchar(45),
	meta_name varchar(45),
	meta_content text,
	seo_content text,
	page_title text,
	primary key(id)
);



create table if not exists firma(
	id integer not null auto_increment,
	firma_mail varchar(45) not null unique,
	firma_password varchar(60) not null,
	firma_ad varchar(60) not null unique,
	firma_sahip varchar(60) not null,
	firma_image varchar(60) not null,
	firma_control boolean,
	firma_active boolean default false,
	firma_bg_image text,
	primary key(id)
);

create table if not exists  message(
	id integer not null auto_increment,
	mesaj_Kime integer not null,
	mesaj_Kimden varchar(60) not null,
	mesaj_Content text not null,
	primary key(id)
);

create table if not exists referanslar(
	id integer not null auto_increment,
	firma_Id integer not null,
	referans_Adi varchar(60) not null,
	referans_Link varchar(60) not null,
	referans_Image varchar(60),
	foreign key (firma_Id) references firma(id) ON UPDATE CASCADE,
	primary key(id)
);

create table if not exists urunler(
	id integer not null auto_increment,
	urunkategori_id integer not null,
	urunaltkategori_id integer not null,
	urunsubkategori_id integer not null,
	urunad varchar(60) not null,
	urunfiyat float not null,
	urunhakkinda text not null,
	urunsahibi_firma integer not null,
	urunstokdurumu boolean,
	uruncontrol boolean,
	primary key(id)
);

create table if not exists sube(
	id integer not null auto_increment,
	firma_id integer not null,
	email varchar(60) not null,
	ad varchar(90) not null,
	foreign key (firma_id) references firma(id) ON UPDATE CASCADE,
	primary key (id)
);








