/*
insert into users(username,password) values('caner','$2a$11$VeakvhJD5Bo.ww8iUOEG3uGMsJZcgZsyJhWSaWItSOGUoxQPIsJLe');
insert into user_role(role_username,rolename) values('caner','ROLE_ADMIN');
insert into seo(page_name) values('index');
insert into seo(page_name) values('hakkimizda');
insert into seo_Content(seo_id,meta_name,meta_content) values('1','title','index title');
insert into seo_Content(seo_id,meta_name,meta_content) values('1','propert','index propert');

insert into seo_Content(seo_id,meta_name,meta_content) values('2','hakkimizda title','hakkimizda content');
insert into seo_Content(seo_id,meta_name,meta_content) values('2','propert','hakkimizda propert');


insert into firma(firma_mail,firma_password,firma_ad,firma_sahip,firma_image,firma_control) values('honda@deneme','$2a$11$VeakvhJD5Bo.ww8iUOEG3uGMsJZcgZsyJhWSaWItSOGUoxQPIsJLe','deneme','deneme','deneme','false');
insert into firma(firma_mail,firma_password,firma_ad,firma_sahip,firma_image,firma_control) values('yamaha@deneme','$2a$11$VeakvhJD5Bo.ww8iUOEG3uGMsJZcgZsyJhWSaWItSOGUoxQPIsJLe','deneme_1','deneme','deneme','false');
insert into firma(firma_mail,firma_password,firma_ad,firma_sahip,firma_image,firma_control) values('ktm@deneme','$2a$11$VeakvhJD5Bo.ww8iUOEG3uGMsJZcgZsyJhWSaWItSOGUoxQPIsJLe','deneme_2','deneme','deneme','false');

insert into referanslar(firma_Id,referans_Adi,referans_Link,referans_Image) values('1','Referans 1','www.google.com','google.jpg');
insert into referanslar(firma_Id,referans_Adi,referans_Link,referans_Image) values('1','Referans 1','www.google.com','google.jpg');


insert into urunler(urunkategori_id,urunaltkategori_id,urunsubkategori_id,urunad,urunfiyat,urunhakkinda,urunsahibi_firma,urunstokdurumu,uruncontrol) values('1','1','1','5x10 Kereste','230.50','cevizden yapılmış','1','true','false');
insert into urunler(urunkategori_id,urunaltkategori_id,urunsubkategori_id,urunad,urunfiyat,urunhakkinda,urunsahibi_firma,urunstokdurumu,uruncontrol) values('1','1','1','5x5 Çam Kereste','210.50','ithal ceviz','1','true','false');
insert into urunler(urunkategori_id,urunaltkategori_id,urunsubkategori_id,urunad,urunfiyat,urunhakkinda,urunsahibi_firma,urunstokdurumu,uruncontrol) values('1','1','1','5x5 Çam Kereste','210.50','ithal ceviz','2','true','false');
insert into urunler(urunkategori_id,urunaltkategori_id,urunsubkategori_id,urunad,urunfiyat,urunhakkinda,urunsahibi_firma,urunstokdurumu,uruncontrol) values('1','1','1','5x5 Çam Kereste','210.50','ithal ceviz','2','true','false');

insert into sube(firma_id,email,ad) values('1','honda_sube1@deneme','HONDA ŞİŞLİ');

insert into slider(resim_Name,resim_AltText,resim_UstText,resim_Link) values('114x114.png','Alt Yazı','Ust Yazı','www.google.com');
insert into slider(resim_Name,resim_AltText,resim_UstText,resim_Link) values('bitcoin_icon.jpg','Alt Yazı','Ust Yazı','www.google.com');
insert into slider(resim_Name,resim_AltText,resim_UstText,resim_Link) values('doc_icon.png','Alt Yazı','Ust Yazı','www.google.com');


insert into kategoriler(kategoriAd) values('İlçeler');
insert into altkategori(kategori_id,altkategoriad) values('1','Edremit');
insert into subaltkategori(kategori_id,altkategori_id,subaltkategoriad) values('1','1','Zeytinli');
insert into subaltkategori(kategori_id,altkategori_id,subaltkategoriad) values('1','1','Kadıköy');

insert into kategoriler(kategoriAd) values('İller');
insert into altkategori(kategori_id,altkategoriad) values('2','Balıkesir');
insert into subaltkategori(kategori_id,altkategori_id,subaltkategoriad) values('2','2','Merkez');
insert into subaltkategori(kategori_id,altkategori_id,subaltkategoriad) values('2','2','Hüsnü Ala');



insert into altkategori(kategori_id,altkategoriad) values('2','İzmir');
insert into subaltkategori(kategori_id,altkategori_id,subaltkategoriad) values('2','3','Alsancak');
insert into subaltkategori(kategori_id,altkategori_id,subaltkategoriad) values('2','3','Gaziemir');


insert into kategoriler(kategoriAd) values('Ülkeler');
insert into altkategori(kategori_id,altkategoriad) values('3','Amerika');
insert into subaltkategori(kategori_id,altkategori_id,subaltkategoriad) values('3','4','Kuzey');
insert into subaltkategori(kategori_id,altkategori_id,subaltkategoriad) values('3','4','Güney');






/*
insert into seo(page_name,meta_name,meta_content) values('index','description','problem is in the everywhere');
insert into seo(page_name,meta_name,meta_content) values('index','description','problem is in the everywhere');
insert into seo(page_name,meta_name,meta_content) values('kategori','description','problem is in the everywhere');
insert into seo(page_name,meta_name,meta_content) values('kategori','card','problem is in the everywhere');
insert into seo(page_name,meta_name,meta_content) values('link','card-war','problem is this');
insert into seo(page_name,meta_name,meta_content) values('link','card','simple content');
insert into seo(page_name,meta_name,meta_content) values('link','link','problem is in the everywhere');
*/