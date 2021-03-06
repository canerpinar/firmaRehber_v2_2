/*
DROP TABLE IF EXISTS kategoriler;
DROP TABLE IF EXISTS altkategori;
DROP TABLE IF EXISTS subaltkategori;

DROP TABLE IF EXISTS firma;
DROP TABLE IF EXISTS referanslar;
DROP TABLE IF EXISTS sube;
DROP TABLE IF EXISTS users;

DROP TABLE IF EXISTS user_role;
DROP TABLE IF EXISTS kampanya;
DROP TABLE IF EXISTS urunler;

drop table if exists message;

drop table if exists seo_content;
drop table if exists seo;
DROP TABLE IF EXISTS sube_kampanya;
DROP TABLE IF EXISTS kampanya;
DROP TABLE IF EXISTS urunler;
drop table if exists seo_content;
drop table if exists seo;

DROP TABLE IF EXISTS sube_kampanya;
DROP TABLE IF EXISTS kampanya;
DROP TABLE IF EXISTS urunler;
drop table if exists seo_content;
drop table if exists seo;
drop table if exists urunler;
drop table if exists sube;
drop table if exists firma;
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
	page_name varchar(255),
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
	firma_control boolean default false,
	firma_active boolean default false,
	firma_bg_image text,
	firma_hakkinda text,
	website text default null,
	haftasonuacik_mi boolean default false,
	primary key(id)
);

create table if not exists  message(
	id integer not null auto_increment,
	mesaj_Kime integer not null,
	mesaj_gonderen_id integer,
	mesaj_sahip_link varchar(255) not null,
	mesaj_Kimden varchar(255) not null,
	mesaj_Content text not null,
	gonderen_uyemi boolean,
	okundu_Status boolean default false,
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



create table if not exists kampanya(
	id integer not null auto_increment,
	urun_id integer not null,
	kampanya_ad varchar(255),
	kampanya_turu smallint not null,
	current_fiyat float,
	kampanya_fiyat float,
	kampanya_oran varchar(5),
	kampanya_urunu int default null,
	kampanya_kontrol boolean default false,
	start_date date,
	end_date date,
	primary key(id)
);


create table if not exists urunler(
	id integer not null auto_increment,
	urunkategori_id integer not null,
	urunaltkategori_id integer not null,
	urunsubkategori_id integer not null,
	urunkategori_ad varchar(90) not null,
	urunaltkategori_ad varchar(90) not null,
	urunsubkategori_ad varchar(90) not null,
	urunad varchar(60) not null,
	urunfiyat float not null,
	urun_odeme_kart boolean,
	urun_odeme_kapida boolean,
	urun_odeme_havale boolean,
	urunhakkinda text not null,
	urunsahibi_firma integer not null,
	urunstokdurumu boolean,
	urun_marka varchar(120),
	urunwhere_is_id int,/* urun hangi şube de ve şube satış noktası mıdır*/
	urun_adres varchar(255),
	uruncontrol boolean,
	urun_pid varchar(10),
	urun_link varchar(255),
	urun_image_path varchar(255),
	urun_seo_status boolean default false,
	urun_kampanya boolean default false,
	urun_kampanya_fiyat float default null,
	urun_kampanya_oran varchar(5) default null,
	kampanya_id int default null,
	foreign key(kampanya_id) references kampanya(id) ON UPDATE CASCADE,
	primary key(id)
);


create table if not exists sube(
	id integer not null auto_increment,
	firma_id integer not null,
	email varchar(60) not null,
	ad varchar(90) not null,
	sube_sorumlu varchar(120),
	sorumlu_cep_tel varchar(20),
	sube_telefon varchar(20),
	sube_adres text,
	sube_durum boolean,
	sube_location varchar(60),
	foreign key (firma_id) references firma(id) ON UPDATE CASCADE,
	primary key (id)
);

create table if not exists sube_kampanya(
id integer not null auto_increment,
kampanya_id integer not null,
sube_id integer not null,
foreign key(kampanya_id) references kampanya(id) on update cascade,
foreign key(sube_id) references sube(id) on update cascade,
primary key(id)
);









