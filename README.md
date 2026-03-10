# Soliner Digital Card - Full Stack Web Application

Bu proje, Soliner yaz stajı mülakat süreci kapsamında Full Stack Developer yeteneklerini sergilemek amacıyla geliştirilmiş dinamik bir dijital kartvizit uygulamasıdır.

Uygulama, kullanıcıların kişisel bilgilerini, yeteneklerini, projelerini ve sosyal medya bağlantılarını dinamik olarak yönetebildiği bir "Admin Paneli" ve bu bilgilerin sergilendiği, doğrudan iletişim kurulabilen bir "Müşteri Vitrini" (Ana Sayfa) içerir.

## 🚀 Kullanılan Teknolojiler

**Frontend (Kullanıcı Arayüzü):**
* Angular 17+
* TypeScript, HTML5, CSS3
* Karanlık Mod (Dark Theme) Desteği
* Tamamen Responsive (Mobil Uyumlu) Tasarım

**Backend (Sunucu ve İş Mantığı):**
* Java Spring Boot 3+
* Spring Web, Spring Data JPA
* RESTful API Mimarisi
* CORS Yapılandırması

**Veritabanı:**
* PostgreSQL (İlişkisel Veritabanı Yönetimi)

## ✨ Öne Çıkan Özellikler
* **Dinamik İçerik Yönetimi:** Admin paneli üzerinden biyografi, projeler, yetenekler ve sosyal linkler anlık olarak güncellenebilir.
* **Gelişmiş İletişim Formu:** Ana sayfadaki form üzerinden atılan mesajlar doğrudan PostgreSQL veritabanına kaydedilir.
* **Tema Seçeneği (Bonus):** Kullanıcılar tek tıkla Aydınlık/Karanlık mod arasında geçiş yapabilir. Seçimler tarayıcı hafızasında (Local Storage) tutulur.
* **Fiziksel Kart Hissi (Bonus):** Profil resmi ve kart tasarımları, modern UI/UX prensipleriyle (gölgelendirmeler, hover efektleri) gerçeğe en yakın dijital deneyimi sunar.

## ⚙️ Projeyi Lokal Olarak Çalıştırma

### 1. Veritabanı Ayarları (PostgreSQL)
PostgreSQL'de `digitalcarddb` adında boş bir veritabanı oluşturun.
* Backend içindeki `src/main/resources/application.yml` dosyasını açarak kendi veritabanı kullanıcı adı ve şifrenizi girin.

### 2. Backend'i Başlatma (Spring Boot)
Projeyi favori IDE'nizde (IntelliJ, Eclipse vs.) açın ve `DigitalcardApplication.java` dosyasını çalıştırın. (Backend `http://localhost:8080` portunda ayağa kalkacaktır).

### 3. Frontend'i Başlatma (Angular)
Terminalde `digitalcard-frontend` klasörünün içine girin ve şu komutları çalıştırın:
```bash
npm install
ng serve
